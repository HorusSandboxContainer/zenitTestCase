package model;


public abstract class AdvancedString {

    protected final String value;

    public AdvancedString(String value) {
        this.value = value;
    }

    @Override
    public abstract boolean equals(Object o);

}
