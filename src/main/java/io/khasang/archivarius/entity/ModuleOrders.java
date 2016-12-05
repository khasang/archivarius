package io.khasang.archivarius.entity;

/**
 * Created by Eugene NeocortexF on 05.12.2016.
 * Entity of Orders module of Archivarius
 * Class is describe the data model of orders.jsp page
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ModuleOrders {


    @Id
    @GeneratedValue
    private int orderId;
    private String orderTheme;
    private String orderType;
    private Date orderDate;
    private String orderText;
    private String orderReceiver;
    private String orderPathToAttachedFile;

    public ModuleOrders() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderTheme() {
        return orderTheme;
    }

    public void setOrderTheme(String orderTheme) {
        this.orderTheme = orderTheme;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    public String getOrderReceiver() {
        return orderReceiver;
    }

    public void setOrderReceiver(String orderReciever) {
        this.orderReceiver = orderReciever;
    }

    public String getOrderPathToAttachedFile() {
        return orderPathToAttachedFile;
    }

    public void setOrderPathToAttachedFile(String orderPathToAttachedFile) {
        this.orderPathToAttachedFile = orderPathToAttachedFile;
    }
}
