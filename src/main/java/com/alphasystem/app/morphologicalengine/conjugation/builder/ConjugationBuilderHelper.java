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

        final String pastTenseRoot = (pastActiveTenseGroup == null) ? null : pastActiveTenseGroup.defaultValue();
        final String presentTenseRoot = (presentActiveTenseGroup == null) ? null : presentActiveTenseGroup.defaultValue();
        final String imperativeRoot = (imperativeGroup == null) ? null : imperativeGroup.defaultValue();
        final String forbiddenRoot = (forbiddenGroup == null) ? null : forbiddenGroup.defaultValue();
        final String activeParticipleRoot = (masculineActiveParticipleGroup == null) ? null : masculineActiveParticipleGroup.defaultValue();
        final String[] verbalNounDefaultWords = getDefaultWordPairs(verbalNouns);
        final String verbalNoun = toDefaultStringValue(null, outputFormat, verbalNounDefaultWords);

        final ConjugationHeader conjugationHeader = createConjugationHeader(conjugationRoots, rootLetters, pastTenseRoot,
                presentTenseRoot, outputFormat);
        final ActiveLine activeLine = new ActiveLine();
        activeLine.setPastTense(pastTenseRoot);
        activeLine.setPresentTense(presentTenseRoot);
        activeLine.setActiveParticipleMasculine(activeParticipleRoot);
        activeLine.setActiveParticipleValue(toDefaultStringValue(PARTICIPLE_PREFIX, outputFormat, activeParticipleRoot));
        activeLine.setVerbalNouns(verbalNounDefaultWords);
        activeLine.setVerbalNoun(verbalNoun);

        PassiveLine passiveLine = null;
        if (!removePassiveLine) {
            final String pastPassiveTenseRoot = (pastPassiveTenseGroup == null) ? null : pastPassiveTenseGroup.defaultValue();
            final String presentPassiveTenseRoot = (presentPassiveTenseGroup == null) ? null : presentPassiveTenseGroup.defaultValue();
            final String passiveParticipleRoot = (masculinePassiveParticipleGroup == null) ? null : masculinePassiveParticipleGroup.defaultValue();
            passiveLine = new PassiveLine();
            passiveLine.setPastPassiveTense(pastPassiveTenseRoot);
            passiveLine.setPresentPassiveTense(presentPassiveTenseRoot);
            passiveLine.setPassiveParticipleMasculine(passiveParticipleRoot);
            passiveLine.setPassiveParticipleValue(toDefaultStringValue(PARTICIPLE_PREFIX, outputFormat, passiveParticipleRoot));
            passiveLine.setVerbalNouns(verbalNounDefaultWords);
            passiveLine.setVerbalNoun(verbalNoun);
        }

        final ImperativeAndForbiddingLine commandLine = new ImperativeAndForbiddingLine();
        if (imperativeRoot != null) {
            commandLine.setImperative(toDefaultStringValue(COMMAND_PREFIX, outputFormat, imperativeRoot));
        }
        if (forbiddenRoot != null) {
            commandLine.setForbidding(toDefaultStringValue(FORBIDDING_PREFIX, outputFormat, forbiddenRoot));
        }

        final String[] nounOfPlaceAndTimeDefaultWords = getDefaultWordPairs(nounsOfPlaceAndTime);
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
                                                             String pastTenseRoot, String presentTenseRoot,
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

        if(rootLetters != null){
            builder.append(" ").append(getStringValue(ArabicLetterType.LEFT_PARENTHESIS, outputFormat))
                    .append(getStringValue(rootLetters.toLabel(), outputFormat))
                    .append(getStringValue(ArabicLetterType.RIGHT_PARENTHESIS, outputFormat));
        }
        return builder.toString();
    }

    private static String[] getDefaultWordPairs(NounConjugationGroup[] nounConjugationGroups) {
        if (!ArrayUtils.isEmpty(nounConjugationGroups)) {
            List<String> rootWords = new ArrayList<>();
            for (final NounConjugationGroup nounConjugationGroup : nounConjugationGroups) {
                if (nounConjugationGroup != null) {
                    rootWords.add(nounConjugationGroup.defaultValue());
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
