/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;

/**
 * @author sali
 */
public abstract class AbstractRuleProcessor extends RuleProcessorHelper {

    protected final NamedTemplate template;

    public AbstractRuleProcessor(NamedTemplate template) {
        this.template = template;
    }

    public abstract RootWord applyRules(RootWord baseRootWord);
}
