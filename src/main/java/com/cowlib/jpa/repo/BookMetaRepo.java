package com.cowlib.jpa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cowlib.jpa.model.BookMeta;

@Repository
public interface BookMetaRepo extends CrudRepository<BookMeta, Long> {

}
