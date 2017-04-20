package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.ChartMode;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.spring.MorphologicalEngineFactory;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.RootType;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.ConjugationHeader;
import com.alphasystem.morphologicalengine.model.NounConjugation;
import com.alphasystem.morphologicalengine.model.NounConjugationGroup;
import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;

import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.ZAIN;
import static com.alphasystem.arabic.model.ArabicWord.getWord;

/**
 * @author sali
 */
final class ConjugationBuilderHelper {

    // private static final Logger LOGGER = LoggerFactory.getLogger(ConjugationBuilder.class);

    private static final ArabicWord WEIGHT_LABEL = getWord(WAW, ZAIN, NOON);

    static ConjugationHeader createConjugationHeader(ConjugationRoots conjugationRoots, String pastTenseRoot,
                                                     String presentTenseRoot,
                                                     OutputFormat outputFormat) {

        RootLetters rootLetters = conjugationRoots.getRootLetters();
        WordStatus status = new WordStatus(rootLetters);
        RootType rootType = RootType.CONSONANT;
        VerbType verbType = VerbType.CONSONANT;
        WeakVerbType weakVerbType = null;
        if (status.isWeak()) {
            rootType = RootType.WEAK;
            if (status.twoSeparateLettersWeak()) {
                verbType = VerbType.TWO_SEPARATE_RADICALS_WEAK;
            } else if (status.twoConsecutiveLettersWeak()) {
                verbType = VerbType.TWO_CONSECUTIVE_RADICALS_WEAK;
            } else if (status.isAssimilated()) {
                verbType = VerbType.FIRST_RADICAL_WEAK;
                weakVerbType = status.isFirstRadicalWaw() ? WeakVerbType.FIRST_RADICAL_WEAK_WAW
                        : WeakVerbType.FIRST_RADICAL_WEAK_YA;
            } else if (status.isHollow()) {
                verbType = VerbType.SECOND_RADICAL_WEAK;
                weakVerbType = status.isSecondRadicalWaw() ? WeakVerbType.SECOND_RADICAL_WEAK_WAW
                        : WeakVerbType.SECOND_RADICAL_WEAK_YA;
            } else if (status.isDefective()) {
                verbType = VerbType.THIRD_RADICAL_WEAK;
                weakVerbType = status.isThirdRadicalWaw() ? WeakVerbType.THIRD_RADICAL_WEAK_WAW
                        : WeakVerbType.THIRD_RADICAL_WEAK_YA;
            }
        } else if (status.isDoubledLettered()) {
            verbType = VerbType.DOUBLE_LETTERED;
        } else if (status.isHamzatted()) {
            if (status.isFirstRadicalHamza()) {
                verbType = VerbType.FIRST_RADICAL_HAMZA;
            } else if (status.isSecondRadicalHamza()) {
                verbType = VerbType.SECOND_RADICAL_HAMZA;
            } else if (status.isThirdRadicalHamza()) {
                verbType = VerbType.THIRD_RADICAL_HAMZA;
            }
        }

        final NamedTemplate template = conjugationRoots.getTemplate();
        ChartMode chartMode = new ChartMode(template, rootType, verbType, weakVerbType);
        ConjugationHeader conjugationHeader = new ConjugationHeader();
        conjugationHeader.setRootLetters(rootLetters);
        conjugationHeader.setChartMode(chartMode);
        conjugationHeader.setBaseWord(getStringValue(template.toLabel(), outputFormat));
        conjugationHeader.setPastTenseRoot(pastTenseRoot);
        conjugationHeader.setPresentTenseRoot(presentTenseRoot);
        conjugationHeader.setTranslation(conjugationRoots.getTranslation());
        conjugationHeader.setTitle(getHeaderTitle(pastTenseRoot, presentTenseRoot, rootLetters, outputFormat));
        conjugationHeader.setTypeLabel1(getStringValue(template.getType(), outputFormat));
        conjugationHeader.setTypeLabel2(getStringValue(ArabicWord.concatenateWithSpace(WEIGHT_LABEL, template.toLabel()), outputFormat));

        ArabicWord label = verbType.getLabel();
        if (weakVerbType != null) {
            final ArabicWord arabicWord = ArabicWord.concatenate(label, new ArabicWord(ArabicLetters.LETTER_COMMA));
            label = ArabicWord.concatenateWithSpace(arabicWord, weakVerbType.getLabel());
        }
        conjugationHeader.setTypeLabel3(getStringValue(label, outputFormat));

        return conjugationHeader;
    }

    private static String getHeaderTitle(String pastTenseRoot, String presentTenseRoot, RootLetters rootLetters,
                                         OutputFormat outputFormat) {
        StringBuilder builder = new StringBuilder();
        if (pastTenseRoot != null) {
            builder.append(pastTenseRoot).append(" ");
        }
        if (presentTenseRoot != null) {
            builder.append(presentTenseRoot);
        }

        if (rootLetters != null) {
            builder.append(" ").append(getStringValue(ArabicLetterType.LEFT_PARENTHESIS, outputFormat))
                    .append(getStringValue(rootLetters.toLabel(), outputFormat))
                    .append(getStringValue(ArabicLetterType.RIGHT_PARENTHESIS, outputFormat));
        }
        return builder.toString();
    }

    static String createDefaultVerb(SarfTermType sarfTermType, VerbRootBase verbRootBase, RuleProcessor ruleProcessor,
                                    RootLetters rootLetters, OutputFormat outputFormat) {
        final VerbTransformerFactory factory = MorphologicalEngineFactory.getVerbTransformerFactory(verbRootBase.getType());
        final boolean imperativeOrForbidding = SarfTermType.IMPERATIVE.equals(sarfTermType) ||
                SarfTermType.FORBIDDING.equals(sarfTermType);
        final VerbTransformer verbTransformer = imperativeOrForbidding ? factory.secondPersonMasculineTransformer() :
                factory.thirdPersonMasculineTransformer();
        return verbTransformer.doTransform(ruleProcessor, verbRootBase.getRoot().getRootWord(), sarfTermType,
                outputFormat, rootLetters).getSingular();
    }

    static String createDefaultNoun(SarfTermType sarfTermType, NounRootBase nounRootBase, RuleProcessor ruleProcessor,
                                    RootLetters rootLetters, OutputFormat outputFormat) {
        final NounTransformerFactory factory = MorphologicalEngineFactory.getNounTransformerFactory(nounRootBase.getType());
        final NounTransformer nounTransformer = factory.singularTransformer();
        final RootWord rootWord = nounRootBase.getSingularBaseWord().getRootWord();
        final NounConjugation nounConjugation = nounTransformer.doTransform(ruleProcessor, rootWord, sarfTermType,
                outputFormat, rootLetters);
        return SarfTermType.VERBAL_NOUN.equals(sarfTermType) ? nounConjugation.getAccusative() : nounConjugation.getNominative();
    }

    static VerbConjugationGroup createVerbConjugationGroup(SarfTermType sarfTermType, VerbRootBase verbRootBase, RuleProcessor ruleProcessor,
                                                           RootLetters rootLetters, OutputFormat outputFormat) {
        final VerbTransformerFactory factory = MorphologicalEngineFactory.getVerbTransformerFactory(verbRootBase.getType());
        return factory.doConjugation(ruleProcessor, sarfTermType, outputFormat, verbRootBase, rootLetters);
    }

    static NounConjugationGroup createNounConjugationGroup(SarfTermType sarfTermType, NounRootBase nounRootBase, RuleProcessor ruleProcessor,
                                                           RootLetters rootLetters, OutputFormat outputFormat) {
        final NounTransformerFactory factory = MorphologicalEngineFactory.getNounTransformerFactory(nounRootBase.getType());
        return factory.doConjugation(ruleProcessor, sarfTermType, outputFormat, nounRootBase, rootLetters);
    }

    private static String getStringValue(ArabicSupport arabicSupport, OutputFormat outputFormat) {
        if (arabicSupport == null) {
            return null;
        }
        String value;
        final ArabicWord arabicWord = arabicSupport.toLabel();
        switch (outputFormat) {
            case HTML:
                value = arabicWord.toHtmlCode();
                break;
            case BUCK_WALTER:
                value = arabicWord.toBuckWalter();
                break;
            default:
                value = arabicWord.toUnicode();
                break;
        }
        return value;
    }
}
