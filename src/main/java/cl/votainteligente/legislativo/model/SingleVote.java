package cl.votainteligente.legislativo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "single_vote")
public class SingleVote {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne
	@JoinColumn(name = "legislator_role_id")
	private LegislatorRole legislatorRole;

	@ManyToOne
	@JoinColumn(name = "vote_id")
	private Vote vote;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the legislatorRole
	 */
	public LegislatorRole getLegislatorRole() {
		return legislatorRole;
	}

	/**
	 * @param legislatorRole
	 *            the legislatorRole to set
	 */
	public void setLegislatorRole(LegislatorRole legislatorRole) {
		this.legislatorRole = legislatorRole;
	}

	/**
	 * @return the vote
	 */
	public Vote getVote() {
		return vote;
	}

	/**
	 * @param vote
	 *            the vote to set
	 */
	public void setVote(Vote vote) {
		this.vote = vote;
	}
}
