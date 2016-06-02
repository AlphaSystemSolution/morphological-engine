package com.alphasystem.app.sarfengine.conjugation.model;

/**
 * @author sali
 */
public final class NounConjugationGroup {

    private NounConjugation singular;

    private NounConjugation dual;

    private NounConjugation plural;

    public NounConjugationGroup() {
        this(null, null, null);
    }

    public NounConjugationGroup(NounConjugation singular, NounConjugation dual, NounConjugation plural) {
        setSingular(singular);
        setDual(dual);
        setPlural(plural);
    }

    public NounConjugation getSingular() {
        return singular;
    }

    public void setSingular(NounConjugation singular) {
        this.singular = singular;
    }

    public NounConjugation getDual() {
        return dual;
    }

    public void setDual(NounConjugation dual) {
        this.dual = dual;
    }

    public NounConjugation getPlural() {
        return plural;
    }

    public void setPlural(NounConjugation plural) {
        this.plural = plural;
    }
}
