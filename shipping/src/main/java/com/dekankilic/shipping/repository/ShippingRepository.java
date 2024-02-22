package com.dekankilic.shipping.repository;

import com.dekankilic.shipping.model.Shipping;
import org.springframework.data.repository.CrudRepository;

public interface ShippingRepository extends CrudRepository<Shipping, Long> {
}
