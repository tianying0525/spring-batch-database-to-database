package com.tutorial.psqlpsqldemo.processor;

import com.tutorial.psqlpsqldemo.model.PeopleNew;
import com.tutorial.psqlpsqldemo.model.PeopleOld;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Timestamp;

public class PeopleItemProcessor  implements ItemProcessor<PeopleOld, PeopleNew>{

    @Override
    public PeopleNew process(PeopleOld peopleOld) throws Exception{
        PeopleNew peopleNew = new PeopleNew();
        peopleNew.setName(peopleOld.getName());
        peopleNew.setGender(peopleOld.getGender());
        peopleNew.setAge(peopleOld.getAge());
        peopleNew.setQuery_time(new Timestamp(System.currentTimeMillis()));

        return peopleNew;
    }
}
