/**
 * 
 */
package com.toolshop.payload;

import com.toolshop.pojos.BrandsPojo;
import com.toolshop.utilities.FakerUtils;
import com.toolshop.utilities.RandomDataConstant;

/**
 * 
 */
public class BrandsPayload {

	// returns payload to create new brand resource
	public static BrandsPojo getNewBrandPayload() {
		return BrandsPojo.builder()
				.name(FakerUtils.getRandomData(RandomDataConstant.NAME))
				.slug(FakerUtils.getRandomData(RandomDataConstant.SLUG)).build();
	}

	// returns payload to update existing resource
	public static BrandsPojo getUpdateBrandPayload() {
		return BrandsPojo.builder()
				.name(FakerUtils.getRandomData(RandomDataConstant.NAME))
				.slug(FakerUtils.getRandomData(RandomDataConstant.SLUG)).build();
	}

	// returns payload with missing field
	public static BrandsPojo getMissingNameFieldBrandPayload() {
		return BrandsPojo.builder()
				.name(null)
				.slug(FakerUtils.getRandomData(RandomDataConstant.SLUG)).build();
	}
	
	public static BrandsPojo getMissingSlugFieldBrandPayload() {
		return BrandsPojo.builder()
				.name(FakerUtils.getRandomData(RandomDataConstant.NAME))
				.slug(null).build();
	}
}