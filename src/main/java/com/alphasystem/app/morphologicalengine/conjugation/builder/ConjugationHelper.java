package com.alphasystem.app.morphologicalengine.conjugation.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbalNounFactory;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationData;
import com.alphasystem.morphologicalanalysis.morphology.model.MorphologicalEntry;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

/**
 * @author sali
 */
public final class ConjugationHelper {

    /**
     * Do not let any one instantiate this class;
     */
    private ConjugationHelper() {
    }

    /**
     * Creates new {@link ConjugationRoots} and populates from given {@link NamedTemplate}.
     *
     * @param template {@link NamedTemplate}
     * @return populated {@link ConjugationRoots}
     * @throws NullPointerException if given <code>template</code> is null
     */
    public static ConjugationRoots applyTemplate(NamedTemplate template) {
        return applyTemplate(new ConjugationRoots(), template);
    }

    /**
     * Populates {@link ConjugationRoots} from given {@link NamedTemplate}.
     *
     * @param source   source source object to populate
     * @param template {@link NamedTemplate}
     * @return populated {@link ConjugationRoots}
     * @throws NullPointerException if given <code>template</code> is null
     */
    public static ConjugationRoots applyTemplate(ConjugationRoots source, NamedTemplate template) {
        if (template == null) {
            throw new NullPointerException("Template cannot be null.");
        }
        return applyTemplate(source, Form.getByTemplate(template));
    }

    /**
     * Creates new {@link ConjugationRoots} and populates from given {@link Form}.
     *
     * @param form {@link Form} template to populate values from
     * @return populated {@link ConjugationRoots}
     * @throws NullPointerException if given <code>form</code> is null
     */
    public static ConjugationRoots applyTemplate(Form form) {
        return applyTemplate(new ConjugationRoots(), form);
    }

    /**
     * Populates {@link ConjugationRoots} from given {@link Form}.
     *
     * @param source source object to populate
     * @param form   {@link Form} template to populate values from
     * @return populated {@link ConjugationRoots}
     * @throws NullPointerException if given <code>form</code> is null
     */
    public static ConjugationRoots applyTemplate(ConjugationRoots source, Form form) {
        if (form == null) {
            throw new NullPointerException("Form cannot be null.");
        }
        if (source == null) {
            source = new ConjugationRoots();
        }
        return source.template(form.getTemplate()).pastTense(form.getPastTense()).
                presentTense(form.getPresentTense()).pastPassiveTense(form.getPastPassiveTense())
                .presentPassiveTense(form.getPresentPassiveTense())
                .activeParticipleMasculine(form.getActiveParticipleMasculine())
                .activeParticipleFeminine(form.getActiveParticipleFeminine())
                .passiveParticipleMasculine(form.getPassiveParticipleMasculine())
                .passiveParticipleFeminine(form.getPassiveParticipleFeminine())
                .imperative(form.getImperative()).forbidding(form.getForbidding())
                .verbalNouns(true, form.getVerbalNouns()).adverbs(true, form.getAdverbs());
    }

    public static ConjugationRoots getConjugationRoots(NamedTemplate template, RootLetters rootLetters, String translation) {
        return getConjugationRoots(template, rootLetters, translation, null, null);
    }

    public static ConjugationRoots getConjugationRoots(NamedTemplate template, RootLetters rootLetters, String translation,
                                                       NounRootBase[] verbalNouns, NounRootBase[] adverbs) {
        final ConjugationRoots conjugationRoots = applyTemplate(null, template);
        conjugationRoots.setRootLetters(new RootLetters(rootLetters));
        conjugationRoots.translation(translation);
        if (verbalNouns != null) {
            conjugationRoots.setVerbalNouns(verbalNouns);
        }
        if (adverbs != null) {
            conjugationRoots.setAdverbs(adverbs);
        }
        return conjugationRoots;
    }

    public static ConjugationRoots getConjugationRoots(ConjugationData conjugationData) {
        if (conjugationData == null) {
            return null;
        }
        NounRootBase[] verbalNounRoots = null;
        final List<VerbalNoun> verbalNouns = conjugationData.getVerbalNouns();
        if (verbalNouns != null && !verbalNouns.isEmpty()) {
            verbalNounRoots = VerbalNounFactory.getByVerbalNouns(verbalNouns);
        }
        final ConjugationRoots conjugationRoots = getConjugationRoots(conjugationData.getTemplate(),
                conjugationData.getRootLetters(), conjugationData.getTranslation(), verbalNounRoots, null);
        conjugationRoots.setConjugationConfiguration(conjugationData.getConfiguration());
        return conjugationRoots;
    }

    public static ConjugationRoots getConjugationRoots(MorphologicalEntry morphologicalEntry) {
        if (morphologicalEntry == null) {
            return null;
        }
        NounRootBase[] verbalNounRoots = null;
        final List<VerbalNoun> verbalNouns = new ArrayList<>(morphologicalEntry.getVerbalNouns());
        if (!verbalNouns.isEmpty()) {
            verbalNounRoots = new NounRootBase[verbalNouns.size()];
            for (int i = 0; i < verbalNouns.size(); i++) {
                verbalNounRoots[i] = new NounRootBase(VerbalNounFactory.getByVerbalNoun(verbalNouns.get(i)));
            }
        }
        final ConjugationRoots conjugationRoots = getConjugationRoots(morphologicalEntry.getForm(),
                morphologicalEntry.getRootLetters(), morphologicalEntry.getShortTranslation(), verbalNounRoots, null);
        conjugationRoots.setConjugationConfiguration(conjugationRoots.getConjugationConfiguration());
        return conjugationRoots;
    }

    public static VerbRootBase[] getVerbRootBases(SarfTermType sarfTermType, Form form) {
        List<VerbRootBase> roots = new ArrayList<>();

        switch (sarfTermType) {
            case PAST_TENSE:
                roots.add(form.getPastTense());
                break;
            case PRESENT_TENSE:
                roots.add(form.getPresentTense());
                break;
            case PAST_PASSIVE_TENSE:
                roots.add(form.getPastPassiveTense());
                break;
            case PRESENT_PASSIVE_TENSE:
                roots.add(form.getPresentPassiveTense());
                break;
            case IMPERATIVE:
                roots.add(form.getImperative());
                break;
            case FORBIDDING:
                roots.add(form.getForbidding());
                break;
        }

        return roots.toArray(new VerbRootBase[roots.size()]);
    }

    public static NounRootBase[] getNounRootBase(SarfTermType sarfTermType, Form form, VerbalNoun[] patterns) {
        List<NounRootBase> roots = new ArrayList<>();
        switch (sarfTermType) {
            case VERBAL_NOUN:
                NounRootBase[] verbalNouns;
                if (!ArrayUtils.isEmpty(patterns)) {
                    verbalNouns = VerbalNounFactory.getByVerbalNouns(Arrays.asList(patterns));
                } else {
                    verbalNouns = form.getVerbalNouns();
                }
                if (!ArrayUtils.isEmpty(verbalNouns)) {
                    Collections.addAll(roots, verbalNouns);
                }
                break;
            case ACTIVE_PARTICIPLE_MASCULINE:
                roots.add(form.getActiveParticipleMasculine());
                break;
            case ACTIVE_PARTICIPLE_FEMININE:
                roots.add(form.getActiveParticipleFeminine());
                break;
            case PASSIVE_PARTICIPLE_MASCULINE:
                roots.add(form.getPassiveParticipleMasculine());
                break;
            case PASSIVE_PARTICIPLE_FEMININE:
                roots.add(form.getPassiveParticipleFeminine());
                break;
            case NOUN_OF_PLACE_AND_TIME:
                final NounRootBase[] adverbs = form.getAdverbs();
                if (!ArrayUtils.isEmpty(adverbs)) {
                    Collections.addAll(roots, adverbs);
                }
                break;
        }

        return roots.toArray(new NounRootBase[roots.size()]);
    }


}
