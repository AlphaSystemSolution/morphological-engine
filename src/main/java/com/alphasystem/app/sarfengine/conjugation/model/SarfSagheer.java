/**
 *
 */
package com.alphasystem.app.sarfengine.conjugation.model;

import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.ActiveLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.AdverbLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.CommandLine;
import com.alphasystem.app.sarfengine.conjugation.model.sarfsagheer.PassiveLine;

import static com.alphasystem.util.HashCodeUtil.hash;

/**
 * @author sali
 */
public class SarfSagheer {

    private final ActiveLine activeLine;

    private final PassiveLine passiveLine;

    private final CommandLine commandLine;

    private final AdverbLine zarfLine;

    public SarfSagheer(ActiveLine activeLine, PassiveLine passiveLine,
                       CommandLine commnadLine, AdverbLine adverbLine) {
        this.activeLine = activeLine;
        this.passiveLine = passiveLine;
        this.commandLine = commnadLine;
        this.zarfLine = adverbLine;
    }

    public ActiveLine getActiveLine() {
        return activeLine;
    }

    public CommandLine getCommandLine() {
        return commandLine;
    }

    public PassiveLine getPassiveLine() {
        return passiveLine;
    }

    public AdverbLine getZarfLine() {
        return zarfLine;
    }

    @Override
    public int hashCode() {
        int hc = hash(super.hashCode(), activeLine);
        hc = hash(hc, passiveLine);
        hc = hash(hc, commandLine);
        hc = hash(hc, zarfLine);
        return hc;
    }
}
