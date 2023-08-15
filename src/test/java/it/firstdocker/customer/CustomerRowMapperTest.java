package it.firstdocker.customer;

import it.firstdocker.Component.CustomerRowMapper;
import it.firstdocker.Entity.Gender;
import it.firstdocker.Entity.User;
import org.junit.jupiter.api.Test;


import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRowMapperTest {

    @Test
    void mapRow() throws SQLException {
        CustomerRowMapper customerRowMapper = new CustomerRowMapper();

        ResultSet mock = mock(ResultSet.class);
        when(mock.getLong("id")).thenReturn(1L);
        when(mock.getInt("age")).thenReturn(19);
        when(mock.getString("name")).thenReturn("Jamila");
        when(mock.getString("email")).thenReturn("jamila@gmail.com");
        when(mock.getString("gender")).thenReturn("FEMALE");

        // When
        User user = customerRowMapper.mapRow(mock, 1);

        // Then
        User expected = new User(1L, "Jamila", "jamila@gmail.com", "password", 19, Gender.FEMALE);
        assertThat(user).isEqualTo(expected);
    }
}
