package com.alphasystem.app.morphologicalengine.conjugation.builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author sali
 */
@Configuration
public class BuilderConfiguration {

    @Bean
    @Scope("prototype")
    ConjugationBuilder formBuilder(){
        return new ConjugationBuilder();
    }
}
