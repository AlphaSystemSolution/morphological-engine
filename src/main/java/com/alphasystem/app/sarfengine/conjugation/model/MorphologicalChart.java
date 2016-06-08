/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;

/**
 * @author sali
 */
public class MorphologicalChart {

    private final AbbreviatedConjugation abbreviatedConjugation;
    private final SarfKabeer sarfKabeer;
    private final ConjugationHeader conjugationHeader;

    /**
     * @param conjugationHeader
     * @param abbreviatedConjugation
     * @param sarfKabeer
     */
    public MorphologicalChart(ConjugationHeader conjugationHeader, AbbreviatedConjugation abbreviatedConjugation, SarfKabeer sarfKabeer) {
        this.conjugationHeader = conjugationHeader;
        this.abbreviatedConjugation = abbreviatedConjugation;
        this.sarfKabeer = sarfKabeer;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null && MorphologicalChart.class.isAssignableFrom(obj.getClass())) {
            MorphologicalChart other = (MorphologicalChart) obj;
            SarfChartComparator c = new SarfChartComparator();
            result = c.compare(this, other) == 0;
        }
        return result;
    }

    public ConjugationHeader getHeader() {
        return conjugationHeader;
    }

    public NamedTemplate getNamedTemplate() {
        NamedTemplate namedTemplate = null;
        if (conjugationHeader != null) {
            ChartMode chartMode = conjugationHeader.getChartMode();
            if (chartMode != null) {
                namedTemplate = chartMode.getTemplate();
            }
        }
        return namedTemplate;
    }

    public RootLetters getRootLetters() {
        return conjugationHeader == null ? new RootLetters() : conjugationHeader.getRootLetters();
    }

    public SarfKabeer getSarfKabeer() {
        return sarfKabeer;
    }

    public AbbreviatedConjugation getAbbreviatedConjugation() {
        return abbreviatedConjugation;
    }

}
