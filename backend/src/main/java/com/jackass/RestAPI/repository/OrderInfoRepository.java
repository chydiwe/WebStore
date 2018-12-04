package com.jackass.RestAPI.repository;

import com.jackass.RestAPI.entity.OrderInfo;
import com.jackass.RestAPI.entity.id.OrderInfoId;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = OrderInfo.class, idClass = OrderInfoId.class)
public interface OrderInfoRepository {

    void save(OrderInfo orderInfo);

    void delete(OrderInfo orderInfo);
}
