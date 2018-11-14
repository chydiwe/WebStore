package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.OrderInfo;

public interface OrderInfoRepository {

    void save(OrderInfo orderInfo);

    void delete(OrderInfo orderInfo);
}
