package com.jihuayu.screw.mixin;

import com.jihuayu.screw.imixin.ITest;

public abstract class TestMixin implements ITest {

    @Override
    public void a() {
        System.out.println(111);
    }
}
