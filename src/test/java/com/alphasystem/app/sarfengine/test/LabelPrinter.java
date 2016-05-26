package com.alphasystem.app.sarfengine.test;

import com.alphasystem.arabic.model.*;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Noun;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.alphasystem.util.AppUtil.NEW_LINE;
import static java.lang.String.format;
import static java.util.Collections.addAll;
import static java.util.Collections.reverse;

/**
 * @author sali
 */
public class LabelPrinter extends CommonTest {

    private static final int NUM_OF_COLUMNS = 3;

    @Test
    public void printArabicLetters() {
        addTable(ArabicLetterType.values(), "Arabic Letters", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printArabicLetters"})
    public void printNamedTemplates() {
        addTable(NamedTemplate.values(), "Named Templates", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printNamedTemplates"})
    public void printMorphologicalTerms() {
        addTable(SarfTermType.values(), "Morphological terms", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printMorphologicalTerms"})
    public void printNouns() {
        addTable(Noun.values(), "Noun patterns", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printNouns"})
    public void printVerbalNouns() {
        addTable(VerbalNoun.values(), "Verbal Noun patterns", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printVerbalNouns"})
    public void printNounOfPlaceAndTime() {
        addTable(NounOfPlaceAndTime.values(), "Noun of Place and Time patterns", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printNounOfPlaceAndTime"})
    public void printHiddenNounStatus() {
        addTable(HiddenNounStatus.values(), "Hidden Noun Status", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printHiddenNounStatus"})
    public void printHiddenProNounStatus() {
        addTable(HiddenPronounStatus.values(), "Hidden ProNoun Status", NUM_OF_COLUMNS);
    }

    private <M extends Enum<M> & ArabicSupport> void addTable(M[] values, String title, int numOfColumns) {
        if (title != null) {
            lines.add(format(".%s", title));
        }
        List<M> list = new ArrayList<>();
        addAll(list, values);
        int size = list.size();
        while (size % numOfColumns != 0) {
            list.add(null);
            size = list.size();
        }

        lines.add(format("[cols=\"%s*^.^\"]", numOfColumns));
        lines.add(ASCII_DOC_TABLE_DECELERATION);

        int fromIndex = 0;
        int toIndex = numOfColumns;
        while (fromIndex < list.size()) {
            final List<M> subList = list.subList(fromIndex, toIndex);
            reverse(subList);

            StringBuilder builder = new StringBuilder();
            subList.forEach(m -> {
                final String text = (m == null) ? " " : format("[small]#%s#", m.name());
                builder.append(format("|%s%s", text, NEW_LINE));
            });
            builder.append(NEW_LINE);

            subList.forEach(m -> {
                final String text = (m == null) ? " " : format("[arabicNormal]#%s#", m.getLabel().toHtmlCode());
                builder.append(format("|%s%s", text, NEW_LINE));
            });
            builder.append(NEW_LINE);

            builder.append(format("%s+| ", numOfColumns)).append(NEW_LINE);

            lines.add(builder.toString());
            fromIndex = toIndex;
            toIndex += numOfColumns;
        }

        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }
}
