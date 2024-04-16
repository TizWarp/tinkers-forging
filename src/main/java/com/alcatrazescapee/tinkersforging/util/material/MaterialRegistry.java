package com.alcatrazescapee.tinkersforging.util.material;

import java.awt.*;
import java.util.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.item.Item;

import com.alcatrazescapee.tinkersforging.ModConfig;
import com.alcatrazescapee.tinkersforging.TinkersForging;

@ParametersAreNonnullByDefault
public final class MaterialRegistry
{
    private static final Map<String, MaterialType> MATERIALS = new HashMap<>();
    private static final Set<String> TINKERS_MATERIALS = new HashSet<>();
    private static final Set<String> NTP_MATERIALS = new HashSet<>();
    private static final Set<String> TOOLBOX_MATERIALS = new HashSet<>();

    public static void preInit()
    {
        // Vanilla Materials
        addMaterial(new MaterialType("iron", Item.ToolMaterial.IRON, new Color(255, 255, 255).getRGB(), 2, 1350f, 1600f));
        addMaterial(new MaterialType("gold", Item.ToolMaterial.GOLD, new Color(255, 248, 53).getRGB(), 1, 700f, 1100f));

        // Common Modded Materials
        addMaterial(new MaterialType("copper", new Color(185, 92, 58).getRGB(), 1, 600f, 1000f));
        addMaterial(new MaterialType("tin", new Color(148, 148, 165).getRGB(), 1, 150f, 300f));
        addMaterial(new MaterialType("bronze", new Color(210, 133, 19).getRGB(), 2, 700f, 950f));
        addMaterial(new MaterialType("steel", new Color(95, 95, 95).getRGB(), 3, 950f, 1350f));
        addMaterial(new MaterialType("lead", new Color(52, 56, 69).getRGB(), 1, 200f, 350f));
        addMaterial(new MaterialType("silver", new Color(112, 112, 145).getRGB(), 1, 700f, 950f));
        addMaterial(new MaterialType("aluminium", new Color(133, 148, 156).getRGB(), 1, 450f, 700f));
        addMaterial(new MaterialType("platinum", new Color(97, 172, 216).getRGB(), 4, 1050f, 2000f));

        // NTP Compat Materials
        NTP_MATERIALS.addAll(Arrays.asList("iron", "gold", "tin", "copper", "bronze", "steel"));
        
        // Force Enable materials
        for (String s : ModConfig.GENERAL.forceEnabledMetals)
        {
            if (MATERIALS.containsKey(s))
            {
                MATERIALS.get(s).setEnabled();
            }
        }
    }

    public static void addMaterial(MaterialType material)
    {
        if (MATERIALS.containsKey(material.getName()))
        {
            TinkersForging.getLog().debug("Material {} was overriden!", material.getName());
        }
        MATERIALS.put(material.getName(), material);
    }

    public static void addTinkersMaterial(MaterialType material)
    {
        if (MATERIALS.containsKey(material.getName()))
        {
            TINKERS_MATERIALS.add(material.getName());
        }
    }

    public static void addToolboxMaterial(MaterialType material)
    {
        if (MATERIALS.containsKey(material.getName()))
        {
            TOOLBOX_MATERIALS.add(material.getName());
        }
    }

    @Nonnull
    public static Collection<MaterialType> getAllMaterials()
    {
        return MATERIALS.values();
    }

    @Nullable
    public static MaterialType getMaterial(String name)
    {
        return MATERIALS.get(name);
    }

    public static boolean isTinkersMaterial(MaterialType material)
    {
        return TINKERS_MATERIALS.contains(material.getName());
    }

    public static boolean isNTPMaterial(MaterialType material)
    {
        return NTP_MATERIALS.contains(material.getName());
    }

    public static boolean isToolboxMaterial(MaterialType material)
    {
        return TOOLBOX_MATERIALS.contains(material.getName());
    }
}
