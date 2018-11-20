package com.jackass.RestAPI.repository.inmemory.reflection;

import java.util.Collection;
import java.util.List;

public interface Table<T> extends List<T> {

    @Override
    @Recursive(RecursiveType.WRITE)
    boolean add(T t);

    @Override
    @Recursive(RecursiveType.WRITE)
    boolean remove(Object o);

    @Override
    @Recursive(RecursiveType.WRITE)
    boolean removeAll(Collection<?> c);

}
