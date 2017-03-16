package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb;

import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.TransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;

/**
 * @author sali
 */
public interface VerbTransformerFactory extends TransformerFactory<VerbConjugationGroup, VerbRootBase> {

    /**
     * Returns {@link VerbTransformer}  for <i>third person masculine</i> conjugation.
     *
     * @return {@link VerbTransformer}  for <i>third person masculine</i> conjugation
     */
    VerbTransformer thirdPersonMasculineTransformer();

    /**
     * Returns {@link VerbTransformer}  for <i>third person feminine</i> conjugation.
     *
     * @return {@link VerbTransformer}  for <i>third person feminine</i> conjugation
     */
    VerbTransformer thirdPersonFeminineTransformer();

    /**
     * Returns {@link VerbTransformer}  for <i>second person masculine</i> conjugation.
     *
     * @return {@link VerbTransformer}  for <i>second person masculine</i> conjugation
     */
    VerbTransformer secondPersonMasculineTransformer();

    /**
     * Returns {@link VerbTransformer}  for <i>second person feminine</i> conjugation.
     *
     * @return {@link VerbTransformer}  for <i>second person feminine</i> conjugation
     */
    VerbTransformer secondPersonFeminineTransformer();

    /**
     * Returns {@link VerbTransformer}  for <i>first person</i> conjugation.
     *
     * @return {@link VerbTransformer}  for <i>first person</i> conjugation
     */
    VerbTransformer firstPersonTransformer();

}
