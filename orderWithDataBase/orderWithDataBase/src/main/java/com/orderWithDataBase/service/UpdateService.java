package com.orderWithDataBase.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orderWithDataBase.entities.UserOrder;
import com.orderWithDataBase.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class UpdateService {

    @Autowired
    UserOrderRepository userOrderRepository;

    @Autowired
    TradeService tradeService;

    @Autowired
    UserOrderService userOrderService;

    @Autowired
    AlgoOrderService algoOrderService;

    @Autowired
    JSONObject json;

    public void getAllOrdersFromLocalDataBase()
    {

        ArrayList<UserOrder> orderList= (ArrayList<UserOrder>) userOrderRepository.findAll();
        Iterator<UserOrder> itr=orderList.iterator();
        while(itr.hasNext())
        {
            UserOrder userOrder=itr.next();
            System.out.println(userOrder.getOrderType());

            if(userOrder.getOrderType().equals("limit") || userOrder.getOrderType().equals("market"))
            {
                String orderId=userOrder.getOrderId();
                json=tradeService.getOrderDetails(userOrder.getInstrumentId(),orderId);
                JSONArray dataArray=json.getJSONArray("data");
                if(dataArray.isEmpty())
                {
                    System.out.println("Order does not exist");
                }
                else {
                    JSONObject jsonObject = dataArray.getJSONObject(0);
                    String status = jsonObject.getString("state");

                    userOrderService.updatedStatus(orderId, status);
                }

            }
            else if(userOrder.getOrderType().equals("conditional") || userOrder.getOrderType().equals("trigger"))
            {
                String algoOrderId=userOrder.getAlgoOrderId();
                json=tradeService.getAlgoOrderDetails(algoOrderId);
                JSONArray dataArray=json.getJSONArray("data");

                if(dataArray.isEmpty())
                {
                    System.out.println("Order does not exist");
                }

                else {
                    JSONObject jsonObject = dataArray.getJSONObject(0);
                    String status = jsonObject.getString("state");

                    algoOrderService.updateStatusOfOrder(algoOrderId, status);
                }
            }
        }//while
        System.out.println("*************out from while loop********************");
    }
}
