package io.khasang.archivarius.dao;

/**
 * Created by Eugene NeocortexF  on 05.12.2016.
 */

import io.khasang.archivarius.entity.ModuleOrders;
import java.util.List;

public interface ModuleOrdersDAO {

    void addOrder(ModuleOrders moduleOrders);

    void updateOrder(ModuleOrders moduleOrders);

    void deleteOrder(ModuleOrders moduleOrders);

    ModuleOrders getOrderById(int orderId);

    ModuleOrders getOrderByTheme(String orderTheme);

    List<ModuleOrders> getOrderList();

}

