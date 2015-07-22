package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.NamedTemplate;

/**
 * @author sali
 */
public abstract class AbstractRuleProcessor implements RuleProcessor {

    @SuppressWarnings("unused")
    protected final NamedTemplate template;
    @SuppressWarnings("unused")

    protected DiacriticType diacriticForWeakSecondRadicalWaw;
    @SuppressWarnings("unused")
    protected boolean pastTenseHasTransformed;

    public AbstractRuleProcessor(NamedTemplate template, DiacriticType diacriticForWeakSecondRadicalWaw,
                                 boolean pastTenseHasTransformed) {
        this.template = template;
        this.diacriticForWeakSecondRadicalWaw = diacriticForWeakSecondRadicalWaw;
        this.pastTenseHasTransformed = pastTenseHasTransformed;
    }

    @Override
    public DiacriticType getDiacriticForWeakSecondRadicalWaw() {
        return diacriticForWeakSecondRadicalWaw;
    }

    @Override
    public void setDiacriticForWeakSecondRadicalWaw(DiacriticType diacriticForWeakSecondRadicalWaw) {
        this.diacriticForWeakSecondRadicalWaw = diacriticForWeakSecondRadicalWaw;
    }

    @Override
    public boolean isPastTenseHasTransformed() {
        return pastTenseHasTransformed;
    }

    @Override
    public void setPastTenseHasTransformed(boolean pastTenseHasTransformed) {
        this.pastTenseHasTransformed = pastTenseHasTransformed;
    }
}
