package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.morphologicalanalysis.morphology.model.RootLetters;
import com.alphasystem.morphologicalengine.model.AbbreviatedConjugation;
import com.alphasystem.morphologicalengine.model.ConjugationHeader;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sali
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MorphologicalChart {

    private final AbbreviatedConjugation abbreviatedConjugation;
    private final DetailedConjugation detailedConjugation;
    private final ConjugationHeader conjugationHeader;

    /**
     * @param abbreviatedConjugation
     * @param detailedConjugation
     */
    public MorphologicalChart(AbbreviatedConjugation abbreviatedConjugation, DetailedConjugation detailedConjugation) {
        this.abbreviatedConjugation = abbreviatedConjugation;
        this.detailedConjugation = detailedConjugation;
        this.conjugationHeader = (abbreviatedConjugation == null) ? null : abbreviatedConjugation.getConjugationHeader();
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

    public DetailedConjugation getDetailedConjugation() {
        return detailedConjugation;
    }

    public AbbreviatedConjugation getAbbreviatedConjugation() {
        return abbreviatedConjugation;
    }

}
