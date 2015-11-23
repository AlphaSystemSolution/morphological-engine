package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.member.AbstractTriLiteralVerbalNounAndAdverbBuilder;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * @author sali
 */
public class TriLiteralVerbalNounBuilder extends AbstractTriLiteralVerbalNounAndAdverbBuilder {

    @AssistedInject
    public TriLiteralVerbalNounBuilder(@Assisted RuleProcessor ruleProcessor,
                                       @Assisted NamedTemplate template,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted RootWord baseRootWord) {
        super(ruleProcessor, template, skipRuleProcessing, baseRootWord, -1, true);
    }

}
