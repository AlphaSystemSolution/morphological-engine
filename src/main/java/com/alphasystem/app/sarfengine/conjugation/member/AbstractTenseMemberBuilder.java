package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;

import static com.alphasystem.arabic.model.HiddenPronounStatus.*;

/**
 * @author sali
 */
public abstract class AbstractTenseMemberBuilder extends AbstractConjugationMemberBuilder
        implements TenseMemberBuilder {

    protected AbstractTenseMemberBuilder(NamedTemplate template, boolean skipRuleProcessing,
                                         ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                         ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        super(template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, fourthRadical);
    }

    protected AbstractTenseMemberBuilder(NamedTemplate template, boolean skipRuleProcessing,
                                         ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                         ArabicLetterType thirdRadical) {
        super(template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical);
    }


    // Interface methods

    @Override
    public RootWord[] doConjugation() {
        RootWord[] conjugations = new RootWord[15];

        conjugations[2] = thirdPersonMasculineSingular();
        conjugations[1] = thirdPersonMasculineDual();
        conjugations[0] = thirdPersonMasculinePlural();

        conjugations[5] = thirdPersonFeminineSingular();
        conjugations[4] = thirdPersonFeminineDual();
        conjugations[3] = thirdPersonFemininePlural();

        conjugations[8] = secondPersonMasculineSingular();
        conjugations[7] = secondPersonMasculineDual();
        conjugations[6] = secondPersonMasculinePlural();

        conjugations[11] = secondPersonFeminineSingular();
        conjugations[10] = secondPersonFeminineDual();
        conjugations[9] = secondPersonFemininePlural();

        conjugations[14] = firstPersonSingular();
        conjugations[13] = null;
        conjugations[12] = firstPersonPlural();

        return conjugations;
    }

    @Override
    public RootWord getDefaultConjugation() {
        return new RootWord(thirdPersonMasculineSingular());
    }

    // Internal methods

    protected RootWord doThirdPersonMasculineSingular(RootWord rootWord) {
        return new RootWord(rootWord);
    }

    protected abstract RootWord doThirdPersonMasculineDual(RootWord rootWord);

    protected abstract RootWord doThirdPersonMasculinePlural(RootWord rootWord);

    protected abstract RootWord doThirdPersonFeminineSingular(RootWord rootWord);

    protected abstract RootWord doThirdPersonFeminineDual(RootWord rootWord);

    protected abstract RootWord doThirdPersonFemininePlural(RootWord rootWord);

    protected abstract RootWord doSecondPersonMasculineSingular(RootWord rootWord);

    protected abstract RootWord doSecondPersonMasculineDual(RootWord rootWord);

    protected abstract RootWord doSecondPersonMasculinePlural(RootWord rootWord);

    protected abstract RootWord doSecondPersonFeminineSingular(RootWord rootWord);

    protected RootWord doSecondPersonFeminineDual(RootWord rootWord) {
        return doSecondPersonMasculineDual(createRootWord(rootWord, SECOND_PERSON_FEMININE_DUAL));
    }

    protected abstract RootWord doSecondPersonFemininePlural(RootWord rootWord);

    protected abstract RootWord doFirstPersonSingular(RootWord rootWord);

    protected abstract RootWord doFirstPersonPlural(RootWord rootWord);

    // public methods

    @Override
    public RootWord thirdPersonMasculineSingular() {
        return doPostProcessConjugation(doThirdPersonMasculineSingular(
                createRootWord(THIRD_PERSON_MASCULINE_SINGULAR)));
    }

    @Override
    public RootWord thirdPersonMasculineDual() {
        return doPostProcessConjugation(doThirdPersonMasculineDual(createRootWord(THIRD_PERSON_MASCULINE_DUAL)));
    }

    @Override
    public RootWord thirdPersonMasculinePlural() {
        return doPostProcessConjugation(doThirdPersonMasculinePlural(createRootWord(THIRD_PERSON_MASCULINE_PLURAL)));
    }

    @Override
    public RootWord thirdPersonFeminineSingular() {
        return doPostProcessConjugation(doThirdPersonFeminineSingular(createRootWord(THIRD_PERSON_FEMININE_SINGULAR)));
    }

    @Override
    public RootWord thirdPersonFeminineDual() {
        return doPostProcessConjugation(doThirdPersonFeminineDual(createRootWord(THIRD_PERSON_FEMININE_DUAL)));
    }

    @Override
    public RootWord thirdPersonFemininePlural() {
        return doPostProcessConjugation(doThirdPersonFemininePlural(createRootWord(THIRD_PERSON_FEMININE_PLURAL)));
    }

    @Override
    public RootWord secondPersonMasculineSingular() {
        return doPostProcessConjugation(doSecondPersonMasculineSingular(
                createRootWord(SECOND_PERSON_MASCULINE_SINGULAR)));
    }

    @Override
    public RootWord secondPersonMasculineDual() {
        return doPostProcessConjugation(doSecondPersonMasculineDual(createRootWord(SECOND_PERSON_MASCULINE_DUAL)));
    }

    @Override
    public RootWord secondPersonMasculinePlural() {
        return doPostProcessConjugation(doSecondPersonMasculinePlural(createRootWord(SECOND_PERSON_MASCULINE_PLURAL)));
    }

    @Override
    public RootWord secondPersonFeminineSingular() {
        return doPostProcessConjugation(doSecondPersonFeminineSingular(
                createRootWord(SECOND_PERSON_FEMININE_SINGULAR)));
    }

    @Override
    public RootWord secondPersonFeminineDual() {
        return doPostProcessConjugation(doSecondPersonFeminineDual(createRootWord(SECOND_PERSON_FEMININE_DUAL)));
    }

    @Override
    public RootWord secondPersonFemininePlural() {
        return doPostProcessConjugation(doSecondPersonFemininePlural(createRootWord(SECOND_PERSON_FEMININE_PLURAL)));
    }

    @Override
    public RootWord firstPersonSingular() {
        return doPostProcessConjugation(doFirstPersonSingular(createRootWord(FIRST_PERSON_SINGULAR)));
    }

    @Override
    public RootWord firstPersonPlural() {
        return doPostProcessConjugation(doFirstPersonPlural(createRootWord(FIRST_PERSON_PLURAL)));
    }
}
