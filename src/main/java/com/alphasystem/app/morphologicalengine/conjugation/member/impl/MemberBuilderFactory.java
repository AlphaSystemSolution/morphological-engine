package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.model.Form;
import com.alphasystem.app.sarfengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.morphologicalanalysis.morphology.model.NounRootBase;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import javax.annotation.Nullable;

import static com.alphasystem.app.morphologicalengine.conjugation.member.impl.MemberBuilderModule.*;

/**
 * @author sali
 */
public interface MemberBuilderFactory {

    @Named(ACTIVE_PARTICIPLE_MASCULINE_BUILDER)
    ParticipleMemberBuilder getActiveParticipleMasculineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                                @Assisted NounRootBase nounRootBase,
                                                                @Assisted RootLetters rootLetters);

    @Named(ACTIVE_PARTICIPLE_MASCULINE_BUILDER)
    ParticipleMemberBuilder getActiveParticipleMasculineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                                @Assisted Form form,
                                                                @Assisted RootLetters rootLetters);

    @Named(ACTIVE_PARTICIPLE_FEMININE_BUILDER)
    ParticipleMemberBuilder getActiveParticipleFeminineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                               @Assisted NounRootBase nounRootBase,
                                                               @Assisted RootLetters rootLetters);

    @Named(ACTIVE_PARTICIPLE_FEMININE_BUILDER)
    ParticipleMemberBuilder getActiveParticipleFeminineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                               @Assisted Form form,
                                                               @Assisted RootLetters rootLetters);

    @Named(PASSIVE_PARTICIPLE_MASCULINE_BUILDER)
    ParticipleMemberBuilder getPassiveParticipleMasculineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                                 @Assisted NounRootBase nounRootBase,
                                                                 @Assisted RootLetters rootLetters);

    @Named(PASSIVE_PARTICIPLE_MASCULINE_BUILDER)
    ParticipleMemberBuilder getPassiveParticipleMasculineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                                 @Assisted Form form,
                                                                 @Assisted RootLetters rootLetters);


    @Named(PASSIVE_PARTICIPLE_FEMININE_BUILDER)
    ParticipleMemberBuilder getPassiveParticipleFeminineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                                @Assisted NounRootBase nounRootBase,
                                                                @Assisted RootLetters rootLetters);

    @Named(PASSIVE_PARTICIPLE_FEMININE_BUILDER)
    ParticipleMemberBuilder getPassiveParticipleFeminineBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                                @Assisted Form form,
                                                                @Assisted RootLetters rootLetters);
}
