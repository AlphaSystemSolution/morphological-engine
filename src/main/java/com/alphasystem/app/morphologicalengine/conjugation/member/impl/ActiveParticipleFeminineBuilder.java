package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractParticipleMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.model.Form;
import com.alphasystem.app.sarfengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounRootBase;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
public class ActiveParticipleFeminineBuilder extends AbstractParticipleMemberBuilder {

    @AssistedInject
    ActiveParticipleFeminineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted NounRootBase nounRootBase,
                                    @Assisted RootLetters rootLetters) {
        super(ruleProcessor, nounRootBase, rootLetters);
    }

    @AssistedInject
    ActiveParticipleFeminineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                                    @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    protected void initializeTransformers() {
        singularTransformer = transformerFactory.getFeminineEndingSoundTransformer(getRuleProcessor());
        dualTransformer = transformerFactory.getFeminineDualTransformer(getRuleProcessor());
        pluralTransformer = transformerFactory.getFemininePluralTransformer(getRuleProcessor());
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_FEMININE;
    }
}
