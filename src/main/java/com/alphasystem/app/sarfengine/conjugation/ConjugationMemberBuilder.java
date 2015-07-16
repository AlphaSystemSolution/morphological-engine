/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation;

import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

/**
 * Interface to build individual conjugation for each member term.
 *
 * @author sali
 */
public interface ConjugationMemberBuilder extends ArabicLetters {

    /**
     * Perform the conjugation.
     *
     * @return Array of {@linkplain ConjugationMember}s
     */
    ConjugationMember[] doConjugation();

    /**
     * @return
     */
    ConjugationMember getDefaultConjugation();

    /**
     * @return
     */
    RuleProcessor getRuleProcessor();

    /**
     * @param ruleProcessor
     */
    void setRuleProcessor(RuleProcessor ruleProcessor);

    /**
     * @return
     */
    NamedTemplate getTemplate();

    /**
     * @param template
     */
    void setTemplate(NamedTemplate template);

    /**
     * @return
     */
    SarfTermType getTermType();

    /**
     * @return
     */
    boolean isSkipRuleProcessing();

    /**
     * @param skip
     */
    void setSkipRuleProcessing(boolean skip);

}
