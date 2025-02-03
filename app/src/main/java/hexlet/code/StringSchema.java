package hexlet.code;

public class StringSchema {
    private boolean required = false;
    private Integer minLength = null;
    private String contains = null;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(Integer length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String containString) {
        this.contains = containString;
        return this;
    }

    public boolean isValid(String stringToValidate) {
        if (required) {
            if (stringToValidate == null || stringToValidate.isEmpty()) {
                return false;
            }
        }
        if (minLength != null && stringToValidate.length() < minLength) {
            return false;
        }
        return contains == null || stringToValidate.contains(contains);
    }
}
