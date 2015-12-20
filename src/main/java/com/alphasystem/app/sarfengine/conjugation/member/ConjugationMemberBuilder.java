/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * Interface to build individual conjugation for each member term.
 *
 * @author sali
 */
public interface ConjugationMemberBuilder extends ArabicLetters {

    /**
     * Perform the conjugation.
     *
     * @return Array of {@linkplain RootWord}s
     */
    RootWord[] doConjugation();

    /**
     * @return
     */
    RootWord getDefaultConjugation();

    /**
     * @return
     */
    RuleProcessor getRuleProcessor();

    /**
     * @return
     */
    SarfTermType getTermType();

    /**
     * @return
     */
    boolean isSkipRuleProcessing();

}
