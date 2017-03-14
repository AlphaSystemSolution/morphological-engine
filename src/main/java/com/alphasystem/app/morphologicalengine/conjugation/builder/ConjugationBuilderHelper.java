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

import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.ZAIN;
import static com.alphasystem.arabic.model.ArabicWord.getWord;

/**
 * @author sali
 */
final class ConjugationBuilderHelper {

    private static final ArabicWord WEIGHT_LABEL = getWord(WAW, ZAIN, NOON);

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
        final RootWord[] verbalNounDefaultWords = getDefaultWordPairs(verbalNouns);

        final ConjugationHeader conjugationHeader = createConjugationHeader(conjugationRoots, rootLetters, pastTenseRoot,
                presentTenseRoot, outputFormat);
        final ActiveLine activeLine = new ActiveLine(pastTenseRoot, presentTenseRoot, activeParticipleRoot, verbalNounDefaultWords);
        PassiveLine passiveLine = null;
        if (!removePassiveLine) {
            final RootWord pastPassiveTenseRoot = (pastPassiveTenseGroup == null) ? null : pastPassiveTenseGroup.defaultValue();
            final RootWord presentPassiveTenseRoot = (presentPassiveTenseGroup == null) ? null : presentPassiveTenseGroup.defaultValue();
            final RootWord passiveParticipleRoot = (masculinePassiveParticipleGroup == null) ? null : masculinePassiveParticipleGroup.defaultValue();
            passiveLine = new PassiveLine(pastPassiveTenseRoot, presentPassiveTenseRoot, passiveParticipleRoot, verbalNounDefaultWords);
        }

        final ImperativeAndForbiddingLine commandLine = new ImperativeAndForbiddingLine(imperativeRoot, forbiddenRoot);
        final RootWord[] nounOfPlaceAndTimeDefaultWords = getDefaultWordPairs(nounsOfPlaceAndTime);
        AdverbLine adverbLine = null;
        if (!ArrayUtils.isEmpty(nounOfPlaceAndTimeDefaultWords)) {
            adverbLine = new AdverbLine(nounOfPlaceAndTimeDefaultWords);
        }
        return new AbbreviatedConjugation(conjugationHeader, activeLine, passiveLine, commandLine, adverbLine);
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

    private static RootWord[] getDefaultWordPairs(NounConjugationGroup[] nounConjugationGroups) {
        if (!ArrayUtils.isEmpty(nounConjugationGroups)) {
            List<RootWord> rootWords = new ArrayList<>();
            for (final NounConjugationGroup nounConjugationGroup : nounConjugationGroups) {
                if (nounConjugationGroup != null) {
                    rootWords.add(nounConjugationGroup.defaultValue());
                }
            }
            return rootWords.toArray(new RootWord[rootWords.size()]);
        }
        return null;
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
        String value = null;
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
