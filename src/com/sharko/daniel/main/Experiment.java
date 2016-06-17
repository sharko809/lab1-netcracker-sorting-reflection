package com.sharko.daniel.main;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.Reflections;

import com.sharko.daniel.array.ArrayFactory;
import com.sharko.daniel.array.ArrayFillingType;
import com.sharko.daniel.array.ArrayType;
import com.sharko.daniel.service.Service;
import com.sharko.daniel.sort.Sorter;
import com.sharko.daniel.sort.SorterClass;

/**
 * 
 * @author Sharko Daniel
 *         <p>
 *         Class for executing experiment.
 *         </p>
 */
public class Experiment {

	Experiment() {
	}

	ArrayFactory arrayFactory;

	Reflections reflections = new Reflections("com.sharko.daniel.sort");
	Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(SorterClass.class);
	// ^ this returns all classes from "com.sharko.daniel.sort" with
	// @SorterClass annotation

	/**
	 * Starts the experiment. The experiment will be held as much times, as
	 * specified in <b>exNumber</b> parameter.
	 * 
	 * @param exNumber
	 *            how much times experiment will be held
	 * @param fillingTypes
	 *            array filling types
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 * @throws NoSuchFieldException
	 */
	protected void startExperiment(int exNumber, Field[] fillingTypes) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, InvocationTargetException, NoSuchFieldException, IOException {
		for (int i = 0; i < exNumber; i++) {
			experiment(fillingTypes);
		}
	}

	private void experiment(Field[] fillingTypes) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException {

		arrayFactory = new ArrayFactory(Service.arrayElementsNumber());
		// goes through all classes with @SorterClass annotation
		for (Class<?> class1 : annotated) {
			Object obj1 = class1.newInstance();
			// goes through all methods with @Sorter annotation
			for (Method method : class1.getDeclaredMethods()) {
				if (method.getAnnotation(Sorter.class) != null) {
					// goes through all fields with @ArrayFillingTypes annotations
					for (Field field : fillingTypes) {
						if (field.getAnnotation(ArrayFillingType.class) != null) {
							long t1 = System.nanoTime();// starts timer
							method.invoke(obj1, arrayFactory.getArray((ArrayType) field.get(ArrayType.class)));// invoke sorting
							long elapsedTime = System.nanoTime() - t1;// ends timer
							MyExcelUtils.toExcelWriterXLSX(field.getName(), arrayFactory.getArrayLength(), elapsedTime,
									annotated);
						}
					}
				}
			}
		}
	}

}
