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
		Assert.assertEquals(TestUtils.getStatusCode(response), 201);
		Assert.assertTrue(id >= 0);
		Assert.assertEquals(TestUtils.getStringValue(response, "name"), reqPayload.getName());
		Assert.assertEquals(TestUtils.getStringValue(response, "slug"), reqPayload.getSlug());
		Assert.assertTrue(TestUtils.validateIntDataType(response, "id"));
		Assert.assertTrue(TestUtils.validateStringDataType(response, "name"));
		Assert.assertTrue(TestUtils.validateStringDataType(response, "slug"));
		Assert.assertEquals(TestUtils.getResponseHeaderValue(response, "content-type"), "application/json");
	}

	// create resource with missing field
	@Test(priority = 1)
	public void createNewBrandWithMissingNameField() {
		// get request payload
		BrandsPojo reqPayload = BrandsPayload.getMissingNameFieldBrandPayload();
		// post call
		Response response = BrandApi.postBrand(reqPayload);
		// validation
		Assert.assertEquals(TestUtils.getStatusCode(response), 422);
		Assert.assertEquals(TestUtils.getStringFromJsonArray(response, "name"), "The name field is required.");
		Assert.assertEquals(TestUtils.getResponseHeaderValue(response, "content-type"), "application/json");
	}

	@Test(priority = 2)
	public void createNewBrandWithMissingSlugField() {
		// get request payload
		BrandsPojo reqPayload = BrandsPayload.getMissingSlugFieldBrandPayload();
		// post call
		Response response = BrandApi.postBrand(reqPayload);
		// validation
		Assert.assertEquals(TestUtils.getStatusCode(response), 422);
		Assert.assertEquals(TestUtils.getStringFromJsonArray(response, "slug"), "The slug field is required.");
		Assert.assertEquals(TestUtils.getResponseHeaderValue(response, "content-type"), "application/json");
	}
}