/**
 * Author Gurminder
 * Date Created 07-May-2013 3:55:03 PM
 */
package com.insonix.qrataapi.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface SiteReviewsApi {
	@GET
	@Path("contentscore")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSitesByKeyword(@QueryParam("search") String search);
	
	@GET
	@Path("ratings")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSiteReviewRatings(@QueryParam("urls[]") String[] urls, @QueryParam("token") String token);
}
