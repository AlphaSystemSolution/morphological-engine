/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConjugationHeader {

    private RootLetters rootLetters;
    private ChartMode chartMode;
    private String baseWord;
    private String pastTenseRoot;
    private String presentTenseRoot;
    private String translation;
    private String title;
    private String typeLabel1;
    private String typeLabel2;
    private String typeLabel3;

    public String getPastTenseRoot() {
        return pastTenseRoot;
    }

    public void setPastTenseRoot(String pastTenseRoot) {
        this.pastTenseRoot = pastTenseRoot;
    }

    public String getPresentTenseRoot() {
        return presentTenseRoot;
    }

    public void setPresentTenseRoot(String presentTenseRoot) {
        this.presentTenseRoot = presentTenseRoot;
    }

    public RootLetters getRootLetters() {
        return rootLetters;
    }

    public void setRootLetters(RootLetters rootLetters) {
        this.rootLetters = rootLetters;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBaseWord() {
        return baseWord;
    }

    public void setBaseWord(String baseWord) {
        this.baseWord = baseWord;
    }

    public ChartMode getChartMode() {
        return chartMode;
    }

    public void setChartMode(ChartMode chartMode) {
        this.chartMode = chartMode;
    }

    public String getTypeLabel1() {
        return typeLabel1;
    }

    public void setTypeLabel1(String typeLabel1) {
        this.typeLabel1 = typeLabel1;
    }

    public String getTypeLabel2() {
        return typeLabel2;
    }

    public void setTypeLabel2(String typeLabel2) {
        this.typeLabel2 = typeLabel2;
    }

    public String getTypeLabel3() {
        return typeLabel3;
    }

    public void setTypeLabel3(String typeLabel3) {
        this.typeLabel3 = typeLabel3;
    }
}
