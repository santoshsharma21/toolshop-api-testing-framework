/**
 * 
 */
package com.toolshop.reporting;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

/**
 * 
 */
public class ExtentReportUtils {
	
	// log string
	public static void logText(String text) {
		ExtentReportConfig.extentTest.info(MarkupHelper.createLabel(text, ExtentColor.CYAN));
	}
	
	// log JSON
	public static void logJson(String jsonAsString) {
		ExtentReportConfig.extentTest.info(MarkupHelper.createCodeBlock(jsonAsString, CodeLanguage.JSON));
	}
	
	// helper methods
	public static void logRequestDetails(RequestSpecification requestSpec, String endpoint, String method, boolean withRequestPayload) {
		QueryableRequestSpecification query = SpecificationQuerier.query(requestSpec);
		if(withRequestPayload) {
			logText("Endpoint: " + endpoint);
			logText("Http Method: " + method);
			logText("Request Payload Details");
			logJson(query.getBody().toString());
		} else {
			logText("Endpoint: " + endpoint);
			logText("Http Method: " + method);
		}
	}
	
	public static void logResponseDetails(Response response, boolean withResponsePayload) {
		if(withResponsePayload) {
			logText("Status Code: " + response.getStatusCode());
			logText("Response Payload Details");
			logJson(response.getBody().asPrettyString());
		} else {
			logText("Status Code: " + response.getStatusCode());
		}
	}
}