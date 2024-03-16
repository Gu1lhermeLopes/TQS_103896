package io.cucumber.skeleton;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import io.cucumber.java.en.When;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("io.cucumber.skeleton")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "io.cucumber.skeleton")
public class RunCucumberTest {
 

    @When("I wait {int} hour")
    public void i_wait_hour(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        System.out.println("none");
    }

    @When("Given I have {int} cukes in my belly")
    public void given_i_have_cukes_in_my_belly(Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("n11one");
    }

    @When("my belly should growl")
    public void my_belly_should_growl() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("n11one");
    }


}
