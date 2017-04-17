/**
 *
 */
package com.alphasystem.app.sarfengine.test;

import org.testng.annotations.Factory;

import com.alphasystem.app.morphologicalengine.conjugation.test.AbbreviatedConjugationBuilderTest;
import com.alphasystem.app.morphologicalengine.conjugation.test.ConjugationTest;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.test.NounTransformerTest;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.test.TransformerTest;
import com.alphasystem.app.morphologicalengine.conjugation.transformer.verb.VerbTransformerTest;

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
                new VerbTransformerTest(),
                new TransformerTest(),
                new AbbreviatedConjugationBuilderTest(),
                new ConjugationTest(),
                new PatternHelperTest()
        };
    }
}
