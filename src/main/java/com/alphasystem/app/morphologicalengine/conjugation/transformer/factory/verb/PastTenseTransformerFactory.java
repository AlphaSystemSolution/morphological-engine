package com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author sali
 */
public class PastTenseTransformerFactory extends AbstractVerbTransformerFactory {

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)
    private VerbTransformer thirdPersonMasculineTransformer;

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)
    private VerbTransformer thirdPersonFeminineTransformer;

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    private VerbTransformer secondPersonMasculineTransformer;

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)
    private VerbTransformer secondPersonFeminineTransformer;

    @Autowired
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_FIRST_PERSON_TRANSFORMER)
    private VerbTransformer firstPersonTransformer;

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
        return secondPersonMasculineTransformer;
    }

    @Override
    public VerbTransformer secondPersonFeminineTransformer() {
        return secondPersonFeminineTransformer;
    }

    @Override
    public VerbTransformer firstPersonTransformer() {
        return firstPersonTransformer;
    }
}
