package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.Transformer;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * Interface to transform given {@link RootWord} into different case.
 *
 * @author sali
 */
public interface VerbTransformer extends Transformer<VerbConjugation> {

}
