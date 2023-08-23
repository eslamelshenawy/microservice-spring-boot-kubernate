package com.example.dbservice.procedure;

import com.example.dbservice.dto.LoginRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LoginProcedure {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;



    public String validateLogin(LoginRequest loginRequest) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("XXX_MOBILE_LOGIN.VALIDATELOGIN")
                .declareParameters(
                        new SqlParameter("USERNAME", Types.VARCHAR),
                        new SqlParameter("PASSWORD", Types.VARCHAR),
                        new SqlParameter("p_lang", Types.VARCHAR),
                        new SqlOutParameter("P_USER_ID", Types.NUMERIC),
                        new SqlOutParameter("l_result", Types.VARCHAR)
                ).withCatalogName("XXMOB");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("USERNAME", loginRequest.getUsername());

        inParams.put("PASSWORD", loginRequest.getPassword());

        inParams.put("p_lang", "ARABIC");

//        CallableStatementCallback; uncategorized SQLException for SQL [{? = call XXMOB.XXX_MOBILE_LOGIN.VALIDATELOGIN()}]; SQL state [99999]; error code [17041]; ORA-17041:
//        Missing IN or OUT parameter at index: 1

        Map<String, Object> result = jdbcCall.execute(inParams);



        // Retrieve the output parameters

        Integer userId = (Integer) result.get("P_USER_ID");

        String returnValue = (String) result.get("l_result");



        // Process the results as needed

        System.out.println("User ID: " + userId);

        return returnValue;

    }


//    public Map<String, Object> validateLogin(LoginRequest loginRequest) {
//        // Define the SQL statement to call the function with output parameter
//        String sql = "{ ? = call XXMOB.XXX_MOBILE_LOGIN.VALIDATELOGIN(?, ?, ?, ? ) }";
//
//        // Define the input parameters
//        String inputParam1 = loginRequest.getUsername();
//        String inputParam2 = loginRequest.getPassword();
//        String inputParam3 = "ARABIC";
//        String inputParam4 = "";
//
//        // Define the output parameter
////        Map<String, Object> outParams = new HashMap<>();
////        outParams.put("outputParam", Types.VARCHAR);
//
//        SqlParameter[] outParams = {
////                new SqlOutParameter("l_result", Types.VARCHAR),
//                new SqlOutParameter("P_USER_ID", Types.NUMERIC)
//        };
////        SqlParameter[] outParams = { new SqlOutParameter("l_result", Types.VARCHAR)  ,new SqlOutParameter("p_user_id", Types.VARCHAR)};
//        // Execute the function call
//        Map<String, Object> result = jdbcTemplate.call(con -> {
//            var callableStatement = con.prepareCall(sql);
//            callableStatement.registerOutParameter(1, Types.VARCHAR);
//            callableStatement.setString(2, inputParam1);
//            callableStatement.setString(3, inputParam2);
//            callableStatement.setString(4, inputParam3);
//            callableStatement.registerOutParameter(5, Types.VARCHAR);
//            return callableStatement;
//        }, List.of(outParams));
//
//
//        // Retrieve the function result
//        String outputValue = (String) result.get("l_result");
//
//        // Handle the result as needed
//        System.out.println("Output value: " + outputValue);
//
//        return result;
//    }


//    public String validateLogin(LoginRequest loginRequest) {
//        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("XXMOB.XXX_MOBILE_LOGIN.VALIDATELOGIN");
//        query.registerStoredProcedureParameter("username", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("password", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("lang", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("l_result", String.class, ParameterMode.OUT);
//
//        query.setParameter("username", loginRequest.getUsername());
//        query.setParameter("password", loginRequest.getPassword());
//        query.setParameter("lang", loginRequest.getLang());
//
//        query.execute();
//
//        String result = (String) query.getOutputParameterValue("l_result");
//
//        return result;
//
////        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("XXMOB.XXX_MOBILE_LOGIN.VALIDATELOGIN");
////        query.registerStoredProcedureParameter("username", String.class, ParameterMode.IN);
////        query.registerStoredProcedureParameter("password", String.class, ParameterMode.IN);
////        query.registerStoredProcedureParameter("lang", String.class, ParameterMode.IN);
////        query.registerStoredProcedureParameter("l_user_id", Long.class, ParameterMode.OUT);
////        query.registerStoredProcedureParameter("l_result", String.class, ParameterMode.OUT);
////
////        query.setParameter("username", loginRequest.getUsername());
////        query.setParameter("password", loginRequest.getPassword());
////        query.setParameter("lang", loginRequest.getLang());
////
////        query.execute();
////
////        Long userId = (Long) query.getOutputParameterValue("l_user_id");
////        String result = (String) query.getOutputParameterValue("l_result");
////
////        return result;
//
//    }
//
}
