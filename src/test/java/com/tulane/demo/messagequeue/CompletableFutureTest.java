package com.tulane.demo.messagequeue;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * Created by Tulane
 * 2019/11/15
 */
public class CompletableFutureTest {

    private Long startTime;

    @Before
    public void before(){
        startTime = System.currentTimeMillis();
    }

    @After
    public void after(){
        System.out.println("完成耗时:" + getBetweenTime());
    }

    @Test
    public void sync(){
        transfer();
        add();
    }

    @Test
    public void async(){
        CompletableFuture.runAsync(() -> {
            transfer();
        }).thenCompose(v -> CompletableFuture.runAsync(() -> {
            add();
        }));
    }


    private void transfer(){
        try {
            Thread.sleep(2000);
            System.out.println("transfer耗时:" + getBetweenTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void add(){
        try {
            Thread.sleep(5000);
            System.out.println("add耗时:" + getBetweenTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Long getBetweenTime(){
        return System.currentTimeMillis() - startTime;
    }

}
