/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.SarfMemberType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import org.apache.commons.lang3.ArrayUtils;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorHelper.checkArgument;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF_MAKSURA;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.FORBIDDING;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_PASSIVE_TENSE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_TENSE;

;

/**
 * @author sali
 */
public class Rule10Processor extends AbstractRuleProcessor {

    public Rule10Processor(final RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        try {
            checkArgument(baseRootWord, null, new SarfTermType[]{IMPERATIVE, FORBIDDING});
        } catch (IllegalArgumentException e) {
            return baseRootWord;
        }
        final WordStatus wordStatus = ruleInfo.getWordStatus();
        if (!wordStatus.isDefective()) {
            return baseRootWord;
        }
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        boolean imperfect = PRESENT_TENSE.equals(sarfTermType)
                || PRESENT_PASSIVE_TENSE.equals(sarfTermType);
        SarfMemberType memberType = baseRootWord.getMemberType();
        ArabicLetter secondRadical = baseRootWord.getSecondRadical();
        DiacriticType secondRadicalDiacritic = RuleProcessorHelper.getDiacritic(secondRadical);
        boolean secondRadicalHasFatha = RuleProcessorHelper.isFatha(secondRadicalDiacritic);
        ArabicLetter thirdRadical = baseRootWord.getThirdRadical();
        boolean weakYa = thirdRadical.getLetter().equals(YA);
        int thirdRadicalIndex = baseRootWord.getThirdRadicalIndex();
        boolean secondRadicalIsDamma = RuleProcessorHelper.isDamma(secondRadicalDiacritic);
        @SuppressWarnings("unused")
        DiacriticType thirdRadicalDiacritic = RuleProcessorHelper.getDiacritic(thirdRadical);
        if (imperfect) {
            ArabicLetter replacementLetter = thirdRadical;
            if (ArrayUtils.contains(RuleProcessorHelper.IMPERFECT_SINGULAR, memberType)) {
                boolean kasraOrDamma = RuleProcessorHelper.isKasra(secondRadicalDiacritic)
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
