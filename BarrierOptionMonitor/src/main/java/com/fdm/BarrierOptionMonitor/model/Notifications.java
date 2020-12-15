package com.fdm.BarrierOptionMonitor.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notifications {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int knockedIn;
	private int knockedOut;
	private int expired;
	@OneToOne
	private Staff staff;
	private int total;
	
	public void clearAll() {
		knockedIn = 0;
		knockedOut = 0;
		expired = 0;
		total = 0;
	}

	public void update(OptionStatus status) {
		if (status == null) return;
		total ++;
		switch (status) {
			case KNOCKED_IN:
				knockedIn ++;
				break;
			case KNOCKED_OUT:
				knockedOut ++;
				break;
			case EXPIRED:
				expired ++;
				break;
		}
	}
}
