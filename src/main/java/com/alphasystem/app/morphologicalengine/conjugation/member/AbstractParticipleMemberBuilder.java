package com.alphasystem.app.morphologicalengine.conjugation.member;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.noun.NounTransformerFactory;
import com.alphasystem.app.sarfengine.conjugation.model.Form;
import com.alphasystem.app.sarfengine.conjugation.model.NounConjugation;
import com.alphasystem.app.sarfengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.sarfengine.conjugation.model.RootLetters;
import com.alphasystem.app.sarfengine.conjugation.rule.RuleProcessor;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.NounRootBase;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.ACTIVE_PARTICIPLE_MASCULINE;
import static java.lang.String.format;

/**
 * @author sali
 */
public abstract class AbstractParticipleMemberBuilder extends AbstractConjugationMemberBuilder<NounConjugationGroup, NounRootBase>
        implements ParticipleMemberBuilder {

    protected static NounTransformerFactory nounTransformerFactory = GuiceSupport.getInstance().getNounTransformerFactory();

    private NounConjugationGroup nounConjugationGroup;

    protected NounTransformer singularTransformer;

    protected NounTransformer dualTransformer;

    protected NounTransformer pluralTransformer;

    protected AbstractParticipleMemberBuilder(RuleProcessor ruleProcessor, NounRootBase rootBase, RootLetters rootLetters) {
        super(ruleProcessor, rootBase, rootLetters);
    }

    protected AbstractParticipleMemberBuilder(RuleProcessor ruleProcessor, Form form, RootLetters rootLetters) {
        super(ruleProcessor, form, rootLetters);
    }

    @Override
    protected NounRootBase getRootBase(Form form) {
        NounRootBase rootBase = null;
        final SarfTermType termType = getTermType();
        switch (termType) {
            case ACTIVE_PARTICIPLE_MASCULINE:
                rootBase = form.getActiveParticipleMasculine();
                break;
            case ACTIVE_PARTICIPLE_FEMININE:
                rootBase = form.getActiveParticipleFeminine();
                break;
            case PASSIVE_PARTICIPLE_MASCULINE:
                rootBase = form.getPassiveParticipleMasculine();
                break;
            case PASSIVE_PARTICIPLE_FEMININE:
                rootBase = form.getPassiveParticipleFeminine();
                break;
            case VERBAL_NOUN:
                break;
            case NOUN_OF_PLACE_AND_TIME:
                break;
        }
        if (rootBase == null) {
            throw new NullPointerException(format("No root base defined for term \"%s\" in form \"%s\".",
                    termType.name(), form.getTemplate().name()));
        }
        return rootBase;
    }

    @Override
    protected void initializeTransformers() {
        initializeSingularTransformer();
        initializeDualTransformer();
        initializePluralTransformer();
    }

    protected void initializeSingularTransformer() {
        singularTransformer = nounTransformerFactory.getMasculineEndingSoundTransformer(getRuleProcessor());
    }

    protected void initializeDualTransformer() {
        dualTransformer = nounTransformerFactory.getMasculineDualTransformer(getRuleProcessor());
    }

    protected void initializePluralTransformer() {
        pluralTransformer = nounTransformerFactory.getMasculinePluralTransformer(getRuleProcessor());
    }

    @Override
    public NounConjugationGroup doConjugation() {
        if (nounConjugationGroup == null) {
            nounConjugationGroup = new NounConjugationGroup();
            final ArabicLetterType firstRadical = rootLetters.getFirstRadical();
            final ArabicLetterType secondRadical = rootLetters.getSecondRadical();
            final ArabicLetterType thirdRadical = rootLetters.getThirdRadical();
            final ArabicLetterType fourthRadical = rootLetters.getFourthRadical();
            nounConjugationGroup.setSingular(doTransform(singularTransformer, rootBase.getSingularBaseWord(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical));
            nounConjugationGroup.setDual(doTransform(dualTransformer, rootBase.getDualBaseWord(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical));
            nounConjugationGroup.setPlural(doTransform(pluralTransformer, rootBase.getPluralBaseWord(),
                    firstRadical, secondRadical, thirdRadical, fourthRadical));
        }
        return nounConjugationGroup;
    }

    private NounConjugation doTransform(NounTransformer transformer, NounSupport baseWord, ArabicLetterType firstRadical,
                                        ArabicLetterType secondRadical, ArabicLetterType thirdRadical, ArabicLetterType fourthRadical) {
        if (transformer != null && baseWord != null) {
            return transformer.doTransform(new RootWord(baseWord.getRootWord()).withSarfTermType(getTermType()),
                    firstRadical, secondRadical, thirdRadical, fourthRadical);
        }
        return null;
    }

    @Override
    public RootWord getDefaultConjugation() {
        if (nounConjugationGroup == null) {
            doConjugation();
        }
        final NounConjugation singular = nounConjugationGroup.getSingular();
        return (singular == null) ? null : singular.getNominative();
    }

    @Override
    public SarfTermType getTermType() {
        return ACTIVE_PARTICIPLE_MASCULINE;
    }
}
