package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.arabic.model.NamedTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author sali
 */
@Configuration
public class BuilderConfiguration {

    @Bean
    @FormBuilderType(NamedTemplate.FORM_II_TEMPLATE)
    @Scope("prototype")
    FormBuilder formIIBuilder() {
        return new FormIIBuilder();
    }

    @Bean
    @FormBuilderType(NamedTemplate.FORM_III_TEMPLATE)
    @Scope("prototype")
    FormBuilder formIIIBuilder() {
        return new FormIIIBuilder();
    }
}
