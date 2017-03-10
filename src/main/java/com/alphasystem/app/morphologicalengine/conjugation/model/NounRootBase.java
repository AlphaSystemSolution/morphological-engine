package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.util.IdGenerator;

import java.util.Objects;

/**
 * @author sali
 */
public final class NounRootBase implements RootBase {

    private final NounSupport singularBaseWord;
    private final NounSupport dualBaseWord;
    private final NounSupport pluralBaseWord;
    private final String id;

    /**
     * Copy constructor.
     *
     * @param src source object to copy values from.
     * @throws NullPointerException if <ode>src</ode> is null
     */
    public NounRootBase(NounRootBase src) {
        Objects.requireNonNull(src);
        this.id = IdGenerator.nextId();
        this.singularBaseWord = src.getSingularBaseWord();
        this.dualBaseWord = src.getDualBaseWord();
        this.pluralBaseWord = src.getPluralBaseWord();
    }

    public NounRootBase(NounSupport singularBaseWord) {
        this(singularBaseWord, singularBaseWord);
    }

    public NounRootBase(NounSupport singularBaseWord, NounSupport pluralBaseWord) {
        this(singularBaseWord, singularBaseWord, pluralBaseWord);
    }

    public NounRootBase(NounSupport singularBaseWord, NounSupport dualBaseWord, NounSupport pluralBaseWord) {
        this.id = IdGenerator.nextId();
        this.singularBaseWord = singularBaseWord;
        this.dualBaseWord = dualBaseWord;
        this.pluralBaseWord = pluralBaseWord;
    }

    @Override
    public String getId() {
        return id;
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
