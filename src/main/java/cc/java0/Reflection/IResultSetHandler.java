package cc.java0.Reflection;

import java.sql.ResultSet;

/**
 * 泛型接口
 *
 * @author cc
 *
 * @param <T>
 */


/**
 *
 */
public interface IResultSetHandler {
	public Object handler(ResultSet rs) throws Exception;
}
