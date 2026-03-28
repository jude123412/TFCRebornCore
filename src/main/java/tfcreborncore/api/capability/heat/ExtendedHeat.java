package tfcreborncore.api.capability.heat;

import javax.annotation.Nullable;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.config.OreTooltipMode;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;

/*
 * Original code from Terrafirmacraft's Heat Enum (EUPL v1.2)
 * Modified to create ExtendedHeat as lots of TFC-Metallums Metals
 * Have Heat Values over 1601.0F
 * Created by xXjudeXx on 2026-03-28
 */
public enum ExtendedHeat {

    WARMING(1.0F, 80.0F, TextFormatting.GRAY, TextFormatting.DARK_GRAY),
    HOT(80.0F, 210.0F, TextFormatting.GRAY, TextFormatting.DARK_GRAY),
    VERY_HOT(210.0F, 480.0F, TextFormatting.GRAY, TextFormatting.DARK_GRAY),
    FAINT_RED(480.0F, 580.0F, TextFormatting.DARK_RED),
    DARK_RED(580.0F, 730.0F, TextFormatting.DARK_RED),
    BRIGHT_RED(730.0F, 930.0F, TextFormatting.RED),
    ORANGE(930.0F, 1100.0F, TextFormatting.GOLD),
    YELLOW(1100.0F, 1300.0F, TextFormatting.YELLOW),
    YELLOW_WHITE(1300.0F, 1400.0F, TextFormatting.YELLOW),
    WHITE(1400.0F, 1500.0F, TextFormatting.WHITE),
    BRILLIANT_WHITE(1500.0F, 1600.0F, TextFormatting.WHITE),
    WALTER_WHITE(1600.0F, 2000.0F, TextFormatting.AQUA),
    BLUE_WHITE(2000.0F, 3000.0F, TextFormatting.BLUE),
    VIOLET_WHITE(3000.0F, 4000.0F, TextFormatting.DARK_PURPLE),
    ULTRAVIOLET_EDGE(4000.0F, 5000.0F, TextFormatting.LIGHT_PURPLE);

    private static final ExtendedHeat[] VALUES = values();
    final TextFormatting format;
    final TextFormatting alternate;
    private final float min;
    private final float max;

    public static float maxVisibleTemperature() {
        return ULTRAVIOLET_EDGE.getMax();
    }

    @Nullable
    public static ExtendedHeat getHeat(float temperature) {
        for (ExtendedHeat heat : VALUES) {
            if (heat.min <= temperature && temperature < heat.max) {
                return heat;
            }
        }

        if (temperature > ULTRAVIOLET_EDGE.max) {
            return ULTRAVIOLET_EDGE;
        } else {
            return null;
        }
    }

    @Nullable
    public static String getTooltipColorless(float temperature) {
        ExtendedHeat heat = getHeat(temperature);
        if (heat == null) {
            return null;
        } else {
            StringBuilder b = new StringBuilder();
            b.append(I18n.format(Helpers.getEnumName(heat), new Object[0]));
            if (heat != ULTRAVIOLET_EDGE) {
                for (int i = 1; i <= 4; ++i) {
                    if (!(temperature <= heat.getMin() + (float) i * 0.2F * (heat.getMax() - heat.getMin()))) {
                        b.append("★");
                    }
                }
            }

            return b.toString();
        }
    }

    @Nullable
    public static String getTooltip(float temperature) {
        ExtendedHeat heat = getHeat(temperature);
        String tooltip = getTooltipColorless(temperature);
        if (tooltip != null && heat != null) {
            tooltip = heat.format + tooltip;
            if (ConfigTFC.Client.TOOLTIP.oreTooltipMode == OreTooltipMode.ADVANCED) {
                tooltip = tooltip + " : " +
                        I18n.format("tfc.tooltip.melttemp", new Object[] { Math.round(temperature) });
            }
        }

        return tooltip;
    }

    @Nullable
    public static String getTooltipAlternate(float temperature) {
        ExtendedHeat heat = getHeat(temperature);
        String tooltip = getTooltipColorless(temperature);
        if (tooltip != null && heat != null) {
            tooltip = heat.alternate + tooltip;
            if (ConfigTFC.Client.TOOLTIP.oreTooltipMode == OreTooltipMode.ADVANCED) {
                tooltip = tooltip + " : " +
                        I18n.format("tfc.tooltip.melttemp", new Object[] { Math.round(temperature) });
            }
        }

        return tooltip;
    }

    private ExtendedHeat(float min, float max, TextFormatting format, TextFormatting alternate) {
        this.min = min;
        this.max = max;
        this.format = format;
        this.alternate = alternate;
    }

    private ExtendedHeat(float min, float max, TextFormatting format) {
        this(min, max, format, format);
    }

    public float getMin() {
        return this.min;
    }

    public float getMax() {
        return this.max;
    }
}
