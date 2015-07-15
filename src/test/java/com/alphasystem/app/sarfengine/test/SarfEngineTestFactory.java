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
        return new Object[]{new RuleTester(), new BuilderTest(),
                new PatternHelperTest(), new ConjugationTest()};
    }
}
