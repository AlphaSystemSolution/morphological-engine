/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;

/**
 * Interface to build individual conjugation for each member term.
 *
 * @author sali
 */
public interface ConjugationMemberBuilder<G extends ConjugationGroup, B extends RootBase> {

    GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();

    /**
     * Perform the conjugation.
     *
     * @param ruleProcessor {@link RuleProcessor} to process null, maybe null if client wants to skip rule processing.
     * @param rootBase      base word for conjugation
     * @param rootLetters   root letters
     * @return instance of specific {@link ConjugationGroup}
     * @see NounRootBase
     * @see VerbRootBase
     * @see NounConjugationGroup
     * @see VerbConjugationGroup
     */
    G doConjugation(RuleProcessor ruleProcessor, B rootBase, RootLetters rootLetters);

    /**
     * @return
     */
    SarfTermType getTermType();

}
