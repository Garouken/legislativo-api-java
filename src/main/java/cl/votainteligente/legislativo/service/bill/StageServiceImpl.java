package cl.votainteligente.legislativo.service.bill;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Stage;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class StageServiceImpl extends EntityManagerService implements
		StageService {

	@Override
	public Stage newStage(Stage stage) throws ServiceException {
		getEntityManager().persist(stage);
		return stage;
	}

}
