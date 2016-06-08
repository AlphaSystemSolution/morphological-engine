/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.AdverbLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;

import static java.util.Objects.hash;

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
        return hash(activeLine, passiveLine, imperativeAndForbiddingLine, adverbLine);
    }
}
