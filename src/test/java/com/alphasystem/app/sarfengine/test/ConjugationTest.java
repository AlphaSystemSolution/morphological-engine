package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.ConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.util.AppUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.BrokenPlural.BROKEN_PLURAL_V12;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_V1;
import static java.lang.String.format;

/**
 * @author sali
 */
public class ConjugationTest extends CommonTest {

    private static final String EMPTY_ROW = "7+| ";
    private static final String SARF_TERM_PATTERN = "3+|%s .%s+| 3+|%s %s";

    @Test
    public void runConjugationBuilder() {
        ConjugationBuilder conjugationBuilder = new ConjugationBuilder();
        conjugationBuilder.applyTemplate(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE);
        conjugationBuilder.setVerbalNouns(new NounRootBase[]{new NounRootBase(VERBAL_NOUN_V1)});
        conjugationBuilder.setAdverbs(new NounRootBase[]{new NounRootBase(NOUN_OF_PLACE_AND_TIME_V1, BROKEN_PLURAL_V12),
                new NounRootBase(NOUN_OF_PLACE_AND_TIME_V2, BROKEN_PLURAL_V12),
                new NounRootBase(NOUN_OF_PLACE_AND_TIME_V3)});
        printMorphologicalChart(conjugationBuilder.doConjugation(NOON, SAD, RA, null));
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
        final DetailedConjugation detailedConjugation = chart.getDetailedConjugation();

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

        lines.add(EMPTY_ROW);
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

        lines.add(EMPTY_ROW);
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
