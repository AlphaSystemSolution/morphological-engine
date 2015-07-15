/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.triliteralwords;

/**
 * @author sali
 */
public class TriLiteralVerbalNounV1Builder
        extends
        AbstractTriLiteralVerbalNounAndZarfBuilder<TriLiteralActiveParticipleMasculineBuilder, TriLiteralActiveParticipleFeminineBuilder> {

    @Override
    protected void postInit() {
        setVariableLetterIndex(this.rootWord.getLength() - 1);
        super.postInit();
    }

}
