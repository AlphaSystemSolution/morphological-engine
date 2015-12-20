package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

;

/**
 * @author sali
 */
public interface RuleProcessor {

    RootWord applyRules(RootWord baseRootWord);

}
