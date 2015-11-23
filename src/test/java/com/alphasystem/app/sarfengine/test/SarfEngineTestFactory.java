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
                // new GuiceTest(),
                new RuleTester(),
                new ConjugationMemberTest(),
                new BuilderTest()
                /*,
                new PatternHelperTest(), new ConjugationTest()*/
        };
    }
}
