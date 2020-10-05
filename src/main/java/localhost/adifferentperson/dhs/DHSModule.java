package localhost.adifferentperson.dhs;

import dev.rootnet.addons.api.addon.AddonModule;
import dev.rootnet.addons.api.annotations.RootnetModule;
import dev.rootnet.addons.api.annotations.RootnetSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

@RootnetModule(name = "DHSCoordinates")
public final class DHSModule extends AddonModule {
    private static final Minecraft MC = Minecraft.getMinecraft();

    public enum Corner {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
    }

    public Corner[] cornerValues = Corner.values();

    //@RootnetSetting(name = "Corner")
    public Corner corner = Corner.BOTTOM_LEFT;

    @RootnetSetting(name = "CornerInt")
    public int cornerInt = 0;

    private ScaledResolution scaledResolution = new ScaledResolution(MC);

    @Override
    public final void renderOverlay() {
        scaledResolution = new ScaledResolution(MC);

        corner = cornerValues[cornerInt % 4];
        final String coordString = String.format(
                "%s %s %s %s%s%sXYZ_%s",
                round(Math.abs(MC.player.posX)),
                round(Math.abs(MC.player.posY)),
                round(Math.abs(MC.player.posZ)),
                getSign(MC.player.posX),
                getSign(MC.player.posY),
                getSign(MC.player.posZ),
                getDimension(MC.player.dimension)
        );

        MC.fontRenderer.drawString(
                coordString,
                getStringX(coordString),
                getStringY(),
                0xffffff,
                true
        );
    }

    private float getStringX(final String str) {
        if(corner == Corner.TOP_LEFT || corner == Corner.BOTTOM_LEFT) {
            return 2.0f;
        }
        return scaledResolution.getScaledWidth() - MC.fontRenderer.getStringWidth(str) - 2.0f;
    }

    private float getStringY() {
        if(corner == Corner.TOP_LEFT || corner == Corner.TOP_RIGHT) {
            return 2.0f;
        }
        return scaledResolution.getScaledHeight() - MC.fontRenderer.FONT_HEIGHT - 2.0f;
    }

    private static double round(final double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    private static String getSign(final double value) {
        return value >= 0.0 ? "+" : "-";
    }

    private static String getDimension(final int dim) {
        switch(dim) {
            case 0: {
                return "OW";
            }
            case -1: {
                return "UW";
            }
            case 1: {
                return "EW";
            }
            default: {
                return "ERR";
            }
        }
    }

}
