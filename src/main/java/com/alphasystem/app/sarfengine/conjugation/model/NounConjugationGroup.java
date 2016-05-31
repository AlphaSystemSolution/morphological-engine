package com.alphasystem.app.sarfengine.conjugation.model;

/**
 * @author sali
 */
public class NounConjugationGroup {

    protected NounConjugation singular;

    protected NounConjugation dual;

    protected NounConjugation plural;

    public NounConjugationGroup() {
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
