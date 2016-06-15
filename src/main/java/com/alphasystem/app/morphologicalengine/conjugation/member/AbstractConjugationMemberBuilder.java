package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.model.ConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootBase;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;

/**
 * @author sali
 */
abstract class AbstractConjugationMemberBuilder<G extends ConjugationGroup, B extends RootBase> implements ConjugationMemberBuilder<G, B> {

    protected static final GuiceSupport GUICE_SUPPORT = GuiceSupport.getInstance();

}
