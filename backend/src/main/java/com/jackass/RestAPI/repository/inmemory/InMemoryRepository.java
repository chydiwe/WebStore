package com.jackass.RestAPI.repository.inmemory;

import com.jackass.RestAPI.repository.inmemory.reflection.InMemoryTable;
import com.jackass.RestAPI.repository.inmemory.reflection.Recursive;
import com.jackass.RestAPI.repository.inmemory.reflection.Table;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public abstract class InMemoryRepository<T> {

    private static Map<Class, List> database = new ConcurrentHashMap<>();

    protected int id = 1;
    protected Set<T> table;

    public InMemoryRepository() {
        this.table = (Set<T>) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{Table.class}, new TableInvocationHandler(new InMemoryTable<T>()));
    }

    private class TableInvocationHandler implements InvocationHandler{

        private Table delegate;

        public TableInvocationHandler(Table<T> delegate){
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (Recursive.class.equals(annotation.getClass())) {
                    onRecursive(method, args);
                    break;
                }
            }
            return method.invoke(delegate, args);
        }

        private void onRecursive(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            String primaryKey = getPrimaryKey(args);

            if (primaryKey == null) {
                return;
            }

            List<Class> relatedTables = getRelatedTables(primaryKey);
            for (Class relatedTable : relatedTables){
                List rows = database.get(relatedTable);

                rows.stream().filter(o -> {
                    for (Field field : o.getClass().getFields()){
                        if(field.getAnnotation(Column.class) != null){
                            field.setAccessible(true);
                            try{
                                String foreignKey = (String) field.get(o);
                                if(primaryKey.equals(foreignKey)){
                                    return true;
                                }
                            } catch (Exception e) {
                                //Never happen
                            }
                        }
                    }
                    return false;
                }).collect(Collectors.toList());

                method.invoke(rows, rows.toArray());
            }

        }

        private String getPrimaryKey(Object[] args) {
            // Take first one because they are all must have the same type
            Object object = args[0];
            Field[] fields = object.getClass().getFields();
            for (Field field : fields) {
                for (Annotation annotation : field.getAnnotations()) {
                    if (annotation.equals(Id.class)) {
                        return field.getAnnotation(Column.class).name();
                    }
                }
            }

            return null;
        }

        private List<Class> getRelatedTables(String primaryKey){
            List<Class> relatedTables = new ArrayList<>();

            for (Class table : database.keySet()) {
                for (Field field : table.getFields()) {
                    if (field.getAnnotation(Id.class) == null) {
                        Column[] columns = field.getAnnotationsByType(Column.class);
                        for (Column column : columns) {
                            if (column.name().equals(primaryKey)) {
                                relatedTables.add(table);
                            }
                        }
                    }
                }
            }

            return relatedTables;
        }

    }

}
