package com.orderWithDataBase.service;

import com.orderWithDataBase.entities.GridOrder;
import com.orderWithDataBase.repository.GridOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GridOrderService {

    @Autowired
    GridOrderRepository gridOrderRepository;

    public GridOrder saveGridOrder(GridOrder gridOrderObj)
    {
        GridOrder gridOrder=gridOrderRepository.save(gridOrderObj);
        return gridOrder;
    }
}
