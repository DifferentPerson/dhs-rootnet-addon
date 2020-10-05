package localhost.adifferentperson.dhs;

import dev.rootnet.addons.api.addon.Addon;
import dev.rootnet.addons.api.annotations.RootnetAddon;
import org.apache.logging.log4j.Level;

@SuppressWarnings("unused")
@RootnetAddon(name = "DHSCoordinateFormatting", author = "ADifferentPerson", version = "1.0.0")
public final class DHSAddon extends Addon {
    public DHSAddon() { super(); }

    @Override
    public final void init() {
        log(Level.INFO, "Initializing DHS Addon...");
        getRootnet().registerModule(this, new DHSModule());
    }
}