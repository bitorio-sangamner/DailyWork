package com.orderWithDataBase.repository;

import com.orderWithDataBase.entities.UserOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserOrderRepository extends CrudRepository<UserOrder,Integer> {

    public UserOrder findByOrderId(String orderId);
    public UserOrder findByAlgoOrderId(String algoOrderId);
    public List<UserOrder> findByOrderType(String orderType);
}
