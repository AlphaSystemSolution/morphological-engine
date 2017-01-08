package com.alphasystem.app.morphologicalengine.ui.util;

import com.alphasystem.arabic.ui.util.UIUserPreferences;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * @author sali
 */
public class MorphologicalEngineUIPreferences extends UIUserPreferences {

    private static final String ARABIC_HEADING_FONT_SIZE = "arabicHeadingFontSize";

    public MorphologicalEngineUIPreferences(){
        super(MorphologicalEngineUIPreferences.class);
    }

    protected MorphologicalEngineUIPreferences(Class<?> c) {
        super(c);
    }

    public long getArabicHeadingFontSize(){
        return getFontNode().getLong(ARABIC_HEADING_FONT_SIZE, 30);
    }

    public void setArabicHeadingFontSize(long size){
        getFontNode().putLong(ARABIC_HEADING_FONT_SIZE, size);
    }

    public Font getArabicHeadingFont(){
        return Font.font(getArabicFontName(), FontWeight.BOLD, FontPosture.REGULAR, getArabicHeadingFontSize());
    }
}
