package com.orderWithDataBase.service;

import com.okex.open.api.bean.trade.param.AmendOrder;
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

    public String cancelOrder(String orderId)
    {
        try {
            UserOrder userOrderObj = userOrderRepository.findByOrderId(orderId);
            userOrderRepository.delete(userOrderObj);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return "order deleted";

    }

    public String amendOrder(AmendOrder amendOrderObj)
    {
        String orderId=amendOrderObj.getOrdId();
        String newSize=amendOrderObj.getNewSz();
        String newPrice=amendOrderObj.getNewPx();
        String newStopLossTriggerPrice=amendOrderObj.getNewSlTriggerPx();
        String newStopLossOrderPrice=amendOrderObj.getNewSlOrdPx();

        UserOrder userOrderObj=userOrderRepository.findByOrderId(orderId);

        if (userOrderObj != null) {
            if (!newSize.equals("") && !newSize.equals(" ") && newSize != null) {
                userOrderObj.setQuantity(newSize);
            }
            if (!newPrice.equals("") && !newPrice.equals(" ") && newPrice != null) {
                userOrderObj.setOrderPrice(newPrice);
            }

            if(!newStopLossTriggerPrice.equals("") && !newStopLossTriggerPrice.equals(" ") && newStopLossTriggerPrice!=null)
            {
                userOrderObj.setSlTriggerPx(newStopLossTriggerPrice);
            }

            if(!newStopLossOrderPrice.equals("") && !newStopLossOrderPrice.equals(" ") && newStopLossOrderPrice!=null)
            {
                userOrderObj.setSlOrdPx(newStopLossOrderPrice);
            }

            userOrderRepository.save(userOrderObj);
            return "order amended";
        }
        else {
            return "order not found"; // Handle the case where the order is not found
        }

    }//method
}
