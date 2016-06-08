/**
 *
 */
package com.alphasystem.app.morphologicalengine.conjugation.model;

import com.alphasystem.arabic.model.NamedTemplate;
import com.alphasystem.arabic.model.RootType;
import com.alphasystem.arabic.model.VerbType;
import com.alphasystem.arabic.model.WeakVerbType;

/**
 * @author sali
 */
public class ChartMode {

    private final NamedTemplate template;

    private final RootType rootType;

    private final VerbType verbType;

    private final WeakVerbType weakVerbType;

    /**
     * @param template
     * @param rootType
     * @param verbType
     * @param weakVerbType
     */
    public ChartMode(NamedTemplate template, RootType rootType,
                     VerbType verbType, WeakVerbType weakVerbType) {
        this.template = template;
        this.rootType = rootType;
        this.verbType = verbType;
        this.weakVerbType = weakVerbType;
    }

    public RootType getRootType() {
        return rootType;
    }

    public NamedTemplate getTemplate() {
        return template;
    }

    public VerbType getVerbType() {
        return verbType;
    }

    public WeakVerbType getWeakVerbType() {
        return weakVerbType;
    }
}
