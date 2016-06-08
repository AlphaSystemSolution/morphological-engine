/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import static com.alphasystem.util.HashCodeUtil.hash;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
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
        int hc = super.hashCode();
        if (!isEmpty(adverbs)) {
            for (RootWord aw : adverbs) {
                hc = hash(hc, aw);
            }
        }
        return hc;
    }
}
