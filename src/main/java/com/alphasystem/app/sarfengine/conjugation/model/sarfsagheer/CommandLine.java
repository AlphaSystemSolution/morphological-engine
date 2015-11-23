/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer;

import com.alphasystem.sarfengine.xml.model.RootWord;

import static com.alphasystem.util.HashCodeUtil.hash;

/**
 * @author sali
 */
public class CommandLine {

    private final RootWord imperative;

    private final RootWord forbidding;

    public CommandLine(RootWord imperative, RootWord forbidding) {
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
