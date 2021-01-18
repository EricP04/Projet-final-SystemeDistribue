package com.example.demo.Message;

import java.io.Serializable;

public class OrderMessage implements Serializable {

    private Integer idOrder;
    private Integer status;

    public OrderMessage() {
    }

    public OrderMessage(Integer idOrder, Integer status) {
        this.idOrder = idOrder;
        this.status = status;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "idOrder=" + idOrder +
                ", status=" + status +
                '}';
    }
}
