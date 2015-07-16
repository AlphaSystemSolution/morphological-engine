/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation;

import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;
import com.alphasystem.arabic.model.ArabicWord;

import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public abstract class TenseMemberBuilder extends
        AbstractConjugationMemberBuilder {

    @Override
    public ConjugationMember[] doConjugation() {
        ConjugationMember[] words = new ConjugationMember[15];

        words[2] = new ConjugationMember(THIRD_PERSON_MASCULINE_SINGULAR, thirdPersonMasculineSingular());
        words[1] = new ConjugationMember(THIRD_PERSON_MASCULINE_DUAL, thirdPersonMasculineDual());
        words[0] = new ConjugationMember(THIRD_PERSON_MASCULINE_PLURAL, thirdPersonMasculinePlural());

        words[5] = new ConjugationMember(THIRD_PERSON_FEMININE_SINGULAR, thirdPersonFeminineSingular());
        words[4] = new ConjugationMember(THIRD_PERSON_FEMININE_DUAL, thirdPersonFeminineDual());
        words[3] = new ConjugationMember(THIRD_PERSON_FEMININE_PLURAL, thirdPersonFemininePlural());

        words[8] = new ConjugationMember(SECOND_PERSON_MASCULINE_SINGULAR, secondPersonMasculineSingular());
        words[7] = new ConjugationMember(SECOND_PERSON_MASCULINE_DUAL, secondPersonMasculineDual());
        words[6] = new ConjugationMember(SECOND_PERSON_MASCULINE_PLURAL, secondPersonMasculinePlural());

        words[11] = new ConjugationMember(SECOND_PERSON_FEMININE_SINGULAR, secondPersonFeminineSingular());
        words[10] = new ConjugationMember(SECOND_PERSON_FEMININE_DUAL, secondPersonFeminineDual());
        words[9] = new ConjugationMember(SECOND_PERSON_FEMININE_PLURAL, secondPersonFemininePlural());

        words[14] = new ConjugationMember(FIRST_PERSON_SINGULAR, firstPersonSingular());
        words[13] = null;
        words[12] = new ConjugationMember(FIRST_PERSON_PLURAL, firstPersonPlural());

        return words;
    }

    protected abstract ArabicWord doFirstPersonPlural();

    protected abstract ArabicWord doFirstPersonSingular();

    protected ArabicWord doPostFirstPersonPlural(ArabicWord src) {
        return doPostProcessConjugation(FIRST_PERSON_PLURAL, src);
    }

    protected ArabicWord doPostFirstPersonSingular(ArabicWord src) {
        return doPostProcessConjugation(FIRST_PERSON_SINGULAR, src);
    }

    protected ArabicWord doPostSecondPersonFeminineDual(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_FEMININE_DUAL, src);
    }

    protected ArabicWord doPostSecondPersonFemininePlural(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_FEMININE_PLURAL, src);
    }

    protected ArabicWord doPostSecondPersonFeminineSingular(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_FEMININE_SINGULAR, src);
    }

    protected ArabicWord doPostSecondPersonMasculineDual(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_MASCULINE_DUAL, src);
    }

    protected ArabicWord doPostSecondPersonMasculinePlural(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_MASCULINE_PLURAL, src);
    }

    protected ArabicWord doPostSecondPersonMasculineSingular(ArabicWord src) {
        return doPostProcessConjugation(SECOND_PERSON_MASCULINE_SINGULAR, src);
    }

    protected ArabicWord doPostThirdPersonFeminineDual(ArabicWord src) {
        return doPostProcessConjugation(THIRD_PERSON_FEMININE_DUAL, src);
    }

    protected ArabicWord doPostThirdPersonFemininePlural(ArabicWord src) {
        return doPostProcessConjugation(THIRD_PERSON_FEMININE_PLURAL, src);
    }

    protected ArabicWord doPostThirdPersonFeminineSingular(ArabicWord src) {
        return doPostProcessConjugation(THIRD_PERSON_FEMININE_SINGULAR, src);
    }

    protected ArabicWord doPostThirdPersonMasculineDual(ArabicWord src) {
        return doPostProcessConjugation(THIRD_PERSON_MASCULINE_DUAL, src);
    }

    protected ArabicWord doPostThirdPersonMasculinePlural(ArabicWord src) {
        return doPostProcessConjugation(THIRD_PERSON_MASCULINE_PLURAL, src);
    }

    protected ArabicWord doPostThirdPersonMasculineSingular(ArabicWord src) {
        return doPostProcessConjugation(THIRD_PERSON_MASCULINE_SINGULAR, src);
    }

    protected ArabicWord doSecondPersonFeminineDual() {
        return doSecondPersonMasculineDual();
    }

    protected abstract ArabicWord doSecondPersonFemininePlural();

    protected abstract ArabicWord doSecondPersonFeminineSingular();

    protected ArabicWord doSecondPersonMasculineDual() {
        return doThirdPersonFeminineDual();
    }

    protected abstract ArabicWord doSecondPersonMasculinePlural();

    protected ArabicWord doSecondPersonMasculineSingular() {
        return doThirdPersonFeminineSingular();
    }

    protected abstract ArabicWord doThirdPersonFeminineDual();

    protected abstract ArabicWord doThirdPersonFemininePlural();

    protected abstract ArabicWord doThirdPersonFeminineSingular();

    protected abstract ArabicWord doThirdPersonMasculineDual();

    protected abstract ArabicWord doThirdPersonMasculinePlural();

    protected ArabicWord doThirdPersonMasculineSingular() {
        return new ArabicWord(rootWord);
    }

    public ArabicWord firstPersonPlural() {
        return doPostFirstPersonPlural(doFirstPersonPlural());
    }

    public ArabicWord firstPersonSingular() {
        return doPostFirstPersonSingular(doFirstPersonSingular());
    }

    @Override
    public ConjugationMember getDefaultConjugation() {
        return new ConjugationMember(THIRD_PERSON_MASCULINE_SINGULAR, thirdPersonMasculineSingular());
    }

    public ArabicWord secondPersonFeminineDual() {
        return doPostSecondPersonFeminineDual(doSecondPersonFeminineDual());
    }

    public ArabicWord secondPersonFemininePlural() {
        return doPostSecondPersonFemininePlural(doSecondPersonFemininePlural());
    }

    public ArabicWord secondPersonFeminineSingular() {
        return doPostSecondPersonFeminineSingular(doSecondPersonFeminineSingular());
    }

    public ArabicWord secondPersonMasculineDual() {
        return doPostSecondPersonMasculineDual(doSecondPersonMasculineDual());
    }

    public ArabicWord secondPersonMasculinePlural() {
        return doPostSecondPersonMasculinePlural(doSecondPersonMasculinePlural());
    }

    public ArabicWord secondPersonMasculineSingular() {
        return doPostSecondPersonMasculineSingular(doSecondPersonMasculineSingular());
    }

    public ArabicWord thirdPersonFeminineDual() {
        return doPostThirdPersonFeminineDual(doThirdPersonFeminineDual());
    }

    public ArabicWord thirdPersonFemininePlural() {
        return doPostThirdPersonFemininePlural(doThirdPersonFemininePlural());
    }

    public ArabicWord thirdPersonFeminineSingular() {
        return doPostThirdPersonFeminineSingular(doThirdPersonFeminineSingular());
    }

    public ArabicWord thirdPersonMasculineDual() {
        return doPostThirdPersonMasculineDual(doThirdPersonMasculineDual());
    }

    public ArabicWord thirdPersonMasculinePlural() {
        return doPostThirdPersonMasculinePlural(doThirdPersonMasculinePlural());
    }

    public ArabicWord thirdPersonMasculineSingular() {
        return doPostThirdPersonMasculineSingular(doThirdPersonMasculineSingular());
    }
}
