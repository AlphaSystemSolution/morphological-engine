package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationRoots;
import com.alphasystem.app.morphologicalengine.conjugation.model.MorphologicalChart;
import com.alphasystem.app.morphologicalengine.conjugation.model.NounRootBase;
import com.alphasystem.app.morphologicalengine.guice.GuiceSupport;
import com.alphasystem.app.morphologicalengine.ui.MorphologicalChartControl;
import com.alphasystem.fx.ui.util.UiUtilities;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static com.alphasystem.app.morphologicalengine.conjugation.builder.ConjugationHelper.getConjugationRoots;
import static com.alphasystem.app.sarfengine.test.ConjugationTest.FORM_I_ADVERBS;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun.VERBAL_NOUN_V1;

/**
 * @author sali
 */
public class ConjugationUITest extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final ConjugationBuilder conjugationBuilder = GuiceSupport.getInstance().getConjugationBuilder();
        ConjugationRoots conjugationRoots = getConjugationRoots(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, "To Help",
                new NounRootBase[]{new NounRootBase(VERBAL_NOUN_V1)}, FORM_I_ADVERBS);
        final MorphologicalChart morphologicalChart = conjugationBuilder.doConjugation(conjugationRoots, NOON, SAD, RA, null);

        primaryStage.setTitle("Morphological Chart Test");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());

        MorphologicalChartControl control = new MorphologicalChartControl();
        control.setMorphologicalChart(morphologicalChart);
        final ScrollPane scrollPane = UiUtilities.wrapInScrollPane(control);

        BorderPane root = new BorderPane();
        root.setCenter(scrollPane);
        Pane pane = new Pane();
        pane.setMinWidth(300);
        root.setRight(pane);

        pane = new Pane();
        pane.setMinWidth(300);
        root.setLeft(pane);

        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
