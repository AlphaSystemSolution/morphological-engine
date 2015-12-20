package com.alphasystem.app.sarfengine.conjugation.member;


import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

/**
 * @author sali
 */
public interface TenseMemberBuilder extends ConjugationMemberBuilder {

    RootWord thirdPersonMasculineSingular();

    RootWord thirdPersonMasculineDual();

    RootWord thirdPersonMasculinePlural();

    RootWord thirdPersonFeminineSingular();

    RootWord thirdPersonFeminineDual();

    RootWord thirdPersonFemininePlural();

    RootWord secondPersonMasculineSingular();

    RootWord secondPersonMasculineDual();

    RootWord secondPersonMasculinePlural();

    RootWord secondPersonFeminineSingular();

    RootWord secondPersonFeminineDual();

    RootWord secondPersonFemininePlural();

    RootWord firstPersonSingular();

    RootWord firstPersonPlural();
}
