package com.alphasystem.app.sarfengine.conjugation.member.triliteral;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * @author sali
 */
public class TriLiteralImperativeFormIVBuilder extends TriLiteralImperativeBuilder {

    @AssistedInject
    public TriLiteralImperativeFormIVBuilder(@Assisted NamedTemplate template, @Assisted boolean skipRuleProcessing,
                                             @Assisted("firstRadical") ArabicLetterType firstRadical,
                                             @Assisted("secondRadical") ArabicLetterType secondRadical,
                                             @Assisted("thirdRadical") ArabicLetterType thirdRadical) {
        super(template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, ALIF_HAMZA_ABOVE_WITH_FATHA);
    }
}
