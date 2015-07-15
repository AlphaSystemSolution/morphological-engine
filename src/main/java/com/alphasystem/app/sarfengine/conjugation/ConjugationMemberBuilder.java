/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.SarfMemberType;
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
     * @return Array of {@linkplain ArabicWord}s
     */
    ArabicWord[] doConjugation();

    /**
     * @return
     */
    ArabicWord getDefaultConjugation();

    /**
     * @return
     */
    SarfMemberType getDefaultMember();

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
