package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorStringSchemaTest {
    public static StringSchema newValidatorObject() {
        Validator v = new Validator();
        return v.string();
    }

    @Test
    public void testStringSchema() {
        var testString = "what the f";

        var actualInitial = newValidatorObject().isValid("");
        assertThat(actualInitial).isEqualTo(true);

        var actualAfterRequired = newValidatorObject().required().isValid("");
        assertThat(actualAfterRequired).isEqualTo(false);

        var actualAfterMinLengthT = newValidatorObject().minLength(3).isValid(testString);
        assertThat(actualAfterMinLengthT).isEqualTo(true);

        var actualAfterMinLengthF = newValidatorObject().minLength(11).isValid(testString);
        assertThat(actualAfterMinLengthF).isEqualTo(false);

        var actualAfterContainsT = newValidatorObject().contains("what").isValid(testString);
        assertThat(actualAfterContainsT).isEqualTo(true);

        var actualAfterContainsF = newValidatorObject().contains("whatth").isValid(testString);
        assertThat(actualAfterContainsF).isEqualTo(false);

        var actualComplexValueT = newValidatorObject().required().minLength(10)
                .minLength(4).contains("wha").isValid(testString);
        assertThat(actualComplexValueT).isEqualTo(true);

        var actualComplexValueF = newValidatorObject().required().minLength(10)
                .minLength(4).contains("whatth").isValid(testString);
        assertThat(actualComplexValueF).isEqualTo(false);
    }
}
