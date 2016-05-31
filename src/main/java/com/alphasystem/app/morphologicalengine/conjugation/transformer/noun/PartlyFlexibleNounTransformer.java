package com.alphasystem.app.morphologicalengine.conjugation.transformer.noun;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public class PartlyFlexibleNounTransformer extends AbstractNounTransformer {

    @AssistedInject
    PartlyFlexibleNounTransformer(@Assisted @Nullable RuleProcessor ruleProcessor, @Assisted int variableIndex) {
        super(ruleProcessor, variableIndex);
    }

    protected PartlyFlexibleNounTransformer(@Assisted @Nullable RuleProcessor ruleProcessor) {
        this(ruleProcessor, LAST_LETTER);
    }

    @Override
    protected RootWord doNominative(RootWord rootWord) {
        return processRules(copyRootWord(rootWord, NOMINATIVE_PLURAL));
    }

    @Override
    protected RootWord doAccusative(RootWord rootWord) {
        RootWord target = copyRootWord(rootWord, ACCUSATIVE_PLURAL);
        ArabicWord arabicWord = target.getRootWord().replaceDiacritic(variableIndex, FATHA);
        return processRules(target.withRootWord(arabicWord));
    }

    @Override
    protected RootWord doGenitive(RootWord rootWord) {
        return copyRootWord(doAccusative(rootWord), GENITIVE_PLURAL);
    }
}
