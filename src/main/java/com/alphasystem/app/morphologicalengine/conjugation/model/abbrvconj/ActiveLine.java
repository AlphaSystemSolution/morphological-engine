/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model.abbrvconj;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static java.util.Objects.hash;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveLine {

    private String pastTense;

    private String presentTense;

    private String[] verbalNouns;

    private String verbalNoun;

    private String activeParticipleMasculine;

    private String activeParticipleValue;

    public String getPastTense() {
        return pastTense;
    }

    public void setPastTense(String pastTense) {
        this.pastTense = pastTense;
    }

    public String getPresentTense() {
        return presentTense;
    }

    public void setPresentTense(String presentTense) {
        this.presentTense = presentTense;
    }

    public String getVerbalNoun() {
        return verbalNoun;
    }

    public void setVerbalNoun(String verbalNoun) {
        this.verbalNoun = verbalNoun;
    }

    public String[] getVerbalNouns() {
        return verbalNouns;
    }

    public void setVerbalNouns(String[] verbalNouns) {
        this.verbalNouns = verbalNouns;
    }

    public String getActiveParticipleMasculine() {
        return activeParticipleMasculine;
    }

    public void setActiveParticipleMasculine(String activeParticipleMasculine) {
        this.activeParticipleMasculine = activeParticipleMasculine;
    }

    public String getActiveParticipleValue() {
        return activeParticipleValue;
    }

    public void setActiveParticipleValue(String activeParticipleValue) {
        this.activeParticipleValue = activeParticipleValue;
    }

    @Override
    public int hashCode() {
        return hash(pastTense, presentTense, activeParticipleMasculine, verbalNouns);
    }

}
