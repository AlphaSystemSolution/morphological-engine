package com.alphasystem.app.morphologicalengine.util;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.support.BrokenPlural;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Noun;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sali
 */
public final class NounOfPlaceAndTimeFactory {

    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_V1 = new NounRootBase(Noun.NOUN_OF_PLACE_AND_TIME_V1, BrokenPlural.BROKEN_PLURAL_V12);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_V2 = new NounRootBase(Noun.NOUN_OF_PLACE_AND_TIME_V2, BrokenPlural.BROKEN_PLURAL_V13);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_V3 = new NounRootBase(Noun.NOUN_OF_PLACE_AND_TIME_V3);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_FORM_II = new NounRootBase(Noun.FORM_II_MASCULINE_PASSIVE_PARTICIPLE, Noun.FORM_II_FEMININE_PASSIVE_PARTICIPLE);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_FORM_III = new NounRootBase(Noun.FORM_III_MASCULINE_PASSIVE_PARTICIPLE, Noun.FORM_III_FEMININE_PASSIVE_PARTICIPLE);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_FORM_IV = new NounRootBase(Noun.FORM_IV_MASCULINE_PASSIVE_PARTICIPLE, Noun.FORM_IV_FEMININE_PASSIVE_PARTICIPLE);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_FORM_V = new NounRootBase(Noun.FORM_V_MASCULINE_PASSIVE_PARTICIPLE, Noun.FORM_V_FEMININE_PASSIVE_PARTICIPLE);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_FORM_VI = new NounRootBase(Noun.FORM_VI_MASCULINE_PASSIVE_PARTICIPLE, Noun.FORM_VI_FEMININE_PASSIVE_PARTICIPLE);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_FORM_VII = new NounRootBase(Noun.FORM_VII_MASCULINE_PASSIVE_PARTICIPLE, Noun.FORM_VII_FEMININE_PASSIVE_PARTICIPLE);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_FORM_VIII = new NounRootBase(Noun.FORM_VIII_MASCULINE_PASSIVE_PARTICIPLE, Noun.FORM_VIII_FEMININE_PASSIVE_PARTICIPLE);
    private static final NounRootBase NOUN_OF_PLACE_AND_TIME_FORM_X = new NounRootBase(Noun.FORM_X_MASCULINE_PASSIVE_PARTICIPLE, Noun.FORM_X_FEMININE_PASSIVE_PARTICIPLE);

    private static final Map<NamedTemplate, NounRootBase[]> map = new HashMap<>();

    static {
        map.put(NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, new NounRootBase[]{
                NOUN_OF_PLACE_AND_TIME_V1, NOUN_OF_PLACE_AND_TIME_V2, NOUN_OF_PLACE_AND_TIME_V3});
        map.put(NamedTemplate.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE, new NounRootBase[]{
                NOUN_OF_PLACE_AND_TIME_V1, NOUN_OF_PLACE_AND_TIME_V2, NOUN_OF_PLACE_AND_TIME_V3});
        map.put(NamedTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE, new NounRootBase[]{
                NOUN_OF_PLACE_AND_TIME_V1, NOUN_OF_PLACE_AND_TIME_V2, NOUN_OF_PLACE_AND_TIME_V3});
        map.put(NamedTemplate.FORM_I_CATEGORY_U_TEMPLATE, new NounRootBase[]{
                NOUN_OF_PLACE_AND_TIME_V1, NOUN_OF_PLACE_AND_TIME_V2, NOUN_OF_PLACE_AND_TIME_V3});
        map.put(NamedTemplate.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE, new NounRootBase[]{
                NOUN_OF_PLACE_AND_TIME_V1, NOUN_OF_PLACE_AND_TIME_V2, NOUN_OF_PLACE_AND_TIME_V3});
        map.put(NamedTemplate.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE, new NounRootBase[]{
                NOUN_OF_PLACE_AND_TIME_V1, NOUN_OF_PLACE_AND_TIME_V2, NOUN_OF_PLACE_AND_TIME_V3});
        map.put(NamedTemplate.FORM_II_TEMPLATE, new NounRootBase[]{NOUN_OF_PLACE_AND_TIME_FORM_II});
        map.put(NamedTemplate.FORM_III_TEMPLATE, new NounRootBase[]{NOUN_OF_PLACE_AND_TIME_FORM_III});
        map.put(NamedTemplate.FORM_IV_TEMPLATE, new NounRootBase[]{NOUN_OF_PLACE_AND_TIME_FORM_IV});
        map.put(NamedTemplate.FORM_V_TEMPLATE, new NounRootBase[]{NOUN_OF_PLACE_AND_TIME_FORM_V});
        map.put(NamedTemplate.FORM_VI_TEMPLATE, new NounRootBase[]{NOUN_OF_PLACE_AND_TIME_FORM_VI});
        map.put(NamedTemplate.FORM_VII_TEMPLATE, new NounRootBase[]{NOUN_OF_PLACE_AND_TIME_FORM_VII});
        map.put(NamedTemplate.FORM_VIII_TEMPLATE, new NounRootBase[]{NOUN_OF_PLACE_AND_TIME_FORM_VIII});
        map.put(NamedTemplate.FORM_X_TEMPLATE, new NounRootBase[]{NOUN_OF_PLACE_AND_TIME_FORM_X});
    }

    /**
     * Do not let any one instantiate this class
     */
    private NounOfPlaceAndTimeFactory() {
    }

    public static NounRootBase[] getByNamedTemplate(NamedTemplate namedTemplate) {
        final NounRootBase[] nounRootBases = map.get(namedTemplate);
        if (ArrayUtils.isEmpty(nounRootBases)) {
            throw new NullPointerException(String.format("No \"NounOfPlaceAndTime\" is configured for template \"%s\"", namedTemplate));
        }
        return nounRootBases;
    }

}
