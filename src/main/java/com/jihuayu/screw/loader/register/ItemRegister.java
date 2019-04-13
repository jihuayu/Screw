package com.jihuayu.screw.loader.register;

import com.jihuayu.screw.annotation.ModId;
import com.jihuayu.screw.annotation.register.RegItem;
import com.jihuayu.screw.util.NameUtil;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public interface ItemRegister {
    static void regItem(Item item, RegItem regItem, ModId modId){
        Registry.register(Registry.ITEM,new Identifier(modId==null?"screw":modId.value(), NameUtil.buildRegistryName(regItem.value())),item);
    }
}
