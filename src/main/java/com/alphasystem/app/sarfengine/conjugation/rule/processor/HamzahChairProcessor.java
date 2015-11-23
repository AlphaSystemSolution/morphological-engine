/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule.processor;

import com.alphasystem.app.sarfengine.conjugation.rule.AbstractRuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper;
import com.alphasystem.arabic.model.*;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorHelper.*;
import static com.alphasystem.app.sarfengine.util.PatternHelper.removeTatweel;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.fromBuckWalterString;
import static com.alphasystem.arabic.model.DiacriticType.*;

/**
 * @author sali
 */
public class HamzahChairProcessor extends AbstractRuleProcessor {

    protected ArabicLetterType hamzahReplacement;

    @AssistedInject
    public HamzahChairProcessor(@Assisted RuleInfo ruleInfo) {
        super(ruleInfo);
    }

    public ArabicLetterType getHamzahReplacement() {
        return hamzahReplacement;
    }

    public void setHamzahReplacement(ArabicLetterType hamzahReplacement) {
        this.hamzahReplacement = hamzahReplacement;
    }

    @Override
    public RootWord applyRules(NamedTemplate template, RootWord baseRootWord) {
        ArabicWord result = new ArabicWord(baseRootWord.getRootWord());
        // we might have "TATWEEL" at this point, so remove it before proceed
        String text = removeTatweel(result.toBuckWalter());
        result = fromBuckWalterString(text);
        int hamzahIndex = -1;
        ArabicLetterType previousLetterType = null;
        DiacriticType previousDiacritic = null;
        int length = result.getLength();
        for (int i = 0; i < length; i++) {
            ArabicLetterType hamzahReplacement = null;
            ArabicLetter currentLetter = result.getLetter(i);
            ArabicLetterType currentLetterType = currentLetter.getLetter();
            DiacriticType currentDiacritic = getDiacriticIncludingShadda(currentLetter);
            if (currentDiacritic == null) {
                currentDiacritic = DiacriticType.ALIF_KHAN_JAREEYA;
            }
            int nextIndex = i + 1;
            ArabicLetter nextLetter = nextIndex >= length ? null : result
                    .getLetter(nextIndex);
            ArabicLetterType nextLetterType = nextLetter == null ? null
                    : nextLetter.getLetter();
            DiacriticType hamzahDiacritic = null;
            hamzahIndex = i;
            if (currentLetterType.equals(HAMZA)) {
                if (i <= 0) {
                    // Hamzah is first letter
                    hamzahReplacement = isKasra(currentDiacritic) ? ALIF_HAMZA_BELOW
                            : ALIF_HAMZA_ABOVE;
                } else {
                    // Hamzah is in the middle
                    if (this.hamzahReplacement != null) {
                        hamzahReplacement = this.hamzahReplacement;
                    } else {
                        boolean lastLetter = i >= length - 1;
                        if (lastLetter) {
                            // last letter
                            if (isSakin(previousDiacritic)
                                    || isLongAlif(previousLetterType)) {
                                continue;
                            }
                        }
                        if (isLongAlif(previousLetterType)
                                || isLongAlif(nextLetterType)) {
                            hamzahReplacement = YA_HAMZA_ABOVE;
                        } else if (currentDiacritic.equals(previousDiacritic)) {
                            hamzahDiacritic = currentDiacritic;
                        } else if (isSakin(currentDiacritic)
                                || isSakin(previousDiacritic)) {
                            hamzahDiacritic = SUKUN.equals(currentDiacritic) ? previousDiacritic
                                    : currentDiacritic;
                        } else if (!currentDiacritic.equals(previousDiacritic)) {
                            if (isKasra(currentDiacritic)
                                    || isKasra(previousDiacritic)) {
                                hamzahDiacritic = KASRA;
                            } else if (isDamma(currentDiacritic)
                                    || isDamma(previousDiacritic)) {
                                hamzahDiacritic = DAMMA;
                            } else if (isFatha(currentDiacritic)
                                    || isFatha(previousDiacritic)) {
                                hamzahDiacritic = FATHA;
                            }
                        }
                        if (lastLetter) {
                            if (isFatha(hamzahDiacritic)) {
                                if (isYa(previousLetterType)) {
                                    hamzahDiacritic = KASRA;
                                }
                            }
                            if (isDamma(hamzahDiacritic)) {
                                hamzahDiacritic = FATHA;
                            }
                        }
                    }
                }
            }
            if (hamzahDiacritic != null && hamzahReplacement == null) {
                hamzahReplacement = RuleProcessorHelper.getHamzahReplacement(hamzahDiacritic);
            }
            if (hamzahReplacement != null) {
                result.replaceLetter(hamzahIndex, hamzahReplacement);
            }

            // update reference
            previousLetterType = currentLetterType;
            previousDiacritic = currentDiacritic;
        }
        baseRootWord.setRootWord(result);
        return baseRootWord;
    }

}
