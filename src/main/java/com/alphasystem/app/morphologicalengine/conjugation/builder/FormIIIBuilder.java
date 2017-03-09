package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactory;

/**
 * @author sali
 */
public class FormIIIBuilder extends AbstractFormBuilder {
    
    @Override
    public NounTransformerFactory verbalNounTransformerFactory() {
        return null;
    }

    @Override
    public NounTransformerFactory nounOfPlaceAndTimeTransformerFactory() {
        return null;
    }
}
