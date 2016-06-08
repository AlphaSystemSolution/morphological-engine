package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.Transformer;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * Interface to transform given {@link RootWord} into different case.
 *
 * @author sali
 */
public interface NounTransformer extends Transformer<NounConjugation> {

}
