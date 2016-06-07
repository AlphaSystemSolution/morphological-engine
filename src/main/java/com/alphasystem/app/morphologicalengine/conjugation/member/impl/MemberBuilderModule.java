package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.member.TenseMemberBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import static com.google.inject.name.Names.named;

/**
 * @author sali
 */
public class MemberBuilderModule extends AbstractModule {

    public static final String ACTIVE_PARTICIPLE_MASCULINE_BUILDER = "ActiveParticipleMasculineBuilder";
    public static final String ACTIVE_PARTICIPLE_FEMININE_BUILDER = "ActiveParticipleFeminineBuilder";
    public static final String PASSIVE_PARTICIPLE_MASCULINE_BUILDER = "PassiveParticipleMasculineBuilder";
    public static final String PASSIVE_PARTICIPLE_FEMININE_BUILDER = "PassiveParticipleFeminineBuilder";
    public static final String VERBAL_NOUN_BUILDER = "VerbalNounBuilder";
    public static final String ADVERB_BUILDER = "AdverbBuilder";
    public static final String PAST_TENSE_MEMBER_BUILDER = "PastTenseMemberBuilder";
    public static final String PRESENT_TENSE_MEMBER_BUILDER = "PresentTenseMemberBuilder";
    public static final String PAST_PASSIVE_TENSE_MEMBER_BUILDER = "PastPassiveTenseMemberBuilder";
    public static final String PRESENT_PASSIVE_TENSE_MEMBER_BUILDER = "PresentPassiveTenseMemberBuilder";

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(ParticipleMemberBuilder.class, named(ACTIVE_PARTICIPLE_MASCULINE_BUILDER), ActiveParticipleMasculineBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(ACTIVE_PARTICIPLE_FEMININE_BUILDER), ActiveParticipleFeminineBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(PASSIVE_PARTICIPLE_MASCULINE_BUILDER), PassiveParticipleMasculineBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(PASSIVE_PARTICIPLE_FEMININE_BUILDER), PassiveParticipleFeminineBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(VERBAL_NOUN_BUILDER), VerbalNounBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(ADVERB_BUILDER), AdverbBuilder.class)
                .implement(TenseMemberBuilder.class, named(PAST_TENSE_MEMBER_BUILDER), PastTenseMemberBuilder.class)
                .implement(TenseMemberBuilder.class, named(PRESENT_TENSE_MEMBER_BUILDER), PresentTenseMemberBuilder.class)
                .implement(TenseMemberBuilder.class, named(PAST_PASSIVE_TENSE_MEMBER_BUILDER), PastPassiveTenseMemberBuilder.class)
                .implement(TenseMemberBuilder.class, named(PRESENT_PASSIVE_TENSE_MEMBER_BUILDER), PresentPassiveTenseMemberBuilder.class)
                .build(MemberBuilderFactory.class));
    }
}
