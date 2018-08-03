package com.tutorial.psqlpsqldemo.mapper;

import com.tutorial.psqlpsqldemo.model.PeopleNew;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleNewRowMapper implements RowMapper<PeopleNew>{

    @Override
    public PeopleNew mapRow(ResultSet rs, int rowNum) throws SQLException{
        PeopleNew peopleNew = new PeopleNew();
        peopleNew.setName(rs.getNString("name_new"));
        peopleNew.setAge(rs.getInt("age_new"));
        peopleNew.setGender(rs.getNString("gender_new"));
        peopleNew.setQuery_time(rs.getTimestamp("query_time"));

        return peopleNew;
    }


}
