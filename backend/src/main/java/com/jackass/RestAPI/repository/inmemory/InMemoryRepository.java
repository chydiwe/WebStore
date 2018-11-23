package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.repository.inmemory.reflection.*;
import javafx.util.Pair;
import org.springframework.boot.web.embedded.jetty.JettyWebServer;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class InMemoryRepository<T> {

    private static Map<Class, InMemoryTable> database = new ConcurrentHashMap<>();

    protected int id = 1;
    protected Set<T> table;

    public InMemoryRepository() {
        this.table = (InMemoryTable) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{Table.class}, new TableInvocationHandler(new InMemoryTable<T>()));
        this.database.put(ReflectionUtils.getGenericParameterClass(this.getClass()), (InMemoryTable) table);
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
                onRecursive(recursive, args);
            }
            return method.invoke(delegate, args);
        }

        private void onRecursive(Recursive operation, Object[] entities) throws IllegalAccessException {
            Pair<String, String> primaryKeyToValue = getPrimaryKeyToValue(entities);

            if (primaryKeyToValue == null) {
                return;
            }

            for (Object entity : entities) {
                for (Field field : entity.getClass().getDeclaredFields()) {
                    // find table for field and add new object
                    JoinColumn joinColumn = field.getDeclaredAnnotation(JoinColumn.class);
                    if (joinColumn != null) {
                        field.setAccessible(true);
                        Object object = field.get(entity);

                        Class table;
                        if (field.getDeclaredAnnotation(OneToMany.class) != null) {
                            table = ReflectionUtils.getGenericParameterClass(field.getType());
                        } else {
                            table = field.getClass();
                        }

                        executeOperation(operation.value(), table, object);
                        continue;
                    }

                    // find table from foreign key
                    Column column = field.getDeclaredAnnotation(Column.class);
                    if (column != null) {
                        field.setAccessible(true);
                        Object object = field.get(entity);

                        database.entrySet().stream()
                                .forEach(entry -> {
                                    try {
                                        Class table = entry.getKey();
                                        InMemoryTable rows = entry.getValue();
                                        if (!table.equals(entity.getClass())) {
                                            for (Field f : table.getDeclaredFields()) {
                                                Id id = f.getDeclaredAnnotation(Id.class);
                                                Column c = f.getDeclaredAnnotation(Column.class);
                                                if (id != null && c != null && c.name().equals(column.name())) {
                                                    for (Object row : rows) {
                                                        Field columnValueOfRow = row.getClass().getDeclaredField(f.getName());
                                                        columnValueOfRow.setAccessible(true);

                                                        if (columnValueOfRow.get(row).equals(object)) {

                                                        }

                                                    }
                                                }
                                            }
                                        }
                                    } catch (NoSuchFieldException e) {
                                    } catch (IllegalAccessException e) {
                                    }
                                });

//                        executeOperation(operation.value(), table, object);
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

        private void executeOperation(SQL operation, Class table, Object entity) {
            InMemoryTable rows = database.get(table);
            if (operation == SQL.INSERT) {
                rows.insert(entity);
            } else {
                rows.delete(entity);
            }
        }

    }
}
