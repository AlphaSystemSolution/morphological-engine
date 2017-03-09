package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.AbbreviatedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.ChartMode;
import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationHeader;
import com.alphasystem.app.morphologicalengine.conjugation.model.DetailedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounDetailedConjugationPair;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbDetailedConjugationPair;
import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.RootType;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

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

        final ConjugationHeader conjugationHeader = createConjugationHeader(conjugationRoots, rootLetters, pastTenseRoot, presentTenseRoot);
        final ActiveLine activeLine = new ActiveLine(pastTenseRoot, presentTenseRoot, activeParticipleRoot);
        PassiveLine passiveLine = null;
        if (!removePassiveLine) {
            final RootWord pastPassiveTenseRoot = (pastPassiveTenseGroup == null) ? null : pastPassiveTenseGroup.getDefaultValue();
            final RootWord presentPassiveTenseRoot = (presentPassiveTenseGroup == null) ? null : presentPassiveTenseGroup.getDefaultValue();
            final RootWord passiveParticipleRoot = (masculinePassiveParticipleGroup == null) ? null : masculinePassiveParticipleGroup.getDefaultValue();
            passiveLine = new PassiveLine(pastPassiveTenseRoot, presentPassiveTenseRoot, passiveParticipleRoot);
        }

        ImperativeAndForbiddingLine commandLine = new ImperativeAndForbiddingLine(imperativeRoot, forbiddenRoot);

        return new AbbreviatedConjugation(conjugationHeader, activeLine, passiveLine, commandLine, null);
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
        return new DetailedConjugation(activeTensePair, null, activeParticiplePair, passiveTensePair, passiveParticiplePair,
                imperativeAndForbiddingPair, null);
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
}
