package com.alphasystem.app.morphologicalengine.conjugation.test;

import org.apache.commons.lang3.ArrayUtils;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;
import com.alphasystem.morphologicalengine.model.ConjugationHeader;
import com.alphasystem.util.AppUtil;

import static com.alphasystem.arabic.model.ArabicLetterType.AIN;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF_HAMZA_ABOVE;
import static com.alphasystem.arabic.model.ArabicLetterType.DTHA;
import static com.alphasystem.arabic.model.ArabicLetterType.FA;
import static com.alphasystem.arabic.model.ArabicLetterType.HA;
import static com.alphasystem.arabic.model.ArabicLetterType.LAM;
import static com.alphasystem.arabic.model.ArabicLetterType.MEEM;
import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.ArabicLetterType.RA;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static java.lang.String.format;

/**
 * @author sali
 */
public class ConjugationCommon extends CommonTest {

    private static final ArabicWord PARTICIPLE_PREFIX = getWord(FA, HA, WAW);
    private static final ArabicWord IMPERATIVE_PREFIX = getWord(ALIF, LAM, ALIF_HAMZA_ABOVE, MEEM, RA, ArabicLetterType.SPACE,
            MEEM, NOON, HA);
    private static final ArabicWord FORBIDDING_PREFIX = getWord(WAW, NOON, HA, YA, ArabicLetterType.SPACE, AIN, NOON, HA);
    private static final ArabicWord NOUN_OF_PLACE_AND_TIME_PREFIX = getWord(WAW, ALIF, LAM, DTHA, RA, FA, ArabicLetterType.SPACE, MEEM,
            NOON, HA);

    private static String getValue(String prefix, int columnSpan, String... values) {
        if (ArrayUtils.isEmpty(values)) {
            return getColumn("&nbsp;");
        }
        StringBuilder builder = new StringBuilder();
        if (prefix != null) {
            builder.append(prefix).append("&nbsp;");
        }
        builder.append(values[0]);
        for (int i = 1; i < values.length; i++) {
            String value = values[i];
            value = (value == null) ? "&nbsp;" : value;
            builder.append("&nbsp;").append(value);
        }
        return getColumn(columnSpan, builder.toString());
    }

    private static String getValue(String prefix, String[] values) {
        return getValue(prefix, 0, values);
    }

    private static String getValue(String[] values) {
        return getValue(null, values);
    }

    void printAbbreviatedConjugation(AbbreviatedConjugation abbreviatedConjugation) {
        lines.add("[cols=\"^.^25,^.^25,^.^25,^.^25\"]");
        lines.add(ASCII_DOC_TABLE_DECELERATION);
        addHeader(abbreviatedConjugation.getConjugationHeader());
        addActiveLine(abbreviatedConjugation.getPastTense(), abbreviatedConjugation.getPresentTense(),
                abbreviatedConjugation.getActiveParticiple(), abbreviatedConjugation.getVerbalNouns());
        if (abbreviatedConjugation.hasPassiveLine()) {
            addPassiveLine(abbreviatedConjugation.getPastPassiveTense(), abbreviatedConjugation.getPresentPassiveTense(),
                    abbreviatedConjugation.getPassiveParticiple(), abbreviatedConjugation.getVerbalNouns());
        }
        addImperativeAndForbiddingLine(abbreviatedConjugation.getImperative(), abbreviatedConjugation.getForbidding());
        addAdverbLine(abbreviatedConjugation.getAdverbs());
        lines.add(getEmptyRow(4));
        lines.add(ASCII_DOC_TABLE_DECELERATION);
    }

    void addHeader(ConjugationHeader header) {
        if (header == null) {
            return;
        }
        final String rootLettersAndTranslation = addRootLettersAndTranslation(header.getRootLetters(), header.getTranslation());
        final String headerLabels = addHeaderLabels(header);
        lines.add(format("2+|%s 2+>.^|%s", rootLettersAndTranslation, headerLabels));
    }

    private String addRootLettersAndTranslation(RootLetters rootLetters, String translation) {
        String translationValue = (translation == null) ? "" : format("[small]#(%s)#", translation);
        return format("[arabicHeading1]#%s#%s%s%s", rootLetters.toLabel().toHtmlCode(), AppUtil.NEW_LINE, AppUtil.NEW_LINE, translationValue);
    }

    private String addHeaderLabels(ConjugationHeader header) {
        return format("%s%s#%s%s%s%s#%s%s%s%s#", ARABIC_NORMAL_STYLE_START, header.getTypeLabel1(), AppUtil.NEW_LINE,
                AppUtil.NEW_LINE, ARABIC_NORMAL_STYLE_START, header.getTypeLabel2(), AppUtil.NEW_LINE,
                AppUtil.NEW_LINE, ARABIC_NORMAL_STYLE_START, header.getTypeLabel3());
    }

    private void addActiveLine(String pastTense, String presentTense, String activeParticiple, String[] verbalNouns) {
        lines.add(getValue(PARTICIPLE_PREFIX.toHtmlCode(), new String[]{activeParticiple}));
        lines.add(getValue(verbalNouns));
        lines.add(getColumn(presentTense));
        lines.add(getColumn(pastTense));
    }

    private void addPassiveLine(String pastPassiveTense, String presentPassiveTense, String passiveParticiple,
                                String[] verbalNouns) {
        lines.add(getValue(PARTICIPLE_PREFIX.toHtmlCode(), new String[]{passiveParticiple}));
        lines.add(getValue(verbalNouns));
        lines.add(getColumn(presentPassiveTense));
        lines.add(getColumn(pastPassiveTense));
    }

    private void addImperativeAndForbiddingLine(String imperative, String forbidding) {
        lines.add(getValue(IMPERATIVE_PREFIX.toHtmlCode(), 2, imperative));
        lines.add(getValue(FORBIDDING_PREFIX.toHtmlCode(), 2, forbidding));
    }

    private void addAdverbLine(String[] adverbs) {
        if (ArrayUtils.isEmpty(adverbs)) {
            return;
        }
        lines.add(getValue(NOUN_OF_PLACE_AND_TIME_PREFIX.toHtmlCode(), 4, adverbs));
    }

}
