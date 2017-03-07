package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.factory;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.Transformer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sali
 */
public class MasculineBasedFemininePluralTransformerFactory implements NounTransformerFactory {

    @Autowired
    @Transformer(Transformer.TransformerType.MASCULINE_ENDING_SOUND_TRANSFORMER)
    private NounTransformer singularTransformer;

    @Autowired
    @Transformer(Transformer.TransformerType.MASCULINE_DUAL_TRANSFORMER)
    private NounTransformer dualTransformer;

    @Autowired
    @Transformer(Transformer.TransformerType.FEMININE_PLURAL_TRANSFORMER)
    private NounTransformer pluralTransformer;

    @Override
    public NounTransformer singularTransformer() {
        return singularTransformer;
    }

    @Override
    public NounTransformer dualTransformer() {
        return dualTransformer;
    }

    @Override
    public NounTransformer pluralTransformer() {
        return pluralTransformer;
    }
}
