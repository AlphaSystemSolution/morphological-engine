package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.app.sarfengine.conjugation.member.AbstractTriLiteralImperativeAndForbiddingBuilder;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import static com.alphasystem.sarfengine.xml.model.SarfTermType.FORBIDDING;

/**
 * @author sali
 */
public class TriLiteralForbiddingBuilder extends AbstractTriLiteralImperativeAndForbiddingBuilder {

    @AssistedInject
    public TriLiteralForbiddingBuilder(@Assisted NamedTemplate template,
                                       @Assisted boolean skipRuleProcessing,
                                       @Assisted("firstRadical") ArabicLetterType firstRadical,
                                       @Assisted("secondRadical") ArabicLetterType secondRadical,
                                       @Assisted("thirdRadical") ArabicLetterType thirdRadical) {
        super(template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, null, true);
    }

    @Override
    public SarfTermType getTermType() {
        return FORBIDDING;
    }
}
