package com.fdm.pond.model.schedule;


import javax.persistence.*;

import com.fdm.pond.model.employee.Consultant;
import com.fdm.pond.model.opportunity.Opportunity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Interview extends ScheduleEntry{

	private String time;
	private String location;
	private String type;
	@ManyToOne
	private Opportunity opportunity;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	public Date getDate() {
		return super.getDate();
	}

	@Override
	public void setDate(Date date) {
		super.setDate(date);
	}

	@Override
	public String getContent() {
		return super.getContent();
	}

	@Override
	public void setContent(String content) {
		super.setContent(content);
	}

	@Override
	public Consultant getConsultant() {
		return super.getConsultant();
	}

	@Override
	public void setConsultant(Consultant consultant) {
		super.setConsultant(consultant);
	}
}
