package com.alphasystem.app.morphologicalengine.guice;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerModule;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule;
import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.member.MemberBuilderFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycila.guice.ext.closeable.CloseableModule;
import com.mycila.guice.ext.jsr250.Jsr250Module;

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
        injector = Guice.createInjector(new CloseableModule(), new Jsr250Module(), new NounTransformerModule(), new VerbTransformerModule()
                , new RuleProcessorModule(), new com.alphasystem.app.morphologicalengine.conjugation.member.impl.MemberBuilderModule()
                 /*, new MemberBuilderModuleOld(), new ConjugationBuilderModule()*/);
    }

    public static GuiceSupport getInstance() {
        return instance;
    }

    public Injector getInjector() {
        return injector;
    }

    public NounTransformerFactory getNounTransformerFactory() {
        return injector.getInstance(NounTransformerFactory.class);
    }

    public VerbTransformerFactory getVerbTransformerFactory() {
        return injector.getInstance(VerbTransformerFactory.class);
    }

    public RuleProcessorFactory getRuleProcessorFactory() {
        return injector.getInstance(RuleProcessorFactory.class);
    }

    public MemberBuilderFactory getOldMemberBuilderFactory() {
        return injector.getInstance(MemberBuilderFactory.class);
    }

    public com.alphasystem.app.morphologicalengine.conjugation.member.impl.MemberBuilderFactory getMemberBuilderFactory() {
        return injector.getInstance(com.alphasystem.app.morphologicalengine.conjugation.member.impl.MemberBuilderFactory.class);
    }

    public ConjugationBuilderFactory getConjugationBuilderFactory() {
        return injector.getInstance(ConjugationBuilderFactory.class);
    }

}
