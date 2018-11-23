package com.jackass.RestAPI.repository.inmemory.reflection;

import java.util.HashSet;
import java.util.function.Predicate;

public class InMemoryTable<T> extends HashSet<T> implements Table<T> {

    public void insert(T element) {
        super.add(element);
    }

    public void delete(Object o) {
        super.remove(o);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        throw new UnsupportedOperationException("Not relational format.");
    }

}
