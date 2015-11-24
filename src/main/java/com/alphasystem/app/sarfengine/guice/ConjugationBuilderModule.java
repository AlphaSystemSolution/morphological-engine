package com.alphasystem.app.sarfengine.guice;

import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.builder.DefaultConjugationBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * @author sali
 */
public class ConjugationBuilderModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(ConjugationBuilder.class, DefaultConjugationBuilder.class)
                .build(ConjugationBuilderFactory.class));
    }
}
