/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.builder;

import com.alphasystem.arabic.model.NamedTemplate;

import static com.alphasystem.arabic.model.NamedTemplate.*;

/**
 * @author sali
 */
public final class ConjugationBuilderFactory {

    private static ConjugationBuilderFactory instance;

    private ConjugationBuilderFactory() {
    }

    /**
     * @param template
     * @return
     */
    public static ConjugationBuilder getConjugationBuilder(
            NamedTemplate template) {
        DefaultConjugationBuilder builder = null;
        switch (template) {
            case FORM_I_CATEGORY_A_GROUP_U_TEMPLATE:
                builder = new DefaultConjugationBuilder(
                        FORM_I_CATEGORY_A_GROUP_U_TEMPLATE);
                break;
            case FORM_I_CATEGORY_A_GROUP_I_TEMPLATE:
                builder = new DefaultConjugationBuilder(
                        FORM_I_CATEGORY_A_GROUP_I_TEMPLATE);
                break;
            case FORM_I_CATEGORY_A_GROUP_A_TEMPLATE:
                builder = new DefaultConjugationBuilder(
                        FORM_I_CATEGORY_A_GROUP_A_TEMPLATE);
                break;
            case FORM_I_CATEGORY_I_GROUP_A_TEMPLATE:
                builder = new DefaultConjugationBuilder(
                        FORM_I_CATEGORY_I_GROUP_A_TEMPLATE);
                break;
            case FORM_I_CATEGORY_I_GROUP_I_TEMPLATE:
                builder = new DefaultConjugationBuilder(
                        FORM_I_CATEGORY_I_GROUP_I_TEMPLATE);
                break;
            case FORM_I_CATEGORY_U_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_I_CATEGORY_U_TEMPLATE);
                break;
            case FORM_II_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_II_TEMPLATE);
                break;
            case FORM_III_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_III_TEMPLATE);
                break;
            case FORM_IV_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_IV_TEMPLATE);
                break;
            case FORM_V_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_V_TEMPLATE);
                break;
            case FORM_VI_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_VI_TEMPLATE);
                break;
            case FORM_VII_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_VII_TEMPLATE);
                break;
            case FORM_VIII_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_VIII_TEMPLATE);
                break;
            case FORM_IX_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_IX_TEMPLATE);
                break;
            case FORM_X_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_X_TEMPLATE);
                break;
            case FORM_XI_TEMPLATE:
                builder = new DefaultConjugationBuilder(FORM_XI_TEMPLATE);
                break;
            default:
                break;
        }
        return builder;
    }

    public static synchronized ConjugationBuilderFactory getInstance() {
        if (instance == null) {
            instance = new ConjugationBuilderFactory();
        }
        return instance;
    }

    public ConjugationBuilder getFormICategoryAGroupABuilder() {
        return getConjugationBuilder(FORM_I_CATEGORY_A_GROUP_A_TEMPLATE);
    }

    public ConjugationBuilder getFormICategoryAGroupIBuilder() {
        return getConjugationBuilder(FORM_I_CATEGORY_A_GROUP_I_TEMPLATE);
    }

    public ConjugationBuilder getFormICategoryAGroupUBuilder() {
        return getConjugationBuilder(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE);
    }

    public ConjugationBuilder getFormICategoryIGroupABuilder() {
        return getConjugationBuilder(FORM_I_CATEGORY_I_GROUP_A_TEMPLATE);
    }

    public ConjugationBuilder getFormICategoryIGroupIBuilder() {
        return getConjugationBuilder(FORM_I_CATEGORY_I_GROUP_I_TEMPLATE);
    }

    public ConjugationBuilder getFormICategoryUBuilder() {
        return getConjugationBuilder(FORM_I_CATEGORY_U_TEMPLATE);
    }

    public ConjugationBuilder getFormIIBuilder() {
        return getConjugationBuilder(FORM_II_TEMPLATE);
    }

    public ConjugationBuilder getFormIIIBuilder() {
        return getConjugationBuilder(FORM_III_TEMPLATE);
    }

    public ConjugationBuilder getFormIVBuilder() {
        return getConjugationBuilder(FORM_IV_TEMPLATE);
    }

    public ConjugationBuilder getFormIXBuilder() {
        return getConjugationBuilder(FORM_IX_TEMPLATE);
    }

    public ConjugationBuilder getFormVBuilder() {
        return getConjugationBuilder(FORM_V_TEMPLATE);
    }

    public ConjugationBuilder getFormVIBuilder() {
        return getConjugationBuilder(FORM_VI_TEMPLATE);
    }

    public ConjugationBuilder getFormVIIBuilder() {
        return getConjugationBuilder(FORM_VII_TEMPLATE);
    }

    public ConjugationBuilder getFormVIIIBuilder() {
        return getConjugationBuilder(FORM_VIII_TEMPLATE);
    }

    public ConjugationBuilder getFormXBuilder() {
        return getConjugationBuilder(FORM_X_TEMPLATE);
    }

    public ConjugationBuilder getFormXIBuilder() {
        return getConjugationBuilder(FORM_XI_TEMPLATE);
    }
}
