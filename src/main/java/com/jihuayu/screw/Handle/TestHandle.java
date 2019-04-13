package com.jihuayu.screw.Handle;

import com.google.common.eventbus.Subscribe;
import com.jihuayu.screw.Screw;
import com.jihuayu.screw.event.TestEvent;

public class TestHandle {
    public TestHandle(){
        Screw.EVENT_BUS.register(this);
    }
    @Subscribe
    public void a(TestEvent e){
        System.out.println(e.getA());
    }
}
