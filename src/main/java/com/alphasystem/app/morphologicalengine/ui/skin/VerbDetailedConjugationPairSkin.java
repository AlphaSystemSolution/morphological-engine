package com.alphasystem.app.morphologicalengine.ui.skin;

import com.alphasystem.app.morphologicalengine.conjugation.model.VerbConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.VerbDetailedConjugationPair;
import com.alphasystem.app.morphologicalengine.ui.VerbConjugationGroupControl;
import com.alphasystem.app.morphologicalengine.ui.VerbDetailedConjugationPairControl;

/**
 * @author sali
 */
public class VerbDetailedConjugationPairSkin extends DetailedConjugationPairSkin<VerbConjugationGroup, VerbDetailedConjugationPair,
        VerbConjugationGroupControl, VerbDetailedConjugationPairControl> {

    public VerbDetailedConjugationPairSkin(VerbDetailedConjugationPairControl control) {
        super(control);
    }
}
