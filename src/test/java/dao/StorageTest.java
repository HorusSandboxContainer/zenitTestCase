package dao;

import model.AdvancedString;
import model.ResultDTO;
import model.StringWithLength;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class StorageTest {


    /**
     * public void put(V value)
     */
    @Test
    public void shouldPutWithCorrectlyKey() {
        Storage<AdvancedString> storage = Mockito.spy(new Storage<>());

        for (int i = 0; i < 3; i++) {
            storage.put(any(AdvancedString.class));
        }

        assert storage.containsKey(2);
    }

    /**
     * public Optional<ResultDTO<V>> getDTOByKey(Integer key)
     */
    @Test
    public void shouldReturnCorrectlyDTO() {
        Map<Integer, AdvancedString> tempStorage = new HashMap<>();
        tempStorage.put(0, new StringWithLength("test"));
        Storage<AdvancedString> storage = Mockito.spy(new Storage<>());
        ResultDTO<AdvancedString> standard = new ResultDTO<>(0, new StringWithLength("test"));


        when(storage.entrySet()).thenReturn(tempStorage.entrySet());

        assert storage.getDTOByKey(0).isPresent();
        assert storage.getDTOByKey(0).get().equals(standard);

    }
}