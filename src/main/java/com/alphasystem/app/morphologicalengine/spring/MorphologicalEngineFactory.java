package com.alphasystem.app.morphologicalengine.spring;

import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleInfo;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorFactory;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessorType;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactory;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.verb.VerbTransformerFactoryType;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerType;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author sali
 */
public class MorphologicalEngineFactory implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    private static <T> T getBean(String name, Class<T> type) {
        return applicationContext.getBean(name, type);
    }

    public static ConjugationBuilder getFormBuilder() {
        return getBean(ConjugationBuilder.class);
    }

    public static VerbTransformerFactory getVerbTransformerFactory(VerbTransformerFactoryType.Type type) {
        return getBean(type.name(), VerbTransformerFactory.class);
    }

    public static NounTransformerFactory getNounTransformerFactory(NounTransformerFactoryType.Type type) {
        return getBean(type.name(), NounTransformerFactory.class);
    }

    public static VerbTransformer getVerbTransformer(VerbTransformerType.Type type) {
        return getBean(type.name(), VerbTransformer.class);
    }

    public static NounTransformer getNounTransformer(NounTransformerType.Type type) {
        return getBean(type.name(), NounTransformer.class);
    }

    public static RuleProcessor getRuleProcessor(RuleProcessorType.Type type, RuleInfo ruleInfo) {
        final RuleProcessorFactory ruleProcessorFactory = getBean(type.name(), RuleProcessorFactory.class);
        return ruleProcessorFactory.createRuleProcessor(ruleInfo);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MorphologicalEngineFactory.applicationContext = applicationContext;
    }
}
