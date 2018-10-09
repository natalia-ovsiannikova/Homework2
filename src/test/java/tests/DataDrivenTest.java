package tests;

import objects.CalculatorPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DataDrivenTest extends ChromeDriverSetUp {

    private static CalculatorPage calc;

    @Parameterized.Parameter
    public String expression;

    @Parameterized.Parameter(1)
    public static String expected;

    @BeforeClass
    public static void setup() {
        calc = new CalculatorPage(driver);
        calc.open();
    }

    @Before
    public void cleanup() {
        calc.clear();
    }

    @Test
    public void calculator_DataDrivenTest(){
        Assert.assertEquals(expected, calc.calculate(expression));
    }

    @Parameterized.Parameters (name="Test: {0}={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"10000000000000+10000000000000", "20000000000000"},
                {"0100+0200", "300"},
                {"077+077", "154"},
                {"080+080", "160"},
                {"55/0", "Error"}
        });
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }
}