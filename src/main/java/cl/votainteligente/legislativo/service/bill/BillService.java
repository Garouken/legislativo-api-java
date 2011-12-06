package cl.votainteligente.legislativo.service.bill;

import java.util.Date;
import java.util.List;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Matter;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;

public interface BillService {
	Bill newBill(Bill bill) throws ServiceException;

	List<Bill> getAllBills() throws ServiceException;

	List<BillDO> getAllBillDOs() throws ServiceException;

	Bill getBill(Long id) throws ServiceException;

	List<BillDO> getByDateRange(Date from, Date to) throws ServiceException;

	List<BillDO> getByStage(Long stage) throws ServiceException;

	List<BillDO> getByAuthor(Person person) throws ServiceException;

	List<BillDO> getByMatter(Matter matter) throws ServiceException;

}