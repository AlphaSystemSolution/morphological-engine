package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.AdverbLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;
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
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.getWord;

/**
 * @author sali
 */
final class ConjugationBuilderHelper {

    private static final ArabicWord WEIGHT_LABEL = getWord(WAW, ZAIN, NOON);
    private static final ArabicWord PARTICIPLE_PREFIX = getWord(FA, HA, WAW);
    private static final ArabicWord COMMAND_PREFIX = getWord(ALIF, LAM, ALIF_HAMZA_ABOVE, MEEM, RA, ArabicLetterType.SPACE,
            MEEM, NOON, HA);
    private static final ArabicWord FORBIDDING_PREFIX = getWord(WAW, NOON, HA, YA, ArabicLetterType.SPACE, AIN, NOON, HA);
    private static final ArabicWord NOUN_OF_PLACE_AND_TIME_PREFIX = getWord(WAW, ALIF, LAM, DTHA, RA, FA, ArabicLetterType.SPACE, MEEM,
            NOON, HA);

    static AbbreviatedConjugation createAbbreviatedConjugation(ConjugationRoots conjugationRoots,
                                                               RootLetters rootLetters,
                                                               boolean removePassiveLine,
                                                               VerbConjugationGroup pastActiveTenseGroup,
                                                               VerbConjugationGroup presentActiveTenseGroup,
                                                               VerbConjugationGroup pastPassiveTenseGroup,
                                                               VerbConjugationGroup presentPassiveTenseGroup,
                                                               VerbConjugationGroup imperativeGroup,
                                                               VerbConjugationGroup forbiddenGroup,
                                                               NounConjugationGroup masculineActiveParticipleGroup,
                                                               NounConjugationGroup masculinePassiveParticipleGroup,
                                                               NounConjugationGroup[] verbalNouns,
                                                               NounConjugationGroup[] nounsOfPlaceAndTime,
                                                               OutputFormat outputFormat) {

        final RootWord pastTenseRoot = (pastActiveTenseGroup == null) ? null : pastActiveTenseGroup.defaultValue();
        final RootWord presentTenseRoot = (presentActiveTenseGroup == null) ? null : presentActiveTenseGroup.defaultValue();
        final RootWord imperativeRoot = (imperativeGroup == null) ? null : imperativeGroup.defaultValue();
        final RootWord forbiddenRoot = (forbiddenGroup == null) ? null : forbiddenGroup.defaultValue();
        final RootWord activeParticipleRoot = (masculineActiveParticipleGroup == null) ? null : masculineActiveParticipleGroup.defaultValue();
        final String[] verbalNounDefaultWords = getDefaultWordPairs(verbalNouns, outputFormat);
        final String verbalNoun = toDefaultStringValue(null, outputFormat, verbalNounDefaultWords);

        final ConjugationHeader conjugationHeader = createConjugationHeader(conjugationRoots, rootLetters, pastTenseRoot,
                presentTenseRoot, outputFormat);
        final ActiveLine activeLine = new ActiveLine();
        activeLine.setPastTense(getStringValue(pastTenseRoot, outputFormat));
        activeLine.setPresentTense(getStringValue(presentTenseRoot, outputFormat));
        final String activeParticipleMasculine = getStringValue(activeParticipleRoot, outputFormat);
        activeLine.setActiveParticipleMasculine(activeParticipleMasculine);
        activeLine.setActiveParticipleValue(toDefaultStringValue(PARTICIPLE_PREFIX, outputFormat, activeParticipleMasculine));
        activeLine.setVerbalNouns(verbalNounDefaultWords);
        activeLine.setVerbalNoun(verbalNoun);

        PassiveLine passiveLine = null;
        if (!removePassiveLine) {
            final RootWord pastPassiveTenseRoot = (pastPassiveTenseGroup == null) ? null : pastPassiveTenseGroup.defaultValue();
            final RootWord presentPassiveTenseRoot = (presentPassiveTenseGroup == null) ? null : presentPassiveTenseGroup.defaultValue();
            final RootWord passiveParticipleRoot = (masculinePassiveParticipleGroup == null) ? null : masculinePassiveParticipleGroup.defaultValue();
            passiveLine = new PassiveLine();
            passiveLine.setPastPassiveTense(getStringValue(pastPassiveTenseRoot, outputFormat));
            passiveLine.setPresentPassiveTense(getStringValue(presentPassiveTenseRoot, outputFormat));
            final String passiveParticipleMasculine = getStringValue(passiveParticipleRoot, outputFormat);
            passiveLine.setPassiveParticipleMasculine(passiveParticipleMasculine);
            passiveLine.setPassiveParticipleValue(toDefaultStringValue(PARTICIPLE_PREFIX, outputFormat, passiveParticipleMasculine));
            passiveLine.setVerbalNouns(verbalNounDefaultWords);
            passiveLine.setVerbalNoun(verbalNoun);
        }

        final ImperativeAndForbiddingLine commandLine = new ImperativeAndForbiddingLine();
        if (imperativeRoot != null) {
            final ArabicWord arabicWord = ArabicWord.concatenateWithSpace(COMMAND_PREFIX, imperativeRoot.toLabel());
            commandLine.setImperative(getStringValue(arabicWord, outputFormat));
        }
        if (forbiddenRoot != null) {
            final ArabicWord arabicWord = ArabicWord.concatenateWithSpace(FORBIDDING_PREFIX, forbiddenRoot.toLabel());
            commandLine.setForbidding(getStringValue(arabicWord, outputFormat));
        }

        final String[] nounOfPlaceAndTimeDefaultWords = getDefaultWordPairs(nounsOfPlaceAndTime, outputFormat);
        AdverbLine adverbLine = null;
        if (!ArrayUtils.isEmpty(nounOfPlaceAndTimeDefaultWords)) {
            adverbLine = new AdverbLine();
            adverbLine.setAdverbs(nounOfPlaceAndTimeDefaultWords);
            adverbLine.setAdverb(toDefaultStringValue(NOUN_OF_PLACE_AND_TIME_PREFIX, outputFormat, nounOfPlaceAndTimeDefaultWords));
        }

        AbbreviatedConjugation abbreviatedConjugation = new AbbreviatedConjugation();
        abbreviatedConjugation.setConjugationHeader(conjugationHeader);
        abbreviatedConjugation.setActiveLine(activeLine);
        abbreviatedConjugation.setPassiveLine(passiveLine);
        abbreviatedConjugation.setImperativeAndForbiddingLine(commandLine);
        abbreviatedConjugation.setAdverbLine(adverbLine);
        return abbreviatedConjugation;
    }

    static DetailedConjugation createDetailedConjugation(VerbConjugationGroup pastActiveTenseGroup,
                                                         VerbConjugationGroup presentActiveTenseGroup,
                                                         VerbConjugationGroup pastPassiveTenseGroup,
                                                         VerbConjugationGroup presentPassiveTenseGroup,
                                                         VerbConjugationGroup imperativeGroup,
                                                         VerbConjugationGroup forbiddenGroup,
                                                         NounConjugationGroup masculineActiveParticipleGroup,
                                                         NounConjugationGroup feminineActiveParticipleGroup,
                                                         NounConjugationGroup masculinePassiveParticipleGroup,
                                                         NounConjugationGroup femininePassiveParticipleGroup,
                                                         NounConjugationGroup[] verbalNouns,
                                                         NounConjugationGroup[] nounsOfPlaceAndTime) {
        final VerbDetailedConjugationPair activeTensePair = createVerbDetailedConjugationPair(presentActiveTenseGroup, pastActiveTenseGroup);
        final NounDetailedConjugationPair activeParticiplePair = createNounDetailedConjugationPair(feminineActiveParticipleGroup, masculineActiveParticipleGroup);
        final VerbDetailedConjugationPair passiveTensePair = createVerbDetailedConjugationPair(presentPassiveTenseGroup, pastPassiveTenseGroup);
        final NounDetailedConjugationPair passiveParticiplePair = createNounDetailedConjugationPair(femininePassiveParticipleGroup, masculinePassiveParticipleGroup);
        final VerbDetailedConjugationPair imperativeAndForbiddingPair = createVerbDetailedConjugationPair(forbiddenGroup, imperativeGroup);
        final NounDetailedConjugationPair[] verbalNounDetailedConjugationPairs = createNounDetailedConjugationPairs(verbalNouns);
        final NounDetailedConjugationPair[] nounOfPlaceAndTimeDetailedConjugationPairs = createNounDetailedConjugationPairs(nounsOfPlaceAndTime);
        return new DetailedConjugation(activeTensePair, verbalNounDetailedConjugationPairs, activeParticiplePair,
                passiveTensePair, passiveParticiplePair, imperativeAndForbiddingPair, nounOfPlaceAndTimeDetailedConjugationPairs);
    }

    private static ConjugationHeader createConjugationHeader(ConjugationRoots conjugationRoots, RootLetters rootLetters,
                                                             RootWord pastTenseRoot, RootWord presentTenseRoot,
                                                             OutputFormat outputFormat) {

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
        conjugationHeader.setPastTenseRoot(getStringValue(pastTenseRoot, outputFormat));
        conjugationHeader.setPresentTenseRoot(getStringValue(presentTenseRoot, outputFormat));
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

    private static String getHeaderTitle(RootWord pastTenseRoot, RootWord presentTenseRoot, RootLetters rootLetters,
                                         OutputFormat outputFormat) {
        ArabicWord title = ArabicLetters.WORD_SPACE;
        if (pastTenseRoot != null) {
            title = ArabicWord.concatenateWithSpace(title, pastTenseRoot.toLabel());
        }
        if (presentTenseRoot != null) {
            title = ArabicWord.concatenateWithSpace(title, presentTenseRoot.toLabel());
        }
        if (rootLetters != null) {
            ArabicLetterType fourthRadical = rootLetters.getFourthRadical();
            ArabicWord rl = ArabicWord.concatenate(ArabicWord.getWord(ArabicLetterType.LEFT_PARENTHESIS),
                    ArabicWord.getWord(rootLetters.getFirstRadical()),
                    ArabicWord.getWord(rootLetters.getSecondRadical()), ArabicWord.getWord(rootLetters.getThirdRadical()));
            if (fourthRadical != null) {
                rl = ArabicWord.concatenate(ArabicWord.getWord(fourthRadical));
            }
            rl = ArabicWord.concatenate(rl, ArabicWord.getWord(ArabicLetterType.RIGHT_PARENTHESIS));
            title = ArabicWord.concatenateWithSpace(title, rl);
        }
        return getStringValue(title, outputFormat);
    }

    private static String[] getDefaultWordPairs(NounConjugationGroup[] nounConjugationGroups, OutputFormat outputFormat) {
        if (!ArrayUtils.isEmpty(nounConjugationGroups)) {
            List<String> rootWords = new ArrayList<>();
            for (final NounConjugationGroup nounConjugationGroup : nounConjugationGroups) {
                if (nounConjugationGroup != null) {
                    rootWords.add(getStringValue(nounConjugationGroup.defaultValue(), outputFormat));
                }
            }
            return rootWords.toArray(new String[rootWords.size()]);
        }
        return null;
    }

    private static String toDefaultStringValue(ArabicWord prefix, OutputFormat outputFormat, String... values) {
        if (ArrayUtils.isEmpty(values)) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(values[0]);
        for (int i = 1; i < values.length; i++) {
            builder.append(" ").append(getStringValue(ArabicLetterType.WAW, outputFormat)).append(values[i]);
        }
        if (prefix != null && builder.length() > 0) {
            builder.insert(0, String.format("%s ", getStringValue(prefix, outputFormat)));
        }
        return builder.toString();
    }

    private static VerbDetailedConjugationPair createVerbDetailedConjugationPair(VerbConjugationGroup leftSideGroup,
                                                                                 VerbConjugationGroup rightSideGroup) {
        if (leftSideGroup != null || rightSideGroup != null) {
            return new VerbDetailedConjugationPair(leftSideGroup, rightSideGroup);
        }
        return null;
    }

    private static NounDetailedConjugationPair createNounDetailedConjugationPair(NounConjugationGroup leftSideGroup,
                                                                                 NounConjugationGroup rightSideGroup) {
        if (leftSideGroup != null || rightSideGroup != null) {
            return new NounDetailedConjugationPair(leftSideGroup, rightSideGroup);
        }
        return null;
    }

    private static NounDetailedConjugationPair[] createNounDetailedConjugationPairs(NounConjugationGroup[] nounConjugationGroups) {
        if (!ArrayUtils.isEmpty(nounConjugationGroups)) {
            List<NounDetailedConjugationPair> pairs = new ArrayList<>();

            int fromIndex = 0;
            int toIndex = ConjugationBuilder.NUM_OF_COLUMNS;
            while (fromIndex < nounConjugationGroups.length) {
                final NounConjugationGroup[] subArray = ArrayUtils.subarray(nounConjugationGroups, fromIndex, toIndex);
                pairs.add(createNounDetailedConjugationPair(subArray[0], subArray[1]));
                fromIndex = toIndex;
                toIndex += ConjugationBuilder.NUM_OF_COLUMNS;
            }

            return pairs.toArray(new NounDetailedConjugationPair[pairs.size()]);
        }
        return null;
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
