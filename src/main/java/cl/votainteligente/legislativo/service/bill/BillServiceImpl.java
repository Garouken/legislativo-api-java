package cl.votainteligente.legislativo.service.bill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Service;
import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Bill;
import cl.votainteligente.legislativo.model.Matter;
import cl.votainteligente.legislativo.model.Person;
import cl.votainteligente.legislativo.model.Stage;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class BillServiceImpl extends EntityManagerService implements
		BillService {

	@Override
	public Bill newBill(Bill bill) throws ServiceException {
		getEntityManager().persist(bill);
		return bill;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> getAllBills() throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Bill p");
		List<Bill> list = new ArrayList<Bill>();
		for (Bill bill : (List<Bill>) query.getResultList()) {
			list.add(bill);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getAllBillDOs(int pageNumber, int resultsPerPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Bill p");
		List<Bill> resultList = (List<Bill>) query.getResultList();
		List<BillDO> listDO = new ArrayList<BillDO>();
		for (Bill bill : resultList) {
			listDO.add(bill.asDomainObject());
		}
		return Page.listToPage(listDO, pageNumber, resultsPerPage);
	}

	@Override
	public Bill getBill(Long id) throws ServiceException {
		return getEntityManager().find(Bill.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getByDateRange(Date from, Date to, int pageNumber, int resultsPerPage)
			throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select p from Bill p where p.entryDate >= ? and p.entryDate <= ?");
		query.setParameter(1, from, TemporalType.DATE);
		query.setParameter(2, to, TemporalType.DATE);
		List<BillDO> resultList = new ArrayList<BillDO>();
		for (Bill bill : (List<Bill>) query.getResultList()) {
			resultList.add(bill.asDomainObject());
		}
		return Page.listToPage(resultList, pageNumber, resultsPerPage);
	}

	@Override
	public Page<BillDO> getByStage(Long stage, int pageNumber, int resultsPerPage) throws ServiceException {
		List<Bill> allBills = getAllBills();
		List<BillDO> resultList = new ArrayList<BillDO>();
		for (Bill bill : allBills) {
			for (Stage billStage : bill.getStages()) {
				if (billStage.getStageDescription().getId().equals(stage)) {
					resultList.add(bill.asDomainObject());
				}
			}
		}
		return Page.listToPage(resultList, pageNumber, resultsPerPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getByAuthor(Person person, int pageNumber, int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Bill p join p.authors r where r = ?");
		query.setParameter(1, person);
		List<BillDO> resultList = new ArrayList<BillDO>();
		for (Bill bill : (List<Bill>) query.getResultList()) {
			resultList.add(bill.asDomainObject());
		}
		return Page.listToPage(resultList, pageNumber, resultsPerPage);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getByMatter(Matter matter, int pageNumber, int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Bill p where p.matter = ?");
		query.setParameter(1, matter);
		List<BillDO> resultList = new ArrayList<BillDO>();
		for (Bill bill : (List<Bill>) query.getResultList()) {
			resultList.add(bill.asDomainObject());
		}
		return Page.listToPage(resultList, pageNumber, resultsPerPage);
	}
}