/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer;

import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;

import static com.alphasystem.util.HashCodeUtil.hash;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public class ZarfLine {

    private final ConjugationMember[] zarfs;

    public ZarfLine(ConjugationMember... zarfs) {
        this.zarfs = zarfs;
    }

    public ConjugationMember[] getZarfs() {
        return zarfs;
    }

    @Override
    public int hashCode() {
        int hc = super.hashCode();
        if (!isEmpty(zarfs)) {
            for (ConjugationMember aw : zarfs) {
                hc = hash(hc, aw);
            }
        }
        return hc;
    }
}
