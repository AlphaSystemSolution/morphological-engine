package com.alphasystem.app.morphologicalengine.conjugation.builder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static com.alphasystem.arabic.model.NamedTemplate.*;

/**
 * @author sali
 */
@Configuration
public class BuilderConfiguration {

    @Bean("FORM_I_CATEGORY_A_GROUP_U_TEMPLATE")
    @FormBuilderType(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE)
    @Scope("prototype")
    FormBuilder formICategoryAGroupUBuilder() {
        return new FormICategoryAGroupUBuilder();
    }

    @Bean("FORM_I_CATEGORY_A_GROUP_I_TEMPLATE")
    @FormBuilderType(FORM_I_CATEGORY_A_GROUP_I_TEMPLATE)
    @Scope("prototype")
    FormBuilder formICategoryAGroupIBuilder() {
        return new FormICategoryAGroupIBuilder();
    }

    @Bean("FORM_I_CATEGORY_A_GROUP_A_TEMPLATE")
    @FormBuilderType(FORM_I_CATEGORY_A_GROUP_A_TEMPLATE)
    @Scope("prototype")
    FormBuilder formICategoryAGroupABuilder() {
        return new FormICategoryAGroupABuilder();
    }

    @Bean("FORM_I_CATEGORY_U_TEMPLATE")
    @FormBuilderType(FORM_I_CATEGORY_U_TEMPLATE)
    @Scope("prototype")
    FormBuilder formICategoryUBuilder() {
        return new FormICategoryUBuilder();
    }

    @Bean("FORM_I_CATEGORY_I_GROUP_A_TEMPLATE")
    @FormBuilderType(FORM_I_CATEGORY_I_GROUP_A_TEMPLATE)
    @Scope("prototype")
    FormBuilder formICategoryIGroupABuilder() {
        return new FormICategoryIGroupABuilder();
    }

    @Bean("FORM_I_CATEGORY_I_GROUP_I_TEMPLATE")
    @FormBuilderType(FORM_I_CATEGORY_I_GROUP_I_TEMPLATE)
    @Scope("prototype")
    FormBuilder formICategoryIGroupIBuilder() {
        return new FormICategoryIGroupIBuilder();
    }

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

    @Bean("FORM_IV_TEMPLATE")
    @FormBuilderType(FORM_IV_TEMPLATE)
    @Scope("prototype")
    FormBuilder formIVBuilder() {
        return new FormIVBuilder();
    }

    @Bean("FORM_V_TEMPLATE")
    @FormBuilderType(FORM_V_TEMPLATE)
    @Scope("prototype")
    FormBuilder formVBuilder() {
        return new FormVBuilder();
    }

    @Bean("FORM_VI_TEMPLATE")
    @FormBuilderType(FORM_VI_TEMPLATE)
    @Scope("prototype")
    FormBuilder formVIBuilder() {
        return new FormVIBuilder();
    }

    @Bean("FORM_VII_TEMPLATE")
    @FormBuilderType(FORM_VII_TEMPLATE)
    @Scope("prototype")
    FormBuilder formVIIBuilder() {
        return new FormVIIBuilder();
    }

    @Bean("FORM_VIII_TEMPLATE")
    @FormBuilderType(FORM_VIII_TEMPLATE)
    @Scope("prototype")
    FormBuilder formVIIIBuilder() {
        return new FormVIIIBuilder();
    }

    @Bean("FORM_IX_TEMPLATE")
    @FormBuilderType(FORM_IX_TEMPLATE)
    @Scope("prototype")
    FormBuilder formIXBuilder() {
        return new FormIXBuilder();
    }

    @Bean("FORM_X_TEMPLATE")
    @FormBuilderType(FORM_X_TEMPLATE)
    @Scope("prototype")
    FormBuilder formXBuilder() {
        return new FormXBuilder();
    }
}
