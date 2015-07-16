/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer;

import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;

import static com.alphasystem.util.HashCodeUtil.hash;

/**
 * @author sali
 */
public class CommandLine {

    private final ConjugationMember commnad;

    private final ConjugationMember forbidding;

    public CommandLine(ConjugationMember commnad, ConjugationMember forbidding) {
        this.commnad = commnad;
        this.forbidding = forbidding;
    }

    public ConjugationMember getCommnad() {
        return commnad;
    }

    public ConjugationMember getForbidding() {
        return forbidding;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), commnad);
        hc = hash(hc, forbidding);
        return hc;
    }
}
