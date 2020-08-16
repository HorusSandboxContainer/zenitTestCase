package service;

import dao.Storage;
import model.AdvancedString;
import model.ResultDTO;

import java.util.Optional;

/**
 * Service responsibilities:
 * Store prepared data (array of children of AdvancedString) to Storage
 * Print data from Storage by positions
 * @param <V> - type of AdvancedString children
 */
public class StorageService<V extends AdvancedString> {
    private final Storage<V> storage;

    public StorageService(Storage<V> storage) {
        this.storage = storage;
    }

    public void storeData(V[] data) {
        for (V datum : data) {
            storage.put(datum);
        }
    }

    /**
     * @param positions - parsed second input string
     */
    public void printDataByPositions(int[] positions) {
        Optional<ResultDTO<V>> dtoOptional;
        for (int position : positions) {
            dtoOptional = storage.getDTOByKey(position);
            dtoOptional.ifPresent(System.out::println);
        }
    }
}
