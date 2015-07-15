/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.sarfengine.xml.model.RootWord;

/**
 * @author sali
 */
public interface RuleProcessor {

    RootWord applyRules(RootWord baseRootWord);

    DiacriticType getDiacriticForWeakSecondRadicalWaw();

    void setDiacriticForWeakSecondRadicalWaw(
            DiacriticType diacriticForWeakSecondRadicalWaw);

    boolean isPastTenseHasTransformed();

    void setPastTenseHasTransformed(boolean pastTenseHasTransformed);
}
