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
    private String quantity;
    private String orderPrice;
    private String tpTriggerPx;
    private String tpOrdPx;
    private String slTriggerPx;
    private String slOrdPx;
    private String orderId;

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

    public String getQuantity() {
        return quantity;
    }

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

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

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
