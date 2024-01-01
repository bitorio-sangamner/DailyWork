package com.orderWithDataBase.service;


import com.okex.open.api.bean.trade.param.AmendAlgos;
import com.okex.open.api.bean.trade.param.CancelAlgoOrder;
import com.orderWithDataBase.entities.AlgoOrder;
import com.orderWithDataBase.repository.AlgoOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlgoOrderService {

    @Autowired
    AlgoOrderRepository algoOrderRepository;

    public AlgoOrder placeAlgoOrder(AlgoOrder algoOrderObj)
    {
       AlgoOrder algoOrder=algoOrderRepository.save(algoOrderObj);
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
                 AlgoOrder algoOrder=algoOrderRepository.findByAlgoOrderId(algoOrderId);
                 algoOrderRepository.deleteById(algoOrder.getId());
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

        AlgoOrder algoOrder=algoOrderRepository.findByAlgoOrderId(algoOrderId);

        if(instrumentId!=null && !instrumentId.equals("") && !instrumentId.equals(" "))
        {
            algoOrder.setInstrumentId(instrumentId);
        }

        if(algoOrderId!=null && !algoOrderId.equals("") && !algoOrderId.equals(" "))
        {
            algoOrder.setAlgoOrderId(algoOrderId);
        }

        if(newSize!=null && !newSize.equals("") && !newSize.equals(" "))
        {
            algoOrder.setQuantity(newSize);
        }

        if(newTpTriggerPx!=null && !newTpTriggerPx.equals("") && !newTpTriggerPx.equals(" "))
        {
            algoOrder.setTpTriggerPx(newTpTriggerPx);
        }

        if(newTpOrdPx!=null && !newTpOrdPx.equals("") && !newTpOrdPx.equals(" "))
        {
            algoOrder.setTpOrdPx(newTpOrdPx);
        }

        if(newSlTriggerPx!=null && !newSlTriggerPx.equals("") && !newSlTriggerPx.equals(" "))
        {
            algoOrder.setSlTriggerPx(newSlTriggerPx);
        }

        if(newSlOrdPx!=null && !newSlOrdPx.equals("") && !newSlOrdPx.equals(" "))
        {
            algoOrder.setSlOrdPx(newSlOrdPx);
        }

        if(newTpTriggerPxType!=null && !newTpTriggerPxType.equals("") && !newTpTriggerPxType.equals(" "))
        {
            algoOrder.setTpTriggerPxType(newTpTriggerPxType);
        }

        if(newSlTriggerPxType!=null && !newSlTriggerPxType.equals("") && !newSlTriggerPxType.equals(" "))
        {
            algoOrder.setSlTriggerPxType(newSlTriggerPxType);
        }

          AlgoOrder order=algoOrderRepository.save(algoOrder);
        return "order updated";
    }
}
