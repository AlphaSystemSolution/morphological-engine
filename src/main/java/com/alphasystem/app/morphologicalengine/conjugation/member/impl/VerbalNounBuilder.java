package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.sarfengine.conjugation.model.Form;
import com.alphasystem.app.sarfengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.NounRootBase;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.VERBAL_NOUN;

/**
 * @author sali
 */
public class VerbalNounBuilder extends ActiveParticipleMasculineBuilder {

    @AssistedInject
    VerbalNounBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted NounRootBase nounRootBase,
                      @Assisted RootLetters rootLetters) {
        super(ruleProcessor, nounRootBase, rootLetters);
    }

    @AssistedInject
    VerbalNounBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form, @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    protected void initializePluralTransformer() {
        pluralTransformer = nounTransformerFactory.getFeminineMasculineBasedPluralTransformer(getRuleProcessor());
    }

    @Override
    public SarfTermType getTermType() {
        return VERBAL_NOUN;
    }
}
