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
public class BrandPutValidationTests {
	
	// update brand
	@Test
	public void updateBrand(ITestContext context) {
		// get update payload
		BrandsPojo rePayload = BrandsPayload.getUpdateBrandPayload();

		// get id
		int id = (int) context.getAttribute("brandId");

		// put call
		Response response = BrandApi.putBrand(id, rePayload);

		// validation
		// validate status code
		Assert.assertEquals(TestUtils.getStatusCode(response), 200, "expected status code is 200");

		// validate response body fields
		Assert.assertTrue(TestUtils.getBooleanValue(response, "success"));
		Assert.assertTrue(TestUtils.getResponseHeaderValue(response, "content-type").contains("application/json"),
				"expected content-type is application/json");
	}
}