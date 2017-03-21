package com.excilys.computerdatabase.springConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author Guillon Julien
 *
 * 20 mars 2017
 */



@Configuration
@ComponentScan("com.excilys.computerdatabase")
public class AppConfig {
   /** @Bean
    public DatabaseManager databaseManager() {
        return new DatabaseManager();
    }
    
    @Bean
    public ServiceComputer serviceComputer() {
        return new ServiceComputer();
    }
    
    @Bean
    public CrudComputerImpl crudComputerImpl() {
        return new CrudComputerImpl();
    }
    
    
    @Bean
    public ServiceCompany serviceCompany() {
        return new ServiceCompany();
    }
    
    @Bean
    public CrudCompanyImpl crudCompanyImpl() {
        return new CrudCompanyImpl();
    }
    
    @Bean
    public PaginationComputer paginationComputer() {
        return new PaginationComputer();
    }
    
    @Bean
    public PaginationCompany paginationCompany() {
        return new PaginationCompany();
    }**/
}