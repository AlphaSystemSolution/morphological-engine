/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.*;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.arabic.model.ArabicLetters.*;
import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.arabic.model.HiddenPronounStatus.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.*;
import static org.apache.commons.lang3.ArrayUtils.contains;

/**
 * @author sali
 */
public class Rule7Processor extends AbstractRuleProcessor {

    private RuleProcessor parent;

    /**
     * @param template
     */
    public Rule7Processor(NamedTemplate template, RuleProcessor parent) {
        super(template);
        this.parent = parent;
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, null, new SarfTermType[]{VERBAL_NOUN,
                    NOUN_OF_PLACE_AND_TIME, IMPERATIVE, FORBIDDING});
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        WordStatus wordStatus = new WordStatus(baseRootWord);
        SarfMemberType memberType = baseRootWord.getMemberType();
        boolean perfectVerb = PAST_TENSE.equals(sarfTermType)
                || PAST_PASSIVE_TENSE.equals(sarfTermType);
        boolean imperfectVerb = PRESENT_TENSE.equals(sarfTermType)
                || PRESENT_PASSIVE_TENSE.equals(sarfTermType);
        boolean verbBasedType = contains(VERB_BASED_TYPES, sarfTermType);
        boolean perfectVerbDualWithAlif = perfectVerb
                && THIRD_PERSON_MASCULINE_DUAL.equals(memberType);
        boolean imperfectVerbDualWithAlif = imperfectVerb
                && contains(IMPERFECT_DUALS, memberType);
        boolean alifOfTasnia = wordStatus.isDefective() && verbBasedType
                && (perfectVerbDualWithAlif || imperfectVerbDualWithAlif);
        if (!wordStatus.isWeak() || wordStatus.isAssimilated()
                || wordStatus.isDoublyWeak() || alifOfTasnia) {
            return baseRootWord;
        }

        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());

        ArabicLetter firstRadical = baseRootWord.getFirstRadical();
        DiacriticType firstRadicalDiacritic = getDiacritic(firstRadical);
        ArabicLetter secondRadical = baseRootWord.getSecondRadical();
        DiacriticType secondRadicalDiacritic = getDiacritic(secondRadical);
        ArabicLetter thirdRadical = baseRootWord.getThirdRadical();
        DiacriticType thirdRadicalDiacritic = getDiacritic(thirdRadical);
        boolean activeSecondRadical = isMutaharik(secondRadicalDiacritic);
        boolean activeThirdRadical = isMutaharik(thirdRadicalDiacritic);
        int indexOfWeakLetter = wordStatus.isHollow() ? baseRootWord
                .getSecondRadicalIndex() : baseRootWord.getThirdRadicalIndex();
        int previousIndex = indexOfWeakLetter - 1;
        ArabicLetter previousLetter = result.getLetter(previousIndex);
        DiacriticType previousLetterDiacritic = getDiacritic(previousLetter);
        int maddaIndex = maddaIndex(result);
        boolean maddaExtra = isMaddaExtra(result, sarfTermType, maddaIndex);
        if ((wordStatus.isHollow() && !activeSecondRadical)
                || (wordStatus.isDefective() && !activeThirdRadical)
                || (!isFatha(previousLetterDiacritic)) || maddaExtra) {
            return baseRootWord;
        }
        boolean lastIndex = indexOfWeakLetter == result.getLength() - 1;
        ArabicLetter replacementLetter = (wordStatus.isThirdRadicalYa() && lastIndex) ? LETTER_ALIF_MAKSURA
                : LETTER_ALIF;
        int nextIndex = (indexOfWeakLetter >= (result.getLength() - 1)) ? -1
                : indexOfWeakLetter + 1;
        if (nextIndex > -1) {
            ArabicLetter nextLetter = result.getLetter(nextIndex);
            DiacriticType nextLetterDiacritic = getDiacritic(nextLetter);
            if (isSakin(nextLetterDiacritic)) {
                replacementLetter = LETTER_TATWEEL;
            }
        }
        result.replaceLetter(indexOfWeakLetter, replacementLetter);
        if (wordStatus.isHollow()) {
            baseRootWord.setSecondRadical(replacementLetter);
        } else if (wordStatus.isDefective()) {
            baseRootWord.setThirdRadical(replacementLetter);
        }
        if (PAST_TENSE.equals(sarfTermType)) {
            parent.setPastTenseHasTransformed(true);
            boolean thirdPersonFemininePluralAndSeconAndFirstPersonsType = contains(
                    THIRD_PERSON_FEMENINE_PLURAL_AND_SECOND_AND_FIRST_PERSONS_MEMBERS,
                    memberType);
            DiacriticType diacriticForFirstradical = firstRadicalDiacritic;
            if (thirdPersonFemininePluralAndSeconAndFirstPersonsType) {
                if (isKasra(secondRadicalDiacritic)
                        || secondRadical.getLetter().equals(YA)) {
                    diacriticForFirstradical = KASRA;
                } else {
                    diacriticForFirstradical = DAMMA;
                }
                int firstRadicalIndex = baseRootWord.getFirstRadicalIndex();
                result.replaceDiacritic(firstRadicalIndex,
                        diacriticForFirstradical);
                baseRootWord.setFirstRadical(result
                        .getLetter(firstRadicalIndex));
            }
        }
        if (wordStatus.isSecondRadicalWaw()) {
            parent.setDiacriticForWeakSecondRadicalWaw(secondRadicalDiacritic);
        }
        if (wordStatus.isDefective()
                && perfectVerb
                && (THIRD_PERSON_FEMININE_SINGULAR.equals(memberType) || THIRD_PERSON_FEMININE_DUAL
                .equals(memberType))) {
            replacementLetter = LETTER_TATWEEL;
            result.replaceLetter(baseRootWord.getThirdRadicalIndex(),
                    replacementLetter);
            baseRootWord.setThirdRadical(replacementLetter);
        }

        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
