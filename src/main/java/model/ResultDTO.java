package model;

/**
 * DTO for concat integer(key of Storage) and AdvancedString child for result output
 */
public class ResultDTO<V extends AdvancedString> {
    private final long id;
    private final AdvancedString stringWithLength;

    public ResultDTO(Integer key, V value) {
        this.id = key;
        this.stringWithLength = value;
    }

    @Override
    public String toString() {
        return "id:" + id + ", value: " + stringWithLength.getValue() + ", length:" + stringWithLength.getAdvancedField();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultDTO<V> resultDTO = (ResultDTO<V>) o;
        return id == resultDTO.id &&
                stringWithLength.equals(resultDTO.stringWithLength);
    }
}
