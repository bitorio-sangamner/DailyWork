package com.orderWithDataBase.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlgoOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String instrumentId;
    private String tradeMode;
    private String marginCurrency;//ccy
    private String orderSide;
    private String positionSide;
    private String orderType;
    private String Quantity;
    private String orderTag;

    private String orderQuantityUnitSetting;
    private String clientSuppliedAlgoID;
    private String closeFraction;

    private String algoOrderId;
    private String tpTriggerPx;
    private String tpTriggerPxType;
    private String tpOrdPx;
    private String slTriggerPx;
    private String slTriggerPxType;
    private String slOrdPx;
}
