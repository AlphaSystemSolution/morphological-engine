package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.NounRootBase;
import com.alphasystem.morphologicalanalysis.morphology.model.VerbRootBase;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.Noun.*;

/**
 * @author sali
 */
public enum Form {

    FORM_I_CATEGORY_A_GROUP_U_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_I_CATEGORY_A_GROUP_I_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_I_CATEGORY_A_GROUP_A_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_I_CATEGORY_U_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_U_TEMPLATE,
            null, null,
            new NounRootBase(FORM_I_CATEGORY_U_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_CATEGORY_U_FEMININE_ACTIVE_PARTICIPLE), null, null),

    FORM_I_CATEGORY_I_GROUP_A_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_I_CATEGORY_I_GROUP_I_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_II_TEMPLATE(NamedTemplate.FORM_II_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_II_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_II_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_II_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_II_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_III_TEMPLATE(NamedTemplate.FORM_III_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_III_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_III_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_III_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_III_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_IV_TEMPLATE(NamedTemplate.FORM_IV_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_IV_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_IV_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_IV_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_IV_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_V_TEMPLATE(NamedTemplate.FORM_V_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_V_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_V_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_V_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_V_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_VI_TEMPLATE(NamedTemplate.FORM_VI_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_VI_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_VI_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_VI_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_VI_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_VII_TEMPLATE(NamedTemplate.FORM_VII_TEMPLATE,
            null, null,
            new NounRootBase(FORM_VII_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_VII_FEMININE_ACTIVE_PARTICIPLE), null, null),

    FORM_VIII_TEMPLATE(NamedTemplate.FORM_VIII_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_VIII_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_VIII_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_VIII_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_VIII_FEMININE_PASSIVE_PARTICIPLE), null, null),

    FORM_IX_TEMPLATE(NamedTemplate.FORM_IX_TEMPLATE,
            null, null,
            new NounRootBase(FORM_IX_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_IX_FEMININE_ACTIVE_PARTICIPLE), null, null),

    FORM_X_TEMPLATE(NamedTemplate.FORM_X_TEMPLATE,
            null, null, null, null,
            new NounRootBase(FORM_X_MASCULINE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_X_FEMININE_ACTIVE_PARTICIPLE),
            new NounRootBase(FORM_X_MASCULINE_PASSIVE_PARTICIPLE),
            new NounRootBase(FORM_X_FEMININE_PASSIVE_PARTICIPLE), null, null);

    private final NamedTemplate template;
    private final VerbRootBase pastTense;
    private final VerbRootBase presentTense;
    private final VerbRootBase pastPassiveTense;
    private final VerbRootBase presentPassiveTense;
    private final NounRootBase activeParticipleMasculine;
    private final NounRootBase activeParticipleFeminine;
    private final NounRootBase passiveParticipleMasculine;
    private final NounRootBase passiveParticipleFeminine;
    private final VerbRootBase imperative;
    private final VerbRootBase forbidding;

    Form(NamedTemplate template, VerbRootBase pastTense, VerbRootBase presentTense, VerbRootBase pastPassiveTense,
         VerbRootBase presentPassiveTense, NounRootBase activeParticipleMasculine, NounRootBase activeParticipleFeminine,
         NounRootBase passiveParticipleMasculine, NounRootBase passiveParticipleFeminine, VerbRootBase imperative,
         VerbRootBase forbidding) {
        this.template = template;
        this.pastTense = pastTense;
        this.presentTense = presentTense;
        this.pastPassiveTense = pastPassiveTense;
        this.presentPassiveTense = presentPassiveTense;
        this.activeParticipleMasculine = activeParticipleMasculine;
        this.activeParticipleFeminine = activeParticipleFeminine;
        this.passiveParticipleMasculine = passiveParticipleMasculine;
        this.passiveParticipleFeminine = passiveParticipleFeminine;
        this.imperative = imperative;
        this.forbidding = forbidding;
    }

    Form(NamedTemplate template, VerbRootBase pastTense, VerbRootBase presentTense, NounRootBase activeParticipleMasculine,
         NounRootBase activeParticipleFeminine, VerbRootBase imperative, VerbRootBase forbidding) {
        this(template, pastTense, presentTense, null, null, activeParticipleMasculine, activeParticipleFeminine, null,
                null, imperative, forbidding);
    }

    public NamedTemplate getTemplate() {
        return template;
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
}
