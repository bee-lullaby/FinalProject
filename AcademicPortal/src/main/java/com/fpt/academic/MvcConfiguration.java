package com.fpt.academic;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fpt.academic.dao.ClassCourseDAO;
import com.fpt.academic.dao.ClassCourseDAOImpl;
import com.fpt.academic.dao.ClassCourseTeacherDAO;
import com.fpt.academic.dao.ClassCourseTeacherDAOImpl;
import com.fpt.academic.dao.ClassDAO;
import com.fpt.academic.dao.ClassDAOImpl;
import com.fpt.academic.dao.CourseDAO;
import com.fpt.academic.dao.CourseDAOImpl;
import com.fpt.academic.dao.RoomDAO;
import com.fpt.academic.dao.RoomDAOImpl;
import com.fpt.academic.dao.SemesterDAO;
import com.fpt.academic.dao.SemesterDAOImpl;
import com.fpt.academic.dao.TeacherCourseDAO;
import com.fpt.academic.dao.TeacherCourseDAOImpl;
import com.fpt.academic.dao.TeacherDAO;
import com.fpt.academic.dao.TeacherDAOImpl;
import com.fpt.academic.model.Semester;


@Configuration
@ComponentScan
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{
	
	
	@Bean 
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
     
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
 
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/academic_portal");
        dataSource.setUsername("hans");
        dataSource.setPassword("14121993");
        return dataSource;
    }
     
    @Bean
    public TeacherDAO getTeacherDAO() {
        return new TeacherDAOImpl(getDataSource());
    }
    
    @Bean
    public CourseDAO getCourseDAO() {
        return new CourseDAOImpl(getDataSource());
    }
    
    
    @Bean
    public RoomDAO getRoomDAO() {
        return new RoomDAOImpl(getDataSource());
    }
    
    @Bean
    public ClassDAO getClassDAO() {
        return new ClassDAOImpl(getDataSource());
    }
    
    @Bean
    public SemesterDAO getSemesterDAO() {
        return new SemesterDAOImpl(getDataSource());
    }
    
    @Bean
    public TeacherCourseDAO getTeacherCourseDAO() {
        return new TeacherCourseDAOImpl(getDataSource());
    }
    
    @Bean
    public ClassCourseDAO getClassCourseDAO() {
        return new ClassCourseDAOImpl(getDataSource());
    }
    
    @Bean
    public ClassCourseTeacherDAO getClassCourseTeacherDAO() {
        return new ClassCourseTeacherDAOImpl(getDataSource());
    }
}


