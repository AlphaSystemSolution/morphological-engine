/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import com.alphasystem.app.morphologicalengine.conjugation.transformer.test.NounTransformerTest;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.test.TransformerTest;
import org.testng.annotations.Factory;

/**
 * @author sali
 */
public class SarfEngineTestFactory {

    @Factory
    public Object[] createTest() {
        return new Object[]{
                new LabelPrinter(),
                // new RuleTester(),
               //  new BuilderTest(),
                new NounTransformerTest(),
                // new VerbTransformerTest(),
                new TransformerTest(),
                // new ConjugationTest(),
                new PatternHelperTest()
        };
    }
}
