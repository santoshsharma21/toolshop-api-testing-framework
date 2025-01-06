/**
 * 
 */
package com.toolshop.restutils;

import com.toolshop.reporting.ExtentReportUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * 
 */
public class HttpMethod {
	
	// GET method
	public static Response get(String endpoint) {
		
		RequestSpecification reqSpec = SpecBuilder.getRequestSpec(null);
		ResponseSpecification resSpec = SpecBuilder.getResponseSpec();
		
		Response response = RestAssured.given()
				.spec(reqSpec)
				.when()
					.get(endpoint)
				.then()
					.spec(resSpec)
					.log().all().extract().response();
		
		ExtentReportUtils.logRequestDetails(reqSpec, endpoint, "GET", false);
		ExtentReportUtils.logResponseDetails(response, true);
		
		return response;	
	}
	
	// GET method with id
	public static Response get(String endpoint, int id) {
		
		RequestSpecification reqSpec = SpecBuilder.getRequestSpec(null);
		ResponseSpecification resSpec = SpecBuilder.getResponseSpec();
		
		Response response = RestAssured.given()
				.spec(reqSpec)
				.pathParam("brandId", id)
				.when()
					.get(endpoint)
				.then()
					.spec(resSpec)
					.log().all().extract().response();
		
		ExtentReportUtils.logRequestDetails(reqSpec, endpoint, "GET", false);
		ExtentReportUtils.logResponseDetails(response, true);
		
		return response;	
	}
	
	// GET method with query
	public static Response get(String endpoint, String query) {
			
		RequestSpecification reqSpec = SpecBuilder.getRequestSpec(null);
		ResponseSpecification resSpec = SpecBuilder.getResponseSpec();
			
		Response response = RestAssured.given()
				.spec(reqSpec)
				.queryParam("q", query)
				.when()
					.get(endpoint)
				.then()
					.spec(resSpec)
					.log().all().extract().response();
		
		ExtentReportUtils.logRequestDetails(reqSpec, endpoint, "GET", false);
		ExtentReportUtils.logResponseDetails(response, true);
		
		return response;	
	}
	
	// POST method
	public static Response post(String endpoint, Object payload) {
		
		RequestSpecification reqSpec = SpecBuilder.getRequestSpec(payload);
		ResponseSpecification resSpec = SpecBuilder.getResponseSpec();
			
		Response response = RestAssured.given()
				.spec(reqSpec)
				.when()
					.post(endpoint)
				.then()
					.spec(resSpec)
					.log().all().extract().response();
		
		ExtentReportUtils.logRequestDetails(reqSpec, endpoint, "POST", true);
		ExtentReportUtils.logResponseDetails(response, true);
		
		return response;
	}
	
	// PUT method
	public static Response put(String endpoint, int id, Object payload) {
			
		RequestSpecification reqSpec = SpecBuilder.getRequestSpec(payload);
		ResponseSpecification resSpec = SpecBuilder.getResponseSpec();
				
		Response response = RestAssured.given()
				.spec(reqSpec)
				.pathParam("brandId", id)
				.when()
					.put(endpoint)
				.then()
					.spec(resSpec)
					.log().all().extract().response();
		
		ExtentReportUtils.logRequestDetails(reqSpec, endpoint, "PUT", true);
		ExtentReportUtils.logResponseDetails(response, true);	
		
		return response;
	}
	
	// DELETE method with id
	public static Response delete(String endpoint, int id) {
			
		RequestSpecification reqSpec = SpecBuilder.getRequestSpec(null);
		ResponseSpecification resSpec = SpecBuilder.getResponseSpec();
			
		Response response = RestAssured.given()
				.spec(reqSpec)
				.pathParam("brandId", id)
				.when()
					.delete(endpoint)
				.then()
					.spec(resSpec)
					.log().all().extract().response();
		
		ExtentReportUtils.logRequestDetails(reqSpec, endpoint, "DELETE", true);
		ExtentReportUtils.logResponseDetails(response, true);
		
		return response;	
	}
}