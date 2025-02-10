package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorMapSchemaTest {
    public static MapSchema newValidatorObject() {
        Validator v = new Validator();
        return v.map();
    }

    @Test
    public void testMapSchema() {
        var actualInitial = newValidatorObject().isValid(null);
        var expectedInitial = true;
        assertThat(actualInitial).isEqualTo(expectedInitial);

        var actualAfterRequired = newValidatorObject().required().isValid(null);
        var expectedAfterRequired = false;
        assertThat(actualAfterRequired).isEqualTo(expectedAfterRequired);

        var actualAfterPositiveT = newValidatorObject().sizeof(1).isValid(Map.of("srting", 3));
        var expectedAfterPositiveT = true;
        assertThat(actualAfterPositiveT).isEqualTo(expectedAfterPositiveT);

        var actualAfterPositiveF = newValidatorObject().sizeof(1).isValid(Map.of("string", 3, "another String", 4));
        var expectedAfterPositiveF = false;
        assertThat(actualAfterPositiveF).isEqualTo(expectedAfterPositiveF);

        var actualComplexValueT = newValidatorObject().required().sizeof(2)
               .isValid(Map.of("String", 4, "another String", 2));
        var expectedComplexValueT = true;
        assertThat(actualComplexValueT).isEqualTo(expectedComplexValueT);

        var actualComplexValueF = newValidatorObject().required().sizeof(3)
                .isValid(Map.of("String", 4, "another String", 2));
        var expectedComplexValueF = false;
        assertThat(actualComplexValueF).isEqualTo(expectedComplexValueF);

    }
}
