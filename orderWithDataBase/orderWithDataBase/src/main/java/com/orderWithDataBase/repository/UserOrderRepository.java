package com.orderWithDataBase.repository;

import com.orderWithDataBase.entities.UserOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserOrderRepository extends CrudRepository<UserOrder,Integer> {
}
