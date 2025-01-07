/**
 * 
 */
package com.toolshop.tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.toolshop.api.BrandApi;
import com.toolshop.pojos.BrandsPojo;
import com.toolshop.utilities.TestUtils;

import io.restassured.response.Response;

/**
 * 
 */
public class BrandGetValidationTest {

	@Test(priority = 0)
	public void getAllBrands() {
		// get call
		Response response = BrandApi.getBrands();
		
		// validate status code
		Assert.assertEquals(TestUtils.getStatusCode(response), 200, "expected status code is 200");
		
		// validate response body fields
		Assert.assertTrue(TestUtils.validateUniqueIds(response, "id"), "expected unique id");
		Assert.assertFalse(TestUtils.isFieldEmpty(response, false, true, "name"), "expected non-empty name field");
		Assert.assertFalse(TestUtils.isJsonArrayEmpty(response), "expected non-empty jsonArray");
		Assert.assertFalse(TestUtils.isSingleObjectInArray(response), "expected multiple objects in array");
		Assert.assertTrue(TestUtils.getResponseHeaderValue(response, "content-type").contains("application/json"),"expected content-type is application/json");
	}

	@Test(priority = 1)
	public void getBrandByValidId(ITestContext context) {
		// get request payload used to create brand for validation
		BrandsPojo reqPayload = (BrandsPojo) context.getAttribute("reqPayload");
		
		// get id
		int id = (int) context.getAttribute("brandid");
		
		// get call
		Response response = BrandApi.getBrandById(id);
		
		// validation
		// validate status code
		Assert.assertEquals(TestUtils.getStatusCode(response), 200, "expected status code is 200");
		
		// validate response body fields
		Assert.assertEquals(TestUtils.getIntValue(response, "id"), id, "expected non-negative id");
		Assert.assertTrue(TestUtils.validateIntDataType(response, "id"), "expected data type is int");
		Assert.assertEquals(TestUtils.getStringValue(response, "name"), reqPayload.getName(), "name field is different");
		Assert.assertTrue(TestUtils.validateStringDataType(response, "name"), "expected data type is string");
		Assert.assertEquals(TestUtils.getStringValue(response, "slug"), reqPayload.getSlug(), "slug field is different");
		Assert.assertTrue(TestUtils.validateStringDataType(response, "slug"), "expected data type is string");
		Assert.assertTrue(TestUtils.getResponseHeaderValue(response, "content-type").contains("application/json"), "expected content-type is application/json");
	}

	@Test(priority = 2)
	public void getBrandByInValidId() {
		// get call
		int invalidId = -1;
		Response response = BrandApi.getBrandById(invalidId);
		
		// validation
		// validate response body fields
		Assert.assertEquals(TestUtils.getStatusCode(response), 404, "expected status code is 404");
		Assert.assertEquals(TestUtils.getStringValue(response, "message"), "Requested item not found", "expected message is different.");
	}
}