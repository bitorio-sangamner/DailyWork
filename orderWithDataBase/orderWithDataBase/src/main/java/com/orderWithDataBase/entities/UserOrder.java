package com.orderWithDataBase.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String instrumentId;
    private String tradeMode;
    private String orderSide;
    private String positionSide;

    private String orderType;
//    private String quantity;
    private String orderPrice;
    private String tpTriggerPx;
    private String tpOrdPx;
    private String slTriggerPx;
    private String slOrdPx;
    private String orderId;
    private String takeProfitTriggerPriceType="last";
    private String status;
    private String marginCurrency;//ccy

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    private String Quantity;
    private String orderTag;
    private String orderQuantityUnitSetting;
    private String clientSuppliedAlgoID;
    private String closeFraction;
    private String algoOrderId;
    private String tpTriggerPxType;
    private String slTriggerPxType;
    private String triggerPx;
    private String orderPx;
    private String triggerPxType;

    public String getOrderTag() {
        return orderTag;
    }

    public void setOrderTag(String orderTag) {
        this.orderTag = orderTag;
    }

    public String getOrderQuantityUnitSetting() {
        return orderQuantityUnitSetting;
    }

    public void setOrderQuantityUnitSetting(String orderQuantityUnitSetting) {
        this.orderQuantityUnitSetting = orderQuantityUnitSetting;
    }

    public String getClientSuppliedAlgoID() {
        return clientSuppliedAlgoID;
    }

    public void setClientSuppliedAlgoID(String clientSuppliedAlgoID) {
        this.clientSuppliedAlgoID = clientSuppliedAlgoID;
    }

    public String getCloseFraction() {
        return closeFraction;
    }

    public void setCloseFraction(String closeFraction) {
        this.closeFraction = closeFraction;
    }

    public String getAlgoOrderId() {
        return algoOrderId;
    }

    public void setAlgoOrderId(String algoOrderId) {
        this.algoOrderId = algoOrderId;
    }

    public String getTpTriggerPxType() {
        return tpTriggerPxType;
    }

    public void setTpTriggerPxType(String tpTriggerPxType) {
        this.tpTriggerPxType = tpTriggerPxType;
    }

    public String getSlTriggerPxType() {
        return slTriggerPxType;
    }

    public void setSlTriggerPxType(String slTriggerPxType) {
        this.slTriggerPxType = slTriggerPxType;
    }

    public String getTriggerPx() {
        return triggerPx;
    }

    public void setTriggerPx(String triggerPx) {
        this.triggerPx = triggerPx;
    }

    public String getOrderPx() {
        return orderPx;
    }

    public void setOrderPx(String orderPx) {
        this.orderPx = orderPx;
    }

    public String getTriggerPxType() {
        return triggerPxType;
    }

    public void setTriggerPxType(String triggerPxType) {
        this.triggerPxType = triggerPxType;
    }

    public String getMarginCurrency() {
        return marginCurrency;
    }

    public void setMarginCurrency(String marginCurrency) {
        this.marginCurrency = marginCurrency;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }



    public String getTakeProfitTriggerPriceType() {
        return takeProfitTriggerPriceType;
    }

    public void setTakeProfitTriggerPriceType(String takeProfitTriggerPriceType) {
        this.takeProfitTriggerPriceType = takeProfitTriggerPriceType;
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getId() {
        return id;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getTradeMode() {
        return tradeMode;
    }

    public String getOrderSide() {
        return orderSide;
    }

    public String getPositionSide() {
        return positionSide;
    }

    public String getOrderType() {
        return orderType;
    }

//    public String getQuantity() {
//        return quantity;
//    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public String getTpTriggerPx() {
        return tpTriggerPx;
    }

    public String getTpOrdPx() {
        return tpOrdPx;
    }

    public String getSlTriggerPx() {
        return slTriggerPx;
    }

    public String getSlOrdPx() {
        return slOrdPx;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    public void setOrderSide(String orderSide) {
        this.orderSide = orderSide;
    }

    public void setPositionSide(String positionSide) {
        this.positionSide = positionSide;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setTpTriggerPx(String tpTriggerPx) {
        this.tpTriggerPx = tpTriggerPx;
    }

    public void setTpOrdPx(String tpOrdPx) {
        this.tpOrdPx = tpOrdPx;
    }

    public void setSlTriggerPx(String slTriggerPx) {
        this.slTriggerPx = slTriggerPx;
    }

    public void setSlOrdPx(String slOrdPx) {
        this.slOrdPx = slOrdPx;
    }



}
