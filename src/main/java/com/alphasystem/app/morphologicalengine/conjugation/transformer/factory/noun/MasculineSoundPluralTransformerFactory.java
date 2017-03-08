package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sali
 */
public class MasculineSoundPluralTransformerFactory implements NounTransformerFactory {

    @Autowired
    @NounTransformerType(NounTransformerType.Type.MASCULINE_ENDING_SOUND_TRANSFORMER)
    private NounTransformer singularTransformer;

    @Autowired
    @NounTransformerType(NounTransformerType.Type.MASCULINE_DUAL_TRANSFORMER)
    private NounTransformer dualTransformer;

    @Autowired
    @NounTransformerType(NounTransformerType.Type.MASCULINE_PLURAL_TRANSFORMER)
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
