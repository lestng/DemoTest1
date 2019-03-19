package com.jinqu.Testng.multThread;

import org.testng.annotations.Test;

public class InvocationCountDemo {
    @Test(invocationCount = 5,threadPoolSize = 3)
    public void xianCheng(){
        System.out.println(1);
        System.out.printf("thread ID: %s%n ",Thread.currentThread().getId());
    }
}
