package com.alphasystem.app.morphologicalengine.conjugation.transformer.verb;

import com.alphasystem.morphologicalanalysis.morphology.model.RootWord;


/**
 * @author sali
 */
class ForbiddingSecondPersonFeminineTransformer extends PresentTenseSecondPersonFeminineTransformer {

    ForbiddingSecondPersonFeminineTransformer() {
    }

    @Override
    protected RootWord doSingular(RootWord rootWord) {
        final RootWord target = new RootWord(super.doSingular(rootWord));
        target.getRootWord().removeLast();
        return target;
    }

    @Override
    protected RootWord doDual(RootWord rootWord) {
        final RootWord target = new RootWord(super.doDual(rootWord));
        target.getRootWord().removeLast();
        return target;
    }

    @Override
    protected RootWord doPlural(RootWord rootWord) {
        return new RootWord(super.doPlural(rootWord));
    }
}
