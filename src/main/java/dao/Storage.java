package dao;

import model.AdvancedString;
import model.ResultDTO;

import java.util.HashMap;
import java.util.Optional;

public class Storage<V extends AdvancedString> extends HashMap<Integer, V> {

    public void put(V value) {
        super.put(super.size(), value);
    }

    public Optional<ResultDTO> getDTOByKey(Integer key) {
        Optional<ResultDTO> resultDTO = Optional.empty();
        for (Entry<Integer, V> entry : super.entrySet()) {
            if (entry.getKey().equals(key)) {
                resultDTO = Optional.of(new ResultDTO(key, entry.getValue()));
                return resultDTO;
            }
        }
        return resultDTO;
    }
}
