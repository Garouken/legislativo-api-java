package cl.votainteligente.legislativo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import cl.votainteligente.legislativo.model.domainobjects.CommissionDO;

@Entity
@Table(name = "commission")
public class Commission extends Agrupation {

	@Column(name = "executive_lawyer")
	private String executiveLawyer;
	@Column(name = "secretary_lawyer")
	private String secretaryLawyer;
	@Column(name = "assistant_lawyer")
	private String assistantLawyer;
	private String form;
	@Column(name = "phone_number")
	private String phoneNumber;

	@OneToMany
	private Set<LegislatorRole> members;

	@ManyToOne
	@JoinColumn(name = "chamber_id", nullable = false)
	private Chamber chamber;

	@ManyToOne
	@JoinColumn(name = "commission_type_id", nullable = false)
	private CommissionType commissionType;

	public String getAssistantLawyer() {
		return assistantLawyer;
	}

	public void setAssistantLawyer(String assistantLawyer) {
		this.assistantLawyer = assistantLawyer;
	}

	public String getExecutiveLawyer() {
		return executiveLawyer;
	}

	public void setExecutive_lawyer(String executiveLawyer) {
		this.executiveLawyer = executiveLawyer;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSecretaryLawyer() {
		return secretaryLawyer;
	}

	public void setSecretaryLawyer(String secretaryLawyer) {
		this.secretaryLawyer = secretaryLawyer;
	}

	public Chamber getChamber() {
		return chamber;
	}

	public void setChamber(Chamber chamber) {
		this.chamber = chamber;
	}

	public CommissionType getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(CommissionType commissionType) {
		this.commissionType = commissionType;
	}

	/**
	 * @param members
	 *            the members to set
	 */
	public void setMembers(Set<LegislatorRole> members) {
		this.members = members;
	}

	/**
	 * @return the members
	 */
	public Set<LegislatorRole> getMembers() {
		return members;
	}

	@Transient
	public CommissionDO asDomainObject() {
		return new CommissionDO(this);
	}

}