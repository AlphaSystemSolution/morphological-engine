/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.arabic.model.ArabicLetter;
import com.alphasystem.arabic.model.ArabicLetters;
import com.alphasystem.arabic.model.ArabicWord;
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

    public static final String ARABIC_TEXT_SPAN = "<span class='arabicText'>%s</span>";

    public static String printArabicText(ArabicLetter src) {
        return format(ARABIC_TEXT_SPAN, src.toHtmlCode());
    }

    public static String printArabicText(ArabicWord src) {
        return format(ARABIC_TEXT_SPAN, src.toHtmlCode());
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
