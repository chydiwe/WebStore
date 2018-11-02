package com.jackass.RestAPI.repository.inmemory.reflection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class InMemoryTable<T> extends ArrayList<T> implements Table<T> {

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Not relational format.");
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Not relational format.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not relational format.");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not relational format.");
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        throw new UnsupportedOperationException("Not relational format.");
    }
}
