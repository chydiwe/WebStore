package com.jackass.RestAPI.repository.inmemory.reflection;

import java.util.Collection;
import java.util.List;

public interface Table<T> extends List<T> {

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
