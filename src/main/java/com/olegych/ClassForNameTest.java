package com.olegych;

import com.google.caliper.Param;
import com.google.caliper.SimpleBenchmark;
import com.google.common.collect.Lists;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

/**
 */
public class ClassForNameTest extends SimpleBenchmark {
    final ArrayList<Thread> threads = Lists.newArrayList();

    @Param({"1", "2", "4", "8", "20"})
    public int threadCount;

    public int count = 1000;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        threads.clear();
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        for (int i = 0; i < count / threadCount; i++) {
                            Class.forName("com.olegych.ClassForNameTest");
                        }
                    } catch (ClassNotFoundException e) {
                        throw new Error(e);
                    }
                }
            });
        }
    }

    public Object timeForName(int reps) throws Exception {
        for (int i = 0; i < reps; i++) {
            setUp();
            doForName();
        }
        return null;
    }

    private void doForName() {
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        long totalGarbageCollections = 0;
        long garbageCollectionTime = 0;

        for (GarbageCollectorMXBean gc :
                ManagementFactory.getGarbageCollectorMXBeans()) {

            long count = gc.getCollectionCount();

            if (count >= 0) {
                totalGarbageCollections += count;
            }

            long time = gc.getCollectionTime();

            if (time >= 0) {
                garbageCollectionTime += time;
            }
        }

        System.err.println("Garbage Collections: "
                + (totalGarbageCollections));
        System.err.println("Garbage Collection Time (ms): "
                + (garbageCollectionTime));
    }
}
