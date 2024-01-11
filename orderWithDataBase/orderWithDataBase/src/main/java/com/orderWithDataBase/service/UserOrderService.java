package com.orderWithDataBase.service;

import com.okex.open.api.bean.trade.param.AmendOrder;
import com.okex.open.api.bean.trade.param.CancelAlgoOrder;
import com.orderWithDataBase.entities.UserOrder;
import com.orderWithDataBase.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String cancelAlgoOrder(List<CancelAlgoOrder> cancelAlgoOrderList)
    {
        System.out.println("inside cancelAlgoOrder method...");
        int size=cancelAlgoOrderList.size();
        String algoOrderId="";
        try {
            for (int i = 0; i < size; i++) {
                algoOrderId = cancelAlgoOrderList.get(i).getAlgoId();
                System.out.println("algoId "+algoOrderId);
                UserOrder algoOrder=userOrderRepository.findByAlgoOrderId(algoOrderId);
                userOrderRepository.deleteById(algoOrder.getId());
            }
            return "order deleted";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "something went wrong!!";
        }

    }//cancelAlgoOrder


    public String amendOrder(AmendOrder amendOrderObj)
    {
        String orderId=amendOrderObj.getOrdId();
        String newSize=amendOrderObj.getNewSz();
        String newPrice=amendOrderObj.getNewPx();
        String newStopLossTriggerPrice=amendOrderObj.getNewSlTriggerPx();
        String newStopLossOrderPrice=amendOrderObj.getNewSlOrdPx();

        UserOrder userOrderObj=userOrderRepository.findByOrderId(orderId);

        if (userOrderObj != null) {
            try {
                if (!newSize.equals("") && !newSize.equals(" ") && newSize != null) {
                    userOrderObj.setQuantity(newSize);
                }
                if (!newPrice.equals("") && !newPrice.equals(" ") && newPrice != null) {
                    userOrderObj.setOrderPrice(newPrice);
                }

                if (!newStopLossTriggerPrice.equals("") && !newStopLossTriggerPrice.equals(" ") && newStopLossTriggerPrice != null) {
                    userOrderObj.setSlTriggerPx(newStopLossTriggerPrice);
                }

                if (!newStopLossOrderPrice.equals("") && !newStopLossOrderPrice.equals(" ") && newStopLossOrderPrice != null) {
                    userOrderObj.setSlOrdPx(newStopLossOrderPrice);
                }
            }
            catch(Exception e)
            {
              System.out.println(e.getMessage());
            }

            userOrderRepository.save(userOrderObj);
            return "order amended";
        }
        else {
            return "order not found"; // Handle the case where the order is not found
        }

    }//method



    public String updatedStatus(String orderId,String status)
    {
        UserOrder userOrder=userOrderRepository.findByOrderId(orderId);
        if (userOrder != null)
        {
            userOrder.setStatus(status);
            userOrderRepository.save(userOrder);
            return "Status updated";
        }
        else
        {
            return "Order not found for orderId: " + orderId;
        }

    }


}
