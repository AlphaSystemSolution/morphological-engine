package com.alphasystem.app.sarfengine.conjugation.transformer;

import com.alphasystem.app.sarfengine.conjugation.transformer.noun.NounTransformer;
import com.alphasystem.app.sarfengine.conjugation.transformer.noun.TransformerFactory;
import com.alphasystem.app.sarfengine.guice.GuiceSupport;
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
     * @param noun given {@link NounSupport} pattern
     * @return corresponding {@link NounTransformer}
     */
    public NounTransformer getSingularTransformer(NounSupport noun) {
        //checkBrokenPlural(noun);
        if (noun.isFeminine()) {
            return transformerFactory.getFeminineEndingSoundTransformer();
        } else {
            return transformerFactory.getMasculineEndingSoundTransformer();
        }
    }

    /**
     * Gets dual {@link NounTransformer} for the given {@link NounSupport} value.
     *
     * @param noun given {@link NounSupport} pattern
     * @return corresponding {@link NounTransformer}
     */
    public NounTransformer getDualTransformer(NounSupport noun) {
        //checkBrokenPlural(noun);
        if (noun.isFeminine()) {
            return transformerFactory.getFeminineDualTransformer();
        } else {
            return transformerFactory.getMasculineDualTransformer();
        }
    }

    /**
     * Gets plural {@link NounTransformer} for the given {@link NounSupport} value.
     *
     * @param noun given {@link NounSupport} pattern
     * @return corresponding {@link NounTransformer}
     */
    public NounTransformer getPluralTransformer(NounSupport noun) {
        //checkBrokenPlural(noun);
        if (noun.isFeminine()) {
            return transformerFactory.getFemininePluralTransformer();
        } else {
            return transformerFactory.getMasculinePluralTransformer();
        }
    }

    /**
     * Gets broken plural {@link NounTransformer} for the given {@link Noun} value.
     *
     * @return corresponding {@link NounTransformer}
     */
    public NounTransformer getBrokenPluralTransformer(Noun noun) {
        NounTransformer nounTransformer = null;
        switch (noun) {
            case BROKEN_PLURAL_V1:
            case BROKEN_PLURAL_V2:
            case BROKEN_PLURAL_V3:
            case BROKEN_PLURAL_V4:
            case BROKEN_PLURAL_V5:
                nounTransformer = transformerFactory.getMasculineEndingSoundTransformer();
                break;
            case BROKEN_PLURAL_V6:
            case BROKEN_PLURAL_V7:
                nounTransformer = transformerFactory.getFeminineEndingSoundTransformer();
                break;
            case BROKEN_PLURAL_V8:
            case BROKEN_PLURAL_V9:
            case BROKEN_PLURAL_V10:
            case BROKEN_PLURAL_V11:
            case BROKEN_PLURAL_V12:
                nounTransformer = transformerFactory.getPartlyFlexibleNounTransformer(LAST_LETTER);
                break;
            case BROKEN_PLURAL_V13:
                nounTransformer = transformerFactory.getNonFlexibleNounTransformer();
                break;
        }
        return nounTransformer;
    }
}
