package com.sharko.daniel.service;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import com.sharko.daniel.array.ArrayFillingType;

/**
 * 
 * @author Daniel Sharko
 *
 *         <p>
 *         Service class containing methods for generating numbers, toString,
 *         etc.
 *         </p>
 */
public class Service {

	/**
	 * Divide by this to receive seconds from nanoseconds
	 */
	final static int toSeconds = 1000000000;

	/**
	 * Method for translating array to String.
	 * 
	 * @param array
	 *            An array to be translated to String.
	 * @return An array as a String.
	 */
	public static void toStringArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
	}

	/**
	 * Generates a number to serve as an array length.
	 * 
	 * @return <b>int</b> in range from 500k to 1kk.
	 */
	public static int arrayElementsNumber() {
		Random rand = new Random();
		int n = rand.nextInt(5000) + 10000;
		return n;
	}

	/**
	 * Prints method name and his execution time in seconds to console
	 * 
	 * @param method
	 *            Method which runs
	 * @param elapsedTime
	 *            Method's execution time
	 */
	public static void dispMethodTime(Method method, long elapsedTime) {
		System.out.println("Elapsed time for " + method.getName() + " method: " + Service.nanoToSeconds(elapsedTime)
				+ " seconds.");
		// System.out.println("---");
	}

	/**
	 * Displays number of elements in array and time taken to sort an array.
	 * Result is shown in a convenient way to paste to Excel. Time is shown in
	 * nanoseconds.
	 * 
	 * @param length
	 *            array length
	 * @param elapsedTime
	 *            time elapsed while sorting
	 */
	public static void dispLengthAndTime(int length, long elapsedTime) {
		System.out.println(length + "\t" + elapsedTime);
	}

	/**
	 * Prints time to file.
	 * 
	 * @param writer
	 * @param field
	 * @param elapsedTime
	 * @throws IOException
	 */
	public static void toFileWriter(FileWriter writer, Field field, double elapsedTime) throws IOException {
		writer = new FileWriter(field.getName() + ".xls", true);
		writer.write("\t " + elapsedTime);
		writer.close();
	}

	/**
	 * Prints array length in every file with sorting types. This method goes
	 * through all array filling types (personal file is created for every
	 * filling type).
	 * 
	 * @param writer
	 *            FileWriter used to write to file
	 * @param arrayLength
	 *            array length
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void toFileArrayLengthWriter(FileWriter writer, int arrayLength)
			throws IOException, ClassNotFoundException {
		Class<?> tempClass;
		tempClass = Class.forName("com.sharko.daniel.array.ArrayType");
		Field[] fl = tempClass.getDeclaredFields();
		for (Field field : fl) {
			if (field.getAnnotation(ArrayFillingType.class) != null) {
				toFileArrayLength(writer, arrayLength, field.getName().toString());
			}
		}
	}

	/**
	 * Terminates the line in every file with sorting types. This method goes
	 * through all array filling types (personal file is created for every
	 * filling type).
	 * 
	 * @param writer
	 *            FileWriter used to write to file
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void toFileLineTerminator(FileWriter writer) throws ClassNotFoundException, IOException {
		Class<?> tempClass;
		tempClass = Class.forName("com.sharko.daniel.array.ArrayType");
		Field[] fl = tempClass.getDeclaredFields();
		for (Field field : fl) {
			if (field.getAnnotation(ArrayFillingType.class) != null) {
				lineTerminator(writer, field.getName().toString());
			}
		}
	}

	/**
	 * Converts nanoseconds to seconds
	 * 
	 * @param elapsedTime
	 *            time in nanoseconds
	 * @return Time in seconds
	 */
	public static double nanoToSeconds(long elapsedTime) {
		double seconds = (double) elapsedTime / toSeconds;
		return seconds;
	}

	/**
	 * Terminates line in file.
	 * 
	 * @param writer
	 * @param fileName
	 * @throws IOException
	 */
	private static void lineTerminator(FileWriter writer, String fileName) throws IOException {
		writer = new FileWriter(fileName + ".xls", true);
		writer.write("\n");
		writer.close();
	}

	/**
	 * Prints array length to file.
	 * 
	 * @param writer
	 * @param arrayLength
	 * @param field
	 * @throws IOException
	 */
	private static void toFileArrayLength(FileWriter writer, int arrayLength, String field) throws IOException {
		writer = new FileWriter(field + ".xls", true);
		writer.write(arrayLength + " ");
		writer.close();
	}

}
