package com.jackass.RestAPI.conf;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
public class ConditionsConfig {

    public static boolean IN_MEMORY = false;

    public static class ProductionCondition implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return !IN_MEMORY;
        }
    }

    public static class InMemoryCondition implements Condition {

        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            return IN_MEMORY;
        }
    }

}
