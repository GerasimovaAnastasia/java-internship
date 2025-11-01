import test.EncapsulationTest;

import test.utils.TestRunnerUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void  main(String[] arg) {

        Map<String, Boolean> testResults = new LinkedHashMap<>();

        testResults.put("1. Инкапсуляция и валидация", EncapsulationTest.runEncapsulationTests());

        TestRunnerUtil.printFinalReport(testResults);
    }
}
