package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerbDetailedConjugationPair extends DetailedConjugationPair<VerbConjugationGroup> {

    public VerbDetailedConjugationPair(VerbConjugationGroup leftSideConjugations, VerbConjugationGroup rightSideConjugations) {
        super(leftSideConjugations, rightSideConjugations);
    }
}
