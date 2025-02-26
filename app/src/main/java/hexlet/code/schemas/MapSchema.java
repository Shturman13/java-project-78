package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public void addPredicate(String key, Predicate<Map<?, ?>> predicate) {
        check.put(key, predicate);
    }

    public MapSchema required() {
        Predicate<Map<?, ?>> required = Objects::nonNull;
        addPredicate("required", required);
        return this;
    }

    public MapSchema sizeof(Integer numberOfElements) {
        Predicate<Map<?, ?>> sizeof = mapToValidate ->
                mapToValidate != null && mapToValidate.size() == numberOfElements;
        addPredicate("sizeof", sizeof);
        return this;
    }

    public <T> MapSchema shape(Map<?, BaseSchema<T>> schema) {
        Predicate<Map<?, ?>> shape = detailsMapToValidate -> {
            var values = new ArrayList<Boolean>();
            detailsMapToValidate.forEach((key, value) -> {
                if (schema.containsKey(key)) {
                    values.add(schema.get(key).isValid((T) value));
                }
            });
            return !values.contains(false);
        };
        addPredicate("shape", shape);
        return this;
    }
}
