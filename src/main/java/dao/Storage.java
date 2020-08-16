package dao;

import model.AdvancedString;
import model.ResultDTO;

import java.util.HashMap;
import java.util.Optional;

/**
 * Class realization very closed with HashMap, but you not needed to put pair <Key,Value>
 * Key automatic set by size of collection (because it's mean natural order of element putting)
 *
 * @param <V> type of AdvancedString children
 */
public class Storage<V extends AdvancedString> extends HashMap<Integer, V> {

    public void put(V value) {
        super.put(super.size(), value);
    }

    public Optional<ResultDTO<V>> getDTOByKey(Integer key) {
        Optional<ResultDTO<V>> resultDTO = Optional.empty();
        for (Entry<Integer, V> entry : entrySet()) {
            if (entry.getKey().equals(key)) {
                resultDTO = Optional.of(new ResultDTO<>(key, entry.getValue()));
                return resultDTO;
            }
        }
        return resultDTO;
    }
}
