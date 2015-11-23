package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.member.AbstractTriLiteralImperativeAndForbiddingBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

/**
 * @author sali
 */
public class TriLiteralImperativeBuilder extends AbstractTriLiteralImperativeAndForbiddingBuilder {

    @AssistedInject
    public TriLiteralImperativeBuilder(@Assisted RuleProcessor ruleProcessor,
                                       @Assisted NamedTemplate template,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted("firstRadical") ArabicLetterType firstRadical,
                                       @Assisted("secondRadical") ArabicLetterType secondRadical,
                                       @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                       @Nullable @Assisted ArabicLetter imperativeLetter) {
        super(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, imperativeLetter, false);
    }

    @AssistedInject
    public TriLiteralImperativeBuilder(@Assisted RuleProcessor ruleProcessor,
                                       @Assisted NamedTemplate template,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted("firstRadical") ArabicLetterType firstRadical,
                                       @Assisted("secondRadical") ArabicLetterType secondRadical,
                                       @Assisted("thirdRadical") ArabicLetterType thirdRadical) {
        this(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, null);
    }

}
