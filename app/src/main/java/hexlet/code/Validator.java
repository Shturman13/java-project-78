package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    /**
     * string.
     * upon call string()
     * @return new object StringSchema
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * number.
     * upon call number()
     * @return new object NumberSchema
     */
    public NumberSchema number() {
        return new NumberSchema();
    }

    /**
     * map.
     * upon call map()
     * @return new object MapSchema
     */
    public MapSchema map() {
        return new MapSchema();
    }

}
