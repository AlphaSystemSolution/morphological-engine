package com.alphasystem.app.morphologicalengine.conjugation.rule.processor;

import com.alphasystem.app.morphologicalengine.conjugation.model.WordStatus;
import com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.arabic.model.ArabicLetters.ALIF_HAMZA_ABOVE_WITH_DAMMA;
import static com.alphasystem.arabic.model.ArabicLetters.ALIF_HAMZA_ABOVE_WITH_FATHA;
import static com.alphasystem.arabic.model.ArabicLetters.ALIF_HAMZA_BELOW_WITH_KASRA;
import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.arabic.model.DiacriticType.SHADDA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_IV_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_VII_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.IMPERATIVE;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public class ImperativeProcessor extends AbstractRuleProcessor {

    public ImperativeProcessor(final RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public RootWord applyRules(RootWord baseRootWord) {
        final SarfTermType sarfTermType = baseRootWord.getSarfTermType();
        if (!IMPERATIVE.equals(sarfTermType)) {
            return baseRootWord;
        }
        ArabicLetter imperativeLetter = FORM_IV_TEMPLATE.equals(ruleInfo.getTemplate()) ? ALIF_HAMZA_ABOVE_WITH_FATHA :
                getImperativeLetter(baseRootWord);
        final ArabicWord arabicWord = baseRootWord.getRootWord().remove(0);
        final ArabicLetter firstLetter = arabicWord.getFirstLetter();
        final DiacriticType[] diacritics = firstLetter.getDiacritics();
        DiacriticType firstLetterDiacritics = isEmpty(diacritics) ? SUKUN : firstLetter.getDiacritics()[0];
        if ((SHADDA.equals(firstLetterDiacritics) || SUKUN.equals(firstLetterDiacritics)) && imperativeLetter != null) {
            arabicWord.preppend(imperativeLetter);
        }
        return baseRootWord;
    }

    private ArabicLetter getImperativeLetter(RootWord rootWord) {
        ArabicLetter imperativeLetter = null;

        ArabicLetter secondRadical = rootWord.getSecondRadical();
        DiacriticType[] diacritics;
        DiacriticType secondRadicalDiacritic;
        DiacriticType imperativeDiacritic;
        if (secondRadical != null) {
            diacritics = secondRadical.getDiacritics();
            secondRadicalDiacritic = isEmpty(diacritics) ? FATHA : diacritics[0];
            imperativeDiacritic = secondRadicalDiacritic.equals(DAMMA) ? DAMMA : KASRA;
            imperativeLetter = imperativeDiacritic.equals(DAMMA) ? ALIF_HAMZA_ABOVE_WITH_DAMMA
                    : ALIF_HAMZA_BELOW_WITH_KASRA;
            ArabicLetter firstRadical = rootWord.getFirstRadical();
            diacritics = firstRadical.getDiacritics();
            DiacriticType firstLetterDiacritics = isEmpty(diacritics) ? SUKUN : firstRadical.getDiacritics()[0];
            if (!firstLetterDiacritics.equals(SUKUN) && !firstLetterDiacritics.equals(SHADDA)) {
                imperativeLetter = null;
            }
        }

        final WordStatus wordStatus = ruleInfo.getWordStatus();
        if (wordStatus.isFirstRadicalHamza() && FORM_I_CATEGORY_A_GROUP_U_TEMPLATE.equals(ruleInfo.getTemplate())) {
            imperativeLetter = null;
        } else if (FORM_VII_TEMPLATE.equals(ruleInfo.getTemplate())) {
            imperativeLetter = ALIF_HAMZA_BELOW_WITH_KASRA;
        }

        return imperativeLetter;
    }
}
