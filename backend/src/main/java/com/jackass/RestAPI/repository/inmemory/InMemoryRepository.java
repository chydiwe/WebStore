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
import java.util.stream.Collectors;

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
                onRecursive(args);
            }
            return method.invoke(delegate, args);
        }

        private void onRecursive(Object[] args) throws IllegalAccessException {
            Pair<String, String> primaryKeyToValue = getPrimaryKeyToValue(args);

            if (primaryKeyToValue == null) {
                return;
            }

            for (Object arg : args) {
                List<Field> relatedFields = new ArrayList<>();
                for (Field field : arg.getClass().getDeclaredFields()) {
                    Column column = field.getDeclaredAnnotation(Column.class);

                    database.keySet().stream()
                            .filter(table -> {
                                if(!table.equals(arg.getClass())){
                                    for (Field f : table.getDeclaredFields()){
                                        Id id = f.getDeclaredAnnotation(Id.class);
                                        Column c = f.getDeclaredAnnotation(Column.class);
                                        if(id != null && c != null && c.name().equals(column.name())){
                                            return true;
                                        }
                                    }
                                }

                                return false;
                            }).collect(Collectors.toSet());

                    if (database.containsKey(field.getType())) {
                        relatedFields.add(field);
                    }
                }

                for (Field field : relatedFields) {
                    List rows = database.get(field.getClass());
                    if (rows == null) {
                        continue;
                    }

                    for (Object row : rows) {
                        for (Field column : row.getClass().getDeclaredFields()) {
                            Column[] columns = column.getDeclaredAnnotationsByType(Column.class);
                            for (Column c : columns) {
                                if (c.name().equals(primaryKeyToValue.getKey()) && column.get(row).equals(primaryKeyToValue.getValue())) {
                                    column.setAccessible(true);
                                    column.set(row, field.get(arg));
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
