package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.AdverbLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.util.AppUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.BrokenPlural.BROKEN_PLURAL_V12;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_V1;
import static java.lang.String.format;

/**
 * @author sali
 */
public class ConjugationTest extends CommonTest {

    private static final String SARF_TERM_PATTERN = "3+|%s .%s+| 3+|%s %s";
    public static final ArabicWord COMMAND_PREFIX = getWord(ALIF, LAM, ALIF_HAMZA_ABOVE, MEEM, RA, ArabicLetterType.SPACE,
            MEEM, NOON, HA);
    private static final ArabicWord FORBIDDING_PREFIX = getWord(WAW, NOON, HA, YA, ArabicLetterType.SPACE, AIN, NOON, HA);
    private static final ArabicWord ADVERB_PREFIX = getWord(WAW, ALIF, LAM, DTHA, RA, FA, ArabicLetterType.SPACE, MEEM,
            NOON, HA);

    @Test
    public void runConjugationBuilder() {
        ConjugationBuilder conjugationBuilder = new ConjugationBuilder();
        conjugationBuilder.applyTemplate(NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE);
        conjugationBuilder.setVerbalNouns(new NounRootBase[]{new NounRootBase(VERBAL_NOUN_V1)});
        conjugationBuilder.setAdverbs(new NounRootBase[]{new NounRootBase(NOUN_OF_PLACE_AND_TIME_V1, BROKEN_PLURAL_V12),
                new NounRootBase(NOUN_OF_PLACE_AND_TIME_V2, BROKEN_PLURAL_V12),
                new NounRootBase(NOUN_OF_PLACE_AND_TIME_V3)});
        printMorphologicalChart(conjugationBuilder.doConjugation(NOON, SAD, RA, null));

        conjugationBuilder = new ConjugationBuilder();
        conjugationBuilder.applyTemplate(Form.FORM_IV_TEMPLATE);
        printMorphologicalChart(conjugationBuilder.doConjugation(SEEN, LAM, MEEM, null));
        /*ConjugationBuilderFactory cbf = GuiceSupport.getInstance().getConjugationBuilderFactory();
        ConjugationBuilder cb = cbf.getConjugationBuilder();
        MorphologicalChart morphologicalChart = cb.doConjugation(FORM_IV_TEMPLATE, "To send down", false, false, NOON, ZAIN, LAM,
                asList(VERBAL_NOUN_FORM_IV), asList(NOUN_OF_PLACE_AND_TIME_FORM_IV));
        printConjugation(morphologicalChart);

        morphologicalChart = cb.doConjugation(FORM_IX_TEMPLATE, "To collapse", true, false, NOON, QAF, DDAD,
                asList(VERBAL_NOUN_FORM_IX), null);
        printConjugation(morphologicalChart);*/
    }

    private void printMorphologicalChart(MorphologicalChart chart) {
        createAbbreviatedConjugationChart(chart.getAbbreviatedConjugation());
        createDetailedConjugationChart(chart.getDetailedConjugation());
    }

    private void createAbbreviatedConjugationChart(AbbreviatedConjugation abbreviatedConjugation) {
        lines.add("[cols=\"^.^25,^.^25,^.^25,^.^25\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        addActiveLine(abbreviatedConjugation.getActiveLine());
        addPassiveLine(abbreviatedConjugation.getPassiveLine());
        addImperativeAndForbiddingLine(abbreviatedConjugation.getImperativeAndForbiddingLine());
        addAdverbLine(abbreviatedConjugation.getAdverbLine());
        lines.add(getEmptyRow(4));
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private void addActiveLine(ActiveLine activeLine) {
        lines.add(getRootWord(activeLine.getActiveParticipleMasculine()));
        lines.add(getRootWord(activeLine.getVerbalNouns()));
        lines.add(getRootWord(activeLine.getPresentTense()));
        lines.add(getRootWord(activeLine.getPastTense()));
    }

    private void addPassiveLine(PassiveLine passiveLine) {
        lines.add(getRootWord(passiveLine.getPassiveParticipleMasculine()));
        lines.add(getRootWord(passiveLine.getVerbalNouns()));
        lines.add(getRootWord(passiveLine.getPresentPassiveTense()));
        lines.add(getRootWord(passiveLine.getPastPassiveTense()));
    }

    private void addImperativeAndForbiddingLine(ImperativeAndForbiddingLine imperativeAndForbiddingLine) {
        lines.add(getRootWord(2, FORBIDDING_PREFIX, imperativeAndForbiddingLine.getForbidding()));
        lines.add(getRootWord(2, COMMAND_PREFIX, imperativeAndForbiddingLine.getImperative()));
    }

    private void addAdverbLine(AdverbLine adverbLine) {
        lines.add(getRootWord(4, ADVERB_PREFIX, adverbLine.getAdverbs()));
    }

    private void createDetailedConjugationChart(DetailedConjugation detailedConjugation) {
        lines.add("[cols=\"^.^17,^.^16,^.^16,^.^2,^.^17,^.^16,^.^16\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        addTenseConjugations(detailedConjugation.getActiveTensePair());
        addPairConjugations(detailedConjugation.getVerbalNounPairs());
        addParticipleConjugations(detailedConjugation.getActiveParticiplePair());
        addTenseConjugations(detailedConjugation.getPassiveTensePair());
        addParticipleConjugations(detailedConjugation.getPassiveParticiplePair());
        addPairConjugations(detailedConjugation.getAdverbPairs());
        addTenseConjugations(detailedConjugation.getImperativeAndForbiddingPair(), 3);
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private void addTenseConjugations(VerbDetailedConjugationPair tensePair, int numOfColumns) {
        if (tensePair == null) {
            return;
        }
        final VerbConjugationGroup lsc = tensePair.getLeftSideConjugations();
        final VerbConjugationGroup rsc = tensePair.getRightSideConjugations();

        lines.add(getSarfTermTypeHeader(lsc, rsc, numOfColumns));

        ConjugationTuple leftTuple = lsc.getMasculineThirdPerson();
        ConjugationTuple rightTuple = rsc.getMasculineThirdPerson();
        if (leftTuple != null && rightTuple != null) {
            lines.add(getRowData(leftTuple, rightTuple));
        }

        leftTuple = lsc.getFeminineThirdPerson();
        rightTuple = rsc.getFeminineThirdPerson();
        if (leftTuple != null && rightTuple != null) {
            lines.add(getRowData(leftTuple, rightTuple));
        }

        leftTuple = lsc.getMasculineSecondPerson();
        rightTuple = rsc.getMasculineSecondPerson();
        if (leftTuple != null && rightTuple != null) {
            lines.add(getRowData(leftTuple, rightTuple));
        }

        leftTuple = lsc.getFeminineSecondPerson();
        rightTuple = rsc.getFeminineSecondPerson();
        if (leftTuple != null && rightTuple != null) {
            lines.add(getRowData(leftTuple, rightTuple));
        }

        leftTuple = lsc.getFirstPerson();
        rightTuple = rsc.getFirstPerson();
        if (leftTuple != null && rightTuple != null) {
            lines.add(getRowData(leftTuple, rightTuple));
        }

        lines.add(getEmptyRow(7));
        lines.add(AppUtil.NEW_LINE);
    }

    private void addTenseConjugations(VerbDetailedConjugationPair tensePair) {
        addTenseConjugations(tensePair, 6);
    }

    private void addParticipleConjugations(NounDetailedConjugationPair participlePair) {
        if (participlePair == null) {
            return;
        }
        final NounConjugationGroup lsc = participlePair.getLeftSideConjugations();
        final NounConjugationGroup rsc = participlePair.getRightSideConjugations();

        lines.add(getSarfTermTypeHeader(lsc, rsc, 4));

        ConjugationTuple leftSide = (lsc == null) ? null : lsc.getNominative();
        ConjugationTuple rightSide = (rsc == null) ? null : rsc.getNominative();
        lines.add(getRowData(leftSide, rightSide));

        leftSide = (lsc == null) ? null : lsc.getAccusative();
        rightSide = (rsc == null) ? null : rsc.getAccusative();
        lines.add(getRowData(leftSide, rightSide));

        leftSide = (lsc == null) ? null : lsc.getGenitive();
        rightSide = (rsc == null) ? null : rsc.getGenitive();
        lines.add(getRowData(leftSide, rightSide));

        lines.add(getEmptyRow(7));
        lines.add(AppUtil.NEW_LINE);
    }

    private void addPairConjugations(NounDetailedConjugationPair[] pairs) {
        if (ArrayUtils.isEmpty(pairs)) {
            return;
        }
        for (NounDetailedConjugationPair pair : pairs) {
            addParticipleConjugations(pair);
        }
    }

    private String getSarfTermTypeHeader(ConjugationGroup lsc, ConjugationGroup rsc, int numOfRows) {
        SarfTermType leftSideTerm = (lsc == null) ? null : lsc.getTermType();
        SarfTermType rightSideTerm = (rsc == null) ? null : rsc.getTermType();
        return getSarfTermTypeHeader(leftSideTerm, rightSideTerm, numOfRows, SARF_TERM_PATTERN);
    }

    private String getRowData(final ConjugationTuple leftSide, final ConjugationTuple rightSide) {
        return format("%s%s", getColumnData(leftSide), getColumnData(rightSide));
    }

    private String getColumnData(final ConjugationTuple conjugationTuple) {
        StringBuilder builder = new StringBuilder();
        if (conjugationTuple == null) {
            builder.append("|&nbsp; |&nbsp; |&nbsp; ");
        } else {
            builder.append(getRootWord(conjugationTuple.getPlural())).append(AppUtil.NEW_LINE)
                    .append(getRootWord(conjugationTuple.getDual())).append(AppUtil.NEW_LINE)
                    .append(getRootWord(conjugationTuple.getSingular())).append(AppUtil.NEW_LINE);
        }
        return builder.toString();
    }

}
