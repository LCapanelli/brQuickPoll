package com.brquickpoll.domain;

import javax.persistence.Entity; 
import javax.persistence.Column;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id; 
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne;

@Entity
public class Vote {

	@Column(name="VOTE_ID")
	@Id
	@GeneratedValue
	private Long id; 

	@ManyToOne
	@JoinColumn(name="OPTION_ID")
	private Option option;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	} 
}
