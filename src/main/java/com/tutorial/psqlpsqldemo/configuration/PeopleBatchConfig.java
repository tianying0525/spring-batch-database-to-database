package com.tutorial.psqlpsqldemo.configuration;

import com.tutorial.psqlpsqldemo.mapper.PeopleOldRowMapper;
import com.tutorial.psqlpsqldemo.model.PeopleNew;
import com.tutorial.psqlpsqldemo.model.PeopleOld;
import com.tutorial.psqlpsqldemo.processor.PeopleItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class PeopleBatchConfig {

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    @Qualifier("database")
    private DataSource MyDataSource;

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    String read_sql = "SELECT * FROM people_old";
    String write_sql = "INSERT INTO people_new (name_new,gender_new,age_new,query_time) VALUES (:name,:gender,:age,:query_time)";

    @Bean
    @Qualifier("CustomersCompletedScheduledJob")
    public Job GetCustomersCompletedJob() {
        return jobs.get("DatabaseJob")
                .incrementer(new RunIdIncrementer())
                .start(PeopleStep())
                .build();
    }

    @Bean
    public Step PeopleStep() {
        return steps.get("DatabaseStep")
                .<PeopleOld, PeopleNew>chunk(100)
                .reader(peoplereader())//???
                .processor(peopleprocessor())
                .writer(peoplewriter())
                .build();
    }

    @Bean
    public ItemStreamReader<PeopleOld> peoplereader(){
        JdbcCursorItemReader<PeopleOld> reader = new JdbcCursorItemReader<PeopleOld>();
        reader.setDataSource(MyDataSource);
        reader.setSql(read_sql);
        System.err.println("reading");
        reader.setRowMapper(new PeopleOldRowMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<PeopleOld,PeopleNew> peopleprocessor(){

        return new PeopleItemProcessor();

    }

    @Bean
    public ItemWriter<PeopleNew> peoplewriter(){
        JdbcBatchItemWriter<PeopleNew> writer = new JdbcBatchItemWriter<PeopleNew>();
        writer.setDataSource(MyDataSource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<PeopleNew>());
        writer.setSql(write_sql);
        System.err.println("writing");
        return writer;
    }

}
