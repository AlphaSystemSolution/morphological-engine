package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.factory;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.factory.TransformerFactory.TransformerFactoryType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sali
 */
@Configuration
public class NounTransformerFactoryConfiguration {

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_I_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_I_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formIMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_I_CATEGORY_U_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory FormICategoryUMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_II_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIIMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_II_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formIIMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_III_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIIIMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_III_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formIIIMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_IV_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIVMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_IV_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formIVMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_V_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formVMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_V_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formVMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VI_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formVIMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VI_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formVIMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VII_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory FormVIIMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VIII_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory FormVIIIMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VIII_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory FormVIIIMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_IX_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIXMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_X_MASCULINE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formXMasculineActiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_X_MASCULINE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formXMasculinePassiveParticiple() {
        return new MasculineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_I_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_I_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formIFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_I_CATEGORY_U_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formICategoryUFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_II_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIIFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_II_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formIIFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_III_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIIIFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_III_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formIIIFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_IV_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formIVFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_IV_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formIVFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_V_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formVFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_V_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formVFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VI_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory formVIFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VI_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formVIFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VII_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory FormVIIFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VII_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formVIIFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VIII_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory FormVIIIFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_VIII_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formVIIIFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_IX_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory FormIXFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_X_FEMININE_ACTIVE_PARTICIPLE)
    NounTransformerFactory FormXFeminineActiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.FORM_X_FEMININE_PASSIVE_PARTICIPLE)
    NounTransformerFactory formXFemininePassiveParticiple() {
        return new FeminineSoundPluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V1)
    NounTransformerFactory VerbalNounV1() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V2)
    NounTransformerFactory VerbalNounV2() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V3)
    NounTransformerFactory VerbalNounV3() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V4)
    NounTransformerFactory VerbalNounV4() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V5)
    NounTransformerFactory VerbalNounV5() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V6)
    NounTransformerFactory VerbalNounV6() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V7)
    NounTransformerFactory VerbalNounV7() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V8)
    NounTransformerFactory VerbalNounV8() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V9)
    NounTransformerFactory VerbalNounV9() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V10)
    NounTransformerFactory VerbalNounV10() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V11)
    NounTransformerFactory VerbalNounV11() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V12)
    NounTransformerFactory VerbalNounV12() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V13)
    NounTransformerFactory VerbalNounV13() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V14)
    NounTransformerFactory VerbalNounV14() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V15)
    NounTransformerFactory VerbalNounV15() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V27)
    NounTransformerFactory VerbalNounV27() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_V28)
    NounTransformerFactory VerbalNounV28() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_II)
    NounTransformerFactory VerbalNounFormII() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_III_V1)
    NounTransformerFactory VerbalNounFormIIIV1() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_III_V2)
    NounTransformerFactory VerbalNounFormIIIV2() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_IV)
    NounTransformerFactory VerbalNounFormIV() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_V)
    NounTransformerFactory VerbalNounFormV() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_VI)
    NounTransformerFactory VerbalNounFormVI() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_VII)
    NounTransformerFactory VerbalNounFormVII() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_VIII)
    NounTransformerFactory VerbalNounFormVIII() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_IX)
    NounTransformerFactory VerbalNounFormIX() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.VERBAL_NOUN_FORM_X)
    NounTransformerFactory VerbalNounFormX() {
        return new MasculineBasedFemininePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.NOUN_OF_PLACE_AND_TIME_V1)
    NounTransformerFactory NounOfPlaceAndTimeV1() {
        return new MasculineBasedPartlyFlexiblePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.NOUN_OF_PLACE_AND_TIME_V2)
    NounTransformerFactory NounOfPlaceAndTimeV2() {
        return new MasculineBasedPartlyFlexiblePluralTransformerFactory();
    }

    @Bean
    @TransformerFactory(TransformerFactoryType.NOUN_OF_PLACE_AND_TIME_V3)
    NounTransformerFactory NounOfPlaceAndTimeV3() {
        return new FeminineSoundPluralTransformerFactory();
    }
}
