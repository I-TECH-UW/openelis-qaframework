package org.openelisglobal.qaframework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // defining ordered by name features folder
        features = { "src/features" },
        // defining the definition steps package
        glue = "org.openelisglobal.qaframework.automation", plugin = { "html:target/index.html",
                "message:target/cucumber.ndjson" }, monochrome = true)
public class RunTest {
	
	public class HOOK {
		
		public static final String LOGIN = "@login";
		
		public static final String ORDER = "@order";
		
		public static final String RESULT = "@results";
		
		public static final String VALIDATE = "@validate";
		
		public static final String REPORT = "@report";
		
		public static final String PATIENT_ENTRY = "@patientEntry";
		
		public static final String EMR_LIS = "@emrLis";
	}
}
