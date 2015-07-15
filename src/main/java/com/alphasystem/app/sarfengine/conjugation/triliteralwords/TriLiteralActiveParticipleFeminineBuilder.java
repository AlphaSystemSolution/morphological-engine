/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
public class TriLiteralActiveParticipleFeminineBuilder extends
        TriLiteralParticipleBuilder {

    protected int variableLetterIndex = -1;

    @Override
    protected ArabicWord doAccusativeDual() {
        return new ArabicWord(rootWord).removeLast().append(TA_WITH_FATHA, YA_WITH_SUKUN,
                NOON_WITH_KASRA);
    }

    @Override
    protected ArabicWord doAccusativePlural() {
        return new ArabicWord(rootWord).replaceDiacritic(variableLetterIndex, FATHA)
                .removeLast().append(LETTER_ALIF, TA_WITH_KASRATAN);
    }

    @Override
    protected ArabicWord doAccusativeSigular() {
        return new ArabicWord(rootWord).removeLast().append(TA_MARBUTA_WITH_FATHATAN);
    }

    @Override
    protected ArabicWord doGenitiveSigular() {
        return new ArabicWord(rootWord).removeLast().append(TA_MARBUTA_WITH_KASRATAN);
    }

    @Override
    protected ArabicWord doNominativeDual() {
        return new ArabicWord(rootWord).removeLast().append(TA_WITH_FATHA, LETTER_ALIF,
                NOON_WITH_KASRA);
    }

    @Override
    protected ArabicWord doNominativePlural() {
        return new ArabicWord(rootWord).replaceDiacritic(variableLetterIndex, FATHA)
                .removeLast().append(LETTER_ALIF, TA_WITH_DAMMATAN);
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_FEMININE;
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
