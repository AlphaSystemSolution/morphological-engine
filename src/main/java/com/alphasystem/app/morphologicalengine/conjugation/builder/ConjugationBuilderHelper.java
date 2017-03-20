package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.ChartMode;
import com.alphasystem.app.morphologicalengine.conjugation.model.OutputFormat;
import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.RootType;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;
import com.alphasystem.morphologicalengine.model.ConjugationHeader;
import com.alphasystem.morphologicalengine.model.DetailedConjugation;
import com.alphasystem.morphologicalengine.model.NounConjugationGroup;
import com.alphasystem.morphologicalengine.model.NounDetailedConjugationPair;
import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;
import com.alphasystem.morphologicalengine.model.VerbDetailedConjugationPair;
import com.alphasystem.morphologicalengine.model.abbrvconj.ActiveLine;
import com.alphasystem.morphologicalengine.model.abbrvconj.AdverbLine;
import com.alphasystem.morphologicalengine.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.morphologicalengine.model.abbrvconj.PassiveLine;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.getWord;

/**
 * @author sali
 */
final class ConjugationBuilderHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConjugationBuilder.class);

    private static final ArabicWord WEIGHT_LABEL = getWord(WAW, ZAIN, NOON);
    private static final ArabicWord PARTICIPLE_PREFIX = getWord(FA, HA, WAW);
    private static final ArabicWord COMMAND_PREFIX = getWord(ALIF, LAM, ALIF_HAMZA_ABOVE, MEEM, RA, ArabicLetterType.SPACE,
            MEEM, NOON, HA);
    private static final ArabicWord FORBIDDING_PREFIX = getWord(WAW, NOON, HA, YA, ArabicLetterType.SPACE, AIN, NOON, HA);
    private static final ArabicWord NOUN_OF_PLACE_AND_TIME_PREFIX = getWord(WAW, ALIF, LAM, DTHA, RA, FA, ArabicLetterType.SPACE, MEEM,
            NOON, HA);

    static Void applyPastAndPresentActiveTenseGroup(VerbDetailedConjugationPair verbDetailedConjugationPair,
                                                    AbbreviatedConjugation abbreviatedConjugation,
                                                    DetailedConjugation detailedConjugation,
                                                    ConjugationRoots conjugationRoots, OutputFormat outputFormat) {
        ActiveLine activeLine = abbreviatedConjugation.getActiveLine();
        if (activeLine == null) {
            activeLine = new ActiveLine();
            abbreviatedConjugation.setActiveLine(activeLine);
        }
        final String pastTenseRoot = verbDetailedConjugationPair.getRightSideConjugations().defaultValue();
        final String presentTenseRoot = verbDetailedConjugationPair.getLeftSideConjugations().defaultValue();
        activeLine.pastTense(pastTenseRoot).presentTense(presentTenseRoot);
        abbreviatedConjugation.setConjugationHeader(createConjugationHeader(conjugationRoots, pastTenseRoot,
                presentTenseRoot, outputFormat));

        detailedConjugation.setActiveTensePair(verbDetailedConjugationPair);
        return null;
    }

    static Void applyMasculineAndFeminineActiveParticipleGroup(NounDetailedConjugationPair nounDetailedConjugationPair,
                                                               AbbreviatedConjugation abbreviatedConjugation,
                                                               DetailedConjugation detailedConjugation,
                                                               OutputFormat outputFormat) {
        ActiveLine activeLine = abbreviatedConjugation.getActiveLine();
        if (activeLine == null) {
            activeLine = new ActiveLine();
            abbreviatedConjugation.setActiveLine(activeLine);
        }
        final String activeParticipleRoot = nounDetailedConjugationPair.getRightSideConjugations().defaultValue();
        final String activeParticipleValue = toDefaultStringValue(PARTICIPLE_PREFIX, outputFormat, activeParticipleRoot);
        activeLine.activeParticipleMasculine(activeParticipleRoot).activeParticipleValue(activeParticipleValue);

        detailedConjugation.setActiveParticiplePair(nounDetailedConjugationPair);
        return null;
    }

    static Void applyImperativeAndForbiddingGroup(VerbDetailedConjugationPair verbDetailedConjugationPair,
                                                  AbbreviatedConjugation abbreviatedConjugation,
                                                  DetailedConjugation detailedConjugation,
                                                  OutputFormat outputFormat) {
        final String imperative = verbDetailedConjugationPair.getRightSideConjugations().defaultValue();
        final String forbidding = verbDetailedConjugationPair.getLeftSideConjugations().defaultValue();
        final ImperativeAndForbiddingLine imperativeAndForbiddingLine = new ImperativeAndForbiddingLine()
                .imperative(imperative).forbidding(forbidding)
                .imperativeWithPrefix(toDefaultStringValue(COMMAND_PREFIX, outputFormat, imperative))
                .forbiddingWithPrefix(toDefaultStringValue(FORBIDDING_PREFIX, outputFormat, forbidding));
        abbreviatedConjugation.setImperativeAndForbiddingLine(imperativeAndForbiddingLine);
        detailedConjugation.setImperativeAndForbiddingPair(verbDetailedConjugationPair);
        return null;
    }

    static Void applyPastAndPresentPassiveTenseGroup(VerbDetailedConjugationPair verbDetailedConjugationPair,
                                                     AbbreviatedConjugation abbreviatedConjugation,
                                                     DetailedConjugation detailedConjugation) {
        PassiveLine passiveLine = abbreviatedConjugation.getPassiveLine();
        if (passiveLine == null) {
            passiveLine = new PassiveLine();
            abbreviatedConjugation.setPassiveLine(passiveLine);
        }
        passiveLine.pastPassiveTense(verbDetailedConjugationPair.getRightSideConjugations().defaultValue())
                .presentPassiveTense(verbDetailedConjugationPair.getLeftSideConjugations().defaultValue());
        detailedConjugation.setPassiveTensePair(verbDetailedConjugationPair);
        return null;
    }

    static Void applyMasculineAndFemininePassiveParticipleGroup(NounDetailedConjugationPair nounDetailedConjugationPair,
                                                                AbbreviatedConjugation abbreviatedConjugation,
                                                                DetailedConjugation detailedConjugation,
                                                                OutputFormat outputFormat) {
        PassiveLine passiveLine = abbreviatedConjugation.getPassiveLine();
        if (passiveLine == null) {
            passiveLine = new PassiveLine();
            abbreviatedConjugation.setPassiveLine(passiveLine);
        }
        final String passiveParticipleRoot = nounDetailedConjugationPair.getLeftSideConjugations().defaultValue();
        passiveLine.passiveParticipleMasculine(passiveParticipleRoot)
                .passiveParticipleValue(toDefaultStringValue(PARTICIPLE_PREFIX, outputFormat, passiveParticipleRoot));

        detailedConjugation.setPassiveParticiplePair(nounDetailedConjugationPair);
        return null;
    }

    static Void applyVerbalNounGroup(NounDetailedConjugationPair nounDetailedConjugationPair,
                                     AbbreviatedConjugation abbreviatedConjugation,
                                     DetailedConjugation detailedConjugation, OutputFormat outputFormat) {
        ActiveLine activeLine = abbreviatedConjugation.getActiveLine();
        if (activeLine == null) {
            activeLine = new ActiveLine();
            abbreviatedConjugation.setActiveLine(activeLine);
        }
        PassiveLine passiveLine = abbreviatedConjugation.getPassiveLine();
        if (passiveLine == null) {
            passiveLine = new PassiveLine();
            abbreviatedConjugation.setPassiveLine(passiveLine);
        }

        final NounConjugationGroup leftSideConjugations = nounDetailedConjugationPair.getLeftSideConjugations();
        final NounConjugationGroup rightSideConjugations = nounDetailedConjugationPair.getRightSideConjugations();
        String[] verbalNouns = new String[0];
        if (leftSideConjugations != null) {
            verbalNouns = ArrayUtils.addAll(verbalNouns, leftSideConjugations.defaultValue());
        }
        if (rightSideConjugations != null) {
            verbalNouns = ArrayUtils.addAll(verbalNouns, rightSideConjugations.defaultValue());
        }
        activeLine.verbalNouns(verbalNouns);
        final String verbalNoun = toDefaultStringValue(null, outputFormat, activeLine.getVerbalNouns());
        activeLine.verbalNoun(verbalNoun);
        passiveLine.verbalNouns(verbalNouns).verbalNoun(verbalNoun);

        detailedConjugation.verbalNounPairs(nounDetailedConjugationPair);
        return null;
    }

    static Void applyNounOfPlaceAndTimeGroup(NounDetailedConjugationPair nounDetailedConjugationPair,
                                             AbbreviatedConjugation abbreviatedConjugation,
                                             DetailedConjugation detailedConjugation, OutputFormat outputFormat) {

        AdverbLine adverbLine = abbreviatedConjugation.getAdverbLine();
        if (adverbLine == null) {
            adverbLine = new AdverbLine();
            abbreviatedConjugation.setAdverbLine(adverbLine);
        }
        final NounConjugationGroup leftSideConjugations = nounDetailedConjugationPair.getLeftSideConjugations();
        final NounConjugationGroup rightSideConjugations = nounDetailedConjugationPair.getRightSideConjugations();
        String[] adverbs = new String[0];
        if (leftSideConjugations != null) {
            adverbs = ArrayUtils.addAll(adverbs, leftSideConjugations.defaultValue());
        }
        if (rightSideConjugations != null) {
            adverbs = ArrayUtils.addAll(adverbs, rightSideConjugations.defaultValue());
        }
        adverbLine.adverbs(adverbs).adverb(toDefaultStringValue(NOUN_OF_PLACE_AND_TIME_PREFIX, outputFormat,
                adverbLine.getAdverbs()));

        detailedConjugation.adverbPairs(nounDetailedConjugationPair);
        return null;
    }

    private static ConjugationHeader createConjugationHeader(ConjugationRoots conjugationRoots, String pastTenseRoot,
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

    private static String toDefaultStringValue(ArabicWord prefix, OutputFormat outputFormat, String... values) {
        if (ArrayUtils.isEmpty(values)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(values[0]);
        for (int i = 1; i < values.length; i++) {
            builder.append(" ").append(getStringValue(ArabicLetterType.WAW, outputFormat)).append(" ").append(values[i]);
        }
        if (prefix != null && builder.length() > 0) {
            builder.insert(0, String.format("%s ", getStringValue(prefix, outputFormat)));
        }
        return builder.toString();
    }

    static VerbDetailedConjugationPair createVerbDetailedConjugationPair(VerbConjugationGroup leftSideGroup,
                                                                         VerbConjugationGroup rightSideGroup) {
        SarfTermType leftTerm = (leftSideGroup == null) ? null : leftSideGroup.getTermType();
        SarfTermType rightTerm = (rightSideGroup == null) ? null : rightSideGroup.getTermType();
        LOGGER.debug("<<<<< Start combining VerbDetailedConjugationPair for terms {} and {} >>>>>", leftTerm, rightTerm);
        VerbDetailedConjugationPair result = null;
        if (leftSideGroup != null || rightSideGroup != null) {
            result = new VerbDetailedConjugationPair(leftSideGroup, rightSideGroup);
        }
        LOGGER.debug("<<<<< Finish combining VerbDetailedConjugationPair for terms {} and {} >>>>>", leftTerm, rightTerm);
        return result;
    }

    static NounDetailedConjugationPair createNounDetailedConjugationPair(NounConjugationGroup leftSideGroup,
                                                                         NounConjugationGroup rightSideGroup) {
        SarfTermType leftTerm = (leftSideGroup == null) ? null : leftSideGroup.getTermType();
        SarfTermType rightTerm = (rightSideGroup == null) ? null : rightSideGroup.getTermType();
        LOGGER.debug("<<<<< Start combining VerbDetailedConjugationPair for terms {} and {} >>>>>", leftTerm, rightTerm);
        NounDetailedConjugationPair result = null;
        if (leftSideGroup != null || rightSideGroup != null) {
            result = new NounDetailedConjugationPair(leftSideGroup, rightSideGroup);
        }
        LOGGER.debug("<<<<< Finish combining VerbDetailedConjugationPair for terms {} and {} >>>>>", leftTerm, rightTerm);
        return result;
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
