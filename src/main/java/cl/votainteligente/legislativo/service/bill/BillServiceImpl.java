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
import cl.votainteligente.legislativo.model.StageDescription;
import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.common.Page;
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
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getAllBillDOs(int pageNumber, int resultsPerPage)
			throws ServiceException {
		Query query = getEntityManager().createQuery("select p from Bill p");
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<Bill> resultList = (List<Bill>) query.getResultList();
		List<BillDO> listDO = new ArrayList<BillDO>();
		for (Bill bill : resultList) {
			listDO.add(bill.asDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Bill p");
		Long totalBills = (Long) queryCount.getSingleResult();
		return new Page<BillDO>(listDO, pageNumber, resultsPerPage, totalBills);
	}

	@Override
	public Bill getBill(Long id) throws ServiceException {
		return getEntityManager().find(Bill.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getByDateRange(Date from, Date to, int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select p from Bill p where p.entryDate >= ? and p.entryDate <= ?");
		query.setParameter(1, from, TemporalType.DATE);
		query.setParameter(2, to, TemporalType.DATE);
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<Bill> resultList = (List<Bill>) query.getResultList();
		List<BillDO> listDO = new ArrayList<BillDO>();
		for (Bill bill : resultList) {
			listDO.add(bill.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(p) from Bill p where p.entryDate >= ? and p.entryDate <= ?");
		queryCount.setParameter(1, from, TemporalType.DATE);
		queryCount.setParameter(2, to, TemporalType.DATE);
		Long totalBills = (Long) queryCount.getSingleResult();
		return new Page<BillDO>(listDO, pageNumber, resultsPerPage, totalBills);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getByStage(StageDescription stageDescription,
			int pageNumber, int resultsPerPage) throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select distinct p from Bill p join p.stages s where s.endDate is null and s.stageDescription = ?");
		query.setParameter(1, stageDescription);
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<Bill> resultList = (List<Bill>) query.getResultList();
		List<BillDO> listDO = new ArrayList<BillDO>();
		for (Bill bill : resultList) {
			listDO.add(bill.asDomainObject());
		}
		Query queryCount = getEntityManager()
				.createQuery(
						"select count(distinct p) from Bill p join p.stages s where s.endDate is null and s.stageDescription = ?");
		queryCount.setParameter(1, stageDescription);
		Long totalBills = (Long) queryCount.getSingleResult();
		return new Page<BillDO>(listDO, pageNumber, resultsPerPage, totalBills);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getByAuthor(Person person, int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Bill p join p.authors r where r = ?");
		query.setParameter(1, person);
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<Bill> resultList = (List<Bill>) query.getResultList();
		List<BillDO> listDO = new ArrayList<BillDO>();
		for (Bill bill : resultList) {
			listDO.add(bill.asDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Bill p join p.authors r where r = ?");
		queryCount.setParameter(1, person);
		Long totalBills = (Long) queryCount.getSingleResult();
		return new Page<BillDO>(listDO, pageNumber, resultsPerPage, totalBills);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BillDO> getByMatter(Matter matter, int pageNumber,
			int resultsPerPage) throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Bill p where p.matter = ?");
		query.setParameter(1, matter);
		query.setFirstResult((pageNumber - 1) * resultsPerPage);
		query.setMaxResults(resultsPerPage);
		List<Bill> resultList = (List<Bill>) query.getResultList();
		List<BillDO> listDO = new ArrayList<BillDO>();
		for (Bill bill : resultList) {
			listDO.add(bill.asDomainObject());
		}
		Query queryCount = getEntityManager().createQuery(
				"select count(p) from Bill p where p.matter = ?");
		queryCount.setParameter(1, matter);
		Long totalBills = (Long) queryCount.getSingleResult();
		return new Page<BillDO>(listDO, pageNumber, resultsPerPage, totalBills);
	}
}