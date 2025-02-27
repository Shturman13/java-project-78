package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {
    final void addPredicate(String key, Predicate<String> predicate) {
        check.put(key, predicate);
    }

    /**
     * required.
     * form Predicate<String> which check that data cannot be null
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return StringSchema to have possibility to put in chain
     */
    public StringSchema required() {
        Predicate<String> required = stringToValidate -> stringToValidate != null && !stringToValidate.isEmpty();
        addPredicate("required", required);
        return this;
    }

    /**
     * minLength.
     * form Predicate<String> which check that data
     * must be minimum length
     * @param length minimum string length
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return StringSchema to have possibility to put in chain
     */
    public StringSchema minLength(Integer length) {
        Predicate<String> minLength = stringToValidate ->
                stringToValidate != null && stringToValidate.length() >= length;
        addPredicate("minLength", minLength);
        return this;
    }

    /**
     * contains.
     * form Predicate<String> which check that data
     * @param containString string must contain it
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return StringSchema to have possibility to put in chain
     */
    public StringSchema contains(String containString) {
        Predicate<String> contains = stringToValidate ->
                stringToValidate != null && stringToValidate.contains(containString);
        addPredicate("contains", contains);
        return this;
    }
}
