package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;

/**
 * @author sali
 */
public interface NounTransformerFactory {

    /**
     * Returns {@link NounTransformer} for <i>singular</i> conjugation.
     *
     * @return {@link NounTransformer} for <i>singular</i> conjugation.
     */
    NounTransformer singularTransformer();

    /**
     * Returns {@link NounTransformer} for <i>dual</i> conjugation.
     *
     * @return {@link NounTransformer} for <i>dual</i> conjugation.
     */
    NounTransformer dualTransformer();

    /**
     * Returns {@link NounTransformer} for <i>plural</i> conjugation.
     *
     * @return {@link NounTransformer} for <i>plural</i> conjugation.
     */
    NounTransformer pluralTransformer();
}
