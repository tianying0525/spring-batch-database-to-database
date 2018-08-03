package com.tutorial.psqlpsqldemo.mapper;

import com.tutorial.psqlpsqldemo.model.PeopleOld;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleOldRowMapper implements RowMapper<PeopleOld> {

    @Override
    public PeopleOld mapRow(ResultSet rs, int rowNum) throws SQLException {
        PeopleOld peopleOld = new PeopleOld();
        peopleOld.setName(rs.getString("name_old"));
        peopleOld.setAge(rs.getInt("age_old"));
        peopleOld.setGender(rs.getString("gender_old"));

        return peopleOld;
    }


}
