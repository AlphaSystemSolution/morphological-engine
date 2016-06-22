package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.member.TenseMemberBuilder;
import com.google.inject.AbstractModule;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static com.google.inject.name.Names.named;

/**
 * @author sali
 */
public class MemberBuilderModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(TenseMemberBuilder.class).annotatedWith(named(PAST_TENSE.name())).to(PastTenseMemberBuilder.class);
        bind(TenseMemberBuilder.class).annotatedWith(named(PRESENT_TENSE.name())).to(PresentTenseMemberBuilder.class);
        bind(TenseMemberBuilder.class).annotatedWith(named(PAST_PASSIVE_TENSE.name())).to(PastPassiveTenseMemberBuilder.class);
        bind(TenseMemberBuilder.class).annotatedWith(named(PRESENT_PASSIVE_TENSE.name())).to(PresentPassiveTenseMemberBuilder.class);
        bind(TenseMemberBuilder.class).annotatedWith(named(IMPERATIVE.name())).to(ImperativeMemberBuilder.class);
        bind(TenseMemberBuilder.class).annotatedWith(named(FORBIDDING.name())).to(ForbiddingMemberBuilder.class);
        bind(ParticipleMemberBuilder.class).annotatedWith(named(ACTIVE_PARTICIPLE_MASCULINE.name())).to(ActiveParticipleMasculineBuilder.class);
        bind(ParticipleMemberBuilder.class).annotatedWith(named(ACTIVE_PARTICIPLE_FEMININE.name())).to(ActiveParticipleFeminineBuilder.class);
        bind(ParticipleMemberBuilder.class).annotatedWith(named(PASSIVE_PARTICIPLE_MASCULINE.name())).to(PassiveParticipleMasculineBuilder.class);
        bind(ParticipleMemberBuilder.class).annotatedWith(named(PASSIVE_PARTICIPLE_FEMININE.name())).to(PassiveParticipleFeminineBuilder.class);
        bind(ParticipleMemberBuilder.class).annotatedWith(named(VERBAL_NOUN.name())).to(VerbalNounBuilder.class);
        bind(ParticipleMemberBuilder.class).annotatedWith(named(NOUN_OF_PLACE_AND_TIME.name())).to(AdverbBuilder.class);
    }

}
