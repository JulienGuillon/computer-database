package com.excilys.computerdatabase.service;

import java.util.List;
import java.util.Optional;
import com.excilys.computerdatabase.pagination.Page;

/**
 * @author Guillon Julien
 *
 * 22 mars 2017
 */
public interface ServiceBase<T> {
    public List<T> findAll();

    public int getNumber(String filter);

    public Optional<T> find(long id);
    
    public Page<T> getPage(Page<T> page);

}
