package cl.votainteligente.legislativo.service.session;

import java.util.Date;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.SessionChamber;
import cl.votainteligente.legislativo.model.domainobjects.SessionChamberDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionChamberDetailedDO;

public interface SessionChamberService {

	Page<SessionChamberDO> getAllSessionChamberDO(int page, int perPage)
			throws ServiceException;

	SessionChamberDetailedDO getSessionChamberDetailedDO(Long id)
			throws ServiceException;

	SessionChamber getSessionChamber(Long id) throws ServiceException;

	Page<SessionChamberDO> getByDateRange(Date from, Date to, int page,
			int perPage) throws ServiceException;

	Page<SessionChamberDO> getByLegislature(Long id, int page, int perPage)
			throws ServiceException;
}