package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;
import com.alphasystem.util.IdGenerator;

/**
 * @author sali
 */
public final class VerbRootBase implements RootBase {

    private final Verb root;
    private final String id;

    public VerbRootBase(Verb root) {
        this.root = root;
        this.id = IdGenerator.nextId();
    }

    @Override
    public String getId() {
        return id;
    }

    public Verb getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return (root == null) ? super.toString() : root.name();
    }
}
