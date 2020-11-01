package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.arabic.model.NamedTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.alphasystem.app.morphologicalengine.conjugation.model.NounOfPlaceAndTimeFactory.getByNamedTemplate;
import static com.alphasystem.app.morphologicalengine.conjugation.model.VerbalNounFactory.getByVerbalNoun;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.*;
import static org.apache.commons.lang3.ArrayUtils.addAll;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public enum Form {

    FORM_I_CATEGORY_A_GROUP_U_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE,
            VerbRootBaseFactory.FORM_I_PAST_TENSE_V1, VerbRootBaseFactory.FORM_I_PRESENT_TENSE_V1,
            VerbRootBaseFactory.FORM_I_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_I_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_I_IMPERATIVE_V1, VerbRootBaseFactory.FORM_I_FORBIDDING_V1,
            NounRootBaseFactory.FORM_I_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_I_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_PASSIVE_PARTICIPLE,
            null, getByNamedTemplate(NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE)),

    FORM_I_CATEGORY_A_GROUP_I_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE,
            VerbRootBaseFactory.FORM_I_PAST_TENSE_V1, VerbRootBaseFactory.FORM_I_PRESENT_TENSE_V2,
            VerbRootBaseFactory.FORM_I_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_I_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_I_IMPERATIVE_V2, VerbRootBaseFactory.FORM_I_FORBIDDING_V2,
            NounRootBaseFactory.FORM_I_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_I_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_PASSIVE_PARTICIPLE,
            null, getByNamedTemplate(NamedTemplate.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE)),

    FORM_I_CATEGORY_A_GROUP_A_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE,
            VerbRootBaseFactory.FORM_I_PAST_TENSE_V1, VerbRootBaseFactory.FORM_I_PRESENT_TENSE_V3,
            VerbRootBaseFactory.FORM_I_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_I_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_I_IMPERATIVE_V3, VerbRootBaseFactory.FORM_I_FORBIDDING_V3,
            NounRootBaseFactory.FORM_I_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_I_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_PASSIVE_PARTICIPLE,
            null, getByNamedTemplate(NamedTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE)),

    FORM_I_CATEGORY_U_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_U_TEMPLATE,
            VerbRootBaseFactory.FORM_I_PAST_TENSE_V2, VerbRootBaseFactory.FORM_I_PRESENT_TENSE_V1,
            VerbRootBaseFactory.FORM_I_IMPERATIVE_V1, VerbRootBaseFactory.FORM_I_FORBIDDING_V1,
            NounRootBaseFactory.FORM_I_CATEGORY_U_MASCULINE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_I_CATEGORY_U_FEMININE_ACTIVE_PARTICIPLE,
            null, getByNamedTemplate(NamedTemplate.FORM_I_CATEGORY_U_TEMPLATE)),

    FORM_I_CATEGORY_I_GROUP_A_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE,
            VerbRootBaseFactory.FORM_I_PAST_TENSE_V3, VerbRootBaseFactory.FORM_I_PRESENT_TENSE_V3,
            VerbRootBaseFactory.FORM_I_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_I_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_I_IMPERATIVE_V3, VerbRootBaseFactory.FORM_I_FORBIDDING_V3,
            NounRootBaseFactory.FORM_I_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_I_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_PASSIVE_PARTICIPLE,
            null, getByNamedTemplate(NamedTemplate.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE)),

    FORM_I_CATEGORY_I_GROUP_I_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE,
            VerbRootBaseFactory.FORM_I_PAST_TENSE_V3, VerbRootBaseFactory.FORM_I_PRESENT_TENSE_V2,
            VerbRootBaseFactory.FORM_I_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_I_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_I_IMPERATIVE_V2, VerbRootBaseFactory.FORM_I_FORBIDDING_V2,
            NounRootBaseFactory.FORM_I_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_I_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_I_FEMININE_PASSIVE_PARTICIPLE,
            null, getByNamedTemplate(NamedTemplate.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE)),

    FORM_II_TEMPLATE(NamedTemplate.FORM_II_TEMPLATE,
            VerbRootBaseFactory.FORM_II_PAST_TENSE, VerbRootBaseFactory.FORM_II_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_II_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_II_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_II_IMPERATIVE, VerbRootBaseFactory.FORM_II_FORBIDDING,
            NounRootBaseFactory.FORM_II_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_II_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_II_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_II_FEMININE_PASSIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_II)},
            getByNamedTemplate(NamedTemplate.FORM_II_TEMPLATE)),

    FORM_III_TEMPLATE(NamedTemplate.FORM_III_TEMPLATE,
            VerbRootBaseFactory.FORM_III_PAST_TENSE, VerbRootBaseFactory.FORM_III_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_III_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_III_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_III_IMPERATIVE, VerbRootBaseFactory.FORM_III_FORBIDDING,
            NounRootBaseFactory.FORM_III_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_III_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_III_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_III_FEMININE_PASSIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_III_V1), getByVerbalNoun(VERBAL_NOUN_FORM_III_V2)},
            getByNamedTemplate(NamedTemplate.FORM_III_TEMPLATE)),

    FORM_IV_TEMPLATE(NamedTemplate.FORM_IV_TEMPLATE,
            VerbRootBaseFactory.FORM_IV_PAST_TENSE, VerbRootBaseFactory.FORM_IV_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_IV_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_IV_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_IV_IMPERATIVE, VerbRootBaseFactory.FORM_IV_FORBIDDING,
            NounRootBaseFactory.FORM_IV_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_IV_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_IV_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_IV_FEMININE_PASSIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_IV)},
            getByNamedTemplate(NamedTemplate.FORM_IV_TEMPLATE)),

    FORM_V_TEMPLATE(NamedTemplate.FORM_V_TEMPLATE,
            VerbRootBaseFactory.FORM_V_PAST_TENSE, VerbRootBaseFactory.FORM_V_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_V_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_V_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_V_IMPERATIVE, VerbRootBaseFactory.FORM_V_FORBIDDING,
            NounRootBaseFactory.FORM_V_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_V_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_V_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_V_FEMININE_PASSIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_V)},
            getByNamedTemplate(NamedTemplate.FORM_V_TEMPLATE)),

    FORM_VI_TEMPLATE(NamedTemplate.FORM_VI_TEMPLATE,
            VerbRootBaseFactory.FORM_VI_PAST_TENSE, VerbRootBaseFactory.FORM_VI_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_VI_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_VI_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_VI_IMPERATIVE, VerbRootBaseFactory.FORM_VI_FORBIDDING,
            NounRootBaseFactory.FORM_VI_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_VI_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_VI_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_VI_FEMININE_PASSIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_VI)},
            getByNamedTemplate(NamedTemplate.FORM_VI_TEMPLATE)),

    FORM_VII_TEMPLATE(NamedTemplate.FORM_VII_TEMPLATE,
            VerbRootBaseFactory.FORM_VII_PAST_TENSE, VerbRootBaseFactory.FORM_VII_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_VII_IMPERATIVE, VerbRootBaseFactory.FORM_VII_FORBIDDING,
            NounRootBaseFactory.FORM_VII_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_VII_FEMININE_ACTIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_VII)},
            getByNamedTemplate(NamedTemplate.FORM_VII_TEMPLATE)),

    FORM_VIII_TEMPLATE(NamedTemplate.FORM_VIII_TEMPLATE,
            VerbRootBaseFactory.FORM_VIII_PAST_TENSE, VerbRootBaseFactory.FORM_VIII_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_VIII_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_VIII_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_VIII_IMPERATIVE, VerbRootBaseFactory.FORM_VIII_FORBIDDING,
            NounRootBaseFactory.FORM_VIII_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_VIII_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_VIII_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_VIII_FEMININE_PASSIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_VIII)},
            getByNamedTemplate(NamedTemplate.FORM_VIII_TEMPLATE)),

    FORM_IX_TEMPLATE(NamedTemplate.FORM_IX_TEMPLATE,
            VerbRootBaseFactory.FORM_IX_PAST_TENSE, VerbRootBaseFactory.FORM_IX_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_IX_IMPERATIVE, VerbRootBaseFactory.FORM_IX_FORBIDDING,
            NounRootBaseFactory.FORM_IX_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_IX_FEMININE_ACTIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_IX)}, null),

    FORM_X_TEMPLATE(NamedTemplate.FORM_X_TEMPLATE,
            VerbRootBaseFactory.FORM_X_PAST_TENSE, VerbRootBaseFactory.FORM_X_PRESENT_TENSE,
            VerbRootBaseFactory.FORM_X_PAST_PASSIVE_TENSE, VerbRootBaseFactory.FORM_X_PRESENT_PASSIVE_TENSE,
            VerbRootBaseFactory.FORM_X_IMPERATIVE, VerbRootBaseFactory.FORM_X_FORBIDDING,
            NounRootBaseFactory.FORM_X_MASCULINE_ACTIVE_PARTICIPLE, NounRootBaseFactory.FORM_X_FEMININE_ACTIVE_PARTICIPLE,
            NounRootBaseFactory.FORM_X_MASCULINE_PASSIVE_PARTICIPLE, NounRootBaseFactory.FORM_X_FEMININE_PASSIVE_PARTICIPLE,
            new NounRootBase[]{getByVerbalNoun(VERBAL_NOUN_FORM_X)},
            getByNamedTemplate(NamedTemplate.FORM_X_TEMPLATE));

    private static final Map<NamedTemplate, Form> CACHED_VALUES = new HashMap<>();

    static {
        for (Form form : values()) {
            CACHED_VALUES.put(form.getTemplate(), form);
        }
    }

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
    private final NounRootBase[] verbalNouns;
    private final NounRootBase[] adverbs;

    Form(NamedTemplate template, VerbRootBase pastTense, VerbRootBase presentTense, VerbRootBase pastPassiveTense,
         VerbRootBase presentPassiveTense, VerbRootBase imperative, VerbRootBase forbidding,
         NounRootBase activeParticipleMasculine, NounRootBase activeParticipleFeminine, NounRootBase passiveParticipleMasculine,
         NounRootBase passiveParticipleFeminine, NounRootBase[] verbalNouns, NounRootBase[] adverbs) {
        this.template = template;
        this.pastTense = pastTense;
        this.presentTense = presentTense;
        this.pastPassiveTense = pastPassiveTense;
        this.presentPassiveTense = presentPassiveTense;
        this.imperative = imperative;
        this.forbidding = forbidding;
        this.activeParticipleMasculine = activeParticipleMasculine;
        this.activeParticipleFeminine = activeParticipleFeminine;
        this.passiveParticipleMasculine = passiveParticipleMasculine;
        this.passiveParticipleFeminine = passiveParticipleFeminine;
        this.verbalNouns = isEmpty(verbalNouns) ? new NounRootBase[0] : addAll(null, verbalNouns);
        this.adverbs = isEmpty(adverbs) ? new NounRootBase[0] : addAll(null, adverbs);
    }

    Form(NamedTemplate template, VerbRootBase pastTense, VerbRootBase presentTense, VerbRootBase imperative, VerbRootBase forbidding,
         NounRootBase activeParticipleMasculine, NounRootBase activeParticipleFeminine, NounRootBase[] verbalNouns,
         NounRootBase[] adverbs) {
        this(template, pastTense, presentTense, null, null, imperative, forbidding, activeParticipleMasculine,
                activeParticipleFeminine, null, null, verbalNouns, adverbs);
    }

    public static Form getByTemplate(NamedTemplate template) {
        return CACHED_VALUES.get(template);
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

    public NounRootBase[] getVerbalNouns() {
        return verbalNouns;
    }

    public NounRootBase[] getAdverbs() {
        return adverbs;
    }
}
