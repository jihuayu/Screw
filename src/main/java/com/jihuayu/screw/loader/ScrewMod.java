package com.jihuayu.screw.loader;

import com.google.common.collect.Maps;
import com.jihuayu.screw.annotation.loader.After;
import com.jihuayu.screw.annotation.loader.Need;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.Map;

public final class ScrewMod {
    public static final Map<String,ScrewMod> MODS = Maps.newHashMap();
    private Reflections reflections;
    private String id;
    private ModMetadata modMetadata;
    private String scanPath;
    private After[] afters;
    private Need[] Needs;

    public ScrewMod(String id, String scanPath, ModMetadata modMetadata) {
        this.id = id;
        this.scanPath = scanPath;
        this.modMetadata = modMetadata;
        this.reflections = new Reflections(scanPath);
    }

    public String getScanPath() {
        return scanPath;
    }

    public After[] getAfters() {
        return afters;
    }

    public void setAfters(After[] afters) {
        this.afters = afters;
    }

    public Need[] getNeeds() {
        return Needs;
    }

    public void setNeeds(Need[] needs) {
        Needs = needs;
    }

    public ModMetadata getModMetadata() {
        return modMetadata;
    }

    public String getId() {
        return id;
    }

    public Reflections getReflections() {
        return reflections;
    }
}
