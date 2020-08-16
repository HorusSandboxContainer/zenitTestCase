package service;

import config.PropertiesConfig;
import model.AdvancedString;
import model.StringWithLength;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ScannerServiceTest {


    /**
     * public String[] scanStringsFromStream(int countOfStringMustBeRead) throws NoSuchElementException
     */
    @Test
    public void shouldReturnThreeStrings() {
        ScannerService scannerService = new ScannerService(
                new Scanner(new ByteArrayInputStream("one\ntwo\nthree".getBytes())));

        String[] buffer = scannerService.scanStringsFromStream(3);

        assert buffer.length == 3;
    }

    /**
     * public String[] scanStringsFromStream(int countOfStringMustBeRead) throws NoSuchElementException
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementException() {
        ScannerService scannerService = new ScannerService(
                new Scanner(new ByteArrayInputStream("one\ntwo\nthree".getBytes())));

        scannerService.scanStringsFromStream(5);
    }

    /**
     * public <V extends AdvancedString> AdvancedString[] prepareDataToStore(String data, Class<V> typeOfAdvancedString)
     */
    @Test
    public void shouldCorrectlySplitAndWrapRawDataToAdvancedString() {
        ScannerService scannerService = new ScannerService(new Scanner(System.in));
        PropertiesConfig.loadProperties("config/application.properties");
        String rawData = "one two three";
        AdvancedString[] standardWrappedData = new StringWithLength[]{
                new StringWithLength("one"),
                new StringWithLength("two"),
                new StringWithLength("three")
        };

        AdvancedString[] resultWrappedData = scannerService.prepareDataToStore(rawData, StringWithLength.class);

        for (int i = 0; i < resultWrappedData.length; i++) {
            assert resultWrappedData[i].equals(standardWrappedData[i]);
        }
    }

    /**
     * public int[] getPositionsFromString(String string)
     */
    @Test
    public void shouldCorrectlySplitAndParseStringWithPositions() {
        ScannerService scannerService = new ScannerService(new Scanner(System.in));
        PropertiesConfig.loadProperties("config/application.properties");
        String positionsString = "0 3 5";
        int[] standard = new int[]{0, 3, 5};

        int[] result = scannerService.getPositionsFromString(positionsString);

        for (int i = 0; i < result.length; i++) {
            assert result[i] == standard[i];
        }
    }


}