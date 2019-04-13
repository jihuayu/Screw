package com.jihuayu.screw.loader;

import com.jihuayu.screw.Screw;
import com.jihuayu.screw.annotation.Load;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Set;

public final class ModLoader {
    private static Logger logger = Screw.getLogger();
//    private final Map<String, ScrewMod> mods = new HashMap<>();
    public static void loadJson(){
        FabricLoader.getInstance().getAllMods().forEach(i->{
            ModMetadata modMetadata = i.getMetadata();
            if(modMetadata.containsCustomElement("screw")) {
                String scanPath = modMetadata.getCustomElement("screw").getAsString();
                logger.info(scanPath);
                String id = i.getMetadata().getId();
                ScrewMod.MODS.put(id, new ScrewMod(scanPath, id, modMetadata));
                Set<Class<? extends ModInitializer>> annotated = ScrewMod.MODS.get(id).getReflections().getSubTypesOf(ModInitializer.class);
                logger.info(annotated.size());
                for (Class j : annotated) {
                    logger.info(j);
                }
            }
        });
    }
}
