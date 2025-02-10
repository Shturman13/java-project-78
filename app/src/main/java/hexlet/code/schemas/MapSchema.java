package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map> {
    public void addPredicate(String key, Predicate<Map> predicate) {
        check.put(key, predicate);
    }

    public MapSchema required() {
        Predicate<Map> required = Objects::nonNull;
        addPredicate("required", required);
        return this;
    }

    public MapSchema sizeof(Integer numberOfElements) {
        Predicate<Map> sizeof = mapToValidate ->
                mapToValidate != null && mapToValidate.size() == numberOfElements;
        addPredicate("sizeof", sizeof);
        return this;
    }
}
