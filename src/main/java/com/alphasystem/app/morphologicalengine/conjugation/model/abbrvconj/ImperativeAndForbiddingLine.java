/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static java.util.Objects.hash;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImperativeAndForbiddingLine {

    private final RootWord imperative;

    private final RootWord forbidding;

    public ImperativeAndForbiddingLine(RootWord imperative, RootWord forbidding) {
        this.imperative = imperative;
        this.forbidding = forbidding;
    }

    public RootWord getImperative() {
        return imperative;
    }

    public RootWord getForbidding() {
        return forbidding;
    }

    @Override
    public int hashCode() {
        return hash(imperative, forbidding);
    }
}
