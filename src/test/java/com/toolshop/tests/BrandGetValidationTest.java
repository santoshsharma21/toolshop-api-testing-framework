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
		// validation
		Assert.assertEquals(TestUtils.getStatusCode(response), 200);
		Assert.assertEquals(TestUtils.getResponseHeaderValue(response, "content-type"), "application/json");
		Assert.assertTrue(TestUtils.validateUniqueIds(response, "id"));
		Assert.assertTrue(TestUtils.isFieldEmpty(response, false, true, "name"));
		Assert.assertTrue(TestUtils.isJsonArrayEmpty(response));
		Assert.assertTrue(TestUtils.isSingleObjectInArray(response));
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
		Assert.assertEquals(TestUtils.getStatusCode(response), 200);
		Assert.assertEquals(TestUtils.getIntValue(response, "id"), id);
		Assert.assertEquals(TestUtils.getStringValue(response, "name"), reqPayload.getName());
		Assert.assertEquals(TestUtils.getStringValue(response, "slug"), reqPayload.getSlug());
	}
	
	@Test(priority = 2)
	public void getBrandByInValidId() {
		
	}
}
