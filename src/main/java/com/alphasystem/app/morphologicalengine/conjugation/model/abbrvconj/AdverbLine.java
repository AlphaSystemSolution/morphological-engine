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
public class AdverbLine {

    private String[] adverbs;
    private String adverb;

    public String[] getAdverbs() {
        return adverbs;
    }

    public void setAdverbs(String[] adverbs) {
        this.adverbs = adverbs;
    }

    public String getAdverb() {
        return adverb;
    }

    public void setAdverb(String adverb) {
        this.adverb = adverb;
    }

    @Override
    public int hashCode() {
        return hash((Object[]) adverbs);
    }

}
