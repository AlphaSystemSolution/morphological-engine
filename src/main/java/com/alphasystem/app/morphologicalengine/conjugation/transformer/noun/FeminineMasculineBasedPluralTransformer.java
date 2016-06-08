package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.morphologicalanalysis.morphology.model.RootWord.convertToFeminine;

/**
 * @author sali
 */
public class FeminineMasculineBasedPluralTransformer extends FemininePluralTransformer {

    @AssistedInject
    FeminineMasculineBasedPluralTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        super(ruleProcessor);
    }

    @Override
    protected RootWord createRootWord(RootWord rootWord, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                      ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        return super.createRootWord(convertToFeminine(rootWord), firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

}
