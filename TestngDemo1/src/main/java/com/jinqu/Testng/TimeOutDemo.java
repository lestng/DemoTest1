package com.jinqu.Testng;

import org.testng.annotations.Test;

//超时测试
public class TimeOutDemo {
    //期望3s内运行，实际2s运行
    @Test(timeOut = 3000)
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }
    //期望2s内运行，实际3s运行，失败
    @Test(timeOut = 2000)
    public void testFailed() throws InterruptedException {
        Thread.sleep(3000);
    }

}
