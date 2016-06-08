/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import java.util.*;

import static com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType.*;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.*;
import static com.alphasystem.morphologicalanalysis.morphology.util.TriLiteralTemplateHelper.*;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

;

/**
 * @author sali
 */
@XmlType(name = "FormTemplateType")
@XmlEnum
public enum FormTemplate implements ArabicLetters {

    FORM_I_CATEGORY_A_GROUP_U_TEMPLATE(
            NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE,
            createPastTenseRootWord(0, 1, 2, FA_WITH_FATHA, AIN_WITH_FATHA,
                    LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, FA_WITH_SUKUN,
                    AIN_WITH_DAMMA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(0, 1, 2, FA_WITH_DAMMA,
                    AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 2, 3, YA_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    FORM_I_CATEGORY_A_GROUP_I_TEMPLATE(
            NamedTemplate.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE,
            createPastTenseRootWord(0, 1, 2, FA_WITH_FATHA, AIN_WITH_FATHA,
                    LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, FA_WITH_SUKUN,
                    AIN_WITH_KASRA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(0, 1, 2, FA_WITH_DAMMA,
                    AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 2, 3, YA_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    FORM_I_CATEGORY_A_GROUP_A_TEMPLATE(
            NamedTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE,
            createPastTenseRootWord(0, 1, 2, FA_WITH_FATHA, AIN_WITH_FATHA,
                    LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, FA_WITH_SUKUN,
                    AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(0, 1, 2, FA_WITH_DAMMA,
                    AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 2, 3, YA_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    FORM_I_CATEGORY_U_TEMPLATE(NamedTemplate.FORM_I_CATEGORY_U_TEMPLATE,
            createPastTenseRootWord(0, 1, 2, FA_WITH_FATHA, AIN_WITH_DAMMA,
                    LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, FA_WITH_SUKUN,
                    AIN_WITH_DAMMA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(0, 1, 3, FA_WITH_FATHA,
                    AIN_WITH_KASRA, YA_WITH_SUKUN, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(0, 1, 3, FA_WITH_FATHA,
                    AIN_WITH_KASRA, YA_WITH_SUKUN, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN)) {
        @Override
        public boolean isNoPassive() {
            return true;
        }
    },

    FORM_I_CATEGORY_I_GROUP_A_TEMPLATE(
            NamedTemplate.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE,
            createPastTenseRootWord(0, 1, 2, FA_WITH_FATHA, AIN_WITH_KASRA,
                    LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, FA_WITH_SUKUN,
                    AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(0, 1, 2, FA_WITH_DAMMA,
                    AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 2, 3, YA_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    FORM_I_CATEGORY_I_GROUP_I_TEMPLATE(
            NamedTemplate.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE,
            createPastTenseRootWord(0, 1, 2, FA_WITH_FATHA, AIN_WITH_KASRA,
                    LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, FA_WITH_SUKUN,
                    AIN_WITH_KASRA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(0, 1, 2, FA_WITH_DAMMA,
                    AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 2, 3, YA_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(0, 2, 3, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 2, 4, MEEM_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_DAMMA, WAW_WITH_SUKUN,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN)),

    FORM_II_TEMPLATE(NamedTemplate.FORM_II_TEMPLATE,

            createPastTenseRootWord(0, 1, 2, FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA,
                    LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_DAMMA, FA_WITH_FATHA,
                    AIN_WITH_SHADDA_AND_KASRA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(0, 1, 2, FA_WITH_DAMMA,
                    AIN_WITH_SHADDA_AND_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 2, 3, YA_WITH_DAMMA, FA_WITH_FATHA,
                    AIN_WITH_SHADDA_AND_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, AIN_WITH_SHADDA_AND_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, AIN_WITH_SHADDA_AND_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA, LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VerbalNoun.VERBAL_NOUN_FORM_II.getRootWord()},

            new RootWord[]{createZarfRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA,
                    LAM_WITH_DAMMATAN)}),

    FORM_III_TEMPLATE(
            NamedTemplate.FORM_III_TEMPLATE,
            createPastTenseRootWord(0, 2, 3, FA_WITH_FATHA, LETTER_ALIF,
                    AIN_WITH_FATHA, LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 3, 4, YA_WITH_DAMMA, FA_WITH_FATHA,
                    LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(0, 2, 3, FA_WITH_DAMMA,
                    WAW_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 3, 4, YA_WITH_DAMMA,
                    FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_KASRA,
                    LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VERBAL_NOUN_FORM_III_V1.getRootWord(),
                    VERBAL_NOUN_FORM_III_V2.getRootWord()},

            new RootWord[]{createZarfRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA,
                    LAM_WITH_DAMMATAN)}),

    FORM_IV_TEMPLATE(NamedTemplate.FORM_IV_TEMPLATE,

            createPastTenseRootWord(1, 2, 3, ALIF_HAMZA_ABOVE_WITH_FATHA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_DAMMA, FA_WITH_SUKUN,
                    AIN_WITH_KASRA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(1, 2, 3, ALIF_HAMZA_ABOVE_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 2, 3, YA_WITH_DAMMA, FA_WITH_SUKUN,
                    AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VERBAL_NOUN_FORM_IV.getRootWord()},

            new RootWord[]{createZarfRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMATAN)}),

    FORM_V_TEMPLATE(NamedTemplate.FORM_V_TEMPLATE,

            createPastTenseRootWord(1, 2, 3, TA_WITH_FATHA, FA_WITH_FATHA,
                    AIN_WITH_SHADDA_AND_FATHA, LAM_WITH_FATHA),

            createPresentTenseRootWord(2, 3, 4, YA_WITH_FATHA, TA_WITH_FATHA,
                    FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(1, 2, 3, TA_WITH_DAMMA, FA_WITH_DAMMA,
                    AIN_WITH_SHADDA_AND_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(2, 3, 4, YA_WITH_DAMMA, TA_WITH_FATHA,
                    FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, AIN_WITH_SHADDA_AND_KASRA,
                    LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, AIN_WITH_SHADDA_AND_KASRA,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VERBAL_NOUN_FORM_V.getRootWord()},

            new RootWord[]{createZarfRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, AIN_WITH_SHADDA_AND_FATHA,
                    LAM_WITH_DAMMATAN)}),

    FORM_VI_TEMPLATE(NamedTemplate.FORM_VI_TEMPLATE,

            createPastTenseRootWord(1, 3, 4, TA_WITH_FATHA, FA_WITH_FATHA, LETTER_ALIF,
                    AIN_WITH_FATHA, LAM_WITH_FATHA),

            createPresentTenseRootWord(2, 4, 5, YA_WITH_FATHA, TA_WITH_FATHA,
                    FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(1, 3, 4, TA_WITH_DAMMA, FA_WITH_DAMMA,
                    WAW_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(2, 4, 5, YA_WITH_DAMMA, TA_WITH_FATHA,
                    FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(2, 4, 5, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_KASRA,
                    LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(2, 4, 5, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_KASRA,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(2, 4, 5, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(2, 4, 5, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VERBAL_NOUN_FORM_VI.getRootWord()},

            new RootWord[]{createZarfRootWord(2, 4, 5, MEEM_WITH_DAMMA,
                    TA_WITH_FATHA, FA_WITH_FATHA, LETTER_ALIF, AIN_WITH_FATHA,
                    LAM_WITH_DAMMATAN)}),

    FORM_VII_TEMPLATE(NamedTemplate.FORM_VII_TEMPLATE,

            createPastTenseRootWord(2, 3, 4, ALIF_HAMZA_BELOW_WITH_KASRA,
                    NOON_WITH_SUKUN, FA_WITH_FATHA, AIN_WITH_FATHA, LAM_WITH_FATHA),

            createPresentTenseRootWord(2, 3, 4, YA_WITH_FATHA, NOON_WITH_SUKUN,
                    FA_WITH_FATHA, AIN_WITH_KASRA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    NOON_WITH_SUKUN, FA_WITH_FATHA, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    NOON_WITH_SUKUN, FA_WITH_FATHA, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VERBAL_NOUN_FORM_VII.getRootWord()},

            new RootWord[]{createZarfRootWord(2, 3, 4, MEEM_WITH_DAMMA,
                    NOON_WITH_SUKUN, FA_WITH_FATHA, AIN_WITH_FATHA,
                    LAM_WITH_DAMMATAN)}) {
        @Override
        public boolean isNoPassive() {
            return true;
        }
    },

    FORM_VIII_TEMPLATE(NamedTemplate.FORM_VIII_TEMPLATE,

            createPastTenseRootWord(1, 3, 4, ALIF_HAMZA_BELOW_WITH_KASRA,
                    FA_WITH_SUKUN, TA_WITH_FATHA, AIN_WITH_FATHA, LAM_WITH_FATHA),

            createPresentTenseRootWord(1, 3, 4, YA_WITH_FATHA, FA_WITH_SUKUN,
                    TA_WITH_FATHA, AIN_WITH_KASRA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(1, 3, 4, ALIF_HAMZA_ABOVE_WITH_DAMMA,
                    FA_WITH_SUKUN, TA_WITH_DAMMA, AIN_WITH_KASRA, LAM_WITH_FATHA),

            createPresentPassiveTenseRootWord(1, 3, 4, YA_WITH_DAMMA, FA_WITH_SUKUN,
                    TA_WITH_FATHA, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createActiveParticipleMasculineRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, TA_WITH_FATHA, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, TA_WITH_FATHA, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, TA_WITH_FATHA, AIN_WITH_FATHA, LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, TA_WITH_FATHA, AIN_WITH_FATHA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VERBAL_NOUN_FORM_VIII.getRootWord()},

            new RootWord[]{createZarfRootWord(1, 3, 4, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, TA_WITH_FATHA, AIN_WITH_FATHA, LAM_WITH_DAMMATAN)}),

    FORM_IX_TEMPLATE(NamedTemplate.FORM_IX_TEMPLATE,

            createPastTenseRootWord(1, 2, 3, ALIF_HAMZA_BELOW_WITH_KASRA,
                    FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_SHADDA_AND_FATHA),

            createPresentTenseRootWord(1, 2, 3, YA_WITH_FATHA, FA_WITH_SUKUN,
                    AIN_WITH_FATHA, LAM_WITH_SHADDA_AND_DAMMA),

            createActiveParticipleMasculineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(1, 2, 3, MEEM_WITH_DAMMA,
                    FA_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_FATHA,
                    TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VerbalNoun.VERBAL_NOUN_FORM_IX.getRootWord()},

            new RootWord[]{}) {
        @Override
        public boolean isNoPassive() {
            return true;
        }
    },

    FORM_X_TEMPLATE(NamedTemplate.FORM_X_TEMPLATE,

            createPastTenseRootWord(3, 4, 5, ALIF_HAMZA_BELOW_WITH_KASRA,
                    SEEN_WITH_SUKUN, TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_FATHA,
                    LAM_WITH_FATHA),

            createPresentTenseRootWord(3, 4, 5, YA_WITH_FATHA, SEEN_WITH_SUKUN,
                    TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_KASRA, LAM_WITH_DAMMA),

            createPresentPassiveTenseRootWord(3, 4, 5, YA_WITH_DAMMA, SEEN_WITH_SUKUN,
                    TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_FATHA, LAM_WITH_DAMMA),

            createPastPassiveTenseRootWord(3, 4, 5, ALIF_HAMZA_ABOVE_WITH_DAMMA,
                    SEEN_WITH_SUKUN, TA_WITH_DAMMA, FA_WITH_SUKUN, AIN_WITH_KASRA,
                    LAM_WITH_FATHA),

            createActiveParticipleMasculineRootWord(3, 4, 5, MEEM_WITH_DAMMA,
                    SEEN_WITH_SUKUN, TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_KASRA,
                    LAM_WITH_DAMMATAN),

            createActiveParticipleFeminineRootWord(3, 4, 5, MEEM_WITH_DAMMA,
                    SEEN_WITH_SUKUN, TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_KASRA,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN),

            createPassiveParticipleMasculineRootWord(3, 4, 5, MEEM_WITH_DAMMA,
                    SEEN_WITH_SUKUN, TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_FATHA,
                    LAM_WITH_DAMMATAN),

            createPassiveParticipleFeminineRootWord(3, 4, 5, MEEM_WITH_DAMMA,
                    SEEN_WITH_SUKUN, TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_FATHA,
                    LAM_WITH_FATHA, TA_MARBUTA_WITH_DAMMATAN),

            new RootWord[]{VERBAL_NOUN_FORM_X.getRootWord()},

            new RootWord[]{createZarfRootWord(3, 4, 5, MEEM_WITH_DAMMA,
                    SEEN_WITH_SUKUN, TA_WITH_FATHA, FA_WITH_SUKUN, AIN_WITH_FATHA,
                    LAM_WITH_DAMMATAN)});

    private static final Map<NamedTemplate, FormTemplate> NAMED_TEMPLATE_MAP = new HashMap<>();

    static {
        for (FormTemplate formTemplate : values()) {
            NAMED_TEMPLATE_MAP.put(formTemplate.getTemplate(), formTemplate);
        }
    }

    private final Map<SarfTermType, RootWord[]> rootWordsMap = new LinkedHashMap<>();
    private final NamedTemplate template;

    /**
     * No passive.
     *
     * @param template
     * @param pastTenseRoot
     * @param presentTenseRoot
     * @param activeParticipleMasculineRoot
     * @param activeParticipleFeminineRoot
     */
    FormTemplate(NamedTemplate template, RootWord pastTenseRoot,
                 RootWord presentTenseRoot, RootWord activeParticipleMasculineRoot,
                 RootWord activeParticipleFeminineRoot) {
        this(template, pastTenseRoot, presentTenseRoot, null,
                null, activeParticipleMasculineRoot,
                activeParticipleFeminineRoot, null, null);
    }

    /**
     * @param template
     * @param pastTenseRoot
     * @param presentTenseRoot
     * @param pastPassiveTenseRoot
     * @param presentPassiveTenseRoot
     * @param activeParticipleMasculineRoot
     * @param activeParticipleFeminineRoot
     * @param passiveParticipleMasculineRoot
     * @param passiveParticipleFeminineRoot
     */
    FormTemplate(NamedTemplate template, RootWord pastTenseRoot,
                 RootWord presentTenseRoot, RootWord pastPassiveTenseRoot,
                 RootWord presentPassiveTenseRoot,
                 RootWord activeParticipleMasculineRoot,
                 RootWord activeParticipleFeminineRoot,
                 RootWord passiveParticipleMasculineRoot,
                 RootWord passiveParticipleFeminineRoot) {
        this(template, pastTenseRoot, presentTenseRoot, pastPassiveTenseRoot,
                presentPassiveTenseRoot, activeParticipleMasculineRoot,
                activeParticipleFeminineRoot, passiveParticipleMasculineRoot,
                passiveParticipleFeminineRoot, new RootWord[0], new RootWord[0]);
    }

    /**
     * @param template
     * @param pastTenseRoot
     * @param presentTenseRoot
     * @param pastPassiveTenseRoot
     * @param presentPassiveTenseRoot
     * @param activeParticipleMasculineRoot
     * @param activeParticipleFeminineRoot
     * @param passiveParticipleMasculineRoot
     * @param passiveParticipleFeminineRoot
     * @param imperativeRoot                 ;
     * @param forbiddingRoot
     * @param verbalNounRoots
     * @param zarfRoots
     */
    FormTemplate(NamedTemplate template, RootWord pastTenseRoot,
                 RootWord presentTenseRoot, RootWord pastPassiveTenseRoot,
                 RootWord presentPassiveTenseRoot,
                 RootWord activeParticipleMasculineRoot,
                 RootWord activeParticipleFeminineRoot,
                 RootWord passiveParticipleMasculineRoot,
                 RootWord passiveParticipleFeminineRoot, RootWord imperativeRoot,
                 RootWord forbiddingRoot, RootWord[] verbalNounRoots,
                 RootWord[] zarfRoots) {
        this.template = template;
        putValue(PAST_TENSE, pastTenseRoot);
        putValue(PRESENT_TENSE, presentTenseRoot);
        putValue(PAST_PASSIVE_TENSE, pastPassiveTenseRoot);
        putValue(PRESENT_PASSIVE_TENSE, presentPassiveTenseRoot);
        putValue(ACTIVE_PARTICIPLE_MASCULINE, activeParticipleMasculineRoot);
        putValue(ACTIVE_PARTICIPLE_FEMININE, activeParticipleFeminineRoot);
        putValue(PASSIVE_PARTICIPLE_MASCULINE, passiveParticipleMasculineRoot);
        putValue(PASSIVE_PARTICIPLE_FEMININE, passiveParticipleFeminineRoot);
        putValue(IMPERATIVE, imperativeRoot);
        putValue(FORBIDDING, forbiddingRoot);
        putValue(SarfTermType.VERBAL_NOUN, verbalNounRoots);
        putValue(SarfTermType.NOUN_OF_PLACE_AND_TIME, zarfRoots);
    }

    /**
     * @param template
     * @param pastTenseRoot
     * @param presentTenseRoot
     * @param pastPassiveTenseRoot
     * @param presentPassiveTenseRoot
     * @param activeParticipleMasculineRoot
     * @param activeParticipleFeminineRoot
     * @param passiveParticipleMasculineRoot
     * @param passiveParticipleFeminineRoot
     * @param verbalNounRoots
     * @param zarfRoots
     */
    FormTemplate(NamedTemplate template, RootWord pastTenseRoot,
                 RootWord presentTenseRoot, RootWord pastPassiveTenseRoot,
                 RootWord presentPassiveTenseRoot,
                 RootWord activeParticipleMasculineRoot,
                 RootWord activeParticipleFeminineRoot,
                 RootWord passiveParticipleMasculineRoot,
                 RootWord passiveParticipleFeminineRoot, RootWord[] verbalNounRoots,
                 RootWord[] zarfRoots) {
        this(template, pastTenseRoot, presentTenseRoot, pastPassiveTenseRoot,
                presentPassiveTenseRoot, activeParticipleMasculineRoot,
                activeParticipleFeminineRoot, passiveParticipleMasculineRoot,
                passiveParticipleFeminineRoot,
                createImperativeRootWord(presentTenseRoot),
                createForbiddingRootWord(presentTenseRoot), verbalNounRoots,
                zarfRoots);
    }

    FormTemplate(NamedTemplate template, RootWord pastTenseRoot,
                 RootWord presentTenseRoot, RootWord activeParticipleMasculineRoot,
                 RootWord activeParticipleFeminineRoot, RootWord[] verbalNounRoots,
                 RootWord[] zarfRoots) {
        this(template, pastTenseRoot, presentTenseRoot, null, null,
                activeParticipleMasculineRoot, activeParticipleFeminineRoot,
                null, null, createImperativeRootWord(presentTenseRoot),
                createForbiddingRootWord(presentTenseRoot), verbalNounRoots,
                zarfRoots);
    }

    public static FormTemplate getByNamedTemplate(NamedTemplate namedTemplate) {
        return NAMED_TEMPLATE_MAP.get(namedTemplate);
    }

    public RootWord getActiveParticipleFeminineRoot() {
        return getTemplateWord(ACTIVE_PARTICIPLE_FEMININE);
    }

    public RootWord getActiveParticipleMasculineRoot() {
        return getTemplateWord(ACTIVE_PARTICIPLE_MASCULINE);
    }

    public List<RootWord> getAllTemplateWords(SarfTermType sarfTermType) {
        RootWord[] values = rootWordsMap.get(sarfTermType);
        if (values == null) {
            return null;
        }
        List<RootWord> results = new ArrayList<RootWord>();
        for (RootWord rootWord : values) {
            results.add(new RootWord(rootWord));
        }
        return results;
    }

    public RootWord getForbiddingRoot() {
        return getTemplateWord(FORBIDDING);
    }

    public RootWord getImperativeRoot() {
        return getTemplateWord(IMPERATIVE);
    }

    public RootWord getPassiveParticipleFeminineRoot() {
        return getTemplateWord(PASSIVE_PARTICIPLE_FEMININE);
    }

    public RootWord getPassiveParticipleMasculineRoot() {
        return getTemplateWord(PASSIVE_PARTICIPLE_MASCULINE);
    }

    public RootWord getPastPassiveTenseRoot() {
        return getTemplateWord(PAST_PASSIVE_TENSE);
    }

    public RootWord getPastTenseRoot() {
        return getTemplateWord(PAST_TENSE);
    }

    public RootWord getPresentPassiveTenseRoot() {
        return getTemplateWord(PRESENT_PASSIVE_TENSE);
    }

    public RootWord getPresentTenseRoot() {
        return getTemplateWord(PRESENT_TENSE);
    }

    public NamedTemplate getTemplate() {
        return template;
    }

    public RootWord getTemplateWord(SarfTermType sarfTermType) {
        RootWord[] values = rootWordsMap.get(sarfTermType);
        RootWord result = null;
        if (!isEmpty(values)) {
            RootWord src = values[0];
            result = (src == null) ? null : new RootWord(src);
        }

        return result;
    }

    public List<RootWord> getVerbalNounRoots() {
        return getAllTemplateWords(VERBAL_NOUN);
    }

    public List<RootWord> getZarfRoots() {
        return getAllTemplateWords(NOUN_OF_PLACE_AND_TIME);
    }

    public boolean isNoPassive() {
        return false;
    }

    private void putValue(SarfTermType key, RootWord... rootWords) {
        rootWordsMap.put(key, rootWords);
    }

}
