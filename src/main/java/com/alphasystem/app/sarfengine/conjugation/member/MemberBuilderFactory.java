package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import javax.annotation.Nullable;

import static com.alphasystem.app.sarfengine.guice.MemberBuilderModule.*;

/**
 * @author sali
 */
public interface MemberBuilderFactory {

    @Named(TRI_LITERAL_PAST_TENSE_BUILDER)
    TenseMemberBuilder getTriLiteralPastTenseBuilder(@Assisted NamedTemplate template,
                                                     @Assisted boolean skipRuleProcessing,
                                                     @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                     @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                     @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_PRESENT_TENSE_BUILDER)
    TenseMemberBuilder getTriLiteralPresentTenseBuilder(@Assisted NamedTemplate template,
                                                        @Assisted boolean skipRuleProcessing,
                                                        @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                        @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                        @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_PAST_PASSIVE_BUILDER)
    TenseMemberBuilder getTriLiteralPastPassiveBuilder(@Assisted NamedTemplate template,
                                                       @Assisted boolean skipRuleProcessing,
                                                       @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                       @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                       @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_PRESENT_PASSIVE_BUILDER)
    TenseMemberBuilder getTriLiteralPresentPassiveBuilder(@Assisted NamedTemplate template,
                                                          @Assisted boolean skipRuleProcessing,
                                                          @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                          @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                          @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_IMPERATIVE_BUILDER)
    TenseMemberBuilder getTriLiteralImperativeBuilder(@Assisted NamedTemplate template,
                                                      @Assisted boolean skipRuleProcessing,
                                                      @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                      @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                      @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                      @Nullable @Assisted ArabicLetter imperativeLetter);

    @Named(TRI_LITERAL_IMPERATIVE_BUILDER)
    TenseMemberBuilder getTriLiteralImperativeBuilder(@Assisted NamedTemplate template,
                                                      @Assisted boolean skipRuleProcessing,
                                                      @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                      @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                      @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_IMPERATIVE_FORMIV_BUILDER)
    TenseMemberBuilder getTriLiteralImperativeFormIVBuilder(@Assisted NamedTemplate template,
                                                            @Assisted boolean skipRuleProcessing,
                                                            @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                            @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                            @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_FORBIDDING_BUILDER)
    TenseMemberBuilder getTriLiteralForbiddingBuilder(@Assisted NamedTemplate template,
                                                      @Assisted boolean skipRuleProcessing,
                                                      @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                      @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                      @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_ACTIVE_PARTICIPLE_MASCULINE_BUILDER)
    ParticipleMemberBuilder getTriLiteralActiveParticipleMasculineBuilder(@Assisted NamedTemplate template,
                                                                          @Assisted boolean skipRuleProcessing,
                                                                          @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                                          @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                                          @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_ACTIVE_PARTICIPLE_MASCULINE_BUILDER)
    ParticipleMemberBuilder getTriLiteralActiveParticipleMasculineBuilder(@Assisted NamedTemplate template,
                                                                          @Assisted boolean skipRuleProcessing,
                                                                          @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                                          @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                                          @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                                          @Nullable @Assisted RootWord baseRootWord);

    @Named(TRI_LITERAL_ACTIVE_PARTICIPLE_FEMININE_BUILDER)
    ParticipleMemberBuilder getTriLiteralActiveParticipleFeminineBuilder(@Assisted NamedTemplate template,
                                                                         @Assisted boolean skipRuleProcessing,
                                                                         @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                                         @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                                         @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_ACTIVE_PARTICIPLE_FEMININE_BUILDER)
    ParticipleMemberBuilder getTriLiteralActiveParticipleFeminineBuilder(@Assisted NamedTemplate template,
                                                                          @Assisted boolean skipRuleProcessing,
                                                                          @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                                          @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                                          @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                                          @Nullable @Assisted RootWord baseRootWord);

    @Named(TRI_LITERAL_PASSIVE_PARTICIPLE_MASCULINE_BUILDER)
    ParticipleMemberBuilder getTriLiteralPassiveParticipleMasculineBuilder(@Assisted NamedTemplate template,
                                                                           @Assisted boolean skipRuleProcessing,
                                                                           @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                                           @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                                           @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_PASSIVE_PARTICIPLE_FEMININE_BUILDER)
    ParticipleMemberBuilder getTriLiteralPassiveParticipleFeminineBuilder(@Assisted NamedTemplate template,
                                                                          @Assisted boolean skipRuleProcessing,
                                                                          @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                                          @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                                          @Assisted("thirdRadical") ArabicLetterType thirdRadical);

    @Named(TRI_LITERAL_VERBAL_NOUN_BUILDER)
    ParticipleMemberBuilder getTriLiteralVerbalNounBuilder(@Assisted NamedTemplate template,
                                                           @Assisted boolean skipRuleProcessing,
                                                           @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                           @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                           @Assisted("thirdRadical") ArabicLetterType thirdRadical,
                                                           @Nullable @Assisted RootWord baseRootWord);
}
