/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.sarfengine.xml.model.RootLetters;

/**
 * @author sali
 */
public class SarfChart {

    private final SarfSagheer sarfSagheer;
    private final SarfKabeer sarfKabeer;
    private final ConjugationHeader conjugationHeader;

    /**
     * @param conjugationHeader
     * @param sarfSagheer
     * @param sarfKabeer
     */
    public SarfChart(ConjugationHeader conjugationHeader, SarfSagheer sarfSagheer, SarfKabeer sarfKabeer) {
        this.conjugationHeader = conjugationHeader;
        this.sarfSagheer = sarfSagheer;
        this.sarfKabeer = sarfKabeer;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj != null && SarfChart.class.isAssignableFrom(obj.getClass())) {
            SarfChart other = (SarfChart) obj;
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

    public SarfSagheer getSarfSagheer() {
        return sarfSagheer;
    }

}
