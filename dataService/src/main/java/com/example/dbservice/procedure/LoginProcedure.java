package com.example.dbservice.procedure;

import com.example.dbservice.dto.LoginRequest;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginProcedure {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> validateLogin(LoginRequest loginRequest, String lang) {
        String statement = "{ ? = call XXMOB.XXX_MOBILE_LOGIN.VALIDATELOGIN(?, ? , ?, ?) }";
        Session session = entityManager.unwrap(Session.class);
        CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
            @Override
            public CallableStatement execute(Connection connection) throws SQLException {
                CallableStatement function = connection.prepareCall(
                        statement);
                function.setString(2, loginRequest.getUsername());
                function.setString(3, loginRequest.getPassword());
                function.setString(4, lang);
                function.registerOutParameter(5, Types.NUMERIC);
                function.registerOutParameter(1, Types.VARCHAR);
                function.execute();
                return function;
            }
        });

        Map<String, Object> result = new HashMap<>();
        try {
            result.put("p_user_id", callableStatement.getInt(5));
            result.put("result", callableStatement.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


}
