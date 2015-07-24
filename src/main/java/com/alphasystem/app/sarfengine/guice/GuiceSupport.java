package com.alphasystem.app.sarfengine.guice;

import com.alphasystem.app.sarfengine.conjugation.member.MemberBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycila.guice.ext.closeable.CloseableModule;
import com.mycila.guice.ext.jsr250.Jsr250Module;

/**
 * @author sali
 */
public class GuiceSupport {

    private static GuiceSupport instance = new GuiceSupport();
    private final Injector injector;

    /**
     * Do not let anyone instantiate this class
     */
    private GuiceSupport() {
        injector = Guice.createInjector(new CloseableModule(), new Jsr250Module(), new RuleProcessorModule(),
                new MemberBuilderModule());
    }

    public static GuiceSupport getInstance() {
        return instance;
    }

    public Injector getInjector() {
        return injector;
    }

    public RuleProcessor getRuleProcessor(NamedTemplate template,
                                          DiacriticType diacriticForWeakSecondRadicalWaw,
                                          boolean pastTenseHasTransformed,
                                          ArabicLetterType hamzahReplacement) {
        return getRuleProcessorFactory().getRuleProcessor(template,
                diacriticForWeakSecondRadicalWaw, pastTenseHasTransformed, hamzahReplacement);
    }

    public RuleProcessorFactory getRuleProcessorFactory() {
        return injector.getInstance(RuleProcessorFactory.class);
    }

    public MemberBuilderFactory getMemberBuilderFactory() {
        return injector.getInstance(MemberBuilderFactory.class);
    }
}
