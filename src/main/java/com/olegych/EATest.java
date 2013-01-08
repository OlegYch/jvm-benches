package com.olegych;

import com.google.caliper.SimpleBenchmark;
import com.google.common.collect.Lists;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Iterator;

/**
 */
public class EATest extends SimpleBenchmark {
    final ArrayList<Object> objects = Lists.newArrayList();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        for (int i = 0; i < 1000; i++) {
            objects.add(i);
        }
    }

    public Object timeWithEscape(int reps) {
        for (int i = 0; i < reps; i++) {
            doWithEscape();
        }
        return null;
    }

    private boolean enableEscape = true;

    private Object doWithEscape() {
        final Object[] objects1 = new Object[3];
        Object a = null;
        Object object = null;
        Object o = null;
        for (Iterator<Object> iterator = objects.iterator(); iterator.hasNext(); ) {
            object = iterator.next();
            for (Iterator<Object> iterator1 = objects.iterator(); iterator1.hasNext(); ) {
                o = iterator1.next();
                a = new Object();
                if (enableEscape) {
                    objects1[0] = a;
                    objects1[1] = object;
                    objects1[2] = o;
                }
            }
        }
        return a;
    }

    public Object timeWithEscapeAll(int reps) {
        for (int i = 0; i < reps; i++) {
            doWithEscapeAll();
        }
        return null;
    }

    final Object[] objects1 = new Object[3];

    private Object doWithEscapeAll() {
        Object a = null;
        Object object = null;
        Object o = null;
        for (Iterator<Object> iterator = objects.iterator(); iterator.hasNext(); ) {
            object = iterator.next();
            for (Iterator<Object> iterator1 = objects.iterator(); iterator1.hasNext(); ) {
                o = iterator1.next();
                a = new Object();
                if (enableEscape) {
                    objects1[0] = a;
                    objects1[1] = object;
                    objects1[2] = o;
                }
            }
        }
        return objects1;
    }

    public Object timeWithNoEscape(int reps) {
        for (int i = 0; i < reps; i++) {
            doWithNoEscape();
        }
        return null;
    }

    private Object doWithNoEscape() {
        final Object[] objects1 = new Object[3];
        Object a = null;
        Object object = null;
        Object o = null;
        for (Iterator<Object> iterator = objects.iterator(); iterator.hasNext(); ) {
            object = iterator.next();
            for (Iterator<Object> iterator1 = objects.iterator(); iterator1.hasNext(); ) {
                o = iterator1.next();
                a = new Object();
                if (enableEscape) {
                    objects1[0] = a;
                    objects1[1] = object;
                    objects1[2] = o;
                }
            }
        }
        System.out.println("objects1[0] = " + objects1[0]);
        System.out.println("objects1[0] = " + objects1[1]);
        System.out.println("objects1[0] = " + objects1[2]);
        return null;
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
