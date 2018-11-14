package com.jackass.RestAPI.repository.inmemory.reflection;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Table<T> extends Set<T> {

    @Override
    @Recursive
    boolean add(T t);

    @Override
    @Recursive
    boolean remove(Object o);

    @Override
    @Recursive
    boolean removeAll(Collection<?> c);

}
