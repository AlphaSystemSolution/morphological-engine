/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.builder;

import com.alphasystem.app.sarfengine.conjugation.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.model.SarfChart;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import java.util.List;

/**
 * Interface to build conjugation based on root letters.
 *
 * @author sali
 */
public interface ConjugationBuilder {

    /**
     * Perform the conjugation of given root letters.
     *
     * @param translation   &mdash; translation of root word
     * @param firstRadical  &mdash; First radical letter
     * @param secondRadical &mdash; Second radical letter
     * @param thirdRadical  &mdash; Third radical letter
     * @return Entire {@linkplain SarfChart}
     * @see SarfChart
     */
    SarfChart doConjugation(String translation,
                            ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical);

    /**
     * Perform the conjugation of given root letters.
     *
     * @param translation    &mdash; translation of root word
     * @param firstRadical   &mdash; First radical letter
     * @param secondRadical  &mdash; Second radical letter
     * @param thirdRadical   &mdash; Third radical letter
     * @param verbalNounList
     * @param zarfList
     * @return Entire {@linkplain SarfChart}
     * @see SarfChart
     */
    SarfChart doConjugation(String translation,
                            ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical, List<RootWord> verbalNounList,
                            List<RootWord> zarfList);

    /**
     * Perform the conjugation of given root letters.
     *
     * @param translation        &mdash; translation of root word
     * @param removePassiveLine  &mdash; Remove passive line if true
     * @param skipRuleProcessing
     * @param firstRadical       &mdash; First radical letter
     * @param secondRadical      &mdash; Second radical letter
     * @param thirdRadical       &mdash; Third radical letter
     * @return Entire {@linkplain SarfChart}
     * @see SarfChart
     */
    SarfChart doConjugation(String translation,
                            boolean removePassiveLine, boolean skipRuleProcessing,
                            ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical);

    /**
     * Perform the conjugation of given root letters.
     *
     * @param translation        &mdash; translation of root word
     * @param removePassiveLine  &mdash; Remove passive line if true
     * @param skipRuleProcessing
     * @param firstRadical       &mdash; First radical letter
     * @param secondRadical      &mdash; Second radical letter
     * @param thirdRadical       &mdash; Third radical letter
     * @param verbalNounList
     * @param zarfList
     * @return Entire {@linkplain SarfChart}
     * @see SarfChart
     */
    SarfChart doConjugation(String translation,
                            boolean removePassiveLine, boolean skipRuleProcessing,
                            ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical, List<RootWord> verbalNounList,
                            List<RootWord> zarfList);

    /**
     * Returns the {@linkplain ConjugationMemberBuilder} for the given
     * {@linkplain SarfTermType}.
     *
     * @param skipRuleProcessing
     * @param sarfTermType       &mdash; given {@linkplain SarfTermType}
     * @param firstRadical       &mdash; First radical letter
     * @param secondRadical      &mdash; Second radical letter
     * @param thirdRadical       &mdash; Third radical letter
     * @return {@linkplain ConjugationMemberBuilder} for the given
     * {@linkplain SarfTermType}
     * @see ConjugationMemberBuilder
     * @see SarfTermType
     */
    ConjugationMemberBuilder getMemberBuilder(
            boolean skipRuleProcessing, SarfTermType sarfTermType,
            ArabicLetterType firstRadical, ArabicLetterType secondRadical,
            ArabicLetterType thirdRadical);

}
