/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

/**
 * Interface to build individual conjugation for each member term.
 *
 * @author sali
 */
public interface ConjugationMemberBuilder<G> {

    /**
     * Perform the conjugation.
     *
     * @return
     */
    G doConjugation();

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

}
