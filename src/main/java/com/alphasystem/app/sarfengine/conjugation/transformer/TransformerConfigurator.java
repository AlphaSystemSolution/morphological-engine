package com.alphasystem.app.sarfengine.conjugation.transformer;

import com.alphasystem.app.sarfengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.sarfengine.conjugation.transformer.noun.TransformerFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Noun;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounSupport;

import static com.alphasystem.app.sarfengine.conjugation.transformer.noun.AbstractNounTransformer.LAST_LETTER;

/**
 * @author sali
 */
public final class TransformerConfigurator {

    private static TransformerConfigurator instance;

    static {
        instance = new TransformerConfigurator();
    }

    private TransformerFactory transformerFactory;

    /**
     * Do not let anyone instantiate this class
     */
    private TransformerConfigurator() {
        transformerFactory = GuiceSupport.getInstance().getTransformerFactory();
    }

    public static TransformerConfigurator getInstance() {
        return instance;
    }

    @SuppressWarnings({"unused"})
    private static void checkBrokenPlural(Noun noun) throws IllegalArgumentException {
        switch (noun) {
            case BROKEN_PLURAL_V1:
            case BROKEN_PLURAL_V2:
            case BROKEN_PLURAL_V3:
            case BROKEN_PLURAL_V4:
            case BROKEN_PLURAL_V5:
            case BROKEN_PLURAL_V6:
            case BROKEN_PLURAL_V7:
            case BROKEN_PLURAL_V8:
            case BROKEN_PLURAL_V9:
            case BROKEN_PLURAL_V10:
            case BROKEN_PLURAL_V11:
            case BROKEN_PLURAL_V12:
            case BROKEN_PLURAL_V13:
                throw new IllegalArgumentException(String.format("Given value {%s} is a plural."));
        }
    }

    /**
     * Gets singular {@link NounTransformer} for the given {@link NounSupport} value.
     *
     * @param noun          given {@link NounSupport} pattern
     * @param firstRadical  first radical of the target word
     * @param secondRadical second radical of the target word
     * @param thirdRadical  third radical of the target word
     * @param fourthRadical fourth radical of the target word, may be null
     * @return corresponding {@link NounTransformer}
     */
    public NounTransformer getSingularTransformer(NounSupport noun, ArabicLetterType firstRadical,
                                                  ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                  ArabicLetterType fourthRadical) {
        //checkBrokenPlural(noun);
        if (noun.isFeminine()) {
            return transformerFactory.getFeminineEndingSoundTransformer(noun.getRootWord(), firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        } else {
            return transformerFactory.getMasculineEndingSoundTransformer(noun.getRootWord(), firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }
    }

    /**
     * Gets dual {@link NounTransformer} for the given {@link NounSupport} value.
     *
     * @param noun          given {@link NounSupport} pattern
     * @param firstRadical  first radical of the target word
     * @param secondRadical second radical of the target word
     * @param thirdRadical  third radical of the target word
     * @param fourthRadical fourth radical of the target word, may be null
     * @return corresponding {@link NounTransformer}
     */
    public NounTransformer getDualTransformer(NounSupport noun, ArabicLetterType firstRadical,
                                              ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                              ArabicLetterType fourthRadical) {
        //checkBrokenPlural(noun);
        if (noun.isFeminine()) {
            return transformerFactory.getFeminineDualTransformer(noun.getRootWord(), firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        } else {
            return transformerFactory.getMasculineDualTransformer(noun.getRootWord(), firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }
    }

    /**
     * Gets plural {@link NounTransformer} for the given {@link NounSupport} value.
     *
     * @param noun          given {@link NounSupport} pattern
     * @param firstRadical  first radical of the target word
     * @param secondRadical second radical of the target word
     * @param thirdRadical  third radical of the target word
     * @param fourthRadical fourth radical of the target word, may be null
     * @return corresponding {@link NounTransformer}
     */
    public NounTransformer getPluralTransformer(NounSupport noun, ArabicLetterType firstRadical,
                                                ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                ArabicLetterType fourthRadical) {
        //checkBrokenPlural(noun);
        if (noun.isFeminine()) {
            return transformerFactory.getFemininePluralTransformer(noun.getRootWord(), firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        } else {
            return transformerFactory.getMasculinePluralTransformer(noun.getRootWord(), firstRadical,
                    secondRadical, thirdRadical, fourthRadical);
        }
    }

    /**
     * Gets broken plural {@link NounTransformer} for the given {@link Noun} value.
     *
     * @param noun          given {@link Noun} pattern
     * @param firstRadical  first radical of the target word
     * @param secondRadical second radical of the target word
     * @param thirdRadical  third radical of the target word
     * @param fourthRadical fourth radical of the target word, may be null
     * @return corresponding {@link NounTransformer}
     */
    public NounTransformer getBrokenPluralTransformer(Noun noun, ArabicLetterType firstRadical,
                                                      ArabicLetterType secondRadical, ArabicLetterType thirdRadical,
                                                      ArabicLetterType fourthRadical) {
        NounTransformer nounTransformer = null;
        switch (noun) {
            case BROKEN_PLURAL_V1:
            case BROKEN_PLURAL_V2:
            case BROKEN_PLURAL_V3:
            case BROKEN_PLURAL_V4:
            case BROKEN_PLURAL_V5:
                nounTransformer = transformerFactory.getMasculineEndingSoundTransformer(noun.getRootWord(), firstRadical,
                        secondRadical, thirdRadical, fourthRadical);
                break;
            case BROKEN_PLURAL_V6:
            case BROKEN_PLURAL_V7:
                nounTransformer = transformerFactory.getFeminineEndingSoundTransformer(noun.getRootWord(), firstRadical,
                        secondRadical, thirdRadical, fourthRadical);
                break;
            case BROKEN_PLURAL_V8:
            case BROKEN_PLURAL_V9:
            case BROKEN_PLURAL_V10:
            case BROKEN_PLURAL_V11:
            case BROKEN_PLURAL_V12:
                nounTransformer = transformerFactory.getPartlyFlexibleNounTransformer(noun.getRootWord(), firstRadical,
                        secondRadical, thirdRadical, fourthRadical, LAST_LETTER);
                break;
            case BROKEN_PLURAL_V13:
                nounTransformer = transformerFactory.getNonFlexibleNounTransformer(noun.getRootWord(), firstRadical,
                        secondRadical, thirdRadical, fourthRadical);
                break;
        }
        return nounTransformer;
    }
}
