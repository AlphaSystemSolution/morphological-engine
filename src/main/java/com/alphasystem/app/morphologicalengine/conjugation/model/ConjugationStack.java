package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

;

/**
 * @author sali
 */
public class ConjugationStack {

    private final RootWord[] conjugations;
    private final RootWord defaultValue;
    private final SarfTermType label;

    public ConjugationStack(RootWord[] conjugations, RootWord defaultValue, SarfTermType label) {
        this.conjugations = conjugations;
        this.defaultValue = defaultValue;
        this.label = label;
    }

    public RootWord[] getConjugations() {
        return conjugations;
    }

    public RootWord getDefaultValue() {
        return defaultValue;
    }

    public SarfTermType getLabel() {
        return label;
    }

    public boolean isEmpty(){
        return defaultValue == null;
    }

}
