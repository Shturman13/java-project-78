package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    public void addPredicate(String key, Predicate<Integer> predicate) {
        check.put(key, predicate);
    }

    public NumberSchema required() {
        Predicate<Integer> required = Objects::nonNull;
        addPredicate("required", required);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> positive = numberToValidate -> numberToValidate > 0;
        addPredicate("positive", positive);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        Predicate<Integer> range = numberToValidate -> numberToValidate >= min && numberToValidate <= max;
        addPredicate("range", range);
        return this;
    }
}
