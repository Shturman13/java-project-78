package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorNumberSchemaTest {
    public static NumberSchema newValidatorObject() {
        Validator v = new Validator();
        return v.number();
    }

    @Test
    public void testNumberSchema() {
        var actualInitial = newValidatorObject().isValid(null);
        assertThat(actualInitial).isEqualTo(true);

        var actualAfterRequired = newValidatorObject().required().isValid(null);
        assertThat(actualAfterRequired).isEqualTo(false);

        var actualAfterPositiveT = newValidatorObject().positive().isValid(3);
        assertThat(actualAfterPositiveT).isEqualTo(true);

        var actualAfterPositiveF = newValidatorObject().positive().isValid(-5);
        assertThat(actualAfterPositiveF).isEqualTo(false);

        var actualAfterRangeT = newValidatorObject().range(5, 10).isValid(7);
        assertThat(actualAfterRangeT).isEqualTo(true);

        var actualAfterRangeF = newValidatorObject().range(5, 10).isValid(3);
        assertThat(actualAfterRangeF).isEqualTo(false);

        var actualComplexValueT = newValidatorObject().required().positive()
                .range(3, 5).range(5, 10).isValid(7);
        assertThat(actualComplexValueT).isEqualTo(true);

        var actualComplexValueF = newValidatorObject().required().positive()
                .range(2, 5).range(5, 10).isValid(3);
        assertThat(actualComplexValueF).isEqualTo(false);

    }
}
