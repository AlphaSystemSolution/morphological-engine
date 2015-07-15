/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer;

import com.alphasystem.arabic.model.ArabicWord;

import static com.alphasystem.util.HashCodeUtil.hash;

/**
 * @author sali
 */
public class CommandLine {

    private final ArabicWord commnad;

    private final ArabicWord forbidding;

    public CommandLine(ArabicWord commnad, ArabicWord forbidding) {
        this.commnad = commnad;
        this.forbidding = forbidding;
    }

    public ArabicWord getCommnad() {
        return commnad;
    }

    public ArabicWord getForbidding() {
        return forbidding;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), commnad);
        hc = hash(hc, forbidding);
        return hc;
    }
}
