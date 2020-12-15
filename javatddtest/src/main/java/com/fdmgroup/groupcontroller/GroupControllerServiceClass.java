package com.fdmgroup.groupcontroller;

import java.util.Map;

class GroupControllerServiceClass implements GroupControllerService {
    private DatabaseReader reader;
    private DatabaseWriter writer;

    GroupControllerServiceClass(DatabaseReader reader, DatabaseWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public Map<String, Trainee> getAllTrainees() {
        return reader.readGroup();
    }

    @Override
    public void addTrainee(Trainee trainee) {
        writer.addTrainee(trainee);
    }

    @Override
    public void removeTraineeByUsername(String traineeUsername) {
        writer.deleteTraineeByUsername(traineeUsername);
    }
}
