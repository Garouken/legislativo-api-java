package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.PartyAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.Party;
import cl.votainteligente.legislativo.model.DO.PartyDO;
import cl.votainteligente.legislativo.model.DO.PartyDetailedDO;
import cl.votainteligente.legislativo.model.DO.PersonDO;
import cl.votainteligente.legislativo.service.PartyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("party")
@Controller
public class PartyController implements PartyAPI {

	@Autowired
	PartyService partyService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.PartyAPI#getAll(int, int)
	 */
	@RequestMapping(value = "party/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PartyDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return partyService.getAllParties(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.PartyAPI#getAll(java.lang.String, int, int)
	 */
	@RequestMapping(params = { "name" }, value = "party/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PartyDO> getAll(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			return partyService.findPartiesByName(name, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.PartyAPI#getById(long)
	 */
	@RequestMapping(value = "party/any", method = RequestMethod.GET)
	@ResponseBody
	public final PartyDetailedDO getById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			Party p = partyService.getParty(id);
			if(p == null)
				throw new ResourceNotFoundException();
			return (PartyDetailedDO)p.asDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.PartyAPI#getHistoricalAffiliatesByParty(long, int, int)
	 */
	@RequestMapping(value = "party/historicalAffiliates", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getHistoricalAffiliatesByParty(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Party party = partyService.getParty(id);
			if(party==null)
				throw new ResourceNotFoundException();
			return partyService.getHistoricalAffiliatesByParty(party, page,
					perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.PartyAPI#getCurrentAffiliatesByParty(long, int, int)
	 */
	@RequestMapping(value = "party/currentAffiliates", method = RequestMethod.GET)
	@ResponseBody
	public final Page<PersonDO> getCurrentAffiliatesByParty(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Party party = partyService.getParty(id);
			if(party==null)
				throw new ResourceNotFoundException();
			return partyService.getCurrentAffiliatesByParty(party, page,
					perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
