package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ChartConfiguration;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationConfiguration;
import org.apache.commons.lang3.ArrayUtils;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public final class ConjugationRoots {

    NamedTemplate template;
    ConjugationConfiguration conjugationConfiguration;
    ChartConfiguration chartConfiguration;
    String translation;
    VerbRootBase pastTense;
    VerbRootBase presentTense;
    VerbRootBase pastPassiveTense;
    VerbRootBase presentPassiveTense;
    NounRootBase activeParticipleMasculine;
    NounRootBase activeParticipleFeminine;
    NounRootBase passiveParticipleMasculine;
    NounRootBase passiveParticipleFeminine;
    VerbRootBase imperative;
    VerbRootBase forbidding;
    NounRootBase[] verbalNouns;
    NounRootBase[] adverbs;

    public ConjugationRoots() {
        setChartConfiguration(null);
        setConjugationConfiguration(null);
    }

    public NamedTemplate getTemplate() {
        return template;
    }

    public void setTemplate(NamedTemplate template) {
        this.template = template;
    }

    public ConjugationConfiguration getConjugationConfiguration() {
        return conjugationConfiguration;
    }

    public ChartConfiguration getChartConfiguration() {
        return chartConfiguration;
    }

    public String getTranslation() {
        return translation;
    }

    public VerbRootBase getPastTense() {
        return pastTense;
    }

    public VerbRootBase getPresentTense() {
        return presentTense;
    }

    public VerbRootBase getPastPassiveTense() {
        return pastPassiveTense;
    }

    public VerbRootBase getPresentPassiveTense() {
        return presentPassiveTense;
    }

    public NounRootBase getActiveParticipleMasculine() {
        return activeParticipleMasculine;
    }

    public NounRootBase getActiveParticipleFeminine() {
        return activeParticipleFeminine;
    }

    public NounRootBase getPassiveParticipleMasculine() {
        return passiveParticipleMasculine;
    }

    public NounRootBase getPassiveParticipleFeminine() {
        return passiveParticipleFeminine;
    }

    public VerbRootBase getImperative() {
        return imperative;
    }

    public VerbRootBase getForbidding() {
        return forbidding;
    }

    public NounRootBase[] getVerbalNouns() {
        return verbalNouns;
    }

    public NounRootBase[] getAdverbs() {
        return adverbs;
    }

    public void setConjugationConfiguration(ConjugationConfiguration conjugationConfiguration) {
        this.conjugationConfiguration = (conjugationConfiguration == null) ? new ConjugationConfiguration() : conjugationConfiguration;
    }

    public void setChartConfiguration(ChartConfiguration chartConfiguration) {
        this.chartConfiguration = (chartConfiguration == null) ? new ChartConfiguration() : chartConfiguration;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setPastTense(VerbRootBase pastTense) {
        this.pastTense = pastTense;
    }

    public void setPresentTense(VerbRootBase presentTense) {
        this.presentTense = presentTense;
    }

    public void setPastPassiveTense(VerbRootBase pastPassiveTense) {
        this.pastPassiveTense = pastPassiveTense;
    }

    public void setPresentPassiveTense(VerbRootBase presentPassiveTense) {
        this.presentPassiveTense = presentPassiveTense;
    }

    public void setActiveParticipleMasculine(NounRootBase activeParticipleMasculine) {
        this.activeParticipleMasculine = activeParticipleMasculine;
    }

    public void setActiveParticipleFeminine(NounRootBase activeParticipleFeminine) {
        this.activeParticipleFeminine = activeParticipleFeminine;
    }

    public void setPassiveParticipleMasculine(NounRootBase passiveParticipleMasculine) {
        this.passiveParticipleMasculine = passiveParticipleMasculine;
    }

    public void setPassiveParticipleFeminine(NounRootBase passiveParticipleFeminine) {
        this.passiveParticipleFeminine = passiveParticipleFeminine;
    }

    public void setImperative(VerbRootBase imperative) {
        this.imperative = imperative;
    }

    public void setForbidding(VerbRootBase forbidding) {
        this.forbidding = forbidding;
    }

    public void setVerbalNouns(NounRootBase[] verbalNouns) {
        this.verbalNouns = ArrayUtils.addAll(new NounRootBase[0], verbalNouns);
    }

    public void setAdverbs(NounRootBase[] adverbs) {
        this.adverbs = ArrayUtils.addAll(new NounRootBase[0], adverbs);
    }

    public ConjugationRoots template(NamedTemplate template){
        setTemplate(template);
        return this;
    }

    public ConjugationRoots conjugationConfiguration(ConjugationConfiguration conjugationConfiguration) {
        setConjugationConfiguration(conjugationConfiguration);
        return this;
    }

    public ConjugationRoots conjugationConfiguration(boolean removePassiveLine, boolean skipRuleProcessing) {
        setConjugationConfiguration(new ConjugationConfiguration().removePassiveLine(removePassiveLine)
                .skipRuleProcessing(skipRuleProcessing));
        return this;
    }

    public ConjugationRoots chartConfiguration(ChartConfiguration chartConfiguration) {
        setChartConfiguration(chartConfiguration);
        return this;
    }

    public ConjugationRoots translation(String translation) {
        setTranslation(translation);
        return this;
    }

    public ConjugationRoots pastTense(VerbRootBase pastTense) {
        setPastTense(pastTense);
        return this;
    }

    public ConjugationRoots presentTense(VerbRootBase presentTense) {
        setPresentTense(presentTense);
        return this;
    }

    public ConjugationRoots pastPassiveTense(VerbRootBase pastPassiveTense) {
        setPastPassiveTense(pastPassiveTense);
        return this;
    }

    public ConjugationRoots presentPassiveTense(VerbRootBase presentPassiveTense) {
        setPresentPassiveTense(presentPassiveTense);
        return this;
    }

    public ConjugationRoots activeParticipleMasculine(NounRootBase activeParticipleMasculine) {
        setActiveParticipleMasculine(activeParticipleMasculine);
        return this;
    }

    public ConjugationRoots activeParticipleFeminine(NounRootBase activeParticipleFeminine) {
        setActiveParticipleFeminine(activeParticipleFeminine);
        return this;
    }

    public ConjugationRoots passiveParticipleMasculine(NounRootBase passiveParticipleMasculine) {
        setPassiveParticipleMasculine(passiveParticipleMasculine);
        return this;
    }

    public ConjugationRoots passiveParticipleFeminine(NounRootBase passiveParticipleFeminine) {
        setPassiveParticipleFeminine(passiveParticipleFeminine);
        return this;
    }

    public ConjugationRoots imperative(VerbRootBase imperative) {
        setImperative(imperative);
        return this;
    }

    public ConjugationRoots forbidding(VerbRootBase forbidding) {
        setForbidding(forbidding);
        return this;
    }

    public ConjugationRoots verbalNouns(NounRootBase... verbalNouns) {
        if (!isEmpty(verbalNouns)) {
            this.verbalNouns = ArrayUtils.addAll(this.verbalNouns, verbalNouns);
        }
        return this;
    }

    public ConjugationRoots adverbs(NounRootBase... adverbs) {
        if (!isEmpty(adverbs)) {
            this.adverbs = ArrayUtils.addAll(this.adverbs, adverbs);
        }
        return this;
    }
}
