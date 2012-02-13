package cl.votainteligente.legislativo.service;

import java.util.Date;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.common.Page;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Debate;
import cl.votainteligente.legislativo.model.DO.DebateDO;
import cl.votainteligente.legislativo.model.DO.DebateDetailedDO;

public interface DebateService {
	Debate saveDebate(Debate d) throws ServiceException;

	Debate getDebate(long id) throws ServiceException;

	DebateDetailedDO getDebateDetailedDO(long id) throws ServiceException;

	Page<DebateDO> getDebateByBill(Bill bill, int page, int perPage)
			throws ServiceException;

	Page<DebateDO> getDebateByDateRange(Date from, Date to, int page,
			int perPage) throws ServiceException;

}
