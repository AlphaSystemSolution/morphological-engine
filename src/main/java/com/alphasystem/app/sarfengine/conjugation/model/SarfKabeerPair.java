/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

/**
 * @author sali
 */
public class SarfKabeerPair {

    private final List<ArabicWord> rightSideTerms;

    private final List<ArabicWord> leftSideTerms;

    private final ArabicWord rightSideDefaultValue;

    private final ArabicWord leftSideDefaultValue;

    private final SarfTermType rightSideLabel;

    private final SarfTermType leftSideLabel;

    public SarfKabeerPair(ArabicWord[] leftSideTerms,
                          ArabicWord[] rightSideTerms, ArabicWord leftSideDefaultValue,
                          ArabicWord rightSideDefaultValue, SarfTermType leftSideLabel,
                          SarfTermType rightSideLabel) {
        this.rightSideTerms = new ArrayList<ArabicWord>();
        addAll(this.rightSideTerms, rightSideTerms);
        this.leftSideTerms = new ArrayList<ArabicWord>();
        addAll(this.leftSideTerms, leftSideTerms);
        this.rightSideLabel = rightSideLabel;
        this.leftSideLabel = leftSideLabel;
        this.rightSideDefaultValue = rightSideDefaultValue;
        this.leftSideDefaultValue = leftSideDefaultValue;
    }

    public ArabicWord getLeftSideDefaultValue() {
        return leftSideDefaultValue;
    }

    public SarfTermType getLeftSideLabel() {
        return leftSideLabel;
    }

    public List<ArabicWord> getLeftSideTerms() {
        return leftSideTerms;
    }

    public ArabicWord getRightSideDefaultValue() {
        return rightSideDefaultValue;
    }

    public SarfTermType getRightSideLabel() {
        return rightSideLabel;
    }

    public List<ArabicWord> getRightSideTerms() {
        return rightSideTerms;
    }
}
