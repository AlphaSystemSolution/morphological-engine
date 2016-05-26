package com.alphasystem.app.sarfengine.test;

import static com.alphasystem.util.AppUtil.NEW_LINE;
import static java.lang.String.format;

public interface Constants {

    String PSV = "|";
    String ASCII_DOC_TABLE_DECELERATION = format("|===%s", NEW_LINE);
    String ARABIC_NORMAL_STYLE_START = "[arabicNormal]#";
    String ARABIC_CAPTION_STYLE_START = "[arabicTableCaption]#";
    String STYLE_END = "#";
    String SPACE = " ";

    String TABLE_DECLERATION_START = "<div id='conjugationTable'><table style='width: 100%'>";
    String TABLE_DECLERATION_END = "</table></div>";
    String TABLE_BODY_DECLERATION_START = "<tbody>";
    String TABLE_BODY_DECLERATION_END = "</tbody>";
    String TABLE_HEADER_DECLERATION_START = "<thead>";
    String TABLE_HEADER_DECLERATION_END = "</thead>";
    String START_TABLE_ROW = "<tr>";
    String END_TABLE_ROW = "</tr>";
    String START_TABLE_COLUMN = "<td>";
    String END_TABLE_COLUMN = "</td>";
    String TABLE_COLUMN = "<td>%s</td>";
    String TABLE_HEADER = "<th>%s</th>";
    String START_TABLE_TH = "<th>";
    String START_TABLE_TH_COLSPAN3 = "<th colspan=\"3\">";
    String END_TABLE_TH = "</th>";
    String ARABIC_TEXT_SPAN = "<span class='arabicText'>%s</span>";
    String ARABIC_TEXT_SUP_SPAN = "<span class='arabicText'><sup>%s</sup></span>";
    String ARABIC_TEXT_CAPTION_SPAN = "<span class='arabicTextCaption'>%s</span>";
    String HTML_SPACE = "&nbsp;";
    int NUM_OF_COLUMNS = 3;
}
