package com.alphasystem.app.sarfengine.guice;

import com.alphasystem.app.sarfengine.conjugation.member.MemberBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.member.TenseMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.member.triliteral.*;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import static com.google.inject.name.Names.named;

/**
 * @author sali
 */
public class MemberBuilderModule extends AbstractModule {

    public static final String TRI_LITERAL_PAST_TENSE_BUILDER = "TriLiteralPastTenseBuilder";
    public static final String TRI_LITERAL_PRESENT_TENSE_BUILDER = "TriLiteralPresentTenseBuilder";
    public static final String TRI_LITERAL_PAST_PASSIVE_BUILDER = "TriLiteralPastPassiveBuilder";
    public static final String TRI_LITERAL_PRESENT_PASSIVE_BUILDER = "TriLiteralPresentPassiveBuilder";
    public static final String TRI_LITERAL_IMPERATIVE_BUILDER = "TriLiteralImperativeBuilder";
    public static final String TRI_LITERAL_FORBIDDING_BUILDER = "TriLiteralForbiddingBuilder";
    public static final String TRI_LITERAL_ACTIVE_PARTICIPLE_MASCULINE_BUILDER = "TriLiteralActiveParticipleMasculineBuilder";
    public static final String TRI_LITERAL_ACTIVE_PARTICIPLE_FEMININE_BUILDER = "TriLiteralActiveParticipleFeminineBuilder";
    public static final String TRI_LITERAL_PASSIVE_PARTICIPLE_MASCULINE_BUILDER = "TriLiteralPassiveParticipleMasculineBuilder";
    public static final String TRI_LITERAL_PASSIVE_PARTICIPLE_FEMININE_BUILDER = "TriLiteralPassiveParticipleFeminineBuilder";

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(TenseMemberBuilder.class, named(TRI_LITERAL_PAST_TENSE_BUILDER),
                        TriLiteralPastTenseBuilder.class)
                .implement(TenseMemberBuilder.class, named(TRI_LITERAL_PRESENT_TENSE_BUILDER),
                        TriLiteralPresentTenseBuilder.class)
                .implement(TenseMemberBuilder.class, named(TRI_LITERAL_PAST_PASSIVE_BUILDER),
                        TriLiteralPastPassiveBuilder.class)
                .implement(TenseMemberBuilder.class, named(TRI_LITERAL_PRESENT_PASSIVE_BUILDER),
                        TriLiteralPresentPassiveBuilder.class)
                .implement(TenseMemberBuilder.class, named(TRI_LITERAL_IMPERATIVE_BUILDER),
                        TriLiteralImperativeBuilder.class)
                .implement(TenseMemberBuilder.class, named(TRI_LITERAL_FORBIDDING_BUILDER),
                        TriLiteralForbiddingBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(TRI_LITERAL_ACTIVE_PARTICIPLE_MASCULINE_BUILDER),
                        TriLiteralActiveParticipleMasculineBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(TRI_LITERAL_ACTIVE_PARTICIPLE_FEMININE_BUILDER),
                        TriLiteralActiveParticipleFeminineBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(TRI_LITERAL_PASSIVE_PARTICIPLE_MASCULINE_BUILDER),
                        TriLiteralPassiveParticipleMasculineBuilder.class)
                .implement(ParticipleMemberBuilder.class, named(TRI_LITERAL_PASSIVE_PARTICIPLE_FEMININE_BUILDER),
                        TriLiteralPassiveParticipleFeminineBuilder.class)
                .build(MemberBuilderFactory.class));
    }
}
