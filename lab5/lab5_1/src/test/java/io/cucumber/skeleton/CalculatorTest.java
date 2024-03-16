package io.cucumber.skeleton;


import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorTest {

    private Calculator calc;
    
    
    @Given("a calculator I just turned on")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void subtract(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When("I multiply {int} and {int}")
    public void multiply(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I divide {int} by {int}")
    public void divide(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("/");
    }

    @Then("the result is {float}")
    public void the_result_is(double expected) {
        assertEquals(expected, calc.value());
    }
}
