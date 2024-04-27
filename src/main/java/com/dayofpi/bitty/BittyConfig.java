package com.dayofpi.bitty;

import eu.midnightdust.lib.config.MidnightConfig;

public class BittyConfig extends MidnightConfig {
    @Comment(centered = true) public static Comment world;
    @Entry() public static boolean birchOverhaul = true;
    @Entry() public static boolean smallDarkOaks = true;
    @Comment(centered = true) public static Comment blocks;
    @Entry() public static boolean reeds = true;
    @Entry() public static boolean shearOnlyMossCarpets = true;
    @Comment(centered = true) public static Comment items;
    @Entry() public static boolean applePies = true;
    @Entry() public static boolean calamari = true;
    @Entry() public static boolean roastedCarrots = true;
    @Entry() public static boolean leatherRework = true;
    @Entry() public static boolean craftableSaddles = true;
    @Entry() public static boolean repairableTridents = true;
    @Entry() public static boolean nautilusShellsFromArchaeology = true;
    @Comment(centered = true) public static Comment entities;
    @Entry() public static int extraRabbitSpawns = 6;
    @Entry() public static boolean rabbitFallDamageReduction = true;
    @Entry() public static boolean ocelotAsCatVariant = true;
}
