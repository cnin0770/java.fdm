package com.fdmgroup.groupcontroller;

import java.util.Map;

interface GroupControllerService {
	Map<String, Trainee> getAllTrainees();
	void addTrainee(Trainee trainee);
	void removeTraineeByUsername(String traineeUsername);
}
