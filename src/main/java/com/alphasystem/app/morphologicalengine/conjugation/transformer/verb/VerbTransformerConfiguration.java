package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerType.Type.*;

/**
 * @author sali
 */
@Configuration
public class VerbTransformerConfiguration {

    @Bean("PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER")
    @VerbTransformerType(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer pastTenseThirdPersonMasculineTransformer() {
        return new PastTenseThirdPersonMasculineTransformer();
    }

    @Bean("PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER")
    @VerbTransformerType(PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer pastTenseThirdPersonFeminineTransformer() {
        return new PastTenseThirdPersonFeminineTransformer();
    }

    @Bean("PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER")
    @VerbTransformerType(PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer pastTenseSecondPersonMasculineTransformer() {
        return new PastTenseSecondPersonMasculineTransformer();
    }

    @Bean("PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER")
    @VerbTransformerType(PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer pastTenseSecondPersonFeminineTransformer() {
        return new PastTenseSecondPersonFeminineTransformer();
    }

    @Bean("PAST_TENSE_FIRST_PERSON_TRANSFORMER")
    @VerbTransformerType(PAST_TENSE_FIRST_PERSON_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer pastTenseFirstPersonTransformer() {
        return new PastTenseFirstPersonTransformer();
    }

    @Bean("PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER")
    @VerbTransformerType(PRESENT_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer presentTenseThirdPersonMasculineTransformer() {
        return new PresentTenseThirdPersonMasculineTransformer();
    }

    @Bean("PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER")
    @VerbTransformerType(PRESENT_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer presentTenseThirdPersonFeminineTransformer() {
        return new PresentTenseThirdPersonFeminineTransformer();
    }

    @Bean("PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER")
    @VerbTransformerType(PRESENT_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer presentTenseSecondPersonMasculineTransformer() {
        return new PresentTenseSecondPersonMasculineTransformer();
    }

    @Bean("PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER")
    @VerbTransformerType(PRESENT_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer presentTenseSecondPersonFeminineTransformer() {
        return new PresentTenseSecondPersonFeminineTransformer();
    }

    @Bean("PRESENT_TENSE_FIRST_PERSON_TRANSFORMER")
    @VerbTransformerType(PRESENT_TENSE_FIRST_PERSON_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer presentTenseFirstPersonTransformer() {
        return new PresentTenseFirstPersonTransformer();
    }

    @Bean("FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER")
    @VerbTransformerType(FORBIDDING_SECOND_PERSON_MASCULINE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer forbiddingSecondPersonMasculineTransformer() {
        return new ForbiddingSecondPersonMasculineTransformer();
    }

    @Bean("FORBIDDING_SECOND_PERSON_FEMININE_TRANSFORMER")
    @VerbTransformerType(FORBIDDING_SECOND_PERSON_FEMININE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer forbiddingSecondPersonFeminineTransformer() {
        return new ForbiddingSecondPersonFeminineTransformer();
    }

    @Bean("IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER")
    @VerbTransformerType(IMPERATIVE_SECOND_PERSON_MASCULINE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer imperativeSecondPersonMasculineTransformer() {
        return new ImperativeSecondPersonMasculineTransformer();
    }

    @Bean("IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER")
    @VerbTransformerType(IMPERATIVE_SECOND_PERSON_FEMININE_TRANSFORMER)
    @Scope("prototype")
    VerbTransformer imperativeSecondPersonFeminineTransformer() {
        return new ImperativeSecondPersonFeminineTransformer();
    }
}
