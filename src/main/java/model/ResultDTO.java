package model;

import java.util.Objects;

public class ResultDTO {
    private final long id;
    private final StringWithLength stringWithLength;

    public <V extends AdvancedString> ResultDTO(Integer key, V value) {
        this.id = key;
        this.stringWithLength = (StringWithLength) value;
    }

    @Override
    public String toString() {
        return "id:"+id+", value: " + stringWithLength.getValue() + ", length:" + stringWithLength.getLength();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultDTO resultDTO = (ResultDTO) o;
        return id == resultDTO.id &&
                stringWithLength.equals(resultDTO.stringWithLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stringWithLength);
    }
}
