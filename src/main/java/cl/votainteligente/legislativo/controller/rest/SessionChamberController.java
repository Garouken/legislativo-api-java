package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.Constants;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.SessionChamberAPI;
import cl.votainteligente.legislativo.exception.BadRequestException;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.SessionChamber;
import cl.votainteligente.legislativo.model.DO.SessionChamberDO;
import cl.votainteligente.legislativo.model.DO.SessionChamberDetailedDO;
import cl.votainteligente.legislativo.service.SessionChamberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.Path;

@Path("sessionChamber")
@Controller
public class SessionChamberController implements SessionChamberAPI {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationProperties.getProperty("controller.date.format"));

	@Autowired
	SessionChamberService sessionChamberService;

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.SessionAI#getAll(int, int)
	 */
	@RequestMapping(value = "sessionChamber/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<SessionChamberDO> getAll(
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			return sessionChamberService.getAllSessionChamberDO(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.SessionAI#getById(long)
	 */
	@RequestMapping(value = "sessionChamber/any", method = RequestMethod.GET)
	@ResponseBody
	public final SessionChamberDetailedDO getById(@RequestParam(value = "id", required = true) final long id) {
		try {
			SessionChamber sessionChamber = sessionChamberService.getSessionChamber(id);

			if (sessionChamber == null) {
				throw new ResourceNotFoundException();
			}

			return sessionChamber.asSessionChamberDetailedDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.SessionAI#getDateRange(String, String, int, int)
	 */
	@Override
	@RequestMapping(value = "sessionChamber/dateRange", method = RequestMethod.GET)
	@ResponseBody
	public Page<SessionChamberDO> getDateRange(
			@RequestParam(value = "from", required = true) final String from,
			@RequestParam(value = "to", required = true) final String to,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			Date startDate = dateFormat.parse(from);
			Date endDate = dateFormat.parse(to);
			return sessionChamberService.getByDateRange(startDate, endDate, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} catch (ParseException e) {
			throw new BadRequestException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see cl.votainteligente.legislativo.controller.rest.SessionAI#getByLegislature(long, int, int)
	 */
	@Override
	@RequestMapping(value = "sessionChamber/legislature", method = RequestMethod.GET)
	@ResponseBody
	public Page<SessionChamberDO> getByLegislature(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_NUMBER, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = Constants.CONTROLLER_PAGE_DEFAULT_SIZE, required = false) final int perPage) {
		try {
			return sessionChamberService.getByLegislature(id, page, perPage);
		} catch (ServiceException e) {
			throw new ServerErrorException();
		}
	}
}
