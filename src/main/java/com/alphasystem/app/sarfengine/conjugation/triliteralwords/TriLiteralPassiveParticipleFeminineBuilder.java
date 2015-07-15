/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.sarfengine.xml.model.SarfTermType.PASSIVE_PARTICIPLE_FEMININE;

/**
 * @author sali
 */
public class TriLiteralPassiveParticipleFeminineBuilder extends
        TriLiteralActiveParticipleFeminineBuilder {

    @Override
    public SarfTermType getTermType() {
        return PASSIVE_PARTICIPLE_FEMININE;
    }

}
