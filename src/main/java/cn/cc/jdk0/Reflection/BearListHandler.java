package cn.cc.jdk0.Reflection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class BearListHandler<T> implements IResultSetHandler {


    public Class<?> type;

    public BearListHandler(Class<?> type) {
        this.type = type;

    }

    @Override
    public Object handler(ResultSet rs) throws Exception {
        if (!rs.next()) {
            return null;
        }
        ResultSetMetaData meta = rs.getMetaData();
        // 获取结果集中列的个数
        int cols = meta.getColumnCount();

        Object obj = type.newInstance();

        // Object[] result = new Object[cols];
        for (int i = 0; i < cols; i++) {
            // 列的值
            Object value = rs.getObject(i + 1);
            // 列的名称
            String columnName = meta.getColumnName(i + 1);
            // 通过 反射探知对象有哪些属性，并给属性赋值
            Field field = type.getDeclaredField(columnName);

            field.setAccessible(true);
            field.set(obj, value);

            // type.getField(name)
        }
        // result.getClass();
        return obj;
    }
}
