package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PRESENT_PASSIVE_TENSE;

/**
 * @author sali
 */
public class PresentPassiveTenseMemberBuilder extends PresentTenseMemberBuilder {

    @AssistedInject
    PresentPassiveTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted VerbRootBase rootBase,
                                     @Assisted RootLetters rootLetters) {
        super(ruleProcessor, rootBase, rootLetters);
    }

    @AssistedInject
    PresentPassiveTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                                     @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    public SarfTermType getTermType() {
        return PRESENT_PASSIVE_TENSE;
    }
}
