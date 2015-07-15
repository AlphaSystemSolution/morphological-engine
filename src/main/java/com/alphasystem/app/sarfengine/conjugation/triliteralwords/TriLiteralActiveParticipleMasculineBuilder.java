/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.arabic.model.DiacriticType.*;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
public class TriLiteralActiveParticipleMasculineBuilder extends
        TriLiteralParticipleBuilder {

    protected int variableLetterIndex = -1;

    @Override
    protected ArabicWord doAccusativeDual() {
        return new ArabicWord(rootWord).replaceDiacritic(variableLetterIndex,
                FATHA).append(YA_WITH_SUKUN, NOON_WITH_KASRA);
    }

    @Override
    protected ArabicWord doAccusativePlural() {
        return new ArabicWord(rootWord).replaceDiacritic(variableLetterIndex,
                KASRA).append(YA_WITH_SUKUN, NOON_WITH_FATHA);
    }

    @Override
    protected ArabicWord doAccusativeSigular() {
        return new ArabicWord(rootWord).replaceDiacritic(variableLetterIndex,
                FATHATAN).append(LETTER_ALIF);
    }

    @Override
    protected ArabicWord doGenitiveSigular() {
        return new ArabicWord(rootWord).replaceDiacritic(variableLetterIndex,
                KASRATAN);
    }

    @Override
    protected ArabicWord doNominativeDual() {
        return new ArabicWord(rootWord).replaceDiacritic(variableLetterIndex,
                FATHA).append(LETTER_ALIF, NOON_WITH_KASRA);
    }

    @Override
    protected ArabicWord doNominativePlural() {
        return new ArabicWord(rootWord).replaceDiacritic(variableLetterIndex,
                DAMMA).append(WAW_WITH_SUKUN, NOON_WITH_FATHA);
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_MASCULINE;
    }

    public int getVariableLetterIndex() {
        if (variableLetterIndex <= -1) {
            setVariableLetterIndex(-1);
        }
        return variableLetterIndex;
    }

    public void setVariableLetterIndex(int variableLetterIndex) {
        this.variableLetterIndex = variableLetterIndex <= -1 ? getThirdRadicalIndex()
                : variableLetterIndex;

    }

    @Override
    protected void postInit() {
        getVariableLetterIndex();
        super.postInit();
    }

}
