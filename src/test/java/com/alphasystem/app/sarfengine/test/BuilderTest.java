/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.sarfengine.conjugation.ConjugationMemberBuilder;
import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilder;
import com.alphasystem.app.sarfengine.conjugation.builder.ConjugationBuilderFactory;
import com.alphasystem.app.sarfengine.conjugation.builder.DefaultConjugationBuilder;
import com.alphasystem.app.sarfengine.conjugation.model.ConjugationHeader;
import com.alphasystem.app.sarfengine.conjugation.model.SarfChart;
import com.alphasystem.app.sarfengine.conjugation.model.VerbalNoun;
import com.alphasystem.app.sarfengine.conjugation.template.FormTemplate;
import com.alphasystem.arabic.model.*;
import com.alphasystem.sarfengine.xml.model.RootWord;
import com.alphasystem.sarfengine.xml.model.SarfTermType;
import org.apache.commons.lang3.StringEscapeUtils;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBElement;
import java.util.*;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.concatenateWithSpace;
import static com.alphasystem.arabic.model.ArabicWord.fromBuckWalterString;
import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabic.model.HiddenPronounStatus.THIRD_PERSON_MASCULINE_SINGULAR;
import static com.alphasystem.arabic.model.NamedTemplate.FORM_I_CATEGORY_A_GROUP_A_TEMPLATE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PAST_TENSE;
import static com.alphasystem.sarfengine.xml.model.SarfTermType.PRESENT_TENSE;
import static com.alphasystem.util.JAXBUtil.marshall;
import static java.lang.String.format;
import static java.util.Collections.addAll;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static org.apache.commons.lang3.StringEscapeUtils.escapeXml11;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class BuilderTest extends CommonTest {

    public static final ArabicLetterType FIRST_RADICAL = FA;
    public static final ArabicLetterType SECOND_RADICAL = TA;
    public static final ArabicLetterType THIRD_RADICAL = HHA;
    private ConjugationBuilderFactory factory = ConjugationBuilderFactory
            .getInstance();

    private static String toHtmlCodeString(char unicode) {
        String s = format("%04x", (int) unicode);
        int i = Integer.parseInt(s, 16);
        return format("&#%s;", i);
    }

    @SuppressWarnings("unused")
    private static String toHtmlCodeString(String unicode) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < unicode.length(); i++) {
            char c = unicode.charAt(i);
            builder.append(toHtmlCodeString(c));
        }
        return builder.toString();
    }

    private void fillMap(DefaultConjugationBuilder builder,
                         Map<NamedTemplate, ArabicWord> formNameMap) {
        if (builder == null) {
            return;
        }
        FormTemplate formTemplate = builder.getFormTemplate();
        if (formTemplate == null) {
            return;
        }
        ArabicWord pastTenseRoot = (formTemplate.getPastTenseRoot())
                .getRootWord();
        ArabicWord presentTenseRoot = (formTemplate.getPresentTenseRoot())
                .getRootWord();
        formNameMap.put(formTemplate.getTemplate(),
                concatenateWithSpace(pastTenseRoot, presentTenseRoot));
    }

    @Test
    public void fromBuckwalter() {
        fromBuckwalter("yasotaEowino");
        fromBuckwalter("yasotaEino");
        fromBuckwalter("yasotaEowino");
        fromBuckwalter("yasotaEino");
        fromBuckwalter("tasotaEino");
        fromBuckwalter("tasotaEino");
        fromBuckwalter("yasotaEowinu");
        fromBuckwalter("yasotaEiyonu");
        fromBuckwalter("wa<i*o qulonaA lilomala`^}ikapi {sojuduwA@ li'aAdama fasajaduw^A@ <il~aA^ <iboliysa kaAna mina {lojin~i fafasaqa Eano >amori rab~ihi.^ >afatat~axi*uwnahu, wa*ur~iy~atahu,^ >awoliyaA^'a min duwniY wahumo lakumo Eaduw~N[ bi}osa lilZ~a`limiyna badalFA");
    }

    private void fromBuckwalter(String bw) {
        ArabicWord aw = fromBuckWalterString(bw);
        String label = format(ARABIC_TEXT_SPAN, aw.toHtmlCode());
        log(format("<div>%s - %s</div>", bw, label));
    }

    @Test
    public void printForms() {
        Map<NamedTemplate, ArabicWord> formNameMap = new LinkedHashMap<NamedTemplate, ArabicWord>();
        ConjugationBuilderFactory factory = ConjugationBuilderFactory
                .getInstance();

        DefaultConjugationBuilder builder = (DefaultConjugationBuilder) factory
                .getFormICategoryAGroupUBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory
                .getFormICategoryAGroupIBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory
                .getFormICategoryAGroupABuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory
                .getFormICategoryIGroupABuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory
                .getFormICategoryIGroupIBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory
                .getFormICategoryUBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormIIBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormIIIBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormIVBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormVBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormVIBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormVIIBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormVIIIBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormIXBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormXBuilder();
        fillMap(builder, formNameMap);

        builder = (DefaultConjugationBuilder) factory.getFormXIBuilder();
        fillMap(builder, formNameMap);

        List<NamedTemplate> keySet = new ArrayList<NamedTemplate>(
                formNameMap.keySet());
        List<ArabicWord> valueSet = new ArrayList<ArabicWord>(
                formNameMap.values());
        int numOfColumns = 3;
        while (keySet.size() % numOfColumns != 0) {
            keySet.add(null);
            valueSet.add(null);
        }

        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);

        int fromIndex = 0;
        int toIndex = numOfColumns;
        while (fromIndex < keySet.size()) {
            List<NamedTemplate> keySubList = keySet.subList(fromIndex, toIndex);

            log(START_TABLE_ROW);
            for (NamedTemplate s : keySubList) {
                String text = s == null ? "&nbsp;" : s.name();
                log(format("<td>%s</td>", text));
            }
            log(END_TABLE_ROW);

            List<ArabicWord> valueSubList = valueSet
                    .subList(fromIndex, toIndex);
            log(START_TABLE_ROW);
            for (ArabicWord arabicWord : valueSubList) {
                String text = arabicWord == null ? "&nbsp;" : format(
                        ARABIC_TEXT_SPAN, arabicWord.toHtmlCode());
                log(format("<td>%s</td>", text));
            }
            log(END_TABLE_ROW);

            fromIndex = toIndex;
            toIndex += numOfColumns;
        }
        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    private void printLabel(String src) {
        ArabicWord arabicWord = fromBuckWalterString(src);
        boolean empty = isEmpty(src);
        String arabicText = empty ? "&nbsp;" : format(ARABIC_TEXT_SPAN,
                arabicWord.toHtmlCode());
        String html = empty ? "&nbsp;" : format("%s<br/>%s", arabicText, src);
        log(format("<td>%s</td>", html));
    }

    private void printLabelRow(String... encondings) {
        if (isEmpty(encondings)) {
            return;
        }
        log(START_TABLE_ROW);
        for (String encoding : encondings) {
            printLabel(encoding);
        }
        log(END_TABLE_ROW);
    }

    @Test
    public void printLabels() {
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);
        printLabelRow("SHyH", "sAlm", "mDAEf", "wzn");
        printLabelRow("fElQvlAvyQmjrd", "fElQvlAvyQmzydQfyh", "fElQrbAEyQmjrd",
                "fElQrbAEyQmzydQfyh");
        printLabelRow("mhmwzQAlfA'", "mhmwzQAlEyn", "mhmwzQAlAm", "mEtlQAlfA'");
        printLabelRow("mEtlQAlEyn", "mEtlQAlAm", "mvAlQAlwAwy", "mvAlQAlyA}y");
        printLabelRow("OjwfQAlwAwy", "OjwfQAlyA}y", "nAqSQAlwAwy",
                "nAqSQAlyA}y");
        printLabelRow("lfyfQmfrwq", "lfyfQmqrwn", null, null);
        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    @Test
    public void printNamedTemplate() {
        List<NamedTemplate> list = new ArrayList<NamedTemplate>();
        Collections.addAll(list, NamedTemplate.values());
        int numOfColumns = 5;
        while (list.size() % numOfColumns != 0) {
            list.add(null);
        }
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);

        int fromIndex = 0;
        int toIndex = numOfColumns;
        while (fromIndex < list.size()) {
            List<NamedTemplate> subList = list.subList(fromIndex, toIndex);
            log(START_TABLE_ROW);
            for (NamedTemplate namedTemplate : subList) {
                String code = namedTemplate == null ? "&nbsp;" : namedTemplate
                        .getCode();
                log(format("<td>%s</td>", code));
            }
            log(END_TABLE_ROW);

            log(START_TABLE_ROW);
            for (NamedTemplate namedTemplate : subList) {
                String code = namedTemplate == null ? "&nbsp;" : namedTemplate
                        .getLabel().toHtmlCode();
                code = format(ARABIC_TEXT_SPAN, code);
                log(format("<td>%s</td>", code));
            }
            log(END_TABLE_ROW);

            log(START_TABLE_ROW);
            for (NamedTemplate namedTemplate : subList) {
                String code = namedTemplate == null ? "&nbsp;" : namedTemplate
                        .getType().toHtmlCode();
                code = format(ARABIC_TEXT_SPAN, code);
                log(format("<td>%s</td>", code));
            }
            log(END_TABLE_ROW);
            fromIndex = toIndex;
            toIndex += numOfColumns;
        }

        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    @Test
    public void printSarfChartLabels() {
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);
        printSarfChartLabels(NOON, SAD, RA);
        printSarfChartLabels(MEEM, DAL, DAL);
        printSarfChartLabels(HAMZA, KAF, LAM);
        printSarfChartLabels(QAF, WAW, LAM);
        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    private void printSarfChartLabels(ArabicLetterType firstRadical,
                                      ArabicLetterType secondRadical, ArabicLetterType thirdRadical) {
        ConjugationBuilder builder = factory.getFormICategoryAGroupUBuilder();
        printSarfChartLabels(builder, firstRadical, secondRadical, thirdRadical);
    }

    private void printSarfChartLabels(ConjugationBuilder builder,
                                      ArabicLetterType firstRadical, ArabicLetterType secondRadical,
                                      ArabicLetterType thirdRadical) {
        SarfChart sarfChart = builder.doConjugation(null, firstRadical,
                secondRadical, thirdRadical);
        ConjugationHeader conjugationHeader = sarfChart.getChartTitle();
        log(START_TABLE_ROW);
        log(format(
                "<td>%s</td>",
                format(ARABIC_TEXT_SPAN, sarfChart.getSarfSagheer()
                        .getActiveLine().getPastTense().toHtmlCode())));
        log(format(
                "<td>%s</td>",
                format(ARABIC_TEXT_SPAN, conjugationHeader.getTypeLabel1()
                        .toHtmlCode())));
        log(format(
                "<td>%s</td>",
                format(ARABIC_TEXT_SPAN, conjugationHeader.getTypeLabel2()
                        .toHtmlCode())));
        log(format(
                "<td>%s</td>",
                format(ARABIC_TEXT_SPAN, conjugationHeader.getTypeLabel3()
                        .toHtmlCode())));
        log(END_TABLE_ROW);
    }

    @Test
    public void printSarfTermTypes() {
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);
        for (SarfTermType sarfTermType : SarfTermType.values()) {
            log(START_TABLE_ROW);
            String value = StringEscapeUtils.escapeXml11(sarfTermType.value());
            ArabicWord aw = sarfTermType.getLabel();
            String arabicText = format(ARABIC_TEXT_SPAN, aw.toHtmlCode());
            log(format("<td>%s</td><td>%s</td><td>%s</td>",
                    sarfTermType.name(), value, arabicText));
            log(END_TABLE_ROW);
        }
        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }

    @Test
    public void printVerbalNouns() {
        List<VerbalNoun> list = new ArrayList<VerbalNoun>();
        addAll(list, VerbalNoun.values());
        int numOfColumns = 4;
        while (list.size() % numOfColumns != 0) {
            list.add(null);
        }
        log(TABLE_DECLERATION_START);
        log(TABLE_BODY_DECLERATION_START);
        int fromIndex = 0;
        int toIndex = numOfColumns;
        while (fromIndex < list.size()) {
            List<VerbalNoun> subList = list.subList(fromIndex, toIndex);

            log(START_TABLE_ROW);
            for (VerbalNoun verbalNounsContainer : subList) {
                String text = verbalNounsContainer == null ? "&nbsp;"
                        : verbalNounsContainer.name();
                log(format("<td>%s</td>", text));
            }
            log(END_TABLE_ROW);

            log(START_TABLE_ROW);
            for (VerbalNoun verbalNounsContainer : subList) {
                RootWord rootWord = verbalNounsContainer == null ? null
                        : verbalNounsContainer.getRootWord();
                String text = rootWord == null ? "&nbsp;" : format(
                        ARABIC_TEXT_SPAN, rootWord.getRootWord().toHtmlCode());
                log(format("<td>%s</td>", text));
            }
            log(END_TABLE_ROW);

            fromIndex = toIndex;
            toIndex += numOfColumns;
        }
        log(TABLE_BODY_DECLERATION_END);
        log(TABLE_DECLERATION_END);
    }


    @Test
    public void testConstructorCopy() {
        ArabicWord src = ArabicWord.fromBuckWalterString("yasotafoEilu");
        log(format("<div>Original Word: %s</div>", printArabicText(src)));
        ArabicWord target = new ArabicWord(src).replaceDiacritic(5, SUKUN);
        log(format("<div>Original Word after copy: %s</div>",
                printArabicText(src)));
        log(format("<div>New Word after changing: %s</div>",
                printArabicText(target)));
        RootWord srcRootWord = new RootWord().withRootWord(src)
                .withBaseWord(src).withFirstRadicalIndex(3)
                .withSecondRadicalIndex(4).withThirdRadicalIndex(5)
                .withSarfTermType(PRESENT_TENSE);
        log(format("<div>Original Root Word: %s</div>",
                printArabicText(srcRootWord.getRootWord())));
        RootWord targetRootWord = new RootWord(srcRootWord)
                .withRootWord(new ArabicWord(srcRootWord.getRootWord())
                        .replaceDiacritic(5, SUKUN));
        log(format("<div>Original Root Word: after copy %s</div>",
                printArabicText(srcRootWord.getRootWord())));
        log(format("<div>Target Root Word: %s</div>",
                printArabicText(targetRootWord.getRootWord())));
    }

    @Test
    public void testMember() {
        DefaultConjugationBuilder builder = new DefaultConjugationBuilder(
                FORM_I_CATEGORY_A_GROUP_A_TEMPLATE);
        ConjugationMemberBuilder memberBuilder = builder.getMemberBuilder(
                false, PRESENT_TENSE, SEEN, HAMZA, LAM);

        memberBuilder.setSkipRuleProcessing(true);
        ArabicWord src = memberBuilder.getDefaultConjugation();
        String srcText = format(ARABIC_TEXT_SPAN, src.toHtmlCode());

        memberBuilder.setSkipRuleProcessing(false);
        ArabicWord result = memberBuilder.getDefaultConjugation();
        String resultText = format(ARABIC_TEXT_SPAN, result.toHtmlCode());

        log(format(
                "<div>Without Rule processing: %s, With Rule processing: %s,</div>",
                srcText, resultText));
    }

    @Test
    public void testXml() {
        ObjectFactory objectFactory = new ObjectFactory();
        ArabicWord arabicWord = new ArabicWord(new ArabicLetter(FA, FATHA),
                new ArabicLetter(AIN, FATHA), new ArabicLetter(LAM, FATHA));
        JAXBElement<ArabicWord> element = objectFactory
                .createArabicWord(arabicWord);
        String xml = marshall(ArabicWord.class.getPackage().getName(), element);
        log(format("<div>%s</div>", escapeXml11(xml)));

        RootWord rootWord = new RootWord().withBaseWord(arabicWord)
                .withRootWord(arabicWord).withFirstRadicalIndex(0)
                .withSecondRadicalIndex(1).withThirdRadicalIndex(2)
                .withSarfTermType(PAST_TENSE)
                .withMemberType(THIRD_PERSON_MASCULINE_SINGULAR);

        log("<br/>");

        com.alphasystem.sarfengine.xml.model.ObjectFactory objectFactory2 = new com.alphasystem.sarfengine.xml.model.ObjectFactory();
        JAXBElement<RootWord> rootWordElement = objectFactory2
                .createRootWord(rootWord);
        xml = marshall(RootWord.class.getPackage().getName(), rootWordElement);
        log(format("<div>%s</div>", escapeXml11(xml)));
    }

}
