package cl.votainteligente.legislativo.controller.rest.iface;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.DO.PartyDO;
import cl.votainteligente.legislativo.model.DO.PartyDetailedDO;
import cl.votainteligente.legislativo.model.DO.PersonDO;

import javax.ws.rs.*;

public interface PartyAPI {

	/**
	 * Returns all the parties.
	 *
	 * @param page The number of the desired page to be retrieved (optional).
	 * @param perPage Amount of parties to be retrieved in a page (optional).
	 * @return A Page of Party Abstracts (PartyDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/party/all">party/all</a>
	 * @see PartyDO
	 */

	@GET
	@Path("all")
	public Page<PartyDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the parties with a specific name.
	 *
	 * @param name Party name to look for.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of parties to be retrieved in a page (optional).
	 * @return A page of Party Abstracts (PartyDO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/party/any?name=Partido">party/any?name=Partido</a>
	 */
	@GET
	@Path("any")
	public Page<PartyDO> getAll(
			@PathParam("name") final String name,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a party.
	 *
	 * @param id Party identification number.
	 * @return Detailed of a party <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/party/any?id=1">party/any?id=1</a>
	 * @see PartyDetailedDO
	 */
	@GET
	@Path("any")
	public PartyDetailedDO getById(@PathParam("id") final long id);

	/**
	 * Gives all the historical affiliates (person) of a party.
	 *
	 * @param id Party identification number.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (Person DO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/party/historicalMembers?id=1">party/historicalMembers?id=1</a>
	 */
	@GET
	@Path("historicalMembers")
	public Page<PersonDO> getHistoricalPartyMembers(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Gives all the current affiliates (person) of a party.
	 *
	 * @param id Party identification number.
	 * @param page The number of desired page to be retrieved (optional).
	 * @param perPage Amount of people to be retrieved in a page (optional).
	 * @return A Page of Person Abstracts (Person DO) <br />
	 * 			For Example:
	 * 			<a href="http://demo.ciudadanointeligente.cl/Legislativo/api/party/currentMembers?id=1">party/currentMembers?id=1</a>
	 */
	@GET
	@Path("currentMembers")
	public Page<PersonDO> getCurrentPartyMembers(
			@PathParam("id") final long id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}