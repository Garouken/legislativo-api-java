package cl.votainteligente.legislativo.service.geo;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.domainobjects.CircunscriptionDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;

public interface CircunscriptionService {
	Circunscription newCircunscription(Circunscription circunscription)
			throws ServiceException;

	Circunscription getCircunscription(Long id) throws ServiceException;

	Page<CircunscriptionDO> getAllCircunscriptionDOsByRegion(Long regionId,
			int page, int perPage) throws ServiceException;

	Page<CircunscriptionDO> findCircunscriptionDOsByName(String name, int page,
			int perPage) throws ServiceException;

	Page<CircunscriptionDO> getAllCircunscriptionDOs(int page, int perPage)
			throws ServiceException;

	CircunscriptionDO getCircunscriptionDO(Long id) throws ServiceException;
}
