/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.sarfengine.xml.model.SarfTermType.PAST_PASSIVE_TENSE;

/**
 * @author sali
 */
public class TriLiteralPastPassiveBuilder extends TriLiteralPastTenseBuilder {

    @Override
    public SarfTermType getTermType() {
        return PAST_PASSIVE_TENSE;
    }

}
