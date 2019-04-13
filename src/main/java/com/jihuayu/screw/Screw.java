package com.jihuayu.screw;

import com.google.common.eventbus.EventBus;
import com.jihuayu.screw.annotation.Load;
import com.jihuayu.screw.loader.ModLoader;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

public class Screw implements ModInitializer {

    public static final EventBus EVENT_BUS = new EventBus("Screw");
    public static final Reflections modReflections = new Reflections();
    private static Logger logger = LogManager.getLogger("Screw");

    public static Logger getLogger(){
        return logger;
    }

    @Override
    public void onInitialize() {
        logger.info("Hello Screw!");
        ModLoader.loadJson();
        Set<Class<? extends ModInitializer>> subc = modReflections.getSubTypesOf(ModInitializer.class);
        logger.info(subc.size());
        subc.forEach(i->{
            logger.info(i.getName());
            logger.info(i.getModifiers());
        });
        Set<Method> subTypes = modReflections.getMethodsAnnotatedWith(Load.class);
        subTypes.forEach(i->{
            logger.info(i.getName());
            logger.info(i.getModifiers());
            if((i.getModifiers()&(Modifier.STATIC+Modifier.PUBLIC))==(Modifier.STATIC+Modifier.PUBLIC)){
                try {
                    i.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
