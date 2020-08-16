package controller;

import config.PropertiesConfig;
import dao.Storage;
import model.AdvancedString;
import model.StringWithLength;
import service.ScannerService;
import service.StorageService;

import java.util.Scanner;

/**
 * Main controller
 * Invoke chain:
 * 1) Create services
 * 2) Load properties
 * 3) Take temporary transfer data by ScannerService
 * 4) Take result with StorageService
 */
public class Application {
    public static void main(String[] args) {
        ScannerService scannerService = new ScannerService(new Scanner(System.in));
        StorageService<AdvancedString> storageService = new StorageService<>(new Storage<>());

        PropertiesConfig.loadProperties("config/application.properties");

        String[] scannedStrings = scannerService.scanStringsFromStream(Integer.parseInt(PropertiesConfig.getProperty("readCount")));

        storageService.storeData(scannerService.prepareDataToStore(scannedStrings[0], StringWithLength.class));
        storageService.printDataByPositions(scannerService.getPositionsFromString(scannedStrings[1]));

    }
}
