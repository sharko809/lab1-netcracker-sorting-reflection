package com.sharko.daniel.main;

import com.sharko.daniel.array.ArrayFactory;
import com.sharko.daniel.array.ArrayType;
import com.sharko.daniel.sort.annotations.Sorter;
import com.sharko.daniel.sort.annotations.SorterClass;
import com.sharko.daniel.util.Util;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * Class for executing experiment.
 */
class Experiment {

    /**
     * This returns all classes from "com.sharko.daniel.sort" with @SorterClass annotation
     */
    private final Set<Class<?>> annotated = new Reflections(Util.SORT_PACKAGE).getTypesAnnotatedWith(SorterClass.class);

    /**
     * Starts the experiment. The experiment will be held as much times, as
     * specified in {@code exNumber} parameter.
     *
     * @param exNumber     how much times experiment will be held
     * @param fillingTypes array filling types
     */
    void startExperiment(int exNumber, ArrayType[] fillingTypes) {
        for (int i = 0; i < exNumber; i++) {
            System.out.println("Experiment #" + (i + 1) + " in progress...");
            ArrayFactory arrayFactory = new ArrayFactory(Util.getArrayElementsNumber);
            experiment(fillingTypes, annotated, arrayFactory);
        }
    }

    private void experiment(ArrayType[] fillingTypes, Set<Class<?>> classes, ArrayFactory arrayFactory) {
        Arrays.stream(fillingTypes).parallel()
                .forEach(field -> classes.stream().parallel()
                        .forEach(clazz -> Arrays.stream(clazz.getDeclaredMethods()).parallel()
                                .filter(method -> method.isAnnotationPresent(Sorter.class))
                                .forEach(method -> runAndWrite(clazz, method, field, arrayFactory, classes))));
    }

    private void runAndWrite(Class clazz, Method method, ArrayType aType, ArrayFactory aFactory, Set<Class<?>> classes) {
        try {
            long t1 = System.nanoTime();
            method.invoke(clazz.newInstance(), (Object) aFactory.getArray(aType));
            long elapsedTime = System.nanoTime() - t1;
            MyExcelUtils.toExcelWriterXLSX(aType.toString(), aFactory.getArrayLength(), elapsedTime, classes);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

}
