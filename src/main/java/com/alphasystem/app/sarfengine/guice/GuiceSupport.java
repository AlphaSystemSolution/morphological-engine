package com.alphasystem.app.sarfengine.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mycila.guice.ext.closeable.CloseableModule;
import com.mycila.guice.ext.jsr250.Jsr250Module;

/**
 * @author sali
 */
public class GuiceSupport {

    private static GuiceSupport instance = new GuiceSupport();
    private final Injector injector;

    /**
     * Do not let anyone instantiate this class
     */
    private GuiceSupport() {
        injector = Guice.createInjector(new CloseableModule(), new Jsr250Module(), new SarfEngineModule());
    }

    public static GuiceSupport getInstance() {
        return instance;
    }

    public Injector getInjector() {
        return injector;
    }
}
