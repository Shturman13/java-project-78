package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorMapSchemaTest {
    @Test
    public void testMapSchema() {
        var v = new Validator().map();

        var actualInitial = v.isValid(null);
        assertThat(actualInitial).isEqualTo(true);

        var actualAfterRequired = v.required().isValid(null);
        assertThat(actualAfterRequired).isEqualTo(false);

        var actualAfterRequiredNewMap = v.required().isValid(new HashMap<>());
        assertThat(actualAfterRequiredNewMap).isEqualTo(true);

        var actualAfterPositiveT = v.sizeof(1).isValid(Map.of("string", 3));
        assertThat(actualAfterPositiveT).isEqualTo(true);

        var actualAfterPositiveF = v.sizeof(1).isValid(Map.of("string", 3, "another String", 4));
        assertThat(actualAfterPositiveF).isEqualTo(false);

        var actualComplexValueT = v.required().sizeof(2)
               .isValid(Map.of("String", 4, "another String", 2));
        assertThat(actualComplexValueT).isEqualTo(true);

        var actualComplexValueF = v.required().sizeof(3)
                .isValid(Map.of("String", 4, "another String", 2));
        assertThat(actualComplexValueF).isEqualTo(false);
    }

    @Test
    public void testMapDataSchemaString() {
        var staticTestMap1 = Map.of("firstName", "Ivan", "lastName", "Smith");
        var staticTestMap2 = Map.of("firstName", "Dmitrii", "lastName", "A");
        var staticTestMap3 = new HashMap<String, String>();
        staticTestMap3.put("firstName", "Muslimin");
        staticTestMap3.put("lastName", null);
        var staticTestMap4 = Map.of("firstName", "Dm", "lastName", "Smithson");
        var staticTestMap5 = Map.of("firstName", "Dmitrii", "middleName", "Nikolay");

        var v = new Validator();
        var vMap = v.map();

        Map<String, BaseSchema<String>> staticSchema1 = Map.of("firstName", v.string(), "lastName", v.string());
        Map<String, BaseSchema<String>> staticSchema2 = Map.of("firstName", v.string().required(),
                "lastName", v.string().required().minLength(3));
        Map<String, BaseSchema<String>> staticSchema3 = Map.of("firstName",
                v.string().required().minLength(4).contains("trii"), "lastName", v.string().required());
        Map<String, BaseSchema<String>> staticSchema4 = Map.of("firstName",
                v.string().minLength(3), "lastName", v.string());

        var actualInitial = vMap.shape(staticSchema1).isValid(staticTestMap1);
        assertThat(actualInitial).isEqualTo(true);

        var actualRequiredRequiredMinLengthT = vMap.shape(staticSchema2).isValid(staticTestMap1);
        assertThat(actualRequiredRequiredMinLengthT).isEqualTo(true);

        var actualRequiredRequiredMinLengthF = vMap.shape(staticSchema2).isValid(staticTestMap2);
        assertThat(actualRequiredRequiredMinLengthF).isEqualTo(false);

        var actualForNullT = vMap.shape(staticSchema1).isValid(staticTestMap3);
        assertThat(actualForNullT).isEqualTo(true);

        var actualForNullF = vMap.shape(staticSchema2).isValid(staticTestMap3);
        assertThat(actualForNullF).isEqualTo(false);

        var actualShortFirstNameT = vMap.shape(staticSchema2).isValid(staticTestMap4);
        assertThat(actualShortFirstNameT).isEqualTo(true);

        var actualShortFirstNameF = vMap.shape(staticSchema4).isValid(staticTestMap4);
        assertThat(actualShortFirstNameF).isEqualTo(false);

        var actualContainsStringT = vMap.shape(staticSchema3).isValid(staticTestMap2);
        assertThat(actualContainsStringT).isEqualTo(true);

        var actualKeyNotExistT = vMap.shape(staticSchema3).isValid(staticTestMap5);
        assertThat(actualKeyNotExistT).isEqualTo(true);

    }

    @Test
    public void testMapDataSchemaNumber() {
        var staticTestMap1 = Map.of(1, 4, 2, 5);
        var staticTestMap2 = Map.of(10, 15, 11, 20);
        var staticTestMap3 = new HashMap<Integer, Integer>();
        staticTestMap3.put(10, 25);
        staticTestMap3.put(11, null);
        var staticTestMap4 = Map.of(1000, -3, 1001, 4);
        var staticTestMap5 = Map.of(50, 25, 10, 20);

        var v = new Validator();
        var vMapInteger = v.map();

        Map<Integer, BaseSchema<Integer>> staticSchema1 = Map.of(1, v.number(), 2, v.number());
        Map<Integer, BaseSchema<Integer>> staticSchema2 = Map.of(10, v.number().required(),
                11, v.number().required().positive());
        Map<Integer, BaseSchema<Integer>> staticSchema3 = Map.of(10, v.number().required().range(16, 30),
                11, v.number().required());
        Map<Integer, BaseSchema<Integer>> staticSchema31 = Map.of(10, v.number().required().range(16, 30),
                11, v.number());
        Map<Integer, BaseSchema<Integer>> staticSchema4 = Map.of(1000, v.number().positive(),
                1001, v.number().range(2, 5));

        var actualInitial = vMapInteger.shape(staticSchema1).isValid(staticTestMap1);
        assertThat(actualInitial).isEqualTo(true);

        var actualRequiredPositiveT = vMapInteger.shape(staticSchema2).isValid(staticTestMap2);
        assertThat(actualRequiredPositiveT).isEqualTo(true);

        var actualRequiredPositiveF = vMapInteger.shape(staticSchema3).isValid(staticTestMap2);
        assertThat(actualRequiredPositiveF).isEqualTo(false);

        var actualRangeRequiredT = vMapInteger.shape(staticSchema31).isValid(staticTestMap3);
        assertThat(actualRangeRequiredT).isEqualTo(true);

        var actualRangeRequiredF = vMapInteger.shape(staticSchema3).isValid(staticTestMap3);
        assertThat(actualRangeRequiredF).isEqualTo(false);

        var actualPositiveRangeF = vMapInteger.shape(staticSchema4).isValid(staticTestMap4);
        assertThat(actualPositiveRangeF).isEqualTo(false);

        var actualKeyNotExistT = vMapInteger.shape(staticSchema3).isValid(staticTestMap5);
        assertThat(actualKeyNotExistT).isEqualTo(true);

    }

    @Test
    public void testMapDataSchemaStringNumber() {
        var staticTestMap1 = Map.of("firstNumber", 4, "secondNumber", 5);
        var staticTestMap2 = Map.of("firstNumber", 15, "secondNumber", 20);
        var staticTestMap3 = new HashMap<String, Integer>();
        staticTestMap3.put("firstNumber", 25);
        staticTestMap3.put("secondNumber", null);
        var staticTestMap4 = Map.of("firstNumber", -3, "secondNumber", 4);
        var staticTestMap5 = Map.of("firstNumber", 25, "thirdNumber", 20);

        var v = new Validator();
        var vMapStringInteger = v.map();

        Map<String, BaseSchema<Integer>> staticSchema1 = Map.of("firstNumber", v.number(), "secondNumber", v.number());
        Map<String, BaseSchema<Integer>> staticSchema2 = Map.of("firstNumber", v.number().required(),
                "secondNumber", v.number().required().positive());
        Map<String, BaseSchema<Integer>> staticSchema3 = Map.of("firstNumber", v.number().required().range(16, 30),
                "secondNumber", v.number().required());
        Map<String, BaseSchema<Integer>> staticSchema31 = Map.of("firstNumber", v.number().required().range(16, 30),
                "secondNumber", v.number());
        Map<String, BaseSchema<Integer>> staticSchema4 = Map.of("firstNumber", v.number().positive(),
                "secondNumber", v.number().range(2, 5));

        var actualInitial = vMapStringInteger.shape(staticSchema1).isValid(staticTestMap1);
        assertThat(actualInitial).isEqualTo(true);

        var actualRequiredPositiveT = vMapStringInteger.shape(staticSchema2).isValid(staticTestMap2);
        assertThat(actualRequiredPositiveT).isEqualTo(true);

        var actualRangeRequiredF = vMapStringInteger.shape(staticSchema3).isValid(staticTestMap3);
        assertThat(actualRangeRequiredF).isEqualTo(false);

        var actualRangeT = vMapStringInteger.shape(staticSchema31).isValid(staticTestMap3);
        assertThat(actualRangeT).isEqualTo(true);

        var actualPositiveRangeF = vMapStringInteger.shape(staticSchema4).isValid(staticTestMap4);
        assertThat(actualPositiveRangeF).isEqualTo(false);

        var actualKeyNotExist = vMapStringInteger.shape(staticSchema31).isValid(staticTestMap5);
        assertThat(actualKeyNotExist).isEqualTo(true);
    }

}
