package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.*;

import java.util.HashMap;
import java.util.Map;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.BrokenPlural.BROKEN_PLURAL_V12;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.Noun.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.Verb.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.*;
import static org.apache.commons.lang3.ArrayUtils.addAll;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * @author sali
 */
public enum Form {

    FORM_I_CATEGORY_A_GROUP_U_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE,
            new VerbRootBase(FORM_I_PAST_TENSE_V1), new VerbRootBase(FORM_I_PRESENT_TENSE_V1),
            new VerbRootBase(FORM_I_PAST_PASSIVE_TENSE), new VerbRootBase(FORM_I_PRESENT_PASSIVE_TENSE),
            new ParticipleRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null, null,
            new NounRootBase[]{new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V1, BROKEN_PLURAL_V12),
                    new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V2, BROKEN_PLURAL_V12),
                    new AdverbRootBase(NOUN_OF_PLACE_AND_TIME_V3)}),

    FORM_I_CATEGORY_A_GROUP_I_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE,
            new VerbRootBase(FORM_I_PAST_TENSE_V1), new VerbRootBase(FORM_I_PRESENT_TENSE_V2),
            new VerbRootBase(FORM_I_PAST_PASSIVE_TENSE), new VerbRootBase(FORM_I_PRESENT_PASSIVE_TENSE),
            new ParticipleRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null, null,
            new NounRootBase[]{new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V1, BROKEN_PLURAL_V12),
                    new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V2, BROKEN_PLURAL_V12),
                    new AdverbRootBase(NOUN_OF_PLACE_AND_TIME_V3)}),

    FORM_I_CATEGORY_A_GROUP_A_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE,
            new VerbRootBase(FORM_I_PAST_TENSE_V1), null,
            new VerbRootBase(FORM_I_PAST_PASSIVE_TENSE), new VerbRootBase(FORM_I_PRESENT_PASSIVE_TENSE),
            new ParticipleRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null, null,
            new NounRootBase[]{new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V1, BROKEN_PLURAL_V12),
                    new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V2, BROKEN_PLURAL_V12),
                    new AdverbRootBase(NOUN_OF_PLACE_AND_TIME_V3)}),

    FORM_I_CATEGORY_U_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_U_TEMPLATE,
            new VerbRootBase(FORM_I_PAST_TENSE_V2), new VerbRootBase(FORM_I_PRESENT_TENSE_V1),
            new ParticipleRootBase(FORM_I_CATEGORY_U_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_CATEGORY_U_FEMININE_ACTIVE_PARTICIPLE), null, null, null,
            new NounRootBase[]{new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V1, BROKEN_PLURAL_V12),
                    new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V2, BROKEN_PLURAL_V12),
                    new AdverbRootBase(NOUN_OF_PLACE_AND_TIME_V3)}),

    FORM_I_CATEGORY_I_GROUP_A_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE,
            new VerbRootBase(FORM_I_PAST_TENSE_V3), new VerbRootBase(FORM_I_PRESENT_TENSE_V3),
            new VerbRootBase(FORM_I_PAST_PASSIVE_TENSE), new VerbRootBase(FORM_I_PRESENT_PASSIVE_TENSE),
            new ParticipleRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null, null,
            new NounRootBase[]{new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V1, BROKEN_PLURAL_V12),
                    new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V2, BROKEN_PLURAL_V12),
                    new AdverbRootBase(NOUN_OF_PLACE_AND_TIME_V3)}),

    FORM_I_CATEGORY_I_GROUP_I_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE,
            new VerbRootBase(FORM_I_PAST_TENSE_V3), new VerbRootBase(FORM_I_PRESENT_TENSE_V2),
            new VerbRootBase(FORM_I_PAST_PASSIVE_TENSE), new VerbRootBase(FORM_I_PRESENT_PASSIVE_TENSE),
            new ParticipleRootBase(FORM_I_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_I_FEMININE_PASSIVE_PARTICIPLE), null, null, null,
            new NounRootBase[]{new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V1, BROKEN_PLURAL_V12),
                    new BrokenPluralAdverbRootBase(NOUN_OF_PLACE_AND_TIME_V2, BROKEN_PLURAL_V12),
                    new AdverbRootBase(NOUN_OF_PLACE_AND_TIME_V3)}),

    FORM_II_TEMPLATE(NamedTemplate.FORM_II_TEMPLATE,
            null, null, null, null,
            new ParticipleRootBase(FORM_II_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_II_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_II_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_II_FEMININE_PASSIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_II)},
            new NounRootBase[]{new ParticipleRootBase(FORM_II_MASCULINE_PASSIVE_PARTICIPLE, FORM_II_FEMININE_PASSIVE_PARTICIPLE)}),

    FORM_III_TEMPLATE(NamedTemplate.FORM_III_TEMPLATE,
            null, null, null, null,
            new ParticipleRootBase(FORM_III_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_III_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_III_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_III_FEMININE_PASSIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_III_V1), new VerbalNounRootBase(VERBAL_NOUN_FORM_III_V2)},
            new NounRootBase[]{new ParticipleRootBase(FORM_III_MASCULINE_PASSIVE_PARTICIPLE, FORM_III_FEMININE_PASSIVE_PARTICIPLE)}),

    FORM_IV_TEMPLATE(NamedTemplate.FORM_IV_TEMPLATE,
            null, null, null, null,
            new ParticipleRootBase(FORM_IV_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_IV_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_IV_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_IV_FEMININE_PASSIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_IV)},
            new NounRootBase[]{new ParticipleRootBase(FORM_IV_MASCULINE_PASSIVE_PARTICIPLE, FORM_IV_FEMININE_PASSIVE_PARTICIPLE)}),

    FORM_V_TEMPLATE(NamedTemplate.FORM_V_TEMPLATE,
            null, null, null, null,
            new ParticipleRootBase(FORM_V_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_V_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_V_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_V_FEMININE_PASSIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_V)},
            new NounRootBase[]{new ParticipleRootBase(FORM_V_MASCULINE_PASSIVE_PARTICIPLE, FORM_V_FEMININE_PASSIVE_PARTICIPLE)}),

    FORM_VI_TEMPLATE(NamedTemplate.FORM_VI_TEMPLATE,
            null, null, null, null,
            new ParticipleRootBase(FORM_VI_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_VI_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_VI_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_VI_FEMININE_PASSIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_VI)},
            new NounRootBase[]{new ParticipleRootBase(FORM_VI_MASCULINE_PASSIVE_PARTICIPLE, FORM_VI_FEMININE_PASSIVE_PARTICIPLE)}),

    FORM_VII_TEMPLATE(NamedTemplate.FORM_VII_TEMPLATE,
            null, null,
            new ParticipleRootBase(FORM_VII_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_VII_FEMININE_ACTIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_VII)},
            new NounRootBase[]{new ParticipleRootBase(FORM_VII_MASCULINE_PASSIVE_PARTICIPLE, FORM_VII_FEMININE_PASSIVE_PARTICIPLE)}),

    FORM_VIII_TEMPLATE(NamedTemplate.FORM_VIII_TEMPLATE,
            null, null, null, null,
            new ParticipleRootBase(FORM_VIII_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_VIII_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_VIII_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_VIII_FEMININE_PASSIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_VIII)},
            new NounRootBase[]{new ParticipleRootBase(FORM_VIII_MASCULINE_PASSIVE_PARTICIPLE, FORM_VIII_FEMININE_PASSIVE_PARTICIPLE)}),

    FORM_IX_TEMPLATE(NamedTemplate.FORM_IX_TEMPLATE,
            null, null,
            new ParticipleRootBase(FORM_IX_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_IX_FEMININE_ACTIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_IX)}, null),

    FORM_X_TEMPLATE(NamedTemplate.FORM_X_TEMPLATE,
            null, null, null, null,
            new ParticipleRootBase(FORM_X_MASCULINE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_X_FEMININE_ACTIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_X_MASCULINE_PASSIVE_PARTICIPLE),
            new ParticipleRootBase(FORM_X_FEMININE_PASSIVE_PARTICIPLE), null, null,
            new NounRootBase[]{new VerbalNounRootBase(VERBAL_NOUN_FORM_X)},
            new NounRootBase[]{new ParticipleRootBase(FORM_X_MASCULINE_PASSIVE_PARTICIPLE, FORM_X_FEMININE_PASSIVE_PARTICIPLE)});

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
         VerbRootBase presentPassiveTense, NounRootBase activeParticipleMasculine, NounRootBase activeParticipleFeminine,
         NounRootBase passiveParticipleMasculine, NounRootBase passiveParticipleFeminine, VerbRootBase imperative,
         VerbRootBase forbidding, NounRootBase[] verbalNouns, NounRootBase[] adverbs) {
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
        this.verbalNouns = isEmpty(verbalNouns) ? new NounRootBase[0] : addAll(null, verbalNouns);
        this.adverbs = isEmpty(adverbs) ? new NounRootBase[0] : addAll(null, adverbs);
    }

    Form(NamedTemplate template, VerbRootBase pastTense, VerbRootBase presentTense, NounRootBase activeParticipleMasculine,
         NounRootBase activeParticipleFeminine, VerbRootBase imperative, VerbRootBase forbidding, NounRootBase[] verbalNouns,
         NounRootBase[] adverbs) {
        this(template, pastTense, presentTense, null, null, activeParticipleMasculine, activeParticipleFeminine, null,
                null, imperative, forbidding, verbalNouns, adverbs);
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
