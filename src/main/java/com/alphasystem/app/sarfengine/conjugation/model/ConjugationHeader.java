/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicLetters.LETTER_COMMA;
import static com.alphasystem.arabic.model.ArabicWord.*;

/**
 * @author sali
 */
public class ConjugationHeader {

    private static final ArabicWord WEIGHT_LABEL = getWord(WAW, ZAIN, NOON);

    private final ArabicLetterType[] rootLetters;
    private final String translation;
    private final ArabicWord baseWord;
    private final ChartMode chartMode;
    private ArabicWord typeLabel1;
    private ArabicWord typeLabel2;
    private ArabicWord typeLabel3;

    /**
     * @param translation
     * @param baseWord
     * @param chartMode
     */
    public ConjugationHeader(String translation, ArabicWord baseWord,
                             ChartMode chartMode, ArabicLetterType... rootLetters) {
        this.translation = translation;
        this.baseWord = baseWord;
        this.chartMode = chartMode;
        this.rootLetters = rootLetters;
        initLabels();
    }

    public ArabicWord getBaseWord() {
        return baseWord;
    }

    public ChartMode getChartMode() {
        return chartMode;
    }

    public ArabicLetterType[] getRootLetters() {
        return rootLetters;
    }

    public String getTranslation() {
        return translation;
    }

    public ArabicWord getTypeLabel1() {
        return typeLabel1;
    }

    public ArabicWord getTypeLabel2() {
        return typeLabel2;
    }

    public ArabicWord getTypeLabel3() {
        return typeLabel3;
    }

    private void initLabels() {
        if (chartMode != null) {
            typeLabel1 = chartMode.getTemplate().getType();
            typeLabel2 = concatenateWithSpace(WEIGHT_LABEL, baseWord);
            VerbType verbType = chartMode.getVerbType();
            WeakVerbType weakVerbType = chartMode.getWeakVerbType();
            ArabicWord label = verbType.getLabel();
            typeLabel3 = weakVerbType == null ? label : concatenateWithSpace(
                    concatenate(label, new ArabicWord(LETTER_COMMA)),
                    weakVerbType.getLabel());
        }
    }
}
