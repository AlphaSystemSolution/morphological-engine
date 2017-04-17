package com.alphasystem.app.morphologicalengine.conjugation.test;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationRoots;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounOfPlaceAndTimeFactory;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineConfiguration;
import com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.ConjugationGroup;
import com.alphasystem.morphologicalengine.model.ConjugationTuple;
import com.alphasystem.morphologicalengine.model.DetailedConjugation;
import com.alphasystem.morphologicalengine.model.MorphologicalChart;
import com.alphasystem.morphologicalengine.model.NounConjugationGroup;
import com.alphasystem.morphologicalengine.model.NounDetailedConjugationPair;
import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;
import com.alphasystem.morphologicalengine.model.VerbDetailedConjugationPair;
import com.alphasystem.util.AppUtil;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationHelper.getConjugationRoots;
import static com.alphasystem.app.morphologicalengine.conjugation.model.VerbalNounFactory.getByVerbalNoun;
import static com.alphasystem.arabic.model.ArabicLetterType.DDAD;
import static com.alphasystem.arabic.model.ArabicLetterType.HAMZA;
import static com.alphasystem.arabic.model.ArabicLetterType.KAF;
import static com.alphasystem.arabic.model.ArabicLetterType.LAM;
import static com.alphasystem.arabic.model.ArabicLetterType.MEEM;
import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.ArabicLetterType.QAF;
import static com.alphasystem.arabic.model.ArabicLetterType.RA;
import static com.alphasystem.arabic.model.ArabicLetterType.SAD;
import static com.alphasystem.arabic.model.ArabicLetterType.SEEN;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.ZAIN;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IX_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_V1;
import static java.lang.String.format;

/**
 * @author sali
 */
@ContextConfiguration(classes = {MorphologicalEngineConfiguration.class})
public class ConjugationTest extends ConjugationCommon {

    private static final String SARF_TERM_PATTERN = "3+|%s .%s+| 3+|%s %s";
    private static final NounRootBase[] FORM_I_ADVERBS = new NounRootBase[]{NounOfPlaceAndTimeFactory.NOUN_OF_PLACE_AND_TIME_V1,
            NounOfPlaceAndTimeFactory.NOUN_OF_PLACE_AND_TIME_V2, NounOfPlaceAndTimeFactory.NOUN_OF_PLACE_AND_TIME_V3};

    private ConjugationBuilder conjugationBuilder;

    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
        conjugationBuilder = MorphologicalEngineFactory.getConjugationBuilder();
        Assert.assertNotNull(conjugationBuilder);
    }

    @Test(dataProvider = "data")
    public void runConjugationBuilder(NamedTemplate namedTemplate, String translation, NounRootBase[] verbalNouns,
                                      NounRootBase[] nounOfPlaceAndTime, RootLetters rootLetters) {
        final ConjugationRoots conjugationRoots = getConjugationRoots(namedTemplate, rootLetters, translation, verbalNouns,
                nounOfPlaceAndTime);
        printMorphologicalChart(conjugationBuilder.doConjugation(conjugationRoots, OutputFormat.HTML));
    }

    @DataProvider(name = "data")
    private Object[][] conjugationData() {
        return new Object[][]{
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Help", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        FORM_I_ADVERBS, new RootLetters(NOON, SAD, RA)},
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Say", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        FORM_I_ADVERBS, new RootLetters(QAF, WAW, LAM)},
                {FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Eat", new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_V1)},
                        FORM_I_ADVERBS, new RootLetters(HAMZA, KAF, LAM)},
                {FORM_IV_TEMPLATE, "To submit", null, null, new RootLetters(SEEN, LAM, MEEM)},
                {FORM_IV_TEMPLATE, "To send down", null, null, new RootLetters(NOON, ZAIN, LAM)},
                {FORM_IV_TEMPLATE, "To Establish", null, null, new RootLetters(QAF, WAW, MEEM)},
                {FORM_IX_TEMPLATE, "To collapse", null, null, new RootLetters(NOON, QAF, DDAD)}
        };
    }

    private void printMorphologicalChart(MorphologicalChart chart) {
        printAbbreviatedConjugation(chart.getAbbreviatedConjugation());
        createDetailedConjugationChart(chart.getDetailedConjugation());
    }

    private void createDetailedConjugationChart(DetailedConjugation detailedConjugation) {
        lines.add("[cols=\"^.^17,^.^16,^.^16,^.^2,^.^17,^.^16,^.^16\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        addTenseConjugations(detailedConjugation.getActiveTensePair());
        addPairConjugations(detailedConjugation.getVerbalNounPairs());
        addParticipleConjugations(detailedConjugation.getActiveParticiplePair());
        addTenseConjugations(detailedConjugation.getPassiveTensePair());
        addParticipleConjugations(detailedConjugation.getPassiveParticiplePair());
        addTenseConjugations(detailedConjugation.getImperativeAndForbiddingPair(), 3);
        addPairConjugations(detailedConjugation.getAdverbPairs());
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
            builder.append(getColumn(conjugationTuple.getPlural())).append(AppUtil.NEW_LINE)
                    .append(getColumn(conjugationTuple.getDual())).append(AppUtil.NEW_LINE)
                    .append(getColumn(conjugationTuple.getSingular())).append(AppUtil.NEW_LINE);
        }
        return builder.toString();
    }

}
