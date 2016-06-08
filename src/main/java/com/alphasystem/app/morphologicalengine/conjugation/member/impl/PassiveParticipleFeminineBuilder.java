package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.NounRootBase;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.PASSIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
public class PassiveParticipleFeminineBuilder extends ActiveParticipleFeminineBuilder {

    @AssistedInject
    PassiveParticipleFeminineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted NounRootBase nounRootBase,
                                     @Assisted RootLetters rootLetters) {
        super(ruleProcessor, nounRootBase, rootLetters);
    }

    @AssistedInject
    PassiveParticipleFeminineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                                     @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    public SarfTermType getTermType() {
        return PASSIVE_PARTICIPLE_FEMININE;
    }
}
