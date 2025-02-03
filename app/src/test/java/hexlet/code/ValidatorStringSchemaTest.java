package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorStringSchemaTest {
    public static StringSchema newValidatorObject() {
        Validator v = new Validator();
        return v.string();
    }

    @Test
    public void testStringSchema() {
        var actualInitial = newValidatorObject().isValid("");
        var expectedInitial = true;
        assertThat(actualInitial).isEqualTo(expectedInitial);

        var actualAfterRequired = newValidatorObject().required().isValid("");
        var expectedAfterRequired = false;
        assertThat(actualAfterRequired).isEqualTo(expectedAfterRequired);

        var actualAfterMinLengthT = newValidatorObject().minLength(3).isValid("what the f");
        var expectedAfterMinLengthT = true;
        assertThat(actualAfterMinLengthT).isEqualTo(expectedAfterMinLengthT);

        var actualAfterMinLengthF = newValidatorObject().minLength(10).isValid("what f");
        var expectedAfterMinLengthF = false;
        assertThat(actualAfterMinLengthF).isEqualTo(expectedAfterMinLengthF);

        var actualAfterContainsT = newValidatorObject().contains("what").isValid("what the f");
        var expectedAfterContainsT = true;
        assertThat(actualAfterContainsT).isEqualTo(expectedAfterContainsT);

        var actualAfterContainsF = newValidatorObject().contains("whatth").isValid("what the f");
        var expectedAfterContainsF = false;
        assertThat(actualAfterContainsF).isEqualTo(expectedAfterContainsF);

        var actualComplexValueT = newValidatorObject().required().minLength(10)
                .minLength(4).contains("wha").isValid("what the f");
        var expectedComplexValueT = true;
        assertThat(actualComplexValueT).isEqualTo(expectedComplexValueT);

        var actualComplexValueF = newValidatorObject().required().minLength(10)
                .minLength(4).contains("whatth").isValid("what the f");
        var expectedComplexValueF = false;
        assertThat(actualComplexValueF).isEqualTo(expectedComplexValueF);

    }
}
