package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;

import java.util.Objects;

/**
 * @author sali
 */
public final class NounRootBase implements RootBase {

    private final NounSupport singularBaseWord;
    private final NounSupport dualBaseWord;
    private final NounSupport pluralBaseWord;
    private final NounTransformerFactoryType.Type type;

    /**
     * Copy constructor.
     *
     * @param src source object to copy values from.
     * @throws NullPointerException if <ode>src</ode> is null
     */
    public NounRootBase(NounRootBase src) {
        Objects.requireNonNull(src);
        this.type = src.getType();
        this.singularBaseWord = src.getSingularBaseWord();
        this.dualBaseWord = src.getDualBaseWord();
        this.pluralBaseWord = src.getPluralBaseWord();
    }

    public NounRootBase(NounTransformerFactoryType.Type type, NounSupport singularBaseWord) {
        this(type, singularBaseWord, singularBaseWord);
    }

    public NounRootBase(NounTransformerFactoryType.Type type, NounSupport singularBaseWord, NounSupport pluralBaseWord) {
        this(type, singularBaseWord, singularBaseWord, pluralBaseWord);
    }

    public NounRootBase(NounTransformerFactoryType.Type type, NounSupport singularBaseWord, NounSupport dualBaseWord,
                        NounSupport pluralBaseWord) {
        this.type = type;
        this.singularBaseWord = singularBaseWord;
        this.dualBaseWord = dualBaseWord;
        this.pluralBaseWord = pluralBaseWord;
    }

    public NounTransformerFactoryType.Type getType() {
        return type;
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
