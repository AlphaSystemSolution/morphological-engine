/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.util.HashCodeUtil.hash;

/**
 * @author sali
 */
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
        int hc = hash(super.hashCode(), imperative);
        hc = hash(hc, forbidding);
        return hc;
    }
}
