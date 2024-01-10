package com.orderWithDataBase.service;

import com.okex.open.api.bean.trade.param.AmendAlgos;
import com.okex.open.api.bean.trade.param.CancelAlgoOrder;
//import com.orderWithDataBase.entities.AlgoOrder;
import com.orderWithDataBase.entities.UserOrder;
//import com.orderWithDataBase.repository.AlgoOrderRepository;
import com.orderWithDataBase.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgoOrderService {

//    @Autowired
//    AlgoOrderRepository algoOrderRepository;
    @Autowired
    UserOrderRepository userOrderRepository;

    public UserOrder placeAlgoOrder(UserOrder algoOrderObj)
    {
        UserOrder algoOrder=userOrderRepository.save(algoOrderObj);
       return algoOrder;
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

    public String amendAlgoOrder(AmendAlgos amendAlgosObj)
    {
        String instrumentId=amendAlgosObj.getInstId();
        String algoOrderId=amendAlgosObj.getAlgoId();
        String newSize=amendAlgosObj.getNewSz();
        String newTpTriggerPx=amendAlgosObj.getNewTpTriggerPx();
        String newTpOrdPx=amendAlgosObj.getNewTpOrdPx();
        String newSlTriggerPx=amendAlgosObj.getNewSlTriggerPx();
        String newSlOrdPx=amendAlgosObj.getNewSlOrdPx();
        String newTpTriggerPxType=amendAlgosObj.getNewTpTriggerPxType();
        String newSlTriggerPxType=amendAlgosObj.getNewSlTriggerPxType();

        UserOrder algoOrder=userOrderRepository.findByAlgoOrderId(algoOrderId);

        if(algoOrder!=null)
        {
            try {
                if (instrumentId != null && !instrumentId.equals("") && !instrumentId.equals(" ")) {
                    algoOrder.setInstrumentId(instrumentId);
                }

                if (algoOrderId != null && !algoOrderId.equals("") && !algoOrderId.equals(" ")) {
                    algoOrder.setAlgoOrderId(algoOrderId);
                }

                if (newSize != null && !newSize.equals("") && !newSize.equals(" ")) {
                    algoOrder.setQuantity(newSize);
                }

                if (newTpTriggerPx != null && !newTpTriggerPx.equals("") && !newTpTriggerPx.equals(" ")) {
                    algoOrder.setTpTriggerPx(newTpTriggerPx);
                }

                if (newTpOrdPx != null && !newTpOrdPx.equals("") && !newTpOrdPx.equals(" ")) {
                    algoOrder.setTpOrdPx(newTpOrdPx);
                }

                if (newSlTriggerPx != null && !newSlTriggerPx.equals("") && !newSlTriggerPx.equals(" ")) {
                    algoOrder.setSlTriggerPx(newSlTriggerPx);
                }

                if (newSlOrdPx != null && !newSlOrdPx.equals("") && !newSlOrdPx.equals(" ")) {
                    algoOrder.setSlOrdPx(newSlOrdPx);
                }

                if (newTpTriggerPxType != null && !newTpTriggerPxType.equals("") && !newTpTriggerPxType.equals(" ")) {
                    algoOrder.setTpTriggerPxType(newTpTriggerPxType);
                }

                if (newSlTriggerPxType != null && !newSlTriggerPxType.equals("") && !newSlTriggerPxType.equals(" ")) {
                    algoOrder.setSlTriggerPxType(newSlTriggerPxType);
                }
            }//try
            catch(Exception e)
            {

            }
        }

          UserOrder order=userOrderRepository.save(algoOrder);
        return "order updated";
    }

    public UserOrder getAlgoOrderDetails(String algoOrderId)
    {
        UserOrder algoOrderObj=userOrderRepository.findByAlgoOrderId(algoOrderId);
        return algoOrderObj;
    }

    public List<UserOrder> getAlgoOrderList(String orderType)
    {
       List<UserOrder> algoOrderList=userOrderRepository.findByOrderType(orderType);
       return algoOrderList;
    }

    public UserOrder placeAlgoTriggerOrder(UserOrder algoOrder)
    {
        UserOrder order=userOrderRepository.save(algoOrder);
        return order;
    }

    public String updateStatusOfOrder(String algoOrderId,String status)
    {
        UserOrder order=userOrderRepository.findByAlgoOrderId(algoOrderId);

        if(order!=null)
        {
            order.setStatus(status);
            userOrderRepository.save(order);
            return "Status updated";
        }
        else
        {
            return "Order not found for orderId: " + algoOrderId;
        }

    }
}
