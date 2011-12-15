package cl.votainteligente.legislativo.service.geo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import cl.votainteligente.legislativo.ServiceException;
import cl.votainteligente.legislativo.model.Circunscription;
import cl.votainteligente.legislativo.model.domainobjects.CircunscriptionDO;
import cl.votainteligente.legislativo.model.domainobjects.Page;
import cl.votainteligente.legislativo.service.EntityManagerService;

@Service
public class CircunscriptionServiceImpl extends EntityManagerService implements
		CircunscriptionService {

	// Circunscription methods

	@Override
	public Circunscription newCircunscription(Circunscription circunscription)
			throws ServiceException {
		getEntityManager().persist(circunscription);
		return circunscription;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Circunscription> getAllCircunscriptionsByRegion(Long regionId)
			throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select p from Circunscription p join p.regions r where r.id = ?");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, regionId);
		List<Circunscription> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Circunscription> findCircunscriptionsByName(String name)
			throws ServiceException {
		Query query = getEntityManager()
				.createQuery(
						"select p from Circunscription p where upper(p.name) like upper(?)");
		// Use setParameter to avoid SQL Injections.
		query.setParameter(1, "%" + name + "%");
		List<Circunscription> list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Circunscription> getAllCircunscriptions()
			throws ServiceException {
		Query query = getEntityManager().createQuery(
				"select p from Circunscription p");
		List<Circunscription> list = query.getResultList();
		return list;
	}

	@Override
	public Circunscription getCircunscription(Long id) throws ServiceException {
		return getEntityManager().find(Circunscription.class, id);
	}

	// CircunscriptionDO methods
	@Override
	public Page<CircunscriptionDO> getAllCircunscriptionDOsByRegion(
			Long regionId, int page, int perPage) throws ServiceException {
		List<CircunscriptionDO> list = circunscriptionToCircunscriptionDO(this
				.getAllCircunscriptionsByRegion(regionId));
		return Page.listToPage(list, page, perPage);
	}

	@Override
	public Page<CircunscriptionDO> findCircunscriptionDOsByName(String name,
			int page, int perPage) throws ServiceException {
		List<CircunscriptionDO> list = circunscriptionToCircunscriptionDO(this
				.findCircunscriptionsByName(name));
		return Page.listToPage(list, page, perPage);
	}

	@Override
	public Page<CircunscriptionDO> getAllCircunscriptionDOs(int page,
			int perPage) throws ServiceException {
		List<CircunscriptionDO> list = circunscriptionToCircunscriptionDO(this
				.getAllCircunscriptions());
		return Page.listToPage(list, page, perPage);
	}

	@Override
	public CircunscriptionDO getCircunscriptionDO(Long id)
			throws ServiceException {
		Circunscription c = getCircunscription(id);
		return (c != null) ? c.asDomainObject() : null;
	}

	private List<CircunscriptionDO> circunscriptionToCircunscriptionDO(
			List<Circunscription> list) {
		List<CircunscriptionDO> listDO = new LinkedList<CircunscriptionDO>();
		for (Circunscription c : list)
			listDO.add(c.asDomainObject());
		return listDO;
	}

}
