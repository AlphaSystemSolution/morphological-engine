/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer;

import com.alphasystem.arabic.model.ArabicWord;

import static com.alphasystem.util.HashCodeUtil.hash;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public class ZarfLine {

    private final ArabicWord[] zarfs;

    public ZarfLine(ArabicWord... zarfs) {
        this.zarfs = zarfs;
    }

    public ArabicWord[] getZarfs() {
        return zarfs;
    }

    @Override
    public int hashCode() {
        int hc = super.hashCode();
        if (!isEmpty(zarfs)) {
            for (ArabicWord aw : zarfs) {
                hc = hash(hc, aw);
            }
        }
        return hc;
    }
}
