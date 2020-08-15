package model;

import java.util.Objects;

public class StringWithLength extends AdvancedString {

    private final int length;

    public StringWithLength(String value) {
        super(value);
        this.length = value.length();
    }

    public String getValue() {
        return value;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringWithLength that = (StringWithLength) o;
        return length == that.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length);
    }
}
