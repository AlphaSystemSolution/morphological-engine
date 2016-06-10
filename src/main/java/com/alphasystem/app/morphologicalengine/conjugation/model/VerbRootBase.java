package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;

/**
 * @author sali
 */
public final class VerbRootBase {

    private final Verb root;

    public VerbRootBase(Verb root) {
        this.root = root;
    }

    public Verb getRoot() {
        return root;
    }
}
