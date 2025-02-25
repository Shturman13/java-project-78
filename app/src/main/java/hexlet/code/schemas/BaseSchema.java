package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> check = new HashMap<>();

    public boolean isValid(T valueToValidate) throws IllegalStateException {
        if (check.isEmpty()) {
            return true;
        }
        return check.values().stream().allMatch((predicate -> predicate.test(valueToValidate)));
    }
}
