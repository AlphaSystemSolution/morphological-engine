package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.model.FormTemplate;
import com.alphasystem.arabic.model.ArabicCharacter;
import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicSupport;
import com.alphasystem.arabic.model.DiacriticType;
import com.alphasystem.arabic.model.HiddenNounStatus;
import com.alphasystem.arabic.model.HiddenPronounStatus;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;
import com.alphasystem.morphologicalanalysis.morphology.model.support.BrokenPlural;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Noun;
import com.alphasystem.morphologicalanalysis.morphology.model.support.NounOfPlaceAndTime;
import com.alphasystem.morphologicalanalysis.morphology.model.support.SarfTermType;
import com.alphasystem.morphologicalanalysis.morphology.model.support.Verb;
import com.alphasystem.morphologicalanalysis.morphology.model.support.VerbalNoun;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.alphasystem.app.morphologicalengine.conjugation.model.FormTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE;
import static com.alphasystem.app.morphologicalengine.conjugation.model.FormTemplate.FORM_I_CATEGORY_A_GROUP_I_TEMPLATE;
import static com.alphasystem.app.morphologicalengine.conjugation.model.FormTemplate.FORM_I_CATEGORY_A_GROUP_U_TEMPLATE;
import static com.alphasystem.app.morphologicalengine.conjugation.model.FormTemplate.FORM_I_CATEGORY_I_GROUP_A_TEMPLATE;
import static com.alphasystem.app.morphologicalengine.conjugation.model.FormTemplate.FORM_I_CATEGORY_I_GROUP_I_TEMPLATE;
import static com.alphasystem.app.morphologicalengine.conjugation.model.FormTemplate.FORM_I_CATEGORY_U_TEMPLATE;
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
    public void printDiacritics() {
        addTable2(DiacriticType.values(), "Diacritics", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printDiacritics"})
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
    public void printBrokenPlurals() {
        addTable(BrokenPlural.values(), "Broken Plurals patterns", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printBrokenPlurals"})
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

    @Test(dependsOnMethods = {"printHiddenProNounStatus"})
    public void printVerbs() {
        addTable(Verb.values(), "Verb patterns", NUM_OF_COLUMNS);
    }

    @Test(dependsOnMethods = {"printVerbs"})
    public void printForTemplates() {
        printFormTemplate(FORM_I_CATEGORY_A_GROUP_U_TEMPLATE, 2);
        printFormTemplate(FORM_I_CATEGORY_A_GROUP_I_TEMPLATE, 2);
        printFormTemplate(FORM_I_CATEGORY_A_GROUP_A_TEMPLATE, 2);
        printFormTemplate(FORM_I_CATEGORY_U_TEMPLATE, 2);
        printFormTemplate(FORM_I_CATEGORY_I_GROUP_A_TEMPLATE, 2);
        printFormTemplate(FORM_I_CATEGORY_I_GROUP_I_TEMPLATE, 2);
    }

    @Test(dependsOnMethods = {"printForTemplates"})
    public void printArabicLettersAndDiacritics() {
        List<ArabicCharacter> list = new ArrayList<>();
        Collections.addAll(list, ArabicLetterType.values());
        Collections.addAll(list, DiacriticType.values());
        Collections.sort(list, (o1, o2) -> new Character(o1.getCode()).compareTo(o2.getCode()));

        lines.add(".Arabic Letters and Diacritics");
        int size = list.size();
        int numOfColumns = NUM_OF_COLUMNS;
        while (size % numOfColumns != 0) {
            list.add(null);
            size = list.size();
        }

        lines.add(format("[cols=\"%s*^.^\"]", numOfColumns));
        lines.add(ASCII_DOC_TABLE_DECELERATION);

        int fromIndex = 0;
        int toIndex = numOfColumns;
        while (fromIndex < list.size()) {
            final List<ArabicCharacter> subList = list.subList(fromIndex, toIndex);
            reverse(subList);

            StringBuilder builder = new StringBuilder();
            subList.forEach(m -> {
                String text = (m == null) ? null : String.valueOf(m.getCode());
                if("|".equals(text)){
                    text = format("\\%s", text);
                }
                text = StringUtils.isBlank(text) || " ".equals(text) ? "&nbsp;" : format("[small]#%s#", text);
                builder.append(format("|%s%s", text, NEW_LINE));
            });
            builder.append(NEW_LINE);

            subList.forEach(m -> {
                final String text = (m == null) ? " " : format("[arabicNormal]#%s#", m.getHtmlCode());
                builder.append(format("|%s%s", text, NEW_LINE));
            });
            builder.append(NEW_LINE);

            subList.forEach(m -> {
                String text = (m == null) ? " " : format("%s", m.getHtmlCode());
                if ("|".equals(text)) {
                    text = format("\\%s", text);
                }
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
                final String text = (m == null) ? " " : format("[arabicNormal]#%s#", m.toLabel().toHtmlCode());
                builder.append(format("|%s%s", text, NEW_LINE));
            });
            builder.append(NEW_LINE);

            subList.forEach(m -> {
                String text = (m == null) ? " " : format("%s", m.toLabel().toBuckWalter());
                if ("|".equals(text)) {
                    text = format("\\%s", text);
                }
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

    private <M extends Enum<M> & ArabicCharacter> void addTable2(M[] values, String title, int numOfColumns) {
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
                final String text = (m == null) ? " " : format("[arabicNormal]#%s#", m.getHtmlCode());
                builder.append(format("|%s%s", text, NEW_LINE));
            });
            builder.append(NEW_LINE);

            subList.forEach(m -> {
                String text = (m == null) ? " " : format("%s", m.getCode());
                if ("|".equals(text)) {
                    text = format("\\%s", text);
                }
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

    private void printFormTemplate(FormTemplate formTemplate, int numOfColumns) {
        lines.add(format(".%s", formTemplate.getTemplate().name()));
        lines.add(format("[cols=\"%s*^.^\"]", numOfColumns));
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        addFormTemplateRow(formTemplate.getPresentTenseRoot(), formTemplate.getPastTenseRoot());
        addFormTemplateRow(formTemplate.getPresentPassiveTenseRoot(), formTemplate.getPastPassiveTenseRoot());
        addFormTemplateRow(formTemplate.getForbiddingRoot(), formTemplate.getImperativeRoot());
        addFormTemplateRow(formTemplate.getActiveParticipleFeminineRoot(), formTemplate.getActiveParticipleMasculineRoot());
        addFormTemplateRow(formTemplate.getPassiveParticipleFeminineRoot(), formTemplate.getPassiveParticipleMasculineRoot());
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    private void addFormTemplateRow(RootWord leftSideTerm, RootWord rightSideWord) {
        final String value = getFormTemplateRow(leftSideTerm, rightSideWord);
        if (value != null) {
            lines.add(value);
        }
    }

    private static String getFormTemplateRow(RootWord leftSideTerm, RootWord rightSideWord) {
        if ((leftSideTerm == null) && (rightSideWord == null)) {
            return null;
        }
        return format("| %s | %s%s| %s | %s%s", getRootWordSarfTermType(leftSideTerm), getRootWordSarfTermType(rightSideWord), NEW_LINE,
                getRootWordLabel(leftSideTerm), getRootWordLabel(rightSideWord), NEW_LINE);
    }
}
