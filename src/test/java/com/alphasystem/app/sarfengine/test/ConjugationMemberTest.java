package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.member.impl.MemberBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.model.Form;
import com.alphasystem.app.sarfengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.sarfengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.NounRootBase;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.testng.annotations.Test;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;
import static java.lang.String.format;
import static org.apache.commons.lang3.ArrayUtils.*;

/**
 * @author sali
 */
public class ConjugationMemberTest extends CommonTest {

    private MemberBuilderFactory factory = GuiceSupport.getInstance().getMemberBuilderFactory();
    private RuleProcessorFactory ruleProcessorFactory = GuiceSupport.getInstance().getRuleProcessorFactory();

    @Test
    public void runConjugations() {
        runConjugations(Form.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, new RootLetters(NOON, SAD, RA));
        runConjugations(Form.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE, new RootLetters(DDAD, RA, BA));
        runConjugations(Form.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE, new RootLetters(FA, TA, HHA));
        runConjugations(Form.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE, new RootLetters(SEEN, MEEM, AIN));
        runConjugations(Form.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE, new RootLetters(HHA, SEEN, BA));
        runConjugations(Form.FORM_I_CATEGORY_U_TEMPLATE, new RootLetters(KAF, RA, MEEM));
        runConjugations(Form.FORM_II_TEMPLATE, new RootLetters(AIN, LAM, MEEM));
        runConjugations(Form.FORM_III_TEMPLATE, new RootLetters(JEEM, HA, DAL));
        runConjugations(Form.FORM_IV_TEMPLATE, new RootLetters(SEEN, LAM, MEEM));
        runConjugations(Form.FORM_V_TEMPLATE, new RootLetters(AIN, LAM, MEEM));
        runConjugations(Form.FORM_VI_TEMPLATE, new RootLetters(SEEN, HAMZA, LAM));
        runConjugations(Form.FORM_VII_TEMPLATE, new RootLetters(KAF, SEEN, RA));
        runConjugations(Form.FORM_VIII_TEMPLATE, new RootLetters(QAF, RA, BA));
        runConjugations(Form.FORM_X_TEMPLATE, new RootLetters(GHAIN, FA, RA));
    }

    private void runConjugations(final Form form, final RootLetters rootLetters) {
        final NamedTemplate template = form.getTemplate();
        lines.add(format("== %s &mdash; [arabicNormal]#%s#", template.getCode(), template.getLabel().toHtmlCode()));
        RuleProcessor ruleProcessor = ruleProcessorFactory.getRuleEngine(new RuleInfo(template));
        runActiveParticleConjugation(form, rootLetters, ruleProcessor);
        runPassiveParticleConjugation(form, rootLetters, ruleProcessor);
        runAdverbConjugation(form, rootLetters, ruleProcessor);
    }

    private void runActiveParticleConjugation(final Form form, final RootLetters rootLetters, final RuleProcessor ruleProcessor) {
        ParticipleMemberBuilder masculineBuilder = factory.getActiveParticipleMasculineBuilder(ruleProcessor, form, rootLetters);
        ParticipleMemberBuilder feminineBuilder = factory.getActiveParticipleFeminineBuilder(ruleProcessor, form, rootLetters);
        addTable(feminineBuilder.getTermType(), masculineBuilder.getTermType(), createRootWords(feminineBuilder.doConjugation(),
                masculineBuilder.doConjugation()));
    }

    private void runPassiveParticleConjugation(final Form form, final RootLetters rootLetters, final RuleProcessor ruleProcessor) {
        try {
            ParticipleMemberBuilder masculineBuilder = factory.getPassiveParticipleMasculineBuilder(ruleProcessor, form, rootLetters);
            ParticipleMemberBuilder feminineBuilder = factory.getPassiveParticipleFeminineBuilder(ruleProcessor, form, rootLetters);
            addTable(feminineBuilder.getTermType(), masculineBuilder.getTermType(), createRootWords(feminineBuilder.doConjugation(),
                    masculineBuilder.doConjugation()));
        } catch (Exception e) {
            // ignore
        }
    }

    private void runAdverbConjugation(final Form form, final RootLetters rootLetters, final RuleProcessor ruleProcessor) {
        NounRootBase[] adverbs = form.getAdverbs();
        if (isEmpty(adverbs)) {
            return;
        }
        int length = adverbs.length;
        while (length % 2 != 0) {
            adverbs = add(adverbs, null);
            length = adverbs.length;
        }
        int fromIndex = 0;
        int toIndex = 2;
        while (fromIndex < length) {
            final NounRootBase[] subArray = subarray(adverbs, fromIndex, toIndex);
            NounRootBase rightSide = subArray[0];
            NounRootBase leftSide = subArray[1];
            ParticipleMemberBuilder builder = factory.getAdverbBuilder(ruleProcessor, rightSide, rootLetters);
            final SarfTermType rightSideTermType = builder.getTermType();
            final NounConjugationGroup rightSideConjugationGroup = builder.doConjugation();
            NounConjugationGroup leftSideConjugationGroup = null;
            SarfTermType leftSideTermType = null;
            if (leftSide != null) {
                builder = factory.getAdverbBuilder(ruleProcessor, leftSide, rootLetters);
                leftSideTermType = builder.getTermType();
                leftSideConjugationGroup = builder.doConjugation();
            }
            addTable(leftSideTermType, rightSideTermType, createRootWords(leftSideConjugationGroup, rightSideConjugationGroup));
            fromIndex = toIndex;
            toIndex += 2;
        }
    }

    private void addTable(SarfTermType leftTerm, SarfTermType rightTerm, RootWord... rootWords) {
        lines.add("[cols=\"^.^14,^.^14,^.^14,^.^1,^.^14,^.^14,^.^14,^.^15\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        lines.add(getSarfTermTypeHeader(leftTerm, rightTerm));
        lines.add(addNumberHeader(leftTerm == null));
        addRow(lines, NOMINATIVE_SINGULAR, rootWords, 0);
        addRow(lines, ACCUSATIVE_SINGULAR, rootWords, 6);
        addRow(lines, GENITIVE_SINGULAR, rootWords, 12);
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private RootWord[] createRootWords(NounConjugationGroup leftSideGroup, NounConjugationGroup rightSideGroup) {
        RootWord[] rootWords = new RootWord[18];

        if (leftSideGroup == null) {
            addRootWords(rootWords, null, 0);
            addRootWords(rootWords, null, 1);
            addRootWords(rootWords, null, 2);
        } else {
            addRootWords(rootWords, leftSideGroup.getPlural(), 0);
            addRootWords(rootWords, leftSideGroup.getDual(), 1);
            addRootWords(rootWords, leftSideGroup.getSingular(), 2);
        }
        addRootWords(rootWords, rightSideGroup.getPlural(), 3);
        addRootWords(rootWords, rightSideGroup.getDual(), 4);
        addRootWords(rootWords, rightSideGroup.getSingular(), 5);

        return rootWords;
    }

}
