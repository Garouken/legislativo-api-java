package cl.votainteligente.legislativo.controllers.session;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.SessionChamberDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionChamberDetailedDO;

public interface SessionChamberAPI {

	/**
	 * Returns all the sessions.
	 *
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionChamberDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/sessionChamber/all?page=1&perPage=10"
	 *         >sessionChamber/all?page=1&perPage=10</a>
	 * @see SessionChamberDO
	 */
	@Path("all")
	@GET
	Page<SessionChamberDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a session.
	 *
	 * @param id
	 *            The Session identification number.
	 * @return Detailed of a session <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/sessionChamber/any?id=1"
	 *         >sessionChamber/any?id=1</a>
	 * @see SessionChamberDetailedDO
	 */
	@Path("any")
	@GET
	SessionChamberDetailedDO getById(@PathParam("id") final long id);

	/**
	 * Returns all the sessions between two dates.
	 *
	 * @param fromString
	 *            Beggining of the interval, in date format (dd-mm-yyyy).
	 * @param toString
	 *            End of the interval, in date format (dd-mm-yyyy).
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionChamberDO) <br />
	 *         For example: <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/sessionChamber/dateRange?from=01-01-1990&to=01-01-2012&page=1&perPage=10"
	 *         >sessionChamber/dateRange?from=01-01-1990&to=01-01-2012&page=1&perPage=
	 *         10</a>
	 * @see SessionChamberDO
	 */
	@Path("dateRange")
	@GET
	Page<SessionChamberDO> getDateRange(@PathParam("from") final String fromString,
			@PathParam("to") final String toString,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the sessions by Legislature number.
	 *
	 * @param legislatureId
	 *            The legislatureId of the Legislature.
	 * @param page
	 *            The number of the desired page to be retrieved (optional).
	 * @param perPage
	 *            Amount of sessions to be retrieved in a page (optional).
	 * @return A Page of Session Abstracts (SessionChamberDO) <br />
	 *         For example: <a href=
	 *         "http://demo.ciudadanointeligente.cl/Legislativo/api/sessionChamber/legislature?id=1&page=1&perPage=10"
	 *         >sessionChamber/legislature?id=1&page=1&perPage=10</a>
	 * @see SessionChamberDO
	 */
	@Path("legislature")
	@GET
	Page<SessionChamberDO> getByLegislature(@PathParam("id") final long legislatureId,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);
}