/**
 * 
 */
package com.toolshop.tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.toolshop.api.BrandApi;
import com.toolshop.payload.BrandsPayload;
import com.toolshop.pojos.BrandsPojo;
import com.toolshop.utilities.TestUtils;

import io.restassured.response.Response;

/**
 * 
 */
public class BrandPostValidationTests {

	// create resource with valid data
	@Test(priority = 0)
	public void createNewBrandWithValidData(ITestContext context) {
		// get request payload
		BrandsPojo reqPayload = BrandsPayload.getNewBrandPayload();
		context.setAttribute("reqPayload", reqPayload);
		
		// post call
		Response response = BrandApi.postBrand(reqPayload);
		
		// get id and store id
		int id = TestUtils.getIntValue(response, "id");
		context.setAttribute("brandid", id);
		
		// validation
		// validate status code
		Assert.assertEquals(TestUtils.getStatusCode(response), 201, "expected status code is 201");
		
		// validate response body fields
		Assert.assertTrue(id >= 0, "id is negative value");
		Assert.assertTrue(TestUtils.validateIntDataType(response, "id"), "expected data type is int");
		Assert.assertEquals(TestUtils.getStringValue(response, "name"), reqPayload.getName(), "name field is different");
		Assert.assertEquals(TestUtils.getStringValue(response, "slug"), reqPayload.getSlug(), "slug field is different");
		Assert.assertTrue(TestUtils.validateStringDataType(response, "name"), "expected data type is string");
		Assert.assertTrue(TestUtils.validateStringDataType(response, "slug"), "expected data type is string");
		Assert.assertTrue(TestUtils.getResponseHeaderValue(response, "content-type").contains("application/json"),"expected content-type is application/json");
	}

	// create resource with missing field
	@Test(priority = 1)
	public void createNewBrandWithMissingNameField() {
		// get request payload
		BrandsPojo reqPayload = BrandsPayload.getMissingNameFieldBrandPayload();
		
		// post call
		Response response = BrandApi.postBrand(reqPayload);
		
		// validation
		// validate status code
		Assert.assertEquals(TestUtils.getStatusCode(response), 422, "expected status code is 422");
		
		// validate response body fields
		Assert.assertEquals(TestUtils.getStringFromJsonArray(response, "name"),"The name field is required.","expected name field empty");
		Assert.assertTrue(TestUtils.getResponseHeaderValue(response, "content-type").contains("application/json"),"expected content-type is application/json");
	}

	@Test(priority = 2)
	public void createNewBrandWithMissingSlugField() {
		// get request payload
		BrandsPojo reqPayload = BrandsPayload.getMissingSlugFieldBrandPayload();
		
		// post call
		Response response = BrandApi.postBrand(reqPayload);
		
		// validation
		// validate status code
		Assert.assertEquals(TestUtils.getStatusCode(response), 422, "expected status code is 422");
		
		// validate response body fields
		Assert.assertEquals(TestUtils.getStringFromJsonArray(response, "slug"),"The slug field is required.","expected slug field empty");
		Assert.assertTrue(TestUtils.getResponseHeaderValue(response, "content-type").contains("application/json"),"expected content-type is application/json");
	}
}