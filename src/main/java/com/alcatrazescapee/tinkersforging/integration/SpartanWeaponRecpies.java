package com.alcatrazescapee.tinkersforging.integration;

public enum SpartanWeaponRecpies {

    DAGGER(handleType.GUARD_BLADE, false),
    LONGSWORD(handleType.GUARD_BLADE, false),
    KATANA(handleType.HANDLE, false),
    SABER(handleType.GUARD_BLADE, false),
    RAPIER(handleType.GUARD_BLADE, false),
    GREATSWORD(handleType.GUARD_BLADE, false),
    HAMMER(handleType.HANDLE, false),
    WARHAMMER(handleType.HANDLE, false),
    SPEAR(handleType.POLE, false),
    HALBERD(handleType.POLE, true),
    PIKE(handleType.POLE, false),
    LANCE(handleType.POLE, false),
    LONGBOW(handleType.BOW, false),
    CROSSBOW(handleType.CROSSBOW, false),
    THROWING_KNIFE(handleType.HANDLE, false),
    THROWING_AXE(handleType.HANDLE, false),
    JAVELIN(handleType.POLE, false),
    BOOMERANG(handleType.HANDLE, false),
    BATTLEAXE(handleType.HANDLE, true),
    MACE(handleType.HANDLE, false),
    GLAIVE(handleType.POLE, true),
    STAFF(handleType.STAFF, true),
    PARRYINGDAGGER(handleType.GUARD_BLADE, false);


    public enum handleType {
        HANDLE,
        POLE,
        BOW,
        CROSSBOW,
        GUARD_BLADE,
        STAFF;
    }

    public final handleType recipeType;

    private final boolean serperateHead;

    private static final SpartanWeaponRecpies[] allNames = {DAGGER, LONGSWORD, KATANA, SABER, RAPIER, GREATSWORD, HAMMER, WARHAMMER, SPEAR, HALBERD, PIKE, LANCE, LONGBOW, CROSSBOW, THROWING_AXE, THROWING_KNIFE, JAVELIN, BOOMERANG, BATTLEAXE, MACE, GLAIVE, STAFF, PARRYINGDAGGER};

    SpartanWeaponRecpies(handleType type, boolean serperateHead) {

        this.recipeType = type;
        this.serperateHead = serperateHead;
    }

    public static boolean getSeperateHead(SpartanWeaponRecpies type) { return  type.serperateHead; }

    public static handleType getHandle(SpartanWeaponRecpies type) {
        return type.recipeType;
    }

    public static SpartanWeaponRecpies[] allNames()
    {
        return allNames;
    }

}
