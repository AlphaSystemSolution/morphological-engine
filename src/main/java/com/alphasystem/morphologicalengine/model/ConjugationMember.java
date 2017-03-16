package com.alphasystem.morphologicalengine.model;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.SarfMemberType;

import java.io.Serializable;

import static com.alphasystem.util.AppUtil.isInstanceOf;
import static com.alphasystem.util.HashCodeUtil.hash;

/**
 * @author sali
 */
public class ConjugationMember implements Serializable, Comparable<ConjugationMember> {

    private final SarfMemberType memberType;

    private final ArabicWord conjugation;

    public ConjugationMember(SarfMemberType memberType, ArabicWord conjugation) {
        this.memberType = memberType;
        this.conjugation = conjugation;

    }

    @Override
    public boolean equals(Object obj) {
        boolean result = super.equals(obj);
        if (isInstanceOf(ConjugationMember.class, obj)) {
            ConjugationMember o = (ConjugationMember) obj;
            result = getMemberType().equals(o.getMemberType()) && getConjugation().equals(o.getConjugation());
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int hash = hash(super.hashCode(), memberType);
        return hash(hash, conjugation);
    }

    @Override
    public int compareTo(ConjugationMember o) {
        int result = 0;
        if (o == null) {
            result = 1;
        } else {
            result = getConjugation().compareTo(o.getConjugation());
        }
        return result;
    }

    public SarfMemberType getMemberType() {
        return memberType;
    }

    public ArabicWord getConjugation() {
        return conjugation;
    }
}
