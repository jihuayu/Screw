package com.jihuayu.test;

import com.jihuayu.screw.annotation.Load;
import com.jihuayu.screw.annotation.ModId;
import com.jihuayu.screw.annotation.register.RegItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

@ModId("screw")
public interface TestMod {
    @RegItem({"screw","bbbbb"})
    Item i = new Item(new Item.Settings().itemGroup(ItemGroup.FOOD));
    @Load
    static void t(){
        System.out.println(1);
    }
}
