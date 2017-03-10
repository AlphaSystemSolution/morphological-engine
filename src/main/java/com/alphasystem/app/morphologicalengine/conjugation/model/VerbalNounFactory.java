package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

import java.util.HashMap;
import java.util.Map;

import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType.Type.FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY;
import static com.alphasystem.app.morphologicalengine.conjugation.transformer.factory.noun.NounTransformerFactoryType.Type.MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY;

/**
 * @author sali
 */
public final class VerbalNounFactory {

    private static final NounRootBase VERBAL_NOUN_V1 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V1);
    private static final NounRootBase VERBAL_NOUN_V2 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V2);
    private static final NounRootBase VERBAL_NOUN_V3 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V3);
    private static final NounRootBase VERBAL_NOUN_V4 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V4);
    private static final NounRootBase VERBAL_NOUN_V5 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V5);
    private static final NounRootBase VERBAL_NOUN_V6 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V6);
    private static final NounRootBase VERBAL_NOUN_V7 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V7);
    private static final NounRootBase VERBAL_NOUN_V8 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V8);
    private static final NounRootBase VERBAL_NOUN_V9 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V9);
    private static final NounRootBase VERBAL_NOUN_V10 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V10);
    private static final NounRootBase VERBAL_NOUN_V11 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V11);
    private static final NounRootBase VERBAL_NOUN_V12 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V12);
    private static final NounRootBase VERBAL_NOUN_V13 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V13);
    private static final NounRootBase VERBAL_NOUN_V14 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V14);
    private static final NounRootBase VERBAL_NOUN_V15 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V15);
    private static final NounRootBase VERBAL_NOUN_V27 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V27);
    private static final NounRootBase VERBAL_NOUN_V28 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_V28);
    private static final NounRootBase VERBAL_NOUN_FORM_II = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_II);
    private static final NounRootBase VERBAL_NOUN_FORM_III_V1 = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_III_V1);
    private static final NounRootBase VERBAL_NOUN_FORM_III_V2 = new NounRootBase(FEMININE_SOUND_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_III_V2);
    private static final NounRootBase VERBAL_NOUN_FORM_IV = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_IV);
    private static final NounRootBase VERBAL_NOUN_FORM_V = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_V);
    private static final NounRootBase VERBAL_NOUN_FORM_VI = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_VI);
    private static final NounRootBase VERBAL_NOUN_FORM_VII = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_VII);
    private static final NounRootBase VERBAL_NOUN_FORM_VIII = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_VIII);
    private static final NounRootBase VERBAL_NOUN_FORM_IX = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_IX);
    private static final NounRootBase VERBAL_NOUN_FORM_X = new NounRootBase(MASCULINE_BASED_FEMININE_PLURAL_TRANSFORMER_FACTORY, VerbalNoun.VERBAL_NOUN_FORM_X);

    private static final Map<VerbalNoun, NounRootBase> map = new HashMap<>();

    static {
        map.put(VerbalNoun.VERBAL_NOUN_V1, VERBAL_NOUN_V1);
        map.put(VerbalNoun.VERBAL_NOUN_V2, VERBAL_NOUN_V2);
        map.put(VerbalNoun.VERBAL_NOUN_V3, VERBAL_NOUN_V3);
        map.put(VerbalNoun.VERBAL_NOUN_V4, VERBAL_NOUN_V4);
        map.put(VerbalNoun.VERBAL_NOUN_V5, VERBAL_NOUN_V5);
        map.put(VerbalNoun.VERBAL_NOUN_V6, VERBAL_NOUN_V6);
        map.put(VerbalNoun.VERBAL_NOUN_V7, VERBAL_NOUN_V7);
        map.put(VerbalNoun.VERBAL_NOUN_V8, VERBAL_NOUN_V8);
        map.put(VerbalNoun.VERBAL_NOUN_V9, VERBAL_NOUN_V9);
        map.put(VerbalNoun.VERBAL_NOUN_V10, VERBAL_NOUN_V10);
        map.put(VerbalNoun.VERBAL_NOUN_V11, VERBAL_NOUN_V11);
        map.put(VerbalNoun.VERBAL_NOUN_V12, VERBAL_NOUN_V12);
        map.put(VerbalNoun.VERBAL_NOUN_V13, VERBAL_NOUN_V13);
        map.put(VerbalNoun.VERBAL_NOUN_V14, VERBAL_NOUN_V14);
        map.put(VerbalNoun.VERBAL_NOUN_V15, VERBAL_NOUN_V15);
        map.put(VerbalNoun.VERBAL_NOUN_V27, VERBAL_NOUN_V27);
        map.put(VerbalNoun.VERBAL_NOUN_V28, VERBAL_NOUN_V28);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_II, VERBAL_NOUN_FORM_II);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_III_V1, VERBAL_NOUN_FORM_III_V1);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_III_V2, VERBAL_NOUN_FORM_III_V2);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_IV, VERBAL_NOUN_FORM_IV);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_V, VERBAL_NOUN_FORM_V);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_VI, VERBAL_NOUN_FORM_VI);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_VII, VERBAL_NOUN_FORM_VII);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_VIII, VERBAL_NOUN_FORM_VIII);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_IX, VERBAL_NOUN_FORM_IX);
        map.put(VerbalNoun.VERBAL_NOUN_FORM_X, VERBAL_NOUN_FORM_X);
    }

    /**
     * Do not let any one instantiate this class
     */
    private VerbalNounFactory() {
    }

    /**
     * Returns the configured value for given key, {@link VerbalNoun}
     *
     * @param verbalNoun Given key
     * @return Corresponding value for given key
     * @throws NullPointerException if there is no object defined with the given key (<code>verbalNoun</code>).
     */
    public static NounRootBase getByVerbalNoun(VerbalNoun verbalNoun) {
        return new NounRootBase(map.get(verbalNoun));
    }

}
