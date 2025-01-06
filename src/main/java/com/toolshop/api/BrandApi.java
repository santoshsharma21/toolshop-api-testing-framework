/**
 * 
 */
package com.toolshop.api;

import com.toolshop.restutils.HttpMethod;
import com.toolshop.routes.BrandRoutes;

import io.restassured.response.Response;

/**
 * 
 */
public class BrandApi {

	// get all brands
	public static Response getBrands() {
		return HttpMethod.get(BrandRoutes.GET_BRANDS);
	}

	// get brand by id
	public static Response getBrandById(int id) {
		return HttpMethod.get(BrandRoutes.GET_BRANDS_BY_ID, id);
	}

	// get brand by id
	public static Response getBrandBySearchQuery(String query) {
		return HttpMethod.get(BrandRoutes.GET_BRAND_BY_QUERY, query);
	}

	// create new brand
	public static Response postBrand(Object payload) {
		return HttpMethod.post(BrandRoutes.POST_BRANDS, payload);
	}
	
	// update brand
	public static Response putBrand(int id, Object payload) {
		return HttpMethod.put(BrandRoutes.PUT_BRANDS_BY_ID, id, payload);
	}

	// delete brand
	public static Response deleteBrand(int id) {
		return HttpMethod.delete(BrandRoutes.DELETE_BRANDS_BY_ID, id);
	}
}