package cl.votainteligente.legislativo.controllers.person;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.exceptions.ServerErrorException;
import cl.votainteligente.legislativo.service.person.PersonService;
import com.google.gson.Gson;

@Controller
public class PersonController {
	private Logger logger = Logger.getLogger(PersonController.class);
	private Gson gson = new Gson();

	@Autowired
	PersonService service;

	@RequestMapping(value = "person/all.json", method = RequestMethod.GET)
	@ResponseBody
	public final String getAll() {
		try {
			return gson.toJson(service.getAllPersonDOs());
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServerErrorException();
		}
	}
}