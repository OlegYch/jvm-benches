package com.olegych;

import com.google.caliper.SimpleBenchmark;
import com.google.common.collect.Lists;

import java.util.ArrayList;

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

    private Object doWithEscape() {
        Object a = null;
        for (Object object : objects) {
            for (Object o : objects) {
                a = new String();
            }
        }
        return a;
    }

    public Object timeWithNoEscape(int reps) {
        for (int i = 0; i < reps; i++) {
            doWithNoEscape();
        }
        return null;
    }

    private Object doWithNoEscape() {
        for (Object object : objects) {
            for (Object o : objects) {
                Object a = null;
                a = new String();
            }
        }
        return null;
    }
}
