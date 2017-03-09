package com.alphasystem.app.morphologicalengine.conjugation.rule;


/**
 * Spring Factory interface for creating {@link RuleProcessor}.
 *
 * @author sali
 */
public interface RuleProcessorFactory {

    RuleProcessor createRuleProcessor(final RuleInfo ruleInfo);

}
