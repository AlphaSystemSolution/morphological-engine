/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.arabic.model.SarfMemberType;

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
    public ArabicWord[] doConjugation() {
        ArabicWord[] words = new ArabicWord[9];

        words[2] = nominativeSigular();
        words[1] = nominativeDual();
        words[0] = nominativePlural();

        words[5] = accusativeSigular();
        words[4] = accusativeDual();
        words[3] = accusativePlural();

        words[8] = genitiveSigular();
        words[7] = genitiveDual();
        words[6] = genitivePlural();

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
    public ArabicWord getDefaultConjugation() {
        return nominativeSigular();
    }

    @Override
    public SarfMemberType getDefaultMember() {
        return NOMINATIVE_SINGULAR;
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
