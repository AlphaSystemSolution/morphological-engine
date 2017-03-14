/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ActiveLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.AdverbLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.ImperativeAndForbiddingLine;
import com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj.PassiveLine;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static java.util.Objects.hash;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbbreviatedConjugation {

    private final ConjugationHeader conjugationHeader;

    private final ActiveLine activeLine;

    private final PassiveLine passiveLine;

    private final ImperativeAndForbiddingLine imperativeAndForbiddingLine;

    private final AdverbLine adverbLine;

    public AbbreviatedConjugation(ConjugationHeader conjugationHeader, ActiveLine activeLine, PassiveLine passiveLine,
                                  ImperativeAndForbiddingLine commandLine, AdverbLine adverbLine) {
        this.conjugationHeader = conjugationHeader;
        this.activeLine = activeLine;
        this.passiveLine = passiveLine;
        this.imperativeAndForbiddingLine = commandLine;
        this.adverbLine = adverbLine;
    }

    public ConjugationHeader getConjugationHeader() {
        return conjugationHeader;
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
