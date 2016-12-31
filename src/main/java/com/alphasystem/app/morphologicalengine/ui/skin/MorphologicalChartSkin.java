package com.alphasystem.app.morphologicalengine.ui.skin;

import com.alphasystem.app.morphologicalengine.conjugation.model.AbbreviatedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.DetailedConjugation;
import com.alphasystem.app.morphologicalengine.conjugation.model.MorphologicalChart;
import com.alphasystem.app.morphologicalengine.ui.AbbreviatedConjugationControl;
import com.alphasystem.app.morphologicalengine.ui.DetailedConjugationControl;
import com.alphasystem.app.morphologicalengine.ui.MorphologicalChartControl;
import javafx.geometry.Pos;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author sali
 */
public class MorphologicalChartSkin extends SkinBase<MorphologicalChartControl> {

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public MorphologicalChartSkin(MorphologicalChartControl control) {
        super(control);
        getChildren().setAll(new SkinView(control));
    }

    private class SkinView extends BorderPane {

        private final MorphologicalChartControl control;
        private final VBox pane = new VBox();

        private SkinView(MorphologicalChartControl control) {
            this.control = control;
            pane.setAlignment(Pos.CENTER);
            pane.setSpacing(12);
            initialize();
            setCenter(pane);
        }

        private void initialize() {
            control.morphologicalChartProperty().addListener((observable, oldValue, newValue) -> setup(newValue));
            setup(control.getMorphologicalChart());
        }

        private void setup(MorphologicalChart morphologicalChart) {
            pane.getChildren().remove(0, pane.getChildren().size());
            if (morphologicalChart != null) {
                final AbbreviatedConjugation abbreviatedConjugation = morphologicalChart.getAbbreviatedConjugation();
                if (abbreviatedConjugation != null) {
                    AbbreviatedConjugationControl control = new AbbreviatedConjugationControl();
                    control.setConjugationHeader(morphologicalChart.getHeader());
                    control.setAbbreviatedConjugation(abbreviatedConjugation);
                    pane.getChildren().add(control);
                }

                final DetailedConjugation detailedConjugation = morphologicalChart.getDetailedConjugation();
                if (detailedConjugation != null) {
                    DetailedConjugationControl control = new DetailedConjugationControl();
                    control.setDetailedConjugation(detailedConjugation);
                    pane.getChildren().add(control);
                }

            }
        }
    }
}
