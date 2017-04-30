package com.alphasystem.app.morphologicalengine.conjugation.test;

import org.apache.commons.lang3.ArrayUtils;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalanalysis.wordbyword.model.support.NounStatus;
import com.alphasystem.morphologicalanalysis.wordbyword.model.support.NumberType;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;
import com.alphasystem.morphologicalengine.model.AbbreviatedRecord;
import com.alphasystem.morphologicalengine.model.ConjugationHeader;
import com.alphasystem.morphologicalengine.model.ConjugationTuple;
import com.alphasystem.morphologicalengine.model.DetailedConjugation;
import com.alphasystem.morphologicalengine.model.NounConjugationGroup;
import com.alphasystem.morphologicalengine.model.VerbConjugationGroup;

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
import static com.alphasystem.util.AppUtil.NEW_LINE;
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
		lines.add(format("== [arabicHeading1]#%s#", abbreviatedConjugation.getConjugationHeader().getTitle()));
		lines.add("[cols=\"^.^25,^.^25,^.^25,^.^25\"]");
		lines.add(ASCII_DOC_TABLE_DECELERATION);
		addHeader(abbreviatedConjugation.getConjugationHeader());
		addActiveLine(abbreviatedConjugation.getPastTense().getLabel(), abbreviatedConjugation.getPresentTense().getLabel(),
				abbreviatedConjugation.getActiveParticipleMasculine().getLabel(), getValues(abbreviatedConjugation.getVerbalNouns()));
		if (abbreviatedConjugation.hasPassiveLine()) {
			addPassiveLine(abbreviatedConjugation.getPastPassiveTense().getLabel(), abbreviatedConjugation.getPresentPassiveTense().getLabel(),
					abbreviatedConjugation.getPassiveParticipleMasculine().getLabel(), getValues(abbreviatedConjugation.getVerbalNouns()));
		}
		addImperativeAndForbiddingLine(abbreviatedConjugation.getImperative().getLabel(), abbreviatedConjugation.getForbidding().getLabel());
		addAdverbLine(getValues(abbreviatedConjugation.getAdverbs()));
		lines.add(getEmptyRow(4));
		lines.add(ASCII_DOC_TABLE_DECELERATION);
	}

	void printDetailedConjugation(DetailedConjugation detailedConjugation) {
		lines.add("[cols=\"^.^17,^.^16,^.^16,^.^2,^.^17,^.^16,^.^16\"]");
		lines.add(ASCII_DOC_TABLE_DECELERATION);
		printVerbConjugation(detailedConjugation.getPresentTense(), detailedConjugation.getPastTense());
		printNounConjugation(detailedConjugation.getActiveParticipleFeminine(), detailedConjugation.getActiveParticipleMasculine());
		printMultiNounConjugation(detailedConjugation.getVerbalNouns());
		if (detailedConjugation.hasPassiveLine()) {
			printVerbConjugation(detailedConjugation.getPresentPassiveTense(), detailedConjugation.getPastPassiveTense());
			printNounConjugation(detailedConjugation.getPassiveParticipleFeminine(), detailedConjugation.getPassiveParticipleMasculine());
		}
		printVerbConjugation(detailedConjugation.getForbidding(), detailedConjugation.getImperative(), 6);
		printMultiNounConjugation(detailedConjugation.getAdverbs());
		lines.add(ASCII_DOC_TABLE_DECELERATION);
	}

	void printNounConjugation(NounConjugationGroup nounConjugationGroup) {
		lines.add("[cols=\"^.^24,^.^24,^.^24,^.^28\"]");
		lines.add(ASCII_DOC_TABLE_DECELERATION);

		lines.add(getColumn(4, ARABIC_TABLE_CAPTION_STYLE_NAME, nounConjugationGroup.getTermType().toLabel().toHtmlCode()));

		lines.add(getColumn(ARABIC_TABLE_CAPTION_STYLE_NAME, NumberType.PLURAL.toLabel().toHtmlCode()));
		lines.add(getColumn(ARABIC_TABLE_CAPTION_STYLE_NAME, NumberType.DUAL.toLabel().toHtmlCode()));
		lines.add(getColumn(ARABIC_TABLE_CAPTION_STYLE_NAME, NumberType.SINGULAR.toLabel().toHtmlCode()));
		lines.add(getColumn(ARABIC_TABLE_CAPTION_STYLE_NAME, null));
		lines.add("");

		addTuple(nounConjugationGroup.getNominative(), NounStatus.NOMINATIVE.toLabel().toHtmlCode());
		addTuple(nounConjugationGroup.getAccusative(), NounStatus.ACCUSATIVE.toLabel().toHtmlCode());
		addTuple(nounConjugationGroup.getGenitive(), NounStatus.GENITIVE.toLabel().toHtmlCode());

		lines.add(ASCII_DOC_TABLE_DECELERATION);
		lines.add("");
	}

	private void addTuple(ConjugationTuple conjugationTuple, String type) {
		lines.add(getColumn(conjugationTuple.getPlural()));
		lines.add(getColumn(conjugationTuple.getDual()));
		lines.add(getColumn(conjugationTuple.getSingular()));
		lines.add(getColumn(ARABIC_TABLE_CAPTION_STYLE_NAME, type));
		lines.add("");
	}

	private void addHeader(ConjugationHeader header) {
		if (header == null) {
			return;
		}
		final String rootLettersAndTranslation = addRootLettersAndTranslation(header.getRootLetters(), header.getTranslation());
		final String headerLabels = addHeaderLabels(header);
		lines.add(format("2+|%s 2+>.^|%s", rootLettersAndTranslation, headerLabels));
	}

	private String addRootLettersAndTranslation(RootLetters rootLetters, String translation) {
		String translationValue = (translation == null) ? "" : format("[small]#(%s)#", translation);
		return format("[arabicHeading1]#%s#%s%s%s", rootLetters.toLabel().toHtmlCode(), NEW_LINE, NEW_LINE, translationValue);
	}

	private String addHeaderLabels(ConjugationHeader header) {
		return ARABIC_NORMAL_STYLE_START + header.getTypeLabel1() + STYLE_END + NEW_LINE + NEW_LINE
				+ ARABIC_NORMAL_STYLE_START + header.getTypeLabel2() + STYLE_END + NEW_LINE + NEW_LINE
				+ ARABIC_NORMAL_STYLE_START + header.getTypeLabel3() + STYLE_END + NEW_LINE + NEW_LINE;
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

	private String[] getValues(AbbreviatedRecord[] records) {
		if (ArrayUtils.isEmpty(records)) {
			return null;
		}
		String[] values = new String[records.length];
		for (int i = 0; i < records.length; i++) {
			values[i] = records[i].getLabel();
		}
		return values;
	}

	private void printVerbConjugation(VerbConjugationGroup lsc, VerbConjugationGroup rsc, int numOfRows) {
		lines.add(getSarfTermTypeHeader(lsc, rsc, numOfRows));

		ConjugationTuple leftTuple = lsc.getMasculineThirdPerson();
		ConjugationTuple rightTuple = rsc.getMasculineThirdPerson();
		if (leftTuple != null && rightTuple != null) {
			lines.add(getRowData(leftTuple, rightTuple));
		}

		leftTuple = lsc.getFeminineThirdPerson();
		rightTuple = rsc.getFeminineThirdPerson();
		if (leftTuple != null && rightTuple != null) {
			lines.add(getRowData(leftTuple, rightTuple));
		}

		leftTuple = lsc.getMasculineSecondPerson();
		rightTuple = rsc.getMasculineSecondPerson();
		if (leftTuple != null && rightTuple != null) {
			lines.add(getRowData(leftTuple, rightTuple));
		}

		leftTuple = lsc.getFeminineSecondPerson();
		rightTuple = rsc.getFeminineSecondPerson();
		if (leftTuple != null && rightTuple != null) {
			lines.add(getRowData(leftTuple, rightTuple));
		}

		leftTuple = lsc.getFirstPerson();
		rightTuple = rsc.getFirstPerson();
		if (leftTuple != null && rightTuple != null) {
			lines.add(getRowData(leftTuple, rightTuple));
		}

		lines.add(getEmptyRow(7));
		lines.add(NEW_LINE);
	}

	private void printVerbConjugation(VerbConjugationGroup lsc, VerbConjugationGroup rsc) {
		printVerbConjugation(lsc, rsc, 6);
	}

	private void printNounConjugation(NounConjugationGroup lsc, NounConjugationGroup rsc) {
		lines.add(getSarfTermTypeHeader(lsc, rsc, 4));

		ConjugationTuple leftSide = (lsc == null) ? null : lsc.getNominative();
		ConjugationTuple rightSide = (rsc == null) ? null : rsc.getNominative();
		lines.add(getRowData(leftSide, rightSide));

		leftSide = (lsc == null) ? null : lsc.getAccusative();
		rightSide = (rsc == null) ? null : rsc.getAccusative();
		lines.add(getRowData(leftSide, rightSide));

		leftSide = (lsc == null) ? null : lsc.getGenitive();
		rightSide = (rsc == null) ? null : rsc.getGenitive();
		lines.add(getRowData(leftSide, rightSide));

		lines.add(getEmptyRow(7));
		lines.add(NEW_LINE);
	}

	private void printMultiNounConjugation(NounConjugationGroup[] groups) {
		if (ArrayUtils.isEmpty(groups)) {
			return;
		}
		while (groups.length % 2 != 0) {
			groups = ArrayUtils.add(groups, null);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>> " + groups.length);
		int index = 0;
		while (true) {
			System.out.println("|||||||||||||||| " + index);
			final NounConjugationGroup rsc = groups[index++];
			final NounConjugationGroup lsc = groups[index++];
			if (lsc == null && rsc == null) {
				continue;
			}
			printNounConjugation(lsc, rsc);
			System.out.println("????????????????? " + index + " : " + lsc + " : " + rsc);
			if (index >= groups.length) {
				break;
			}
		}
	}

	private String getRowData(final ConjugationTuple leftSide, final ConjugationTuple rightSide) {
		return format("%s%s", getColumnData(leftSide), getColumnData(rightSide));
	}

	private String getColumnData(final ConjugationTuple conjugationTuple) {
		StringBuilder builder = new StringBuilder();
		if (conjugationTuple == null) {
			builder.append("|&nbsp; |&nbsp; |&nbsp; ");
		} else {
			builder.append(getColumn(conjugationTuple.getPlural())).append(NEW_LINE)
					.append(getColumn(conjugationTuple.getDual())).append(NEW_LINE)
					.append(getColumn(conjugationTuple.getSingular())).append(NEW_LINE);
		}
		return builder.toString();
	}

}
