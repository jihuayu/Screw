package com.jihuayu.screw.loader;

import com.jihuayu.screw.Screw;
import com.jihuayu.screw.annotation.Load;
import com.jihuayu.screw.annotation.ModId;
import com.jihuayu.screw.annotation.register.RegItem;
import com.jihuayu.screw.loader.register.ItemRegister;
import net.minecraft.item.Item;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

public interface RegisterLoader {
    @Load
    static void loadItem() {
        Set<Field> items = Screw.modReflections.getFieldsAnnotatedWith(RegItem.class);
        items.forEach(i -> {
            if ((i.getModifiers() & (Modifier.STATIC + Modifier.PUBLIC)) == (Modifier.STATIC + Modifier.PUBLIC)) {
                Object obj = null;
                try {
                    obj = i.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(obj instanceof Item){
                    ItemRegister.regItem((Item)obj,i.getAnnotation(RegItem.class),i.getDeclaringClass().getAnnotation(ModId.class));
                }
            }
        });
    }
}
