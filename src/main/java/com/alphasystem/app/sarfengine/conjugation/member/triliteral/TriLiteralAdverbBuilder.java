package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.member.AbstractTriLiteralVerbalNounAndAdverbBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import javax.annotation.Nullable;

/**
 * @author sali
 */
public class TriLiteralAdverbBuilder extends AbstractTriLiteralVerbalNounAndAdverbBuilder {

    @AssistedInject
    public TriLiteralAdverbBuilder(@Assisted RuleProcessor ruleProcessor,
                                   @Assisted NamedTemplate template,
                                   @Assisted boolean skipRuleProcessing,
                                   @Assisted("firstRadical") ArabicLetterType firstRadical,
                                   @Assisted("secondRadical") ArabicLetterType secondRadical,
                                   @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                   @Nullable @Assisted RootWord baseRootWord) {
        super(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, baseRootWord, -1, true);
    }

}
