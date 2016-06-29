/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.*;
import static com.alphasystem.arabic.model.ArabicLetters.YA_WITH_SUKUN;
import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static org.apache.commons.lang3.ArrayUtils.contains;

;

/**
 * @author sali
 */
public class Rule9Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule9Processor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord,
                    new SarfTermType[]{PAST_PASSIVE_TENSE},
                    new SarfTermType[]{IMPERATIVE, FORBIDDING});
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        WordStatus wordStatus = new WordStatus(ruleInfo.getRootLetters());
        if (!wordStatus.isHollow()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());

        SarfMemberType memberType = baseRootWord.getMemberType();
        ArabicLetter firstRadical = baseRootWord.getFirstRadical();
        DiacriticType firstRadicalDiacritic = getDiacritic(firstRadical);
        ArabicLetter secondRadical = baseRootWord.getSecondRadical();
        int secondRadicalIndex = baseRootWord.getSecondRadicalIndex();
        boolean weakWaw = wordStatus.isSecondRadicalWaw();
        boolean thirdPersonFemininePluralAndSeconAndFirstPersonsType = contains(
                THIRD_PERSON_FEMENINE_PLURAL_AND_SECOND_AND_FIRST_PERSONS_MEMBERS,
                memberType);
        result.replaceDiacritic(baseRootWord.getFirstRadicalIndex(),
                secondRadical.getDiacritics()).replaceLetter(
                secondRadicalIndex, YA_WITH_SUKUN);
        baseRootWord.setSecondRadical(YA_WITH_SUKUN);
        if (thirdPersonFemininePluralAndSeconAndFirstPersonsType) {
            if (isFatha(ruleInfo.getDiacriticForWeakSecondRadicalWaw())
                    && weakWaw) {
                firstRadicalDiacritic = DAMMA;
            } else if ((isKasra(ruleInfo.getDiacriticForWeakSecondRadicalWaw()) && weakWaw)
                    || !weakWaw) {
                firstRadicalDiacritic = KASRA;
            }
            ArabicLetter replacementLetter = REMOVE_MARKER;
            result.replaceDiacritic(baseRootWord.getFirstRadicalIndex(),
                    firstRadicalDiacritic).replaceLetter(secondRadicalIndex, REMOVE_MARKER);
            baseRootWord.setSecondRadical(replacementLetter);
        }

        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
