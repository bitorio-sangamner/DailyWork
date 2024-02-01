package com.orderWithDataBase.repository;

import com.orderWithDataBase.entities.GridOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GridOrderRepository extends CrudRepository<GridOrder,Integer> {

    //custom finder methods
    public GridOrder findByAlgoId(String algoId);
    public List<GridOrder> findByAlgoOrdType(String algoOrderType);
}
