package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    final void addPredicate(String key, Predicate<Integer> predicate) {
        check.put(key, predicate);
    }

    /**
     * required.
     * form Predicate<Integer> which check that data cannot be null
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return NumberSchema to have possibility to put in chain
     */
    public NumberSchema required() {
        Predicate<Integer> required = Objects::nonNull;
        addPredicate("required", required);
        return this;
    }

    /**
     * positive.
     * form Predicate<Integer> which check that data must be positive
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return NumberSchema to have possibility to put in chain
     */
    public NumberSchema positive() {
        Predicate<Integer> positive = numberToValidate -> numberToValidate == null || numberToValidate > 0;
        addPredicate("positive", positive);
        return this;
    }

    /**
     * range.
     * form Predicate<Integer> which check that data limits
     * between min and max
     * @param min min number
     * @param max max number
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return NumberSchema to have possibility to put in chain
     */
    public NumberSchema range(Integer min, Integer max) {
        Predicate<Integer> range = numberToValidate ->
                numberToValidate != null && numberToValidate >= min && numberToValidate <= max;
        addPredicate("range", range);
        return this;
    }
}
