package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
public class PassiveParticipleMasculineBuilder extends ActiveParticipleMasculineBuilder {

    @AssistedInject
    PassiveParticipleMasculineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted NounRootBase nounRootBase,
                                      @Assisted RootLetters rootLetters) {
        super(ruleProcessor, nounRootBase, rootLetters);
    }

    @AssistedInject
    PassiveParticipleMasculineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                                     @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    public SarfTermType getTermType() {
        return PASSIVE_PARTICIPLE_MASCULINE;
    }
}
