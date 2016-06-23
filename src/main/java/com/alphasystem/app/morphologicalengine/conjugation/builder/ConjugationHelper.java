package com.alphasystem.app.morphologicalengine.conjugation.builder;

import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.arabic.model.NamedTemplate;

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
}
