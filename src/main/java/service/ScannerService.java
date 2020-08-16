package service;

import config.PropertiesConfig;
import model.AdvancedString;

import java.lang.reflect.Constructor;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Service responsibilities:
 * Scan InputStream to String[] by \n delimiter
 * Prepare String[] to store into Storage
 * Parse String of positions to int[]
 */
public class ScannerService {

    private final Scanner scanner;

    public ScannerService(Scanner scanner) {
        this.scanner = scanner;
    }


    /**
     * @param countOfStringMustBeRead - how much string must be read from InputStream
     * @return String[] - array of scanned strings
     * @throws NoSuchElementException - throw with exception when countOfStringMustBeRead more then
     *                                real count of string in input stream
     */
    public String[] scanStringsFromStream(int countOfStringMustBeRead) throws NoSuchElementException {
        String[] buffer = new String[countOfStringMustBeRead];
        for (int i = 0; i < countOfStringMustBeRead; i++) {
            buffer[i] = scanner.nextLine();
        }
        return buffer;
    }

    /**
     * @param data                 - unwrapped string needing to store
     * @param typeOfAdvancedString - class of AdvancedString children
     * @param <V>                  - type of AdvancedString children
     * @return AdvancedString[] - array of wrapped into V type strings
     */
    public <V extends AdvancedString> AdvancedString[] prepareDataToStore(String data, Class<V> typeOfAdvancedString) {
        String[] delimitedData = splitStringByDelimiter(data, PropertiesConfig.getProperty("delimiter"));
        return wrapToAdvancedString(delimitedData, typeOfAdvancedString);
    }

    /**
     * @param string - string must be parsed
     * @return int[] - parsed positions by string
     */
    public int[] getPositionsFromString(String string) {
        String[] buffer = splitStringByDelimiter(string, PropertiesConfig.getProperty("delimiter"));
        int[] result = new int[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            try {
                result[i] = Integer.parseInt(buffer[i]);
            } catch (NumberFormatException e) {
                System.out.println("String with positions has bad format");
            }
        }
        return result;
    }

    /**
     * @param stringToSplit - string needing to split
     * @param delimiter     - delimiter for split
     * @return String[] - array of substring splinted by delimiter
     */
    private String[] splitStringByDelimiter(String stringToSplit, String delimiter) {
        if (!delimiter.equals("default")) {
            scanner.useDelimiter(delimiter);
        }
        return scanner.delimiter().split(stringToSplit);
    }

    /**
     * @param strings              - unwrapped string needing to store
     * @param typeOfAdvancedString - class of AdvancedString children
     * @param <V>                  - type of AdvancedString children
     * @return AdvancedString[] - array of wrapped into V type strings
     */
    private <V extends AdvancedString> AdvancedString[] wrapToAdvancedString(String[] strings, Class<V> typeOfAdvancedString) {
        AdvancedString[] buffer = new AdvancedString[strings.length];
        for (int i = 0; i < strings.length; i++) {
            Constructor<V> constructor;
            try {
                constructor = typeOfAdvancedString.getDeclaredConstructor(String.class);
                buffer[i] = constructor.newInstance(strings[i]);
            } catch (ReflectiveOperationException e) {
                System.out.println("Error with try convert strings to Advanced Strings");
            }
        }
        return buffer;
    }
}
