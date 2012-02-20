package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.model.DO.CircunscriptionDO;

import javax.ws.rs.*;

public interface CircunscriptionAPI {

	/**
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of circunscriptions to be retrieved in a page (optional).
	 * @return A Page of Circunscription Abstracts (CircunscriptionDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/geo/circunscription/all">geo/circunscription/all</a>
	 * @see CircunscriptionDO
	 */
	@Path("all")
	@GET
	Page<CircunscriptionDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the Circunscriptions with a specific name.
	 *
	 * @param name Circunscription name to look for.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of circunscriptions to be retrieved in a page (optional).
	 * @return A page of Circunscription Abstracts (CircunscriptionDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/geo/circunscription/any?name=Santiago">geo/circunscription/any?name=Santiago</a>
	 * @see CircunscriptionDO
	 */
	@Path("any")
	@GET
	Page<CircunscriptionDO> findCircunscriptionsByName(
			@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a Circunscription.
	 *
	 * @param id Circunscription identification number.
	 * @return Detailed of a Circunscription <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/geo/circunscription/any?id=17">geo/circunscription/any?id=17</a>
	 * @throws ResourceNotFoundException
	 * @see CircunscriptionDO
	 */
	@Path("any")
	@GET
	CircunscriptionDO getCircunscriptionById(@PathParam("id") final long id);

	/**
	 * Returns all the Circunscriptions of a region.
	 *
	 * @param id Region id to look for.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of circunscriptions to be retrieved in a page (optional).
	 * @return A page of Circunscription Abstracts (CircunscriptionDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/geo/circunscription/region?id=1">geo/circunscription/region?id=1</a>
	 * @see CircunscriptionDO
	 */
	@Path("region")
	@GET
	Page<CircunscriptionDO> getCircunscriptionByRegionId(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}