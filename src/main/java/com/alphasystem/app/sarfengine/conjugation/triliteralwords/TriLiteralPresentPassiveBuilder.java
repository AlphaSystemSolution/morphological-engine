/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.sarfengine.xml.model.SarfTermType.PRESENT_PASSIVE_TENSE;

/**
 * @author sali
 */
public class TriLiteralPresentPassiveBuilder extends
        TriLiteralPresentTenseBuilder {

    @Override
    public SarfTermType getTermType() {
        return PRESENT_PASSIVE_TENSE;
    }

}
