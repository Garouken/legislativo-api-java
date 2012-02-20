package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.model.Region;

import javax.ws.rs.*;

public interface RegionAPI {

	/**
	 *
	 * @param page The number of the desired page to be retrieved.
	 * @param perPage Amount of regions to be retrieved in a page.
	 * @return A Page of Region (Region) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/region/all">region/all</a>
	 * @see Region
	 */
	@Path("all")
	@GET
	Page<Region> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the Regions with a specific name.
	 *
	 * @param name Region name to look for.
	 * @param page The number of desired page to be retrieved.
	 * @param perPage Amount of regions to be retrieved in a page.
	 * @return A page of Region (Region) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/region/any?name=Magallanes">region/any?name=Magallanes</a>
	 * @see Region
	 */
	@Path("any")
	@GET
	Page<Region> findRegionsByName(
			@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a Region.
	 *
	 * @param id Region identification number.
	 * @return Detailed of a Region <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/region/any?id=1">region/any?id=1</a>
	 * @throws ResourceNotFoundException
	 * @see Region
	 */
	@Path("any")
	@GET
	Region getRegionById(@PathParam("id") final long id);

}