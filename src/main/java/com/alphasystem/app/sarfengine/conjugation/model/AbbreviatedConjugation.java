/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ActiveLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.AdverbLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ImperativeAndForbiddingLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.PassiveLine;

import static com.alphasystem.util.HashCodeUtil.hash;

/**
 * @author sali
 */
public class AbbreviatedConjugation {

    private final ActiveLine activeLine;

    private final PassiveLine passiveLine;

    private final ImperativeAndForbiddingLine imperativeAndForbiddingLine;

    private final AdverbLine adverbLine;

    public AbbreviatedConjugation(ActiveLine activeLine, PassiveLine passiveLine,
                                  ImperativeAndForbiddingLine commandLine, AdverbLine adverbLine) {
        this.activeLine = activeLine;
        this.passiveLine = passiveLine;
        this.imperativeAndForbiddingLine = commandLine;
        this.adverbLine = adverbLine;
    }

    public ActiveLine getActiveLine() {
        return activeLine;
    }

    public ImperativeAndForbiddingLine getImperativeAndForbiddingLine() {
        return imperativeAndForbiddingLine;
    }

    public PassiveLine getPassiveLine() {
        return passiveLine;
    }

    public AdverbLine getAdverbLine() {
        return adverbLine;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), activeLine);
        hc = hash(hc, passiveLine);
        hc = hash(hc, imperativeAndForbiddingLine);
        hc = hash(hc, adverbLine);
        return hc;
    }
}
