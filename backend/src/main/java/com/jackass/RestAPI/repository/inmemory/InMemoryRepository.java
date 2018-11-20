package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.repository.inmemory.reflection.*;
import javafx.util.Pair;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class InMemoryRepository<T> {

    private static Map<Class, List> database = new ConcurrentHashMap<>();

    protected int id = 1;
    protected List<T> table;

    public InMemoryRepository() {
        this.table = (List<T>) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{Table.class}, new TableInvocationHandler(new InMemoryTable<T>()));
        this.database.put(ReflectionUtils.getGenericParameterClass(this.getClass()), table);
    }

    private class TableInvocationHandler implements InvocationHandler {

        private Table delegate;

        public TableInvocationHandler(Table<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Recursive recursive = method.getDeclaredAnnotation(Recursive.class);
            if (recursive != null) {
                onRecursive(args, recursive.value());
            }
            return method.invoke(delegate, args);
        }

        private void onRecursive(Object[] args, RecursiveType type) throws IllegalAccessException {
            Pair<String, String> primaryKeyToValue = getPrimaryKeyToValue(args);

            if (primaryKeyToValue == null) {
                return;
            }

            for (Object arg : args){
                List<Field> relatedFields = new ArrayList<>();
                for (Field field : arg.getClass().getDeclaredFields()) {
                    if (database.containsKey(field.getClass())) {
                        relatedFields.add(field);
                    }
                }

                for (Field field : relatedFields) {
                    List rows = database.get(field.getClass());
                    for (Object row : rows){
                        for(Field column : row.getClass().getDeclaredFields()) {
                            Column[] columns = column.getDeclaredAnnotationsByType(Column.class);
                            for (Column c : columns) {
                                if (c.name().equals(primaryKeyToValue.getKey()) && column.get(row).equals(primaryKeyToValue.getValue())) {
                                    if(type == RecursiveType.READ){
                                        field.setAccessible(true);
                                        field.set(arg, column.get(row));
                                    } else {
                                        column.setAccessible(true);
                                        column.set(row, field.get(arg));
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        private Pair<String, String> getPrimaryKeyToValue(Object[] args) throws IllegalAccessException {
            // Take first one because they are all must have the same type
            Object object = args[0];
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                Annotation annotation = field.getDeclaredAnnotation(Id.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    return new Pair(field.getAnnotation(Column.class).name(), field.get(object));
                }
            }

            return null;
        }

    }

}
