package model;


/**
 * Parent for classes having structure: String + [SomethingAdvancedType]
 */
public abstract class AdvancedString {

    protected final String value;

    public AdvancedString(String value) {
        this.value = value;
    }

    @Override
    public abstract boolean equals(Object o);

    public abstract String getValue();

    public abstract Object getAdvancedField();

}
