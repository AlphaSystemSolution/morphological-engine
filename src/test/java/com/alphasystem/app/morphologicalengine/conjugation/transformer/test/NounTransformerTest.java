package com.alphasystem.app.morphologicalengine.conjugation.transformer.test;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.conjugation.test.CommonTest;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerConfiguration;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Noun;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author sali
 */
@ContextConfiguration(classes = {NounTransformerConfiguration.class})
public class NounTransformerTest extends CommonTest {

    private static final RuleProcessorFactory RULE_PROCESSOR_FACTORY = GuiceSupport.getInstance().getRuleProcessorFactory();

    @Autowired
    @NounTransformerType(NounTransformerType.Type.MASCULINE_ENDING_SOUND_TRANSFORMER)
    private NounTransformer masculineEndingSoundTransformer;

    @Autowired
    @NounTransformerType(NounTransformerType.Type.MASCULINE_DUAL_TRANSFORMER)
    private NounTransformer masculineDualTransformer;

    @Autowired
    @NounTransformerType(NounTransformerType.Type.MASCULINE_PLURAL_TRANSFORMER)
    private NounTransformer masculinePluralTransformer;

    @Test(dataProvider = "data")
    public void test(NounTransformer transformer, String title, Noun noun, NamedTemplate template,
                     ArabicLetterType firstRadical, ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        final NounConjugation nounConjugation = transform(transformer, template, noun, SarfTermType.ACTIVE_PARTICIPLE_MASCULINE,
                firstRadical, secondRadical, thirdRadical);
        addTable(nounConjugation, title, new RootWord(noun.getRootWord(), firstRadical, secondRadical, thirdRadical));
    }

    @DataProvider(name = "data")
    private Object[][] data() {
        return new Object[][]{
                {masculineEndingSoundTransformer, "Form I masculine singular", Noun.FORM_I_MASCULINE_ACTIVE_PARTICIPLE,
                        NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, ArabicLetterType.NOON, ArabicLetterType.SAD,
                        ArabicLetterType.RA},
                {masculineDualTransformer, "Form I masculine dual", Noun.FORM_I_MASCULINE_ACTIVE_PARTICIPLE,
                        NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, ArabicLetterType.NOON, ArabicLetterType.SAD,
                        ArabicLetterType.RA},
                {masculinePluralTransformer, "Form I masculine plural", Noun.FORM_I_MASCULINE_ACTIVE_PARTICIPLE,
                        NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, ArabicLetterType.NOON, ArabicLetterType.SAD,
                        ArabicLetterType.RA}
        };
    }

    private NounConjugation transform(final NounTransformer transformer, final NamedTemplate template, Noun noun,
                                      SarfTermType sarfTermType, final ArabicLetterType firstRadical,
                                      final ArabicLetterType secondRadical, final ArabicLetterType thirdRadical) {
        final RootLetters rootLetters = new RootLetters(firstRadical, secondRadical, thirdRadical);

        RuleInfo ruleInfo = new RuleInfo(template, rootLetters, true);
        final RuleProcessor ruleProcessor = RULE_PROCESSOR_FACTORY.getRuleEngine(ruleInfo);

        return transformer.doTransform(ruleProcessor, noun.getRootWord(), sarfTermType, firstRadical, secondRadical, thirdRadical, null);
    }

    private void addTable(NounConjugation nounConjugation, String title, RootWord rootWord) {
        lines.add(String.format(".%s &mdash; %s", title, getLabel(rootWord.getRootWord())));
        lines.add("");
        lines.add("[cols=\"^.^33,^.^33,^.^34\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);

        lines.add(String.format("|%s |%s |%s", getLabel(nounConjugation.getGenitive()),
                getLabel(nounConjugation.getAccusative()), getLabel(nounConjugation.getNominative())));

        lines.add(ASCII_DOC_TABLE_DECELERATION);
        lines.add("");
    }

}
