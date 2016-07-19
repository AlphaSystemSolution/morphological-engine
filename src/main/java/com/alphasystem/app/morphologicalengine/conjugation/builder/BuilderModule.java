package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.google.inject.AbstractModule;

import javax.inject.Singleton;

/**
 * @author sali
 */
public final class BuilderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConjugationHeaderBuilder.class).toProvider(ConjugationHeaderBuilder.ProviderImpl.class).in(Singleton.class);
        bind(AbbreviatedConjugationBuilder.class).toProvider(AbbreviatedConjugationBuilder.ProviderImpl.class).in(Singleton.class);
        bind(DetailedConjugationBuilder.class).toProvider(DetailedConjugationBuilder.ProviderImpl.class).in(Singleton.class);
        bind(ConjugationBuilder.class).toProvider(ConjugationBuilder.ProviderImpl.class).in(Singleton.class);
    }
}
