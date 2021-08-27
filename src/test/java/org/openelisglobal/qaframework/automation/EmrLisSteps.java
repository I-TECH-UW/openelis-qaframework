package org.openelisglobal.qaframework.automation;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openelisglobal.qaframework.RunTest;

public class EmrLisSteps {
	
	@After(RunTest.HOOK.EMR_LIS)
	public void destroy() {
		System.out.println("....Stopping EMR-LIS Components......");
	}
	
	@Before(RunTest.HOOK.EMR_LIS)
	public void startEmrLisComponents() {
		System.out.println("....Starting EMR-LIS Components......");
	}
	
	@Given("The EMR Supports FHIR R4")
	public void emrSupportsFhir() {
		System.out.println("....EMR supports FHIR");
	}
	
	@And("The LIS Supports FHIR R4")
	public void lisSupportsFhir() {
		System.out.println("....LIS supports FHIR");
	}
	
	@And("The EMR and LIS are trusted applications known by the IOL")
	public void lisAndEmrTrustedByIol() {
		System.out.println("....IOL trusts emr and lis");
	}
	
	@And("The SHR Supports FHIR R4")
	public void shrSupportsFhir() {
		System.out.println("....SHR supports FHIR");
	}
	
	@When("EMR Creates Lab Order")
	public void createLabOrder() {
		System.out.println("....create Lab Order");
	}
	
	@Then("The order save generates a FHIR R4 Task Resource")
	public void saveOrderGeneratesTakResource() {
		System.out.println("....Task Resource Created");
	}
	
	@When("EMR Sends New Lab Order")
	public void emrSendsLabOrder() {
		System.out.println("....emr send Lab Order");
	}
	
	@Then("FHIR Task bundled order is sent to the IOL")
	public void taskBundleSentToIol() {
		System.out.println("....Task bundle sent to IOL");
	}
	
	@And("Bundled order is routed through the IOL to both the SHR and the LIS")
	public void orderRoutedToShrAndLis() {
		System.out.println("....Bundled Order routed to LIS and SHR");
	}
	
	@When("LIS Saves Order and Update Order Status")
	public void saveOrderAndUpdateOrderStatus() {
		System.out.println("....save Order and Update Order Status");
	}
	
	@Then("FHIR R4 Task Resource Status is updated locally")
	public void updateTaskResourceSTatus() {
		System.out.println("....Update Task Resource Status");
	}
	
	@When("LIS Sends Order Update")
	public void sendOrderUpdate() {
		System.out.println("....send Order Update");
	}
	
	@Then("Updated Task Resource Status is sent to the IOL")
	public void taskResourceStatusSentToIol() {
		System.out.println("....Updated Task Resource Status sent to IOL");
	}
	
	@And("IOL routes the updated FHIR R4 Tasks to the SHR and the EMR")
	public void routeUpdatedTaskToShrAndEmr() {
		System.out.println("....IOL routes Updated Task to to EMR and SHR");
	}
	
	@When("EMR Updates FHIR Task Status")
	public void emrUpdateTskStatus() {
		System.out.println("....EMR Updates FHIR Task Status");
	}
	
	@Then("FHIR task status updated locally")
	public void fhirTaskStatusUpdatedLocally() {
		System.out.println("....FHIR task status updated locally");
	}
	
	@When("LIS Saves Result and Updates FHIR Task")
	public void lisSavesResult() {
		System.out.println("....LIS Saves Result and Updates FHIR Task");
	}
	
	@Then("The Results save generates a FHIR R4 DiagnosticReport Resource")
	public void lisGenearatesFhirDiagnosticReport() {
		System.out.println("....FThe Results save generates a FHIR R4 DiagnosticReport Resource ");
	}
	
	@When("LIS EMR Sends Lab Results")
	public void lisSendLabResults() {
		System.out.println("....LIS EMR Sends Lab Results");
	}
	
	@Then("Diagnositc Report Resource is sent to the IOL")
	public void DiagnosticReportSentToIol() {
		System.out.println("....Diagnositc Report Resource is sent to the IOL");
	}
	
	@And("Diagnostic Report Resource is routed through the IOL to both the SHR and the EMR")
	public void routeDiagnosticReportToShrAndEmr() {
		System.out.println("....IOL routes Diagnostic Report to to EMR and SHR");
	}
	
	@And("EMR saves Results")
	public void emrSavesResult() {
		System.out.println("....EMR saves Results");
	}
}
