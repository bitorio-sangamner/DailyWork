package com.orderWithDataBase.repository;

import com.orderWithDataBase.entities.GridOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface GridOrderRepository extends CrudRepository<GridOrder,Integer> {
}
