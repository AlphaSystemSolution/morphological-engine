package com.alphasystem.app.sarfengine.conjugation.rule;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootWord;

/**
 * @author sali
 */
public interface RuleProcessor {

    RootWord applyRules(NamedTemplate template, RootWord baseRootWord);

}
