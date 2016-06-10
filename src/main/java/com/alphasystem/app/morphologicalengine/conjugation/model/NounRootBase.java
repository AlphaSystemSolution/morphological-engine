package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;

/**
 * @author sali
 */
public class NounRootBase {

    protected final NounSupport singularBaseWord;
    protected final NounSupport dualBaseWord;
    protected final NounSupport pluralBaseWord;

    public NounRootBase(NounSupport singularBaseWord) {
        this(singularBaseWord, singularBaseWord);
    }

    public NounRootBase(NounSupport singularBaseWord, NounSupport pluralBaseWord) {
        this(singularBaseWord, singularBaseWord, pluralBaseWord);
    }

    public NounRootBase(NounSupport singularBaseWord, NounSupport dualBaseWord, NounSupport pluralBaseWord) {
        this.singularBaseWord = singularBaseWord;
        this.dualBaseWord = dualBaseWord;
        this.pluralBaseWord = pluralBaseWord;
    }

    public NounSupport getSingularBaseWord() {
        return singularBaseWord;
    }

    public NounSupport getDualBaseWord() {
        return dualBaseWord;
    }

    public NounSupport getPluralBaseWord() {
        return pluralBaseWord;
    }

}
