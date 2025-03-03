package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    final void addPredicate(String key, Predicate<Map<?, ?>> predicate) {
        check.put(key, predicate);
    }

    /**
     * required.
     * form Predicate Map<?, ?>> which check that data cannot be null
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return MapSchema to have possibility to put in chain
     */
    public MapSchema required() {
        Predicate<Map<?, ?>> required = Objects::nonNull;
        addPredicate("required", required);
        return this;
    }

    /**
     * sizeof.
     * form Predicate Map<?, ?>> which check that data should have
     * certain amount of elements
     * @param numberOfElements should have certain numbers of elements
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return MapSchema to have possibility to put in chain
     */
    public MapSchema sizeof(Integer numberOfElements) {
        Predicate<Map<?, ?>> sizeof = mapToValidate ->
                mapToValidate != null && mapToValidate.size() == numberOfElements;
        addPredicate("sizeof", sizeof);
        return this;
    }

    /**
     * shape.
     * form Predicate Map<?, ?>> which check that
     * detailsMapToValidate isValid for provided @param schema
     * @param <T> type of data can be used in method
     * @param schema schema which must be validated
     * add Predicate to Map<String, Predicate<T>> check in BaseSchema
     * @return MapSchema to have possibility to put in chain
     */
    public <T> MapSchema shape(Map<?, BaseSchema<T>> schema) {
        Predicate<Map<?, ?>> shape = detailedMapToValidate -> detailedMapToValidate.entrySet()
                .stream().allMatch(pair -> {
                    BaseSchema<T> baseSchema = schema.get(pair.getKey());
                    if (baseSchema == null) {
                        return true;
                    }
                    T value = (T) pair.getValue();
                    return baseSchema.isValid(value);
                });
        addPredicate("shape", shape);
        return this;
    }
}
