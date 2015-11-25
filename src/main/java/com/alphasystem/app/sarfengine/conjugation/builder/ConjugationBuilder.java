package com.alphasystem.app.sarfengine.conjugation.builder;

import com.alphasystem.app.sarfengine.conjugation.model.SarfChart;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.VernalNounAndAdverbContainer;

/**
 * @author sali
 */
public interface ConjugationBuilder {

    SarfChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                            boolean skipRuleProcessing, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical, ArabicLetterType fourthRadical,
                            VernalNounAndAdverbContainer container);

    SarfChart doConjugation(NamedTemplate template, String translation, boolean removePassiveLine,
                            boolean skipRuleProcessing, ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                            ArabicLetterType thirdRadical, VernalNounAndAdverbContainer container);
}
