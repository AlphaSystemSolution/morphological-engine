package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractParticipleMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.NounRootBase;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

/**
 * @author sali
 */
public class ActiveParticipleMasculineBuilder extends AbstractParticipleMemberBuilder {

    @AssistedInject
    ActiveParticipleMasculineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted NounRootBase nounRootBase,
                                     @Assisted RootLetters rootLetters) {
        super(ruleProcessor, nounRootBase, rootLetters);
    }

    @AssistedInject
    ActiveParticipleMasculineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                                    @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

}
