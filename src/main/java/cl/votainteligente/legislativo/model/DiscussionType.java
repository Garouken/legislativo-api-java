package cl.votainteligente.legislativo.model;

import javax.persistence.*;

@Entity
@Table(name = "discussion_type")
public class DiscussionType {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
