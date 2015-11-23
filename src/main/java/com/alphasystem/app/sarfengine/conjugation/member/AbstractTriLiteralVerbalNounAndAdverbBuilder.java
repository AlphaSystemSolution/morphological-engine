package com.alphasystem.app.sarfengine.conjugation.member;

import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;

import static com.alphasystem.app.sarfengine.guice.GuiceSupport.getInstance;
import static com.alphasystem.arabic.model.ArabicLetterType.TA_MARBUTA;
import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.NOUN_OF_PLACE_AND_TIME;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.VERBAL_NOUN;

/**
 * @author sali
 */
public class AbstractTriLiteralVerbalNounAndAdverbBuilder extends AbstractParticipleMemberBuilder {

    protected final ParticipleMemberBuilder masculineBuilder;
    protected final ParticipleMemberBuilder feminineBuilder;
    private boolean feminineBased;
    private boolean verbalNoun;

    protected AbstractTriLiteralVerbalNounAndAdverbBuilder(RuleProcessor ruleProcessor, NamedTemplate template,
                                                           boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                           ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                           RootWord baseRootWord, int variableLetterIndex,
                                                           boolean verbalNoun) throws NullPointerException {
        super(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, baseRootWord,
                variableLetterIndex);
        this.verbalNoun = verbalNoun;
        feminineBased = getRootWord().getRootWord().getLastLetter().getLetter().equals(TA_MARBUTA);
        MemberBuilderFactory memberBuilderFactory = getInstance().getMemberBuilderFactory();
        masculineBuilder = memberBuilderFactory.getTriLiteralActiveParticipleMasculineBuilder(ruleProcessor,
                template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, baseRootWord);
        RootWord feminineRoot = new RootWord(getRootWord());
        feminineRoot.setRootWord(feminineRoot.getRootWord().replaceDiacritic(getVariableLetterIndex(), FATHA)
                .append(TA_MARBUTA_WITH_DAMMATAN));
        feminineRoot = feminineBased ? getRootWord() : feminineRoot;
        feminineBuilder = memberBuilderFactory.getTriLiteralActiveParticipleFeminineBuilder(ruleProcessor,
                template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, feminineRoot);
    }

    protected AbstractTriLiteralVerbalNounAndAdverbBuilder(RuleProcessor ruleProcessor, NamedTemplate template,
                                                           boolean skipRuleProcessing, ArabicLetterType firstRadical,
                                                           ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                           int variableLetterIndex, boolean verbalNoun)
            throws NullPointerException {
        this(ruleProcessor, template, skipRuleProcessing, firstRadical, secondRadical, thirdRadical, null,
                variableLetterIndex, verbalNoun);
    }

    @Override
    protected RootWord doNominativeSingular(RootWord rootWord) {
        RootWord src = feminineBased ? feminineBuilder.nominativeSingular() : masculineBuilder.nominativeSingular();
        return new RootWord(src).withSarfTermType(getTermType());
    }

    @Override
    protected RootWord doNominativeDual(RootWord rootWord) {
        RootWord src = feminineBased ? feminineBuilder.nominativeDual() : masculineBuilder.nominativeDual();
        return new RootWord(src).withSarfTermType(getTermType());
    }

    @Override
    protected RootWord doNominativePlural(RootWord rootWord) {
        return new RootWord(feminineBuilder.nominativePlural()).withSarfTermType(getTermType());
    }

    @Override
    protected RootWord doAccusativeSingular(RootWord rootWord) {
        RootWord src = feminineBased ? feminineBuilder.accusativeSingular() : masculineBuilder.accusativeSingular();
        return new RootWord(src).withSarfTermType(getTermType());
    }

    @Override
    protected RootWord doAccusativeDual(RootWord rootWord) {
        RootWord src = feminineBased ? feminineBuilder.accusativeDual() : masculineBuilder.accusativeDual();
        return new RootWord(src).withSarfTermType(getTermType());
    }

    @Override
    protected RootWord doAccusativePlural(RootWord rootWord) {
        return new RootWord(feminineBuilder.accusativePlural()).withSarfTermType(getTermType());
    }

    @Override
    protected RootWord doGenitiveSingular(RootWord rootWord) {
        RootWord src = feminineBased ? feminineBuilder.genitiveSingular() : masculineBuilder.genitiveSingular();
        return new RootWord(src).withSarfTermType(getTermType());
    }

    @Override
    public RootWord getDefaultConjugation() {
        return verbalNoun ? accusativeSingular() : nominativeSingular();
    }

    @Override
    public SarfTermType getTermType() {
        return verbalNoun ? VERBAL_NOUN : NOUN_OF_PLACE_AND_TIME;
    }

}
