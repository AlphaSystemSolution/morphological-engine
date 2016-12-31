package com.alphasystem.app.morphologicalengine.ui.skin;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounDetailedConjugationPair;
import com.alphasystem.app.morphologicalengine.ui.NounConjugationGroupControl;
import com.alphasystem.app.morphologicalengine.ui.NounDetailedConjugationPairControl;

/**
 * @author sali
 */
public class NounDetailedConjugationPairSkin extends DetailedConjugationPairSkin<NounConjugationGroup, NounDetailedConjugationPair,
        NounConjugationGroupControl, NounDetailedConjugationPairControl> {

    public NounDetailedConjugationPairSkin(NounDetailedConjugationPairControl control) {
        super(control);
    }
}
