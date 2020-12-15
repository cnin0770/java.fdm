package com.fdm.pond.model.employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
public @Data class Skill {

	@Id
    private String name;
	private Boolean visible;

	public Skill(String name) {
	    this.name = name;
    }

	@PrePersist
    void prepare() {
	    if (visible == null) visible = true;
    }
}
