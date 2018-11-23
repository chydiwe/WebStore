package com.jackass.RestAPI.repository.inmemory.reflection;

import java.util.Set;

public interface Table<T> extends Set<T> {

    @Override
    @Recursive(SQL.INSERT)
    boolean add(T t);

    @Override
    @Recursive(SQL.DELETE)
    boolean remove(Object o);

}
