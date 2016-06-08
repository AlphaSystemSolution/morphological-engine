/**
 *
 */
package com.alphasystem.app.morphologicalengine.util;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.alphasystem.app.morphologicalengine.conjugation.rule.AbstractRuleProcessor.REMOVE_MARKER;
import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.arabic.model.ArabicWord.fromBuckWalterString;
import static com.alphasystem.arabic.model.DiacriticType.*;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.remove;

;


/**
 * @author sali
 */
public class PatternHelper {

    private static final char FATHATAN_CODE = FATHATAN.getCode();

    private static final char KASRATAN_CODE = KASRATAN.getCode();

    private static final char DAMMATAN_CODE = DAMMATAN.getCode();

    private static final char DAMMA_CODE = DAMMA.getCode();

    private static final char KASRA_CODE = KASRA.getCode();

    private static final char FATHA_CODE = FATHA.getCode();

    private static final String DIACRITICS = format("[%s%s%s]", FATHA_CODE,
            KASRA_CODE, DAMMA_CODE, FATHATAN_CODE, KASRATAN_CODE, DAMMATAN_CODE);

    private static final char ALIF_HAMZA_BELOW_CODE = ALIF_HAMZA_BELOW
            .getCode();

    private static final char ALIF_HAMZA_ABOVE_CODE = ALIF_HAMZA_ABOVE
            .getCode();

    private static final char YA_HAMZA_ABOVE_CODE = YA_HAMZA_ABOVE.getCode();

    private static final char WAW_HAMZA_ABOVE_CODE = WAW_HAMZA_ABOVE.getCode();

    private static final char ALIF_MADDAH_CODE = ALIF_MADDAH.getCode();

    private static final char WAW_CODE = WAW.getCode();

    private static final char ALIF_CODE = ALIF.getCode();

    private static final char YA_CODE = YA.getCode();

    private static final char HAMZAH_CODE = HAMZA.getCode();

    private static final char SUKUN_CODE = SUKUN.getCode();

    private static final String ARABIC_LETTERS_PATTERN = "[A-Za-z$]";

    private static final Pattern REMOVE_TATWEEL_PATTERN = compile(format("%s+", TATWEEL.toCode()));

    private static final Pattern REMOVE_MARKER_PATTERN = compile(format("\\%s+", REMOVE_MARKER.getLetter().getCode()));

    private static final Pattern MERGE_SIMILAR_LETTERS_WITH_FIRST_SAKIN_PATTERN = compile("[A-Za-z$]o[A-Za-z$]");

    private static final Pattern CONSECUTIVE_HAMZAH_PATTERN1 = compile(format(
            "^%s%s%s%s", HAMZAH_CODE, DIACRITICS, HAMZAH_CODE, SUKUN_CODE));

    private static final Pattern CONSECUTIVE_HAMZAH_PATTERN2 = compile(format(
            "^[%s%s]%s[%s%s%s]%s", ALIF_HAMZA_ABOVE_CODE,
            ALIF_HAMZA_BELOW_CODE, DIACRITICS, ALIF_HAMZA_ABOVE_CODE,
            YA_HAMZA_ABOVE_CODE, WAW_HAMZA_ABOVE_CODE, SUKUN_CODE));

    // "OaOo"
    private static final Pattern ALIF_MADDAH_PATTERN1 = compile(format(
            "%s%s%s%s", ALIF_HAMZA_ABOVE_CODE, FATHA_CODE,
            ALIF_HAMZA_ABOVE_CODE, SUKUN_CODE));

    // "OaA"
    private static final Pattern ALIF_MADDAH_PATTERN2 = compile(format(
            "%s%s%s", ALIF_HAMZA_ABOVE_CODE, FATHA_CODE, ALIF_CODE));

    private static final Pattern ALIF_MADDAH_PATTERN3 = compile(format(
            "%s%s%s", WAW_HAMZA_ABOVE_CODE, FATHA_CODE, ALIF_CODE));

    private static final Pattern CONSECUTIVE_SAKIN_PATTERN = compile(format(
            "%s%s%s%s", ARABIC_LETTERS_PATTERN, SUKUN_CODE,
            ARABIC_LETTERS_PATTERN, SUKUN_CODE));

    private static final Pattern CONSECUTIVE_WAW_PATTERN = compile("uw[aui]wo");

    private static final Pattern CONSECUTIVE_YA_PATTERN = compile("iy[aui]yo");

    private static final Pattern CONSECUTIVE_WAW_YA_PATTERN = compile("uw[aui]yo");

    private static final Pattern CONSECUTIVE_YA_WAW_PATTERN = compile("iy[aui]wo");

    private static final Pattern CONSECUTIVE_WAW_YA_SAKIN_PATTERN = compile("u[wy]o[wy]");

    private static final Pattern ALIF_FOLLOWED_BY_SAKIN = compile(format(
            "%s%s%s", ALIF_CODE, ARABIC_LETTERS_PATTERN, SUKUN_CODE));

    private static final Pattern RULE3_1_PATTERN = compile(format("%s%s%s%s",
            ARABIC_LETTERS_PATTERN, KASRA_CODE, WAW_CODE, SUKUN_CODE));

    private static final Pattern RULE3_2_PATTERN = compile(format("%s%s%s%s",
            ARABIC_LETTERS_PATTERN, DAMMA_CODE, YA_CODE, SUKUN_CODE));

    private static final Pattern RULE3_3_PATTERN = compile(format("%s%s%s",
            ARABIC_LETTERS_PATTERN, KASRA_CODE, ALIF_CODE));

    private static final Pattern RULE3_4_PATTERN = compile(format("%s%s%s",
            ARABIC_LETTERS_PATTERN, DAMMA_CODE, ALIF_CODE));

    public static String applyRule3(String text) {
        String result = text;
        result = applyRule3(text, RULE3_1_PATTERN, WAW_CODE, YA_CODE);
        result = applyRule3(result, RULE3_2_PATTERN, YA_CODE, WAW_CODE);
        result = applyRule3(result, RULE3_3_PATTERN, ALIF_CODE, WAW_CODE);
        result = applyRule3(result, RULE3_4_PATTERN, ALIF_CODE, YA_CODE);
        return result;
    }

    private static String applyRule3(String text, Pattern pattern,
                                     char oldChar, char newChar) {
        String result = text;
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = 0;
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                continue;
            }
            String matchedString = text.substring(start, end);
            String replacement = matchedString.replace(oldChar, newChar);
            result = result.replaceAll(matchedString, replacement);
        }
        return result;
    }

    public static String clusterReduction(String text) {
        String result = null;
        Matcher matcher = CONSECUTIVE_SAKIN_PATTERN.matcher(text);
        while (matcher.find()) {
            int start = 0;
            @SuppressWarnings("unused")
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return text;
            }
            result = text.substring(0, start) + text.substring(start + 2);
        }
        return result == null ? text : result;
    }

    public static RootWord doApplyPatterns(RootWord baseRootWord) {
        String result = baseRootWord.getRootWord().toBuckWalter();
        result = removeMarker(result);
        result = mergeRepeats(result);
        result = applyRule3(result);
        result = replaceConsecutiveHamzah(result);
        result = replaceWithAlifMaddah(result);
        result = dropAlifBeforeSakin(result);
        result = dropConsecutiveWaw(result);
        result = dropConsecutiveYa(result);
        result = dropConsecutiveWawAndYa(result);
        result = dropConsecutiveYaAndWaw(result);
        result = mergeConsecutiveWawYaSakin(result);
        result = clusterReduction(result);
        baseRootWord.setRootWord(fromBuckWalterString(result));
        return baseRootWord;
    }

    private static String dropAlifBeforeSakinVowel(String text, Pattern pattern) {
        String result = null;
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = 0;
            @SuppressWarnings("unused")
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return text;
            }
            result = text.substring(0, start) + text.substring(start + 1);
        }
        return result == null ? text : result;
    }

    public static String dropAlifBeforeSakin(String text) {
        return dropAlifBeforeSakinVowel(text, ALIF_FOLLOWED_BY_SAKIN);
    }

    public static String dropConsecutiveWaw(String text) {
        return dropConsecutiveWawOrYa(CONSECUTIVE_WAW_PATTERN, text);
    }

    private static String dropConsecutiveWawAndYa(Pattern pattern, String text) {
        String result = null;
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = 0;
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return text;
            }
            String matchedString = text.substring(start, end);
            char harkah = matchedString.charAt(2);
            String s = matchedString.substring(3);
            String newString = harkah + s + s;
            result = text.replaceFirst(matchedString, newString);
        }
        return result == null ? text : result;
    }

    public static String dropConsecutiveWawAndYa(String text) {
        return dropConsecutiveWawAndYa(CONSECUTIVE_WAW_YA_PATTERN, text);
    }

    private static String dropConsecutiveWawOrYa(Pattern pattern, String text) {
        String result = null;
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = 0;
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return text;
            }
            String matchedString = text.substring(start, end);
            String newString = matchedString.substring(0, 2) + "o"
                    + matchedString.substring(3);
            result = text.replaceFirst(matchedString, newString);
        }
        return result == null ? text : result;
    }

    public static String dropConsecutiveYa(String text) {
        return dropConsecutiveWawOrYa(CONSECUTIVE_YA_PATTERN, text);
    }

    public static String dropConsecutiveYaAndWaw(String text) {
        return dropConsecutiveWawAndYa(CONSECUTIVE_YA_WAW_PATTERN, text);
    }

    public static String mergeConsecutiveWawYaSakin(String text) {
        String result = text;
        Matcher matcher = CONSECUTIVE_WAW_YA_SAKIN_PATTERN.matcher(text);
        while (matcher.find()) {
            int start = 0;
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                continue;
            }
            String matchedString = text.substring(start, end);
            if (matchedString.contains("wow") || matchedString.contains("yoy")) {
                continue;
            }
            String newString = "iy~";
            result = text.replaceAll(matchedString, newString);
        }
        return result == null ? text : result;
    }

    public static String mergeRepeats(String text) {
        String result = null;
        Matcher matcher = MERGE_SIMILAR_LETTERS_WITH_FIRST_SAKIN_PATTERN
                .matcher(text);
        while (matcher.find()) {
            int start = 0;
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return text;
            }
            String matchedString = text.substring(start, end);
            String[] split = matchedString.split("o");
            if (split != null && split.length == 2) {
                String s1 = split[0];
                String s2 = split[1];
                if (s1.equals(s2)) {
                    result = text.replaceFirst(matchedString, s1
                            + valueOf(SHADDA.getCode()));
                }
            }
        }
        if (result == null) {
            result = text;
        }
        return result;
    }

    public static String removeMarker(String text) {
        return removeMarker(REMOVE_MARKER_PATTERN, text);
    }

    public static String removeTatweel(String text) {
        return removeMarker(REMOVE_TATWEEL_PATTERN, text);
    }

    private static String removeMarker(Pattern pattern, String text) {
        String result = text;
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = 0;
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return text;
            }
            String matchedString = text.substring(start, end);
            result = remove(text, matchedString);
        }
        return result;
    }

    private static String replaceConsecutiveHamzah(Pattern pattern, String text) {
        String result = text;
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = 0;
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return text;
            }
            String matchedString = text.substring(start, end);
            char diacritic = matchedString.charAt(1);
            String newString = null;
            String toBeReplaced = matchedString.substring(2);
            if (diacritic == KASRA_CODE) {
                newString = matchedString.replaceFirst(toBeReplaced,
                        valueOf(YA_CODE) + valueOf(SUKUN_CODE));
            } else if (diacritic == FATHA_CODE) {
                newString = valueOf(ALIF_MADDAH_CODE);
            } else if (diacritic == DAMMA_CODE) {
                newString = matchedString.replaceFirst(toBeReplaced,
                        valueOf(WAW_CODE) + valueOf(SUKUN_CODE));
            }
            if (newString != null) {
                result = text.replaceFirst(matchedString, newString);
            }

        }
        return result;
    }

    public static String replaceConsecutiveHamzah(String text) {
        String result = replaceConsecutiveHamzah(CONSECUTIVE_HAMZAH_PATTERN1,
                text);
        return replaceConsecutiveHamzah(CONSECUTIVE_HAMZAH_PATTERN2, result);
    }

    private static String replaceWithAlifMaddah(Pattern pattern, String text) {
        String result = null;
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            int start = 0;
            int end = 0;
            try {
                start = matcher.start();
                end = matcher.end();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return text;
            }
            String matchedString = text.substring(start, end);
            result = text
                    .replaceFirst(matchedString, valueOf(ALIF_MADDAH_CODE));
        }
        if (result == null) {
            result = text;
        }
        return result;
    }

    public static String replaceWithAlifMaddah(String text) {
        String result = text;
        result = replaceWithAlifMaddah(ALIF_MADDAH_PATTERN1, result);
        result = replaceWithAlifMaddah(ALIF_MADDAH_PATTERN2, result);
        result = replaceWithAlifMaddah(ALIF_MADDAH_PATTERN3, result);
        return result;
    }

}
