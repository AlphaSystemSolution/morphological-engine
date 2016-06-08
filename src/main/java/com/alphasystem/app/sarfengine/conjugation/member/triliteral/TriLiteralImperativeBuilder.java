package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.conjugation.member.AbstractTriLiteralImperativeAndForbiddingBuilder;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * @author sali
 */
public class TriLiteralImperativeBuilder extends AbstractTriLiteralImperativeAndForbiddingBuilder {

    @AssistedInject
    public TriLiteralImperativeBuilder(@Assisted RuleProcessor ruleProcessor,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted RootWord baseRootWord,
                                       @Assisted ArabicLetter imperativeLetter) {
        super(ruleProcessor, skipRuleProcessing, baseRootWord, imperativeLetter, false);
    }

    @AssistedInject
    public TriLiteralImperativeBuilder(@Assisted RuleProcessor ruleProcessor,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted RootWord baseRootWord) {
        this(ruleProcessor, skipRuleProcessing, baseRootWord, null);
    }

}
