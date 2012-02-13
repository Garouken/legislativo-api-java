package cl.votainteligente.legislativo.controller.rest;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.controller.rest.iface.DistrictAPI;
import cl.votainteligente.legislativo.exception.ResourceNotFoundException;
import cl.votainteligente.legislativo.exception.ServerErrorException;
import cl.votainteligente.legislativo.model.District;
import cl.votainteligente.legislativo.model.DO.DistrictDO;
import cl.votainteligente.legislativo.service.DistrictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@Path("district")
@Controller
public class DistrictController implements DistrictAPI {

	@Autowired
	DistrictService service;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.DistrictAPI#getAll(int,int)
	 */
	@RequestMapping(value = "geo/district/all", method = RequestMethod.GET)
	@ResponseBody
	public final Page<DistrictDO> getAll(
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.getAllDistrictDOs(page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see cl.votainteligente.legislativo.controller.rest.DistrictAPI#findDistrictsByName(java.lang.String, int, int)
	 */
	@RequestMapping(params = { "name"}, value = "geo/district/any", method = RequestMethod.GET)
	@ResponseBody
	public final Page<DistrictDO> findDistrictsByName(
			@RequestParam(value = "name", required = true) final String name,
			@RequestParam(value = "page", defaultValue = "1", required = false) final int page,
			@RequestParam(value = "perPage", defaultValue = "10", required = false) final int perPage) {
		try {
			return service.findDistrictDOsByName(name, page, perPage);
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * cl.votainteligente.legislativo.controller.rest.DistrictAPI#getDistrictById(long)
	 */
	@RequestMapping(params = { "id" }, value = "geo/district/any", method = RequestMethod.GET)
	@ResponseBody
	public final DistrictDO getDistrictById(
			@RequestParam(value = "id", required = true) final long id) {
		try {
			District d = service.getDistrict(id);
			if (d == null)
				throw new ResourceNotFoundException();
			return d.asDomainObject();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}
