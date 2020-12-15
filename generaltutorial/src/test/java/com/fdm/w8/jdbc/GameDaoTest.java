package com.fdm.w8.jdbc;

import org.junit.*;
import org.mockito.*;

import java.sql.*;

import static org.mockito.Mockito.*;

public class GameDaoTest {
    @Mock
    ConnectionPool mockPool;

    @Mock
    Connection mockConnection;

    @Mock
    PreparedStatement mockPs;

    @Mock
    Game mockGame;

    GameDao dao;

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);
        dao = new GameDao(mockPool);
    }

    @Test
    public void that_insert_item_into_db() throws SQLException {
        when(mockPool.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);

        dao.safeInsert(mockGame);

        InOrder order = inOrder(mockPool, mockConnection, mockPs);
        order.verify(mockPool, times(1)).getConnection();
        order.verify(mockConnection, times(1)).prepareStatement(anyString());
        order.verify(mockPs, times(1)).setInt(1, mockGame.getId());
        order.verify(mockPs, times(1)).setString(2, mockGame.getName());
        order.verify(mockPs, times(1)).executeUpdate();
        order.verify(mockPs, times(1)).close();
        order.verify(mockPool, times(1)).release(mockConnection);
    }
}
