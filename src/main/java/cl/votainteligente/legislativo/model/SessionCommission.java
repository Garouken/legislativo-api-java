package cl.votainteligente.legislativo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import cl.votainteligente.legislativo.model.domainobjects.SessionCommissionDO;
import cl.votainteligente.legislativo.model.domainobjects.SessionCommissionDetailedDO;

@Entity
@XmlRootElement
@Table(name = "session_commission")
public class SessionCommission extends Session {

	@ManyToOne
	@JoinColumn(name = "commission_id")
	private Commission commission;

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

	public Commission getCommission() {
		return commission;
	}

	public SessionCommissionDO asDomainObject(){
		return new SessionCommissionDO(this);
	}

	public SessionCommissionDetailedDO asDetailedDomainObject() {
		return new SessionCommissionDetailedDO(this);
	}
}
