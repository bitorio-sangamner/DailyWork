package com.orderWithDataBase.repository;

import com.orderWithDataBase.entities.AlgoOrder;
import org.springframework.data.repository.CrudRepository;

public interface AlgoOrderRepository extends CrudRepository<AlgoOrder,Integer> {

    //void deleteByAlgoOrderId(String algoOrderId);
    public AlgoOrder findByAlgoOrderId(String algoOrderId);
}
