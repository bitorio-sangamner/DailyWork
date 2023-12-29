package com.orderWithDataBase.service;


import com.orderWithDataBase.entities.AlgoOrder;
import com.orderWithDataBase.repository.AlgoOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgoOrderService {

    @Autowired
    AlgoOrderRepository algoOrderRepository;

    public AlgoOrder placeAlgoOrder(AlgoOrder algoOrderObj)
    {
       AlgoOrder algoOrder=algoOrderRepository.save(algoOrderObj);
       return algoOrder;
    }
}
