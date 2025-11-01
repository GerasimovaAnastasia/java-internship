import test.EncapsulationTest;
import test.ConstructorTest;
import test.EqualsHashCodeTest;
import test.utils.TestRunnerUtil;
import test.ArrayListComparatorTest;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void  main(String[] arg) {

        Map<String, Boolean> testResults = new LinkedHashMap<>();

        testResults.put("1. Инкапсуляция и валидация", EncapsulationTest.runEncapsulationTests());
        testResults.put("2. Перегрузка конструкторов", ConstructorTest.runConstructorTests());
        testResults.put("3. equals() и hashCode()", EqualsHashCodeTest.runEqualsHashCodeTests());
        testResults.put("4. ArrayList и Comparator", ArrayListComparatorTest.runArrayListComparatorTests());

        TestRunnerUtil.printFinalReport(testResults);
    }
}
