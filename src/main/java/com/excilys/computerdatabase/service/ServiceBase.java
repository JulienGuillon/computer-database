package com.excilys.computerdatabase.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.pagination.Pagination;

/**
 * @author Guillon Julien
 *
 * 22 mars 2017
 */


public interface ServiceBase<T> {
    public Iterable<T> findAll();

    public int getNumber(String filter);

    public Optional<T> find(long id);
    
    public Pagination<T> getPage(Pagination<T> page);

}
