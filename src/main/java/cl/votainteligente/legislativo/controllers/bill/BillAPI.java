package cl.votainteligente.legislativo.controllers.bill;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.BillDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.BillRoleDO;

public interface BillAPI {
	/**
	 * Returns all the bills registered in the system.
	 * 
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("all")
	public Page<BillDO> getAll(
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Allows you to get all the information of a bill.
	 * 
	 * @param id
	 *            The Bill identification number.
	 * @return Detailed view of a bill (BillDetailedDO)
	 * @throws ResourceNotFoundException
	 * @see BillDetailedDO
	 */
	@GET
	@Path("any")
	public BillDetailedDO getBillById(@PathParam("id") final long id);

	/**
	 * Gives all the bills presented between two dates.
	 * 
	 * @param fromString
	 *            Beggining of the interval, in date format.
	 * @param toString
	 *            End of the interval, in date format.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("dateRange")
	public Page<BillDO> getDateRange(
			@PathParam("from") final String fromString,
			@PathParam("to") final String toString,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the bills that currently are in a particular stage.
	 * 
	 * @param stage_id
	 *            The Stage identification number.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("stage")
	public Page<BillDO> getBillsByStage(@PathParam("id") final long stage_id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the bills registered in the system of a particular matter.
	 * 
	 * @param author_id
	 *            The matter identificator.
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bills to be retrieved in a page.
	 * @return A Page of Bill Abstracts (BillDO)
	 * @see BillDO
	 */
	@GET
	@Path("matter")
	public Page<BillDO> getByMatter(@PathParam("id") final long author_id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

	/**
	 * Returns all the bills and role (of person) registered in the system of a
	 * particular author
	 * 
	 * @param author_id
	 *            The person id of the author
	 * @param page
	 *            The number of the desired page to be retrieved.
	 * @param perPage
	 *            Amount of bill-roles to be retrieved in a page.
	 * @return A Page of BillRole Abstracts (BillRoleDO)
	 * @see BillRoleDO
	 */
	@GET
	@Path("author")
	public Page<BillRoleDO> getBillRoleByPerson(
			@PathParam("id") final long author_id,
			@PathParam("page") @DefaultValue("1") final int page,
			@PathParam("perPage") @DefaultValue("10") final int perPage);

}
