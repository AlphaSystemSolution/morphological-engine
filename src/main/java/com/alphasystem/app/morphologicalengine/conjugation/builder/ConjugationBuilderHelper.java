package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.*;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.AdverbLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.RootType;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sali
 */
final class ConjugationBuilderHelper {

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
                                                               NounConjugationGroup[] nounsOfPlaceAndTime) {

        final RootWord pastTenseRoot = (pastActiveTenseGroup == null) ? null : pastActiveTenseGroup.getDefaultValue();
        final RootWord presentTenseRoot = (presentActiveTenseGroup == null) ? null : presentActiveTenseGroup.getDefaultValue();
        final RootWord imperativeRoot = (imperativeGroup == null) ? null : imperativeGroup.getDefaultValue();
        final RootWord forbiddenRoot = (forbiddenGroup == null) ? null : forbiddenGroup.getDefaultValue();
        final RootWord activeParticipleRoot = (masculineActiveParticipleGroup == null) ? null : masculineActiveParticipleGroup.getDefaultValue();
        final RootWord[] verbalNounDefaultWords = getDefaultWordPairs(verbalNouns);

        final ConjugationHeader conjugationHeader = createConjugationHeader(conjugationRoots, rootLetters, pastTenseRoot, presentTenseRoot);
        final ActiveLine activeLine = new ActiveLine(pastTenseRoot, presentTenseRoot, activeParticipleRoot, verbalNounDefaultWords);
        PassiveLine passiveLine = null;
        if (!removePassiveLine) {
            final RootWord pastPassiveTenseRoot = (pastPassiveTenseGroup == null) ? null : pastPassiveTenseGroup.getDefaultValue();
            final RootWord presentPassiveTenseRoot = (presentPassiveTenseGroup == null) ? null : presentPassiveTenseGroup.getDefaultValue();
            final RootWord passiveParticipleRoot = (masculinePassiveParticipleGroup == null) ? null : masculinePassiveParticipleGroup.getDefaultValue();
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
                                                             RootWord pastTenseRoot, RootWord presentTenseRoot) {

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
        return new ConjugationHeader(conjugationRoots.getTranslation(), pastTenseRoot, presentTenseRoot, template.toLabel(),
                chartMode, rootLetters);
    }

    private static RootWord[] getDefaultWordPairs(NounConjugationGroup[] nounConjugationGroups) {
        if (!ArrayUtils.isEmpty(nounConjugationGroups)) {
            List<RootWord> rootWords = new ArrayList<>();
            for (final NounConjugationGroup nounConjugationGroup : nounConjugationGroups) {
                if (nounConjugationGroup != null) {
                    rootWords.add(nounConjugationGroup.getDefaultValue());
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
}
