/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.sarfengine.xml.model.SarfTermType.PASSIVE_PARTICIPLE_MASCULINE;

/**
 * @author sali
 */
public class TriLiteralPassiveParticipleMasculineBuilder extends
        TriLiteralActiveParticipleMasculineBuilder {

    @Override
    public SarfTermType getTermType() {
        return PASSIVE_PARTICIPLE_MASCULINE;
    }

}
