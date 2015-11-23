package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.member.AbstractTriLiteralImperativeAndForbiddingBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * @author sali
 */
public class TriLiteralImperativeBuilder extends AbstractTriLiteralImperativeAndForbiddingBuilder {

    @AssistedInject
    public TriLiteralImperativeBuilder(@Assisted RuleProcessor ruleProcessor,
                                       @Assisted NamedTemplate template,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted RootWord baseRootWord,
                                       @Assisted ArabicLetter imperativeLetter) {
        super(ruleProcessor, template, skipRuleProcessing, baseRootWord, imperativeLetter, false);
    }

    @AssistedInject
    public TriLiteralImperativeBuilder(@Assisted RuleProcessor ruleProcessor,
                                       @Assisted NamedTemplate template,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted RootWord baseRootWord) {
        this(ruleProcessor, template, skipRuleProcessing, baseRootWord, null);
    }

}
