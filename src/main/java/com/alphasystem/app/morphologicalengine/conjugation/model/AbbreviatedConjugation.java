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

    private ConjugationHeader conjugationHeader;

    private ActiveLine activeLine;

    private PassiveLine passiveLine;

    private ImperativeAndForbiddingLine imperativeAndForbiddingLine;

    private AdverbLine adverbLine;

    public ConjugationHeader getConjugationHeader() {
        return conjugationHeader;
    }

    public void setConjugationHeader(ConjugationHeader conjugationHeader) {
        this.conjugationHeader = conjugationHeader;
    }

    public ActiveLine getActiveLine() {
        return activeLine;
    }

    public void setActiveLine(ActiveLine activeLine) {
        this.activeLine = activeLine;
    }

    public PassiveLine getPassiveLine() {
        return passiveLine;
    }

    public void setPassiveLine(PassiveLine passiveLine) {
        this.passiveLine = passiveLine;
    }

    public ImperativeAndForbiddingLine getImperativeAndForbiddingLine() {
        return imperativeAndForbiddingLine;
    }

    public void setImperativeAndForbiddingLine(ImperativeAndForbiddingLine imperativeAndForbiddingLine) {
        this.imperativeAndForbiddingLine = imperativeAndForbiddingLine;
    }

    public AdverbLine getAdverbLine() {
        return adverbLine;
    }

    public void setAdverbLine(AdverbLine adverbLine) {
        this.adverbLine = adverbLine;
    }

    @Override
    public int hashCode() {
        return hash(activeLine, passiveLine, imperativeAndForbiddingLine, adverbLine);
    }
}
