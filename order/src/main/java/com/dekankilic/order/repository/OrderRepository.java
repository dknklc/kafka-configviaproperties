package com.dekankilic.order.repository;

import com.dekankilic.order.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
