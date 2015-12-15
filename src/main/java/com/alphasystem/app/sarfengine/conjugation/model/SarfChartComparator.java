/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ActiveLine;
import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.RootType;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;
import com.alphasystem.sarfengine.xml.model.SortDirection;
import com.alphasystem.sarfengine.xml.model.SortDirective;

import java.util.Comparator;

import static com.alphasystem.sarfengine.xml.model.SortDirection.ASCENDING;
import static com.alphasystem.sarfengine.xml.model.SortDirection.DESCENDING;
import static com.alphasystem.sarfengine.xml.model.SortDirective.*;

/**
 * @author sali
 */
public class SarfChartComparator implements Comparator<SarfChart> {

    private SortDirective sortDirective;

    private SortDirection sortDirection;

    public SarfChartComparator() {
        this(ALPHABATICAL, ASCENDING);
    }

    /**
     * @param sortDirection
     */
    public SarfChartComparator(SortDirection sortDirection) {
        this(null, sortDirection);
    }

    /**
     * @param sortDirective
     */
    public SarfChartComparator(SortDirective sortDirective) {
        this(sortDirective, null);
    }

    public SarfChartComparator(SortDirective sortDirective,
                               SortDirection sortDirection) {
        setSortDirective(sortDirective);
        setSortDirection(sortDirection);
    }

    @Override
    public int compare(SarfChart o1, SarfChart o2) {
        if (sortDirective.equals(NONE)) {
            return 1;
        }
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }
        int result = 0;
        if (sortDirective.equals(ALPHABATICAL)) {
            result = compareAlphabatically(o1, o2);
        } else if (sortDirective.equals(TYPE)) {
            result = compareByType(o1, o2);
        }

        return result;
    }

    private int compareAlphabatically(SarfChart o1, SarfChart o2) {
        ConjugationMember pt1 = getPastTense(o1);
        ConjugationMember pt2 = getPastTense(o2);
        return compareArabicWords(pt1, pt2);
    }

    private int compareArabicWords(ConjugationMember aw1, ConjugationMember aw2) {
        if (aw1 == null && aw2 == null) {
            return 0;
        } else if (aw1 == null) {
            return -1;
        } else if (aw2 == null) {
            return 1;
        } else {
            ConjugationMember t1 = aw1;
            ConjugationMember t2 = aw2;
            if (sortDirection.equals(DESCENDING)) {
                ConjugationMember temp = t1;
                t1 = t2;
                t2 = temp;
            }
            return t1.compareTo(t2);
        }
    }

    private int compareByType(SarfChart o1, SarfChart o2) {
        int result = 0;
        ChartMode cm1 = o1.getHeader().getChartMode();
        ChartMode cm2 = o2.getHeader().getChartMode();
        result = compareNamedTemplate(o1.getNamedTemplate(),
                o2.getNamedTemplate());
        if (result == 0) {
            result = compareRootType(cm1.getRootType(), cm2.getRootType());
            if (result == 0) {
                result = compareVerbType(cm1.getVerbType(), cm2.getVerbType());
                if (result == 0) {
                    WeakVerbType t1 = cm1.getWeakVerbType();
                    WeakVerbType t2 = cm2.getWeakVerbType();
                    if (t1 != null && t2 != null) {
                        result = compareWeakVerbType(t1, t2);
                    }
                    result = compareAlphabatically(o1, o2);
                }
            }
        }
        return result;
    }

    private int compareInteger(Integer i1, Integer i2) {
        Integer t1 = i1;
        Integer t2 = i2;
        if (sortDirection.equals(DESCENDING)) {
            Integer temp = t1;
            t1 = t2;
            t2 = temp;
        }
        return t1.compareTo(t2);
    }

    private int compareNamedTemplate(NamedTemplate t1, NamedTemplate t2) {
        // just to make sure two enum types are of same type
        int result = t1.compareTo(t2);
        result = compareInteger(t1.getIndex(), t2.getIndex());
        if (result == 0) {
            result = compareInteger(t1.getSubIndex(), t2.getSubIndex());
        }
        return result;
    }

    private int compareRootType(RootType rt1, RootType rt2) {
        RootType t1 = rt1;
        RootType t2 = rt1;
        if (sortDirection.equals(DESCENDING)) {
            RootType temp = t1;
            t1 = t2;
            t2 = temp;
        }
        return t1.compareTo(t2);
    }

    private int compareVerbType(VerbType vt1, VerbType vt2) {
        VerbType t1 = vt1;
        VerbType t2 = vt2;
        if (sortDirection.equals(DESCENDING)) {
            VerbType temp = vt1;
            t1 = t2;
            t2 = temp;
        }
        return t1.compareTo(t2);
    }

    private int compareWeakVerbType(WeakVerbType wvt1, WeakVerbType wvt2) {
        WeakVerbType t1 = wvt1;
        WeakVerbType t2 = wvt2;
        if (sortDirection.equals(DESCENDING)) {
            WeakVerbType temp = wvt1;
            t1 = t2;
            t2 = temp;
        }
        return t1.compareTo(t2);
    }

    private ConjugationMember getPastTense(SarfChart sarfChart) {
        ConjugationMember arabicWord = null;
        if (sarfChart != null) {
            SarfSagheer sarfSagheer = sarfChart.getSarfSagheer();
            if (sarfSagheer != null) {
                ActiveLine activeLine = sarfSagheer.getActiveLine();
                if (activeLine != null) {
                    //TODO:
                    //arabicWord = activeLine.getPastTense();
                }
            }
        }
        return arabicWord;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection == null ? ASCENDING : sortDirection;
    }

    public void setSortDirective(SortDirective sortDirective) {
        this.sortDirective = sortDirective == null ? TYPE : sortDirective;
    }

}
