package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.AbstractTenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformer;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerModule.*;

/**
 * @author sali
 */
public class PastTenseMemberBuilder extends AbstractTenseMemberBuilder {

    @AssistedInject
    PastTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted VerbRootBase rootBase,
                           @Assisted RootLetters rootLetters) {
        super(ruleProcessor, rootBase, rootLetters);
    }

    @AssistedInject
    PastTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted Form form,
                           @Assisted RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    protected VerbTransformer initializeThirdPersonMasculineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_THIRD_PERSON_MASCULINE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeThirdPersonFeminineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_THIRD_PERSON_FEMININE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeSecondPersonMasculineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_SECOND_PERSON_MASCULINE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeSecondPersonFeminineTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_SECOND_PERSON_FEMININE_TRANSFORMER);
    }

    @Override
    protected VerbTransformer initializeFirstPersonTransformer() {
        return GUICE_SUPPORT.getVerbTransformer(PAST_TENSE_FIRST_PERSON_TRANSFORMER);
    }
}
