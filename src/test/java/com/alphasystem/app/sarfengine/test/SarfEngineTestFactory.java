/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import org.testng.annotations.Factory;

/**
 * @author sali
 */
public class SarfEngineTestFactory {

    @Factory
    public Object[] createTest() {
        return new Object[]{
                new GuiceTest(),
                new LabelPrinter(),
                new RuleTester(),
                new BuilderTest(),
                new TransformerTest(),
                new ConjugationTest(),
                new PatternHelperTest()
        };
    }
}
