package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sali
 */
public class ImperativeTransformerFactory extends AbstractVerbTransformerFactory {

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    private VerbTransformer thirdPersonMasculineTransformer;

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER)
    private VerbTransformer thirdPersonFeminineTransformer;

    @Override
    public VerbTransformer thirdPersonMasculineTransformer() {
        return thirdPersonFeminineTransformer();
    }

    @Override
    public VerbTransformer thirdPersonFeminineTransformer() {
        return thirdPersonFeminineTransformer;
    }

    @Override
    public VerbTransformer secondPersonMasculineTransformer() {
        return null;
    }

    @Override
    public VerbTransformer secondPersonFeminineTransformer() {
        return null;
    }

    @Override
    public VerbTransformer firstPersonTransformer() {
        return null;
    }
}
