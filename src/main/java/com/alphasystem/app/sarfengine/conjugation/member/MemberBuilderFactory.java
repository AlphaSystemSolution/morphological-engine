package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
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
                                                      @Assisted ArabicLetter imperativeLetter);

    @Named(TRI_LITERAL_FORBIDDING_BUILDER)
    TenseMemberBuilder getTriLiteralForbiddingBuilder(@Assisted NamedTemplate template,
                                                      @Assisted boolean skipRuleProcessing,
                                                      @Assisted("firstRadical") ArabicLetterType firstRadical,
                                                      @Assisted("secondRadical") ArabicLetterType secondRadical,
                                                       @Nullable @Assisted("thirdRadical") ArabicLetterType thirdRadical);
}
