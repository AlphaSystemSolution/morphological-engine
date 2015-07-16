/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation;

import com.alphasystem.app.sarfengine.conjugation.model.ConjugationMember;
import com.alphasystem.arabic.model.ArabicWord;

import static com.alphasystem.arabic.model.HiddenNounStatus.*;

/**
 * @author sali
 */
public abstract class ParticipleMemberBuilder extends
        AbstractConjugationMemberBuilder {

    public ArabicWord accusativeDual() {
        return doPostAccusativeDual(doAccusativeDual());
    }

    public ArabicWord accusativePlural() {
        return doPostAccusativePlural(doAccusativePlural());
    }

    public ArabicWord accusativeSigular() {
        return doPostAccusativeSigular(doAccusativeSigular());
    }

    protected abstract ArabicWord doAccusativeDual();

    protected abstract ArabicWord doAccusativePlural();

    protected abstract ArabicWord doAccusativeSigular();

    @Override
    public ConjugationMember[] doConjugation() {
        ConjugationMember[] words = new ConjugationMember[9];

        words[2] = new ConjugationMember(NOMINATIVE_SINGULAR, nominativeSigular());
        words[1] = new ConjugationMember(NOMINATIVE_DUAL, nominativeDual());
        words[0] = new ConjugationMember(NOMINATIVE_PLURAL, nominativePlural());

        words[5] = new ConjugationMember(ACCUSATIVE_SINGULAR, accusativeSigular());
        words[4] = new ConjugationMember(ACCUSATIVE_DUAL, accusativeDual());
        words[3] = new ConjugationMember(ACCUSATIVE_DUAL, accusativePlural());

        words[8] = new ConjugationMember(GENITIVE_SINGULAR, genitiveSigular());
        words[7] = new ConjugationMember(GENITIVE_DUAL, genitiveDual());
        words[6] = new ConjugationMember(GENITIVE_PLURAL, genitivePlural());

        return words;
    }

    protected ArabicWord doGenitiveDual() {
        return doAccusativeDual();
    }

    protected ArabicWord doGenitivePlural() {
        return doAccusativePlural();
    }

    protected abstract ArabicWord doGenitiveSigular();

    protected abstract ArabicWord doNominativeDual();

    protected abstract ArabicWord doNominativePlural();

    protected ArabicWord doNominativeSigular() {
        return rootWord;
    }

    protected ArabicWord doPostAccusativeDual(ArabicWord src) {
        return doPostProcessConjugation(ACCUSATIVE_DUAL, src);
    }

    protected ArabicWord doPostAccusativePlural(ArabicWord src) {
        return doPostProcessConjugation(ACCUSATIVE_PLURAL, src);
    }

    protected ArabicWord doPostAccusativeSigular(ArabicWord src) {
        return doPostProcessConjugation(ACCUSATIVE_SINGULAR, src);
    }

    protected ArabicWord doPostGenitiveDual(ArabicWord src) {
        return doPostProcessConjugation(GENITIVE_DUAL, src);
    }

    protected ArabicWord doPostGenitivePlural(ArabicWord src) {
        return doPostProcessConjugation(GENITIVE_PLURAL, src);
    }

    protected ArabicWord doPostGenitiveSigular(ArabicWord src) {
        return doPostProcessConjugation(GENITIVE_SINGULAR, src);
    }

    protected ArabicWord doPostNominativeDual(ArabicWord src) {
        return doPostProcessConjugation(NOMINATIVE_DUAL, src);
    }

    protected ArabicWord doPostNominativePlural(ArabicWord src) {
        return doPostProcessConjugation(NOMINATIVE_PLURAL, src);
    }

    protected ArabicWord doPostNominativeSigular(ArabicWord src) {
        return doPostProcessConjugation(NOMINATIVE_SINGULAR, src);
    }

    public ArabicWord genitiveDual() {
        return doPostGenitiveDual(doGenitiveDual());
    }

    public ArabicWord genitivePlural() {
        return doPostGenitivePlural(doGenitivePlural());
    }

    public ArabicWord genitiveSigular() {
        return doPostGenitiveSigular(doGenitiveSigular());
    }

    @Override
    public ConjugationMember getDefaultConjugation() {
        return new ConjugationMember(NOMINATIVE_SINGULAR, nominativeSigular());
    }

    public ArabicWord nominativeDual() {
        return doPostNominativeDual(doNominativeDual());
    }

    public ArabicWord nominativePlural() {
        return doPostNominativePlural(doNominativePlural());
    }

    public ArabicWord nominativeSigular() {
        return doPostNominativeSigular(doNominativeSigular());
    }

}
