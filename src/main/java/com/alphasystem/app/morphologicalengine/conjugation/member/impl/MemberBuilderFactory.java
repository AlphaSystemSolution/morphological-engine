package com.alphasystem.app.morphologicalengine.conjugation.member.impl;

import com.alphasystem.app.morphologicalengine.conjugation.member.ParticipleMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.member.TenseMemberBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.model.Form;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.model.RootLetters;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbRootBase;
import com.alphasystem.app.morphologicalengine.conjugation.rule.RuleProcessor;
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

    @Named(VERBAL_NOUN_BUILDER)
    ParticipleMemberBuilder getVerbalNounBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                 @Assisted NounRootBase nounRootBase,
                                                 @Assisted RootLetters rootLetters);

    @Named(VERBAL_NOUN_BUILDER)
    ParticipleMemberBuilder getVerbalNounBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                 @Assisted Form form,
                                                 @Assisted RootLetters rootLetters);

    @Named(ADVERB_BUILDER)
    ParticipleMemberBuilder getAdverbBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                             @Assisted NounRootBase nounRootBase,
                                             @Assisted RootLetters rootLetters);

    @Named(ADVERB_BUILDER)
    ParticipleMemberBuilder getAdverbBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                             @Assisted Form form,
                                             @Assisted RootLetters rootLetters);

    @Named(PAST_TENSE_MEMBER_BUILDER)
    TenseMemberBuilder getPastTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                 @Assisted VerbRootBase verbRootBase,
                                                 @Assisted RootLetters rootLetters);

    @Named(PAST_TENSE_MEMBER_BUILDER)
    TenseMemberBuilder getPastTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                 @Assisted Form form,
                                                 @Assisted RootLetters rootLetters);

    @Named(PRESENT_TENSE_MEMBER_BUILDER)
    TenseMemberBuilder getPresentTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                    @Assisted VerbRootBase verbRootBase,
                                                    @Assisted RootLetters rootLetters);

    @Named(PRESENT_TENSE_MEMBER_BUILDER)
    TenseMemberBuilder getPresentTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                    @Assisted Form form,
                                                    @Assisted RootLetters rootLetters);

    @Named(PAST_PASSIVE_TENSE_MEMBER_BUILDER)
    TenseMemberBuilder getPastPassiveTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                        @Assisted VerbRootBase verbRootBase,
                                                        @Assisted RootLetters rootLetters);

    @Named(PAST_PASSIVE_TENSE_MEMBER_BUILDER)
    TenseMemberBuilder getPastPassiveTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                        @Assisted Form form,
                                                        @Assisted RootLetters rootLetters);

    @Named(PRESENT_PASSIVE_TENSE_MEMBER_BUILDER)
    TenseMemberBuilder getPresentPassiveTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                           @Assisted VerbRootBase verbRootBase,
                                                           @Assisted RootLetters rootLetters);

    @Named(PRESENT_PASSIVE_TENSE_MEMBER_BUILDER)
    TenseMemberBuilder getPresentPassiveTenseMemberBuilder(@Assisted @Nullable RuleProcessor ruleProcessor,
                                                           @Assisted Form form,
                                                           @Assisted RootLetters rootLetters);
}
