/**
 * 
 */
package com.toolshop.restutils;

import com.toolshop.utilities.ConfigUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * 
 */
public class SpecBuilder {

	// request specification
	public static RequestSpecification getRequestSpec(Object payload) {
		RequestSpecification reqSpec;

		if (payload != null && !payload.toString().isEmpty()) {
			RequestSpecBuilder spec = new RequestSpecBuilder();
			spec.setBaseUri(ConfigUtils.getUrl())
				.setBody(payload)
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.log(LogDetail.ALL);
			
			reqSpec = spec.build();
		} else {
			RequestSpecBuilder spec = new RequestSpecBuilder();
			spec.setBaseUri(ConfigUtils.getUrl())
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.log(LogDetail.ALL);
			
			reqSpec = spec.build();
		}

//		RequestSpecBuilder spec = new RequestSpecBuilder();
//		spec.setBaseUri(ConfigUtil.getUrl())
//			.setContentType(ContentType.JSON)
//			.log(LogDetail.ALL);
//		
//		RequestSpecification reqSpec = spec.build();
		
		return reqSpec;
	}

	// response specification
	public static ResponseSpecification getResponseSpec() {
		ResponseSpecBuilder spec = new ResponseSpecBuilder();
		spec.expectContentType(ContentType.JSON);
		spec.log(LogDetail.ALL);

		ResponseSpecification resSpec = spec.build();
		return resSpec;
	}
}