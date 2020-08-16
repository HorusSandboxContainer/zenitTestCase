package service;

import dao.Storage;
import model.AdvancedString;
import model.ResultDTO;
import model.StringWithLength;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class StorageServiceTest {

    @Mock
    private Storage<AdvancedString> storageMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * public void storeData(V[] data)
     */
    @Test
    public void shouldPutIntoStorageThreeElements() {
        AdvancedString[] testData = new AdvancedString[]{
                new StringWithLength("first"),
                new StringWithLength("second"),
                new StringWithLength("third")
        };
        StorageService<AdvancedString> storageService = new StorageService<>(storageMock);

        storageService.storeData(testData);

        verify(storageMock, times(3)).put(any(AdvancedString.class));
    }

    /**
     * public void shouldReturnedCorrectlyDataInOutputStream()
     */
    @Test
    public void shouldReturnedCorrectlyDataInOutputStream() {
        StorageService<AdvancedString> storageService = new StorageService<>(storageMock);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        PrintStream printStreamMock = Mockito.spy(new PrintStream(new ByteArrayOutputStream()));
        System.setOut(printStreamMock);
        Optional<ResultDTO<AdvancedString>> standard = Optional.of(new ResultDTO<>(3, new StringWithLength("testString")));

        when(storageMock.getDTOByKey(anyInt())).thenReturn(standard);
        storageService.printDataByPositions(new int[]{3});
        verify(printStreamMock).print(argumentCaptor.capture());


        assert standard.toString().contains(argumentCaptor.getValue());

    }

}