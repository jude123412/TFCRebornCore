package tfcreborncore.objects.items.enums;

public enum ModGroup {

    FORESTRY("forestry"),
    BINNIES("binnie"),
    IMMERSIVE_ENGINEERING("immersiveengineering"),
    OTHER("other");

    private final String modId;

    ModGroup(String modId) {
        this.modId = modId;
    }

    public String getModId() {
        return modId;
    }
}
