package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {
    public void addPredicate(String key, Predicate<Map<K, V>> predicate) {
        check.put(key, predicate);
    }

    public MapSchema<K, V> required() {
        Predicate<Map<K, V>> required = Objects::nonNull;
        addPredicate("required", required);
        return this;
    }

    public MapSchema<K, V> sizeof(Integer numberOfElements) {
        Predicate<Map<K, V>> sizeof = mapToValidate ->
                mapToValidate != null && mapToValidate.size() == numberOfElements;
        addPredicate("sizeof", sizeof);
        return this;
    }

    public MapSchema<K, V> shape(Map<K, BaseSchema<V>> schema) {
        Predicate<Map<K, V>> shape = detailsMapToValidate -> {
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
