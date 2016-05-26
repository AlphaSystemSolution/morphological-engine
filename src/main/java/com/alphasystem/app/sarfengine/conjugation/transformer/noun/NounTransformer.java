package com.alphasystem.app.sarfengine.conjugation.transformer.noun;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * Interface to transform given {@link RootWord} into different case.
 *
 * @author sali
 */
public interface NounTransformer {

    /**
     * Transforms to nominative form.
     *
     * @return transformed {@link RootWord} in nominative form
     */
    RootWord doNominative();

    /**
     * Transforms to accusative form.
     *
     * @return transformed {@link RootWord} in accusative form
     */
    RootWord doAccusative();

    /**
     * Transforms to genitive form.
     *
     * @return transformed {@link RootWord} in genitive form
     */
    RootWord doGenitive();
}
