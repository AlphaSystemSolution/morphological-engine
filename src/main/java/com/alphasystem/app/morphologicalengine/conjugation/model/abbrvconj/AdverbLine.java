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
public class AdverbLine {

    private final RootWord[] adverbs;

    public AdverbLine(RootWord... adverbs) {
        this.adverbs = adverbs;
    }

    public RootWord[] getAdverbs() {
        return adverbs;
    }

    @Override
    public int hashCode() {
        return hash(adverbs);
    }
}
