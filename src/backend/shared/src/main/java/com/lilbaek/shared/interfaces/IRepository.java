package com.lilbaek.shared.interfaces;

import org.springframework.data.repository.CrudRepository;

public interface IRepository<T extends IAggregateRoot, ID> extends CrudRepository<T, ID> {

}