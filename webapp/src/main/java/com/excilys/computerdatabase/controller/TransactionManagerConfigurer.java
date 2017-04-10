package com.excilys.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.excilys.computerdatabase.persistence.Datasource;

/**
 * @author Guillon Julien
 *
 * 23 mars 2017
 */
@Repository
public class TransactionManagerConfigurer implements TransactionManagementConfigurer {

    @Autowired
    private Datasource datasource;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(datasource);
    }

}
