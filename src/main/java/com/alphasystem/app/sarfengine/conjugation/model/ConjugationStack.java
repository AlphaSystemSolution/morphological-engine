package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import java.util.List;

/**
 * @author sali
 */
public class ConjugationStack {

    private final List<RootWord> conjugations;
    private final RootWord defaultValue;
    private final SarfTermType label;

    public ConjugationStack(List<RootWord> conjugations, RootWord defaultValue, SarfTermType label) {
        this.conjugations = conjugations;
        this.defaultValue = defaultValue;
        this.label = label;
    }

    public List<RootWord> getConjugations() {
        return conjugations;
    }

    public RootWord getDefaultValue() {
        return defaultValue;
    }

    public SarfTermType getLabel() {
        return label;
    }

}