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

    private NamedTemplate template;

    private RootType rootType;

    private VerbType verbType;

    private WeakVerbType weakVerbType;

    public ChartMode() {
    }

    public ChartMode(NamedTemplate template, RootType rootType, VerbType verbType, WeakVerbType weakVerbType) {
        setTemplate(template);
        setRootType(rootType);
        setVerbType(verbType);
        setWeakVerbType(weakVerbType);
    }

    public NamedTemplate getTemplate() {
        return template;
    }

    public void setTemplate(NamedTemplate template) {
        this.template = template;
    }

    public RootType getRootType() {
        return rootType;
    }

    public void setRootType(RootType rootType) {
        this.rootType = rootType;
    }

    public VerbType getVerbType() {
        return verbType;
    }

    public void setVerbType(VerbType verbType) {
        this.verbType = verbType;
    }

    public WeakVerbType getWeakVerbType() {
        return weakVerbType;
    }

    public void setWeakVerbType(WeakVerbType weakVerbType) {
        this.weakVerbType = weakVerbType;
    }
}
