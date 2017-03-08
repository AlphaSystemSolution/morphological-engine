package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sali
 */
@Configuration
public class VerbTransformerConfiguration {

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer pastTenseThirdPersonMasculineTransformer(){
        return new PastTenseThirdPersonMasculineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer pastTenseThirdPersonFeminineTransformer(){
        return new PastTenseThirdPersonFeminineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer pastTenseSecondPersonMasculineTransformer(){
        return new PastTenseSecondPersonMasculineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer pastTenseSecondPersonFeminineTransformer(){
        return new PastTenseSecondPersonFeminineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PAST_TENSE_FIRST_PERSON_TRANSFORMER)
    VerbTransformer pastTenseFirstPersonTransformer(){
        return new PastTenseFirstPersonTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer presentTenseThirdPersonMasculineTransformer(){
        return new PresentTenseThirdPersonMasculineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer presentTenseThirdPersonFeminineTransformer(){
        return new PresentTenseThirdPersonFeminineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer presentTenseSecondPersonMasculineTransformer(){
        return new PresentTenseSecondPersonMasculineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer presentTenseSecondPersonFeminineTransformer(){
        return new PresentTenseSecondPersonFeminineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.PRESENT_TENSE_FIRST_PERSON_TRANSFORMER)
    VerbTransformer presentTenseFirstPersonTransformer(){
        return new PresentTenseFirstPersonTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer forbiddingSecondPersonMasculineTransformer(){
        return new ForbiddingSecondPersonMasculineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.FORBIDDING_SECOND_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer forbiddingSecondPersonFeminineTransformer(){
        return new ForbiddingSecondPersonFeminineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    VerbTransformer imperativeSecondPersonMasculineTransformer(){
        return new ImperativeSecondPersonMasculineTransformer();
    }

    @Bean
    @VerbTransformerType(VerbTransformerType.Type.IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER)
    VerbTransformer imperativeSecondPersonFeminineTransformer(){
        return new ImperativeSecondPersonFeminineTransformer();
    }
}
