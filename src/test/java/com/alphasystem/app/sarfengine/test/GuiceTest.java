package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.FemininePluralTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.MasculineEndingSoundTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NonFlexibleNounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import org.testng.annotations.Test;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerModule.MASCULINE_ENDING_SOUND_TRANSFORMER;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerModule.NON_FLEXIBLE_NOUN_TRANSFORMER;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.Noun.FORM_I_FEMININE_ACTIVE_PARTICIPLE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.Noun.FORM_I_MASCULINE_ACTIVE_PARTICIPLE;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class GuiceTest extends CommonTest {

    private static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();

    @Test
    public void testGetInstance() {
        testGetInstance(NounTransformer.class, MASCULINE_ENDING_SOUND_TRANSFORMER, MasculineEndingSoundTransformer.class);
        testGetInstance(NounTransformer.class, NON_FLEXIBLE_NOUN_TRANSFORMER, NonFlexibleNounTransformer.class);
        testGetInstance(NounTransformer.class, FORM_I_FEMININE_ACTIVE_PARTICIPLE.getPluralRootName(), FemininePluralTransformer.class);
        testGetInstance(NounTransformer.class, FORM_I_MASCULINE_ACTIVE_PARTICIPLE.getSingularRootName(), MasculineEndingSoundTransformer.class);

//        testGetInstance(VerbTransformer.class, PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER, PastTenseThirdPersonMasculineTransformer.class);
    }

    private <T> void testGetInstance(Class<T> type, String name, Class<? extends T> expectedType) {
        final T instance = GUICE_SUPPORT.getInstance(type, name);
        assertNotNull(instance);
        assertEquals(instance.getClass().getName(), expectedType.getName());
        log(format("Instance found \"%s\" with name \"%s\" and instance \"%s\".", instance.getClass().getName(),
                name, instance), true);
    }
}
