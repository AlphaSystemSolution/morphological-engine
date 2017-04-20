package com.alphasystem.app.morphologicalengine.conjugation.builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sali
 */
@Configuration
public class BuilderConfiguration {

    @Bean
    ConjugationBuilder formBuilder() {
        return new ConjugationBuilder(abbreviatedConjugationBuilder(), detailedConjugationBuilder());
    }

    @Bean
    AbbreviatedConjugationBuilder abbreviatedConjugationBuilder() {
        return new AbbreviatedConjugationBuilder();
    }

    @Bean
    DetailedConjugationBuilder detailedConjugationBuilder() {
        return new DetailedConjugationBuilder();
    }
}
