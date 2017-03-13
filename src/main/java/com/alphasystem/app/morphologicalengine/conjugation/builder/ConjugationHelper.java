package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbalNounFactory;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.ConjugationData;
import com.alphasystem.morphologicalanalysis.morphology.model.MorphologicalEntry;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

import java.util.ArrayList;
import java.util.List;

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
            verbalNounRoots = new NounRootBase[verbalNouns.size()];
            for (int i = 0; i < verbalNouns.size(); i++) {
                verbalNounRoots[i] = new NounRootBase(VerbalNounFactory.getByVerbalNoun(verbalNouns.get(i)));
            }
        }
        return getConjugationRoots(conjugationData.getTemplate(), conjugationData.getRootLetters(),
                conjugationData.getTranslation(), verbalNounRoots, null);
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
        return getConjugationRoots(morphologicalEntry.getForm(), morphologicalEntry.getRootLetters(),
                morphologicalEntry.getShortTranslation(), verbalNounRoots, null);
    }


}
