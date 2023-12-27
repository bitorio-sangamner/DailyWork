package com.orderWithDataBase.service;

import com.orderWithDataBase.entities.UserOrder;
import com.orderWithDataBase.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOrderService {

    @Autowired
    UserOrderRepository userOrderRepository;

    public UserOrder saveOrder(UserOrder order)
    {
        UserOrder userOrderObj=userOrderRepository.save(order);
        return userOrderObj;
    }
}
