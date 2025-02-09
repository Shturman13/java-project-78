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
        var expectedInitial = true;
        assertThat(actualInitial).isEqualTo(expectedInitial);

        var actualAfterRequired = newValidatorObject().required().isValid(null);
        var expectedAfterRequired = false;
        assertThat(actualAfterRequired).isEqualTo(expectedAfterRequired);

        var actualAfterPositiveT = newValidatorObject().positive().isValid(3);
        var expectedAfterPositiveT = true;
        assertThat(actualAfterPositiveT).isEqualTo(expectedAfterPositiveT);

        var actualAfterPositiveF = newValidatorObject().positive().isValid(-5);
        var expectedAfterPositiveF = false;
        assertThat(actualAfterPositiveF).isEqualTo(expectedAfterPositiveF);

        var actualAfterRangeT = newValidatorObject().range(5, 10).isValid(7);
        var expectedAfterRangeT = true;
        assertThat(actualAfterRangeT).isEqualTo(expectedAfterRangeT);

        var actualAfterRangeF = newValidatorObject().range(5, 10).isValid(3);
        var expectedAfterRangeF = false;
        assertThat(actualAfterRangeF).isEqualTo(expectedAfterRangeF);

        var actualComplexValueT = newValidatorObject().required().positive()
                .range(3, 5).range(5, 10).isValid(7);
        var expectedComplexValueT = true;
        assertThat(actualComplexValueT).isEqualTo(expectedComplexValueT);

        var actualComplexValueF = newValidatorObject().required().positive()
                .range(2, 5).range(5, 10).isValid(3);
        var expectedComplexValueF = false;
        assertThat(actualComplexValueF).isEqualTo(expectedComplexValueF);

    }
}
