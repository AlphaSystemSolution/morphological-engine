/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

/**
 * @author sali
 */
public class SarfTerm {

    private ArabicWord[] values;

    private SarfTermType sarfTermType;

    private ArabicWord label;

    private ArabicWord defaultValue;

    public SarfTerm() {
    }

    /**
     * @param sarfTermType
     * @param defaultValue
     * @param values
     */
    public SarfTerm(SarfTermType sarfTermType, ArabicWord defaultValue,
                    ArabicWord... values) {
        this.sarfTermType = sarfTermType;
        this.label = this.sarfTermType == null ? null : this.sarfTermType
                .getLabel();
        this.defaultValue = defaultValue;
        this.values = values;
    }

    public ArabicWord getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(ArabicWord defaultValue) {
        this.defaultValue = defaultValue;
    }

    public ArabicWord getLabel() {
        return label;
    }

    public void setLabel(ArabicWord label) {
        this.label = label;
    }

    public SarfTermType getSarfTermType() {
        return sarfTermType;
    }

    public void setSarfTermType(SarfTermType label) {
        this.sarfTermType = label;
    }

    public ArabicWord[] getValues() {
        return values;
    }

    public void setValues(ArabicWord[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return sarfTermType == null ? "NULL" : sarfTermType.name();
    }
}
