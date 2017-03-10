package com.alphasystem.app.morphologicalengine.conjugation.builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.alphasystem.arabic.model.NamedTemplate.FORM_III_TEMPLATE;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_II_TEMPLATE;

/**
 * @author sali
 */
@Configuration
public class BuilderConfiguration {

    @Bean("FORM_II_TEMPLATE")
    @FormBuilderType(FORM_II_TEMPLATE)
    @Scope("prototype")
    FormBuilder formIIBuilder() {
        return new FormIIBuilder();
    }

    @Bean("FORM_III_TEMPLATE")
    @FormBuilderType(FORM_III_TEMPLATE)
    @Scope("prototype")
    FormBuilder formIIIBuilder() {
        return new FormIIIBuilder();
    }
}
