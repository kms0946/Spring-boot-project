package com.project.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration //java 기반의 설정 파일로 인식
@PropertySource("classpath:/application.properties") //클래스에서 참조할 properties의 경로를 선언
public class DatabaseConfig {

    @Autowired
    private ApplicationContext context; //spring container, bean의 생성과 사용, 생명주기 관리

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    //히카리 cp 생성
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean
    //datasource 객체 생성
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    //SqlSessionFactory 객체 생성
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
        factoryBean.setConfiguration(mybatisConfig());
        return factoryBean.getObject();
    }

    @Bean
    //DB 커밋, 롤백 등 SQL의 실행에 필요한 모든 메서드를 갖는 객체
    public SqlSessionTemplate sqlSession() throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig(){
        return new org.apache.ibatis.session.Configuration();
    }
}
