package cl.votainteligente.legislativo.common;

import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.domainobjects.BillDO;
import cl.votainteligente.legislativo.model.domainobjects.BillDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.BillRoleDO;
import cl.votainteligente.legislativo.model.domainobjects.CircunscriptionDO;
import cl.votainteligente.legislativo.model.domainobjects.CommuneDO;
import cl.votainteligente.legislativo.model.domainobjects.DebateDO;
import cl.votainteligente.legislativo.model.domainobjects.DebateDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.DistrictDO;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDO;
import cl.votainteligente.legislativo.model.domainobjects.LegislatorDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PartyDO;
import cl.votainteligente.legislativo.model.domainobjects.PartyDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonDetailedDO;
import cl.votainteligente.legislativo.model.domainobjects.PersonPartyDO;

@XmlRootElement
public class Page<T> {

	private Long pageNumber;
	private Long totalPages;
	private Long totalElements;

	private List<T> elements;

	public Page() {
	};

	public Page(List<T> content, long pageNumber, long resultsPerPage,
			long totalElements) {
		this.elements = content;
		this.pageNumber = pageNumber;
		this.totalElements = totalElements;
		this.totalPages = ((long) Math.ceil(1.0 * totalElements
				/ resultsPerPage));
	}

	public Long getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Long page) {
		this.pageNumber = page;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	@XmlElementRefs( { 
		@XmlElementRef(type = BillDO.class),
		@XmlElementRef(type = BillRoleDO.class),
		@XmlElementRef(type = BillDetailedDO.class),
		@XmlElementRef(type = CircunscriptionDO.class),
		@XmlElementRef(type = CommuneDO.class),
		@XmlElementRef(type = DebateDetailedDO.class),
		@XmlElementRef(type = DebateDO.class),
		@XmlElementRef(type = DistrictDO.class),
		@XmlElementRef(type = LegislatorDetailedDO.class),
		@XmlElementRef(type = LegislatorDO.class),
		@XmlElementRef(type = PartyDetailedDO.class),
		@XmlElementRef(type = PartyDO.class),
		@XmlElementRef(type = PersonDetailedDO.class),
		@XmlElementRef(type = PersonDO.class),
		@XmlElementRef(type = PersonPartyDO.class)})
	public List<T> getElements() {
		return elements;
	}

	public void setElements(List<T> elements) {
		this.elements = elements;
	}
}
