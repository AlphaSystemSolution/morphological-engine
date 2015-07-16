/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.sarfengine.xml.model.SarfTermType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

/**
 * @author sali
 */
public class SarfKabeerPair {

    private final List<ConjugationMember> rightSideTerms;

    private final List<ConjugationMember> leftSideTerms;

    private final ConjugationMember rightSideDefaultValue;

    private final ConjugationMember leftSideDefaultValue;

    private final SarfTermType rightSideLabel;

    private final SarfTermType leftSideLabel;

    public SarfKabeerPair(ConjugationMember[] leftSideTerms,
                          ConjugationMember[] rightSideTerms, ConjugationMember leftSideDefaultValue,
                          ConjugationMember rightSideDefaultValue, SarfTermType leftSideLabel,
                          SarfTermType rightSideLabel) {
        this.rightSideTerms = new ArrayList<>();
        addAll(this.rightSideTerms, rightSideTerms);
        this.leftSideTerms = new ArrayList<>();
        addAll(this.leftSideTerms, leftSideTerms);
        this.rightSideLabel = rightSideLabel;
        this.leftSideLabel = leftSideLabel;
        this.rightSideDefaultValue = rightSideDefaultValue;
        this.leftSideDefaultValue = leftSideDefaultValue;
    }

    public ConjugationMember getLeftSideDefaultValue() {
        return leftSideDefaultValue;
    }

    public SarfTermType getLeftSideLabel() {
        return leftSideLabel;
    }

    public List<ConjugationMember> getLeftSideTerms() {
        return leftSideTerms;
    }

    public ConjugationMember getRightSideDefaultValue() {
        return rightSideDefaultValue;
    }

    public SarfTermType getRightSideLabel() {
        return rightSideLabel;
    }

    public List<ConjugationMember> getRightSideTerms() {
        return rightSideTerms;
    }
}
