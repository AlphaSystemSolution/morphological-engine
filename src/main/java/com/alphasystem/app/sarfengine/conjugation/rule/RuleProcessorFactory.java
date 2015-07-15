/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;

/**
 * @author sali
 */
public final class RuleProcessorFactory {

    private static RuleProcessorFactory instance;

    private RuleProcessorFactory() {
    }

    public synchronized static RuleProcessorFactory getInstance() {
        if (instance == null) {
            instance = new RuleProcessorFactory();
        }
        return instance;
    }

    public RuleProcessor getRuleProcessor(NamedTemplate template,
                                          RootWord baseRootWord) {
        RuleProcessor processor = new DefaultRuleProcessor(template);
        return processor;
    }

}
