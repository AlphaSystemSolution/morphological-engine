/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static java.util.Objects.hash;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImperativeAndForbiddingLine {

    private String imperative;

    private String forbidding;

    public String getImperative() {
        return imperative;
    }

    public void setImperative(String imperative) {
        this.imperative = imperative;
    }

    public String getForbidding() {
        return forbidding;
    }

    public void setForbidding(String forbidding) {
        this.forbidding = forbidding;
    }

    @Override
    public int hashCode() {
        return hash(imperative, forbidding);
    }
}
