package cl.votainteligente.legislativo.controllers.bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;

import cl.votainteligente.legislativo.exceptions.BadRequestException;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Matter;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;
import cl.votainteligente.legislativo.service.bill.BillService;
import cl.votainteligente.legislativo.service.matter.MatterService;
import cl.votainteligente.legislativo.service.person.PersonService;
import net.sf.json.*;

@Controller
public class BillController {
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			ApplicationProperties.getProperty("simpleDateFormat"));

	@Autowired
	BillService service;

	@Autowired
	PersonService personService;

	@Autowired
	MatterService matterService;

	@RequestMapping(value = "bill/all", method = RequestMethod.GET)
	public final Page<BillDO> getAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {

			Page<BillDO> resultPage = service.getAllBillDOs(page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/any", method = RequestMethod.GET)
	@ResponseBody
	public final String getBillById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			return JSONObject.fromObject(service.getBill(id)).toString(2);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "bill/dateRange", method = RequestMethod.GET)
	public final Page<BillDO> getDateRange(
			@RequestParam(value = "from", required = true) final String fromString,
			@RequestParam(value = "to", required = true) final String toString,
			HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request,
					"perPage", 10);
			Date from = dateFormat.parse(fromString);
			Date to = dateFormat.parse(toString);
			Page<BillDO> resultPage = service.getByDateRange(from, to, page,
					perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		} catch (ParseException e) {
			throw new BadRequestException();
		}
	}

	@RequestMapping(params = { "stage_id" }, value = "bill/stage.json", method = RequestMethod.GET)
	public final Page<BillDO> getBillsByStage(
			@RequestParam(value = "stage_id", required = true) final long stage_id,
			HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request,
					"perPage", 10);
			Page<BillDO> resultPage = service.getByStage(stage_id, page,
					perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/author.json", method = RequestMethod.GET)
	public final Page<BillDO> getByAuthors(
			@RequestParam(value = "id", required = true) final long author_id,
			HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request,
					"perPage", 10);
			Person p = personService.getPerson(author_id);
			if (p == null)
				throw new ResourceNotFoundException();
			Page<BillDO> resultPage = service.getByAuthor(p, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params = { "id" }, value = "bill/matter.json", method = RequestMethod.GET)
	public final Page<BillDO> getByMatter(
			@RequestParam(value = "id", required = true) final long matter_id,
			HttpServletRequest request) {
		try {
			int page = ServletRequestUtils.getIntParameter(request, "page", 1);
			int perPage = ServletRequestUtils.getIntParameter(request,
					"perPage", 10);
			Matter p = matterService.getById(matter_id);
			if (p == null)
				throw new ResourceNotFoundException();
			Page<BillDO> resultPage = service.getByMatter(p, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}