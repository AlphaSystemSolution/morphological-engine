package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sali
 */
public class ForbiddingTransformerFactory extends AbstractVerbTransformerFactory {

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    private VerbTransformer secondPersonMasculineTransformer;

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER)
    private VerbTransformer secondPersonFeminineTransformer;


    @Override
    public VerbTransformer thirdPersonMasculineTransformer() {
        return null;
    }

    @Override
    public VerbTransformer thirdPersonFeminineTransformer() {
        return null;
    }

    @Override
    public VerbTransformer secondPersonMasculineTransformer() {
        return secondPersonMasculineTransformer;
    }

    @Override
    public VerbTransformer secondPersonFeminineTransformer() {
        return secondPersonFeminineTransformer;
    }

    @Override
    public VerbTransformer firstPersonTransformer() {
        return null;
    }
}
