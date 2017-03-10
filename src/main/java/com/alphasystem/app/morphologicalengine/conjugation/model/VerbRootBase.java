package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;

/**
 * @author sali
 */
public final class VerbRootBase implements RootBase {

    private final Verb root;
    private final VerbTransformerFactoryType.Type type;

    public VerbRootBase(VerbTransformerFactoryType.Type type, Verb root) {
        this.type = type;
        this.root = root;
    }

    public VerbTransformerFactoryType.Type getType() {
        return type;
    }

    public Verb getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return (root == null) ? super.toString() : root.name();
    }
}
