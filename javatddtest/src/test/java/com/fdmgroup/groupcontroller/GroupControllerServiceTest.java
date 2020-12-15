package com.fdmgroup.groupcontroller;

import org.junit.*;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class GroupControllerServiceTest {
    @Mock
    DatabaseReader mockDbReader;

    @Mock
    DatabaseWriter mockDbWriter;

    @Mock
    Trainee mockTrainee;

    GroupControllerService service;

    @Before
    public void initMockTest() {
        MockitoAnnotations.initMocks(this);
        service = new GroupControllerServiceClass(mockDbReader, mockDbWriter);
    }

    @Test
    public void getAllTrainees_calls_reader() {
        service.getAllTrainees();
        verify(mockDbReader, times(1)).readGroup();
    }

    @Test
    public void removeTrainee_calls_writer() {
        service.removeTraineeByUsername(anyString());
        verify(mockDbWriter, never()).deleteTraineeByUsername("anyString()");
        verify(mockDbWriter, times(1)).deleteTraineeByUsername(anyString());
    }

    @Test
    public void addTrainee_pass_trainee_obj_to_writer() {
        service.addTrainee(mockTrainee);
        verify(mockDbWriter, never()).addTrainee(new Trainee());
        verify(mockDbWriter, times(1)).addTrainee(mockTrainee);
    }
}
