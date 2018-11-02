package com.jackass.RestAPI.repository.production;

import com.jackass.RestAPI.conf.ConditionsConfig;
import com.jackass.RestAPI.entity.User;
import com.jackass.RestAPI.repository.UserRepository;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
@Conditional(ConditionsConfig.ProductionCondition.class)
public interface ProductionUserRepository extends UserRepository {
}
