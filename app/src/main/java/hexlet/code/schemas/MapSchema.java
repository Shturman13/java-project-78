package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema<T, S> extends BaseSchema<Map<T, T>> {
    public void addPredicate(String key, Predicate<Map<T, T>> predicate) {
        check.put(key, predicate);
    }

    public MapSchema<T, S> required() {
        Predicate<Map<T, T>> required = Objects::nonNull;
        addPredicate("required", required);
        return this;
    }

    public MapSchema<T, S> sizeof(Integer numberOfElements) {
        Predicate<Map<T, T>> sizeof = mapToValidate ->
                mapToValidate != null && mapToValidate.size() == numberOfElements;
        addPredicate("sizeof", sizeof);
        return this;
    }

    public MapSchema<T, S> shape(Map<T, BaseSchema<T>> schema) {
        Predicate<Map<T, T>> shape = detailsMapToValidate -> {
            var values = new ArrayList<Boolean>();
            detailsMapToValidate.forEach((key, value) -> {
                if (schema.containsKey(key)) {
                    values.add(schema.get(key).isValid(value));
                }
            });
            return !values.contains(false);
        };
        addPredicate("shape", shape);
        return this;
    }
}
