package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> check = new LinkedHashMap<>();

    public boolean isValid(T valueToValidate) throws IllegalStateException {
        if (check.isEmpty()) {
            return true;
        }

        if (check.containsKey("required") && valueToValidate == null) {
            return false;
        }
        return check.values().stream().allMatch((predicate -> predicate.test(valueToValidate)));
    }
}
