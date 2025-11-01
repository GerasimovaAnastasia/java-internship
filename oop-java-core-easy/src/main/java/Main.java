import test.EncapsulationTest;
import test.ConstructorTest;
import test.EqualsHashCodeTest;
import test.utils.TestRunnerUtil;
import test.ArrayListComparatorTest;
import test.InheritancePolymorphismTest;
import test.InterfaceAbstractTest;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void  main(String[] arg) {

        Map<String, Boolean> testResults = new LinkedHashMap<>();

        testResults.put("1. Инкапсуляция и валидация", EncapsulationTest.runEncapsulationTests());
        testResults.put("2. Перегрузка конструкторов", ConstructorTest.runConstructorTests());
        testResults.put("3. equals() и hashCode()", EqualsHashCodeTest.runEqualsHashCodeTests());
        testResults.put("4. ArrayList и Comparator", ArrayListComparatorTest.runArrayListComparatorTests());
        testResults.put("5. Наследование и полиморфизм", InheritancePolymorphismTest.runInheritancePolymorphismTests());
        testResults.put("6. Интерфейсы и абстрактные классы", InterfaceAbstractTest.runInterfaceAbstractTests());

        TestRunnerUtil.printFinalReport(testResults);
    }
}
