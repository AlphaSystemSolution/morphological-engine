package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.ConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.util.AppUtil;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
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
        addParticipleConjugations(detailedConjugation.getActiveParticiplePair());
        addTenseConjugations(detailedConjugation.getPassiveTensePair());
        addParticipleConjugations(detailedConjugation.getPassiveParticiplePair());
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private void addTenseConjugations(DetailedConjugationPair<VerbConjugationGroup> tensePair) {
        if (tensePair == null) {
            return;
        }
        final VerbConjugationGroup lsc = tensePair.getLeftSideConjugations();
        final VerbConjugationGroup rsc = tensePair.getRightSideConjugations();

        lines.add(getSarfTermTypeHeader(lsc, rsc, 6));
        lines.add(getRowData(lsc.getMasculineThirdPerson(), rsc.getMasculineThirdPerson()));
        lines.add(getRowData(lsc.getFeminineThirdPerson(), rsc.getFeminineThirdPerson()));
        lines.add(getRowData(lsc.getMasculineSecondPerson(), rsc.getMasculineSecondPerson()));
        lines.add(getRowData(lsc.getFeminineSecondPerson(), rsc.getFeminineSecondPerson()));
        lines.add(getRowData(lsc.getFirstPerson(), rsc.getFirstPerson()));
        lines.add(EMPTY_ROW);
        lines.add(AppUtil.NEW_LINE);
    }

    private void addParticipleConjugations(DetailedConjugationPair<NounConjugationGroup> participlePair) {
        if (participlePair == null) {
            return;
        }
        final NounConjugationGroup lsc = participlePair.getLeftSideConjugations();
        final NounConjugationGroup rsc = participlePair.getRightSideConjugations();

        lines.add(getSarfTermTypeHeader(lsc, rsc, 4));
        lines.add(getRowData(lsc.getNominative(), rsc.getNominative()));
        lines.add(getRowData(lsc.getAccusative(), rsc.getAccusative()));
        lines.add(getRowData(lsc.getGenitive(), rsc.getGenitive()));
        lines.add(EMPTY_ROW);
        lines.add(AppUtil.NEW_LINE);
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
