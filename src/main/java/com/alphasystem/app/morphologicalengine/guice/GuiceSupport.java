package com.alphasystem.app.morphologicalengine.guice;

import com.alphasystem.app.morphologicalengine.conjugation.builder.*;
import com.alphasystem.app.morphologicalengine.conjugation.member.ConjugationMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.member.impl.MemberBuilderModule;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycila.guice.ext.closeable.CloseableModule;
import com.mycila.guice.ext.jsr250.Jsr250Module;

import java.lang.annotation.Annotation;

import static com.google.inject.Key.get;
import static com.google.inject.name.Names.named;

/**
 * @author sali
 */
public final class GuiceSupport {

    private static GuiceSupport instance = new GuiceSupport();
    private final Injector injector;

    /**
     * Do not let anyone instantiate this class
     */
    private GuiceSupport() {
        injector = Guice.createInjector(new CloseableModule(), new Jsr250Module(), new VerbTransformerModule(),
                new RuleProcessorModule(), new MemberBuilderModule(), new BuilderModule());
    }

    public static GuiceSupport getInstance() {
        return instance;
    }

    public Injector getInjector() {
        return injector;
    }

    public RuleProcessorFactory getRuleProcessorFactory() {
        return injector.getInstance(RuleProcessorFactory.class);
    }

    public <T> T getInstance(Class<T> type, Annotation annotation) {
        if (annotation == null) {
            return null;
        }
        T instance = null;
        try {
            instance = injector.getInstance(get(type, annotation));
        } catch (Exception e) {
            // ignore
        }
        return instance;
    }

    public <T> T getInstance(Class<T> type, String name) {
        return injector.getInstance(get(type, named(name)));
    }

    public <B extends ConjugationMemberBuilder> B getMemberBuilder(Class<B> type, SarfTermType termType) {
        return termType == null ? null : getInstance(type, termType.name());
    }

    public NounTransformer getNounTransformer(String name) {
        return (name == null) ? null : getInstance(NounTransformer.class, name);
    }

    public VerbTransformer getVerbTransformer(String name) {
        return (name == null) ? null : getInstance(VerbTransformer.class, name);
    }

    public ConjugationBuilder getConjugationBuilder() {
        return injector.getInstance(ConjugationBuilder.class);
    }

    public AbbreviatedConjugationBuilder getAbbreviatedConjugationBuilder() {
        return injector.getInstance(AbbreviatedConjugationBuilder.class);
    }

    public DetailedConjugationBuilder getDetailedConjugationBuilder() {
        return injector.getInstance(DetailedConjugationBuilder.class);
    }

    public ConjugationHeaderBuilder getConjugationHeaderBuilder() {
        return injector.getInstance(ConjugationHeaderBuilder.class);
    }
}
