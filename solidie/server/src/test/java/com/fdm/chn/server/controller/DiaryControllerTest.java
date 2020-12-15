package com.fdm.chn.server.controller;

import com.fdm.chn.server.model.Diary;
import com.fdm.chn.server.model.DiaryRepo;
import com.fdm.chn.server.model.User;
import com.fdm.chn.server.model.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class DiaryControllerTest {
//    @Mock
    DiaryRepo mockDiaryRepo;
//    @Mock
    UserRepo mockUserRepo;
//    @Mock
    Diary mockDiary;
//    @Mock
    OAuth2User mockOauth2User;
//    @Mock
//    Optional<User> mockOptionalUser;
//    @Mock
    Map<String, Object> mockMap;
//    @Mock
    Object mockObj;

    DiaryController controller;

//    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        controller = new DiaryController(mockUserRepo, mockDiaryRepo);
    }

//    @Test
    public void that_find_calls_repo() {
        controller.find(mockDiary.getId());
        verify(mockDiaryRepo, times(1)).findById(mockDiary.getId());
    }

//    @Test
    public void that_create_calls_user_repo() throws Exception {
        when(mockOauth2User.getAttributes()).thenReturn(mockMap);
        when(mockMap.get("sub")).thenReturn(mockObj);
        when(mockObj.toString()).thenReturn("test");

        controller.create(mockDiary, mockOauth2User);

        verify(mockUserRepo, times(1)).findById("test");
        verify(mockDiaryRepo, times(1)).save(mockDiary);
    }
}
