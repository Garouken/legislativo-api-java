package cl.votainteligente.legislativo.controllers.agrupation;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cl.votainteligente.legislativo.ApplicationProperties;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.exceptions.ResourceNotFoundException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.model.Chamber;
import cl.votainteligente.legislativo.model.Commission;
import cl.votainteligente.legislativo.model.CommissionType;
import cl.votainteligente.legislativo.model.domainobjects.CommissionDO;
import cl.votainteligente.legislativo.model.domainobjects.CommissionDetailedDO;
import cl.votainteligente.legislativo.service.agrupation.CommissionService;
import cl.votainteligente.legislativo.service.agrupation.CommissionTypeService;
import cl.votainteligente.legislativo.service.chamber.ChamberService;

@Path("commission")
@Controller
public class CommissionController implements CommissionAPI {

	@Autowired
	CommissionService commissionService;

	@Autowired
	CommissionTypeService commissionTypeService;

	@Autowired
	ChamberService chamberService;

	@RequestMapping(value = "commission/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<CommissionDO> getAll(
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Page<CommissionDO> resultPage = commissionService
					.getAllCommissions(page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params={"name"}, value = "commission/any", method = RequestMethod.GET)
	@ResponseBody
	public Page<CommissionDO> findByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Page<CommissionDO> resultPage = commissionService
					.findCommissionsByName(name, page, perPage);
			return resultPage;

		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(params={"id"},value = "commission/any", method = RequestMethod.GET)
	@ResponseBody
	public CommissionDetailedDO getCommission(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Commission commission = commissionService.getCommissionById(id);
			if (commission == null)
				throw new ResourceNotFoundException();
			return new CommissionDetailedDO(commission);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}

	}

	@RequestMapping(value = "commission/type", method = RequestMethod.GET)
	@ResponseBody
	public Page<CommissionDO> findByType(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			CommissionType commissionType = commissionTypeService.getById(id);
			Page<CommissionDO> resultPage = commissionService
					.getCommissionsByType(commissionType, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	@RequestMapping(value = "commission/chamber", method = RequestMethod.GET)
	@ResponseBody
	public Page<CommissionDO> findByChamber(
			@RequestParam(value = "id", required = true) final long id,
			@RequestParam(value = "page", defaultValue = ApplicationProperties.CONTROLLER_PAGE_DEFAULT_VALUE, required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = ApplicationProperties.CONTROLLER_PER_PAGE_DEFAULT_VALUE, required = false) final int perPage) {
		try {
			Chamber chamber = chamberService.getById(id);
			Page<CommissionDO> resultPage = commissionService
					.getCommissionsByChamber(chamber, page, perPage);
			return resultPage;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

}
