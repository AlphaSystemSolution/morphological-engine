/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.sarfengine.xml.model.RootWord;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static java.lang.String.format;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class CommonTest extends Assert implements ArabicLetters, Constants {

    public static String printArabicText(ArabicLetter src) {
        return format(ARABIC_TEXT_SPAN, src.toHtmlCode());
    }

    public static String printArabicText(String format, ArabicWord src) {
        return format(format, src.toHtmlCode());
    }

    public static String printArabicText(ArabicWord src) {
        return printArabicText(ARABIC_TEXT_SPAN, src);
    }

    public static String printArabicText(RootWord src) {
        return printArabicText(src.getRootWord());
    }

    @AfterMethod
    public void afterMethod(Method method) {
        log(format("<div>End of test %s</div>", method.getName()));
        log("<hr/></p><br/>");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        log("<p><hr/>");
        log(format("<div>Start of test %s</div>", method.getName()));
    }
}
