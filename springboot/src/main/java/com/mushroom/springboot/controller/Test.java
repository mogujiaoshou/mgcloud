package com.mushroom.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args){

        ExecutorService executorService =
                new ThreadPoolExecutor(5, 20, 60L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(20),
                        new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i= 0;i<11;i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        List<Future<String>> list = new ArrayList<>();
        for (int i= 0;i<11;i++) {
            Future<String> submit = executorService.submit(() -> {
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Success"+Thread.currentThread().getId();
            });
            list.add(submit);
        }

        list.stream().forEach(s -> {
            try{
                System.out.println(s.get(5000,TimeUnit.MILLISECONDS));
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Stream.of(list.toArray()).forEach(s -> {});

        executorService.shutdown();
    }
}
