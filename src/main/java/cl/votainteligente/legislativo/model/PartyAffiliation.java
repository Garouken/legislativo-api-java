package cl.votainteligente.legislativo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "party_affiliation")
public class PartyAffiliation {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@ManyToOne
	@JoinColumn(name = "party_id", nullable = false)
	private Party party;

	@Column(name = "admission_date")
	private Date admissionDate;

	@Column(name = "departure_date")
	private Date departureDate;

	public Long getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}

	public Party getParty() {
		return party;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

}