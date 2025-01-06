/**
 * 
 */
package com.toolshop.pojos;

import lombok.Builder;
import lombok.Data;

/**
 * 
 */

@Data
@Builder(toBuilder = true)
public class BrandsPojo {
	
	private String name;
	private String slug;
}
