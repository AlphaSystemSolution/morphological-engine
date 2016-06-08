/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.*;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.*;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static org.apache.commons.lang3.ArrayUtils.contains;

;

/**
 * @author sali
 */
public class Rule10Processor extends AbstractRuleProcessor {

    @AssistedInject
    public Rule10Processor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, null, new SarfTermType[]{IMPERATIVE,
                    FORBIDDING});
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        WordStatus wordStatus = new WordStatus(baseRootWord);
        if (!wordStatus.isDefective()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        boolean imperfect = PRESENT_TENSE.equals(sarfTermType)
                || PRESENT_PASSIVE_TENSE.equals(sarfTermType);
        SarfMemberType memberType = baseRootWord.getMemberType();
        ArabicLetter secondRadical = baseRootWord.getSecondRadical();
        DiacriticType secondRadicalDiacritic = getDiacritic(secondRadical);
        boolean secondRadicalHasFatha = isFatha(secondRadicalDiacritic);
        ArabicLetter thirdRadical = baseRootWord.getThirdRadical();
        boolean weakYa = thirdRadical.getLetter().equals(YA);
        int thirdRadicalIndex = baseRootWord.getThirdRadicalIndex();
        boolean secondRadicalIsDamma = isDamma(secondRadicalDiacritic);
        @SuppressWarnings("unused")
        DiacriticType thirdRadicalDiacritic = getDiacritic(thirdRadical);
        if (imperfect) {
            ArabicLetter replacementLetter = thirdRadical;
            if (contains(IMPERFECT_SINGULAR, memberType)) {
                boolean kasraOrDamma = isKasra(secondRadicalDiacritic)
                        || secondRadicalIsDamma;
                ArabicLetterType thirdRadicalLetter = secondRadicalHasFatha
                        || (kasraOrDamma && weakYa) ? ALIF_MAKSURA : WAW;
                result.replaceLetter(thirdRadicalIndex, new ArabicLetter(
                        thirdRadicalLetter, SUKUN));
            }/*
             * else if (!contains(IMPERFECT_DUALS, memberType)) { boolean
			 * mutaharikWeakLetter = isMutaharik(thirdRadicalDiacritic); if
			 * (mutaharikWeakLetter && secondRadicalHasFatha) {
			 * result.replaceLetter(thirdRadicalIndex, LETTER_ALIF); } } else if
			 * (!weakYa && thirdRadicalIndex >= 3 && (!secondRadicalIsDamma ||
			 * isSakin(thirdRadicalDiacritic))) {
			 * result.replaceLetter(thirdRadicalIndex, YA); }
			 */
            replacementLetter = result.getLetter(thirdRadicalIndex);
            baseRootWord.setThirdRadical(replacementLetter);
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
