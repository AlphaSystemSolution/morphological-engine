package com.alphasystem.app.morphologicalengine.ui;

import com.alphasystem.app.morphologicalengine.conjugation.model.NounConjugationGroup;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounDetailedConjugationPair;
import com.alphasystem.app.morphologicalengine.ui.skin.NounDetailedConjugationPairSkin;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;

/**
 * @author sali
 */
public class NounDetailedConjugationPairControl extends DetailedConjugationPairView<NounConjugationGroup, NounDetailedConjugationPair> {

    @Override
    protected Skin<?> createDefaultSkin() {
        return new DefaultSkin(this);
    }

    private class DefaultSkin extends SkinBase<NounDetailedConjugationPairControl>{

        /**
         * Constructor for all SkinBase instances.
         *
         * @param control The control for which this Skin should attach to.
         */
        private DefaultSkin(NounDetailedConjugationPairControl control) {
            super(control);
            getChildren().setAll(new NounDetailedConjugationPairSkin(control));
        }
    }
}
