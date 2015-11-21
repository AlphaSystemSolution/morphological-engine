package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.sarfengine.xml.model.RootWord;

/**
 * @author sali
 */
public interface ParticipleMemberBuilder extends ConjugationMemberBuilder {

    RootWord nominativeSingular();

    RootWord nominativeDual();

    RootWord nominativePlural();

    RootWord accusativeSingular();

    RootWord accusativeDual();

    RootWord accusativePlural();

    RootWord genitiveSingular();

    RootWord genitiveDual();

    RootWord genitivePlural();
}
