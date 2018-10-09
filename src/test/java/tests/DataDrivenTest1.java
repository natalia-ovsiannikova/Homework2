package tests;

import objects.CalculatorPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class DataDrivenTest1 extends ChromeDriverSetUp {

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
    public void calculator_DataDrivenTest1(){
        Assert.assertEquals(expected, calc.interprete(expression));
    }

    @Parameterized.Parameters (name="Test: {0}={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Math.sqrt(25)", "5"},
                {"Math.sin(0)", "0"},
                {"Math.cos(0)", "1"},
                {"null*2","0"},
                {"5%3", "2"},
                {"1e+100/1e+49", "1e+51"}
        });
    }

    @AfterClass
    public static void stop() {
        driver.quit();
    }
}