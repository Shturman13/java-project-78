package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {
    public void addPredicate(String key, Predicate<String> predicate) {
        check.put(key, predicate);
    }

    public StringSchema required() {
        Predicate<String> required = stringToValidate -> stringToValidate != null && !stringToValidate.isEmpty();
        addPredicate("required", required);
        return this;
    }

    public StringSchema minLength(Integer length) {
        Predicate<String> minLength = stringToValidate ->
                stringToValidate != null && stringToValidate.length() >= length;
        addPredicate("required", minLength);
        return this;
    }

    public StringSchema contains(String containString) {
        Predicate<String> contains = stringToValidate ->
                stringToValidate != null && stringToValidate.contains(containString);
        addPredicate("contains", contains);
        return this;
    }
}
