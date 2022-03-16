package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;


/**
 * The type Abstract dao.
 *	In this class it is implemented the queries for the database operations.
 * @param <T> the type parameter
 */
public class AbstractDAO<T> {
	/**
	 * The constant LOGGER.
	 */
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	/**
	 * Instantiates a new Abstract dao.
	 */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * Select query.
	 * @param field
	 * @param id
	 * @return
	 */
	private String createSelectQuery(String field, int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " = " + id);
		return sb.toString();
	}

	/**
	 * Find all query.
	 * @return the string
	 */
	private String createFindAllQuerry(){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	/**
	 * Insert query string.
	 *
	 * @param o the o
	 * @return the string
	 */
	public String insertQuery(Object o){
		StringBuilder sb= new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(type.getSimpleName());
		sb.append(" VALUES(");
		for(Field f: o.getClass().getDeclaredFields()){
			f.setAccessible(true);
			try {
				sb.append("'" + f.get(o) + "'");
				sb.append(",");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Update query string.
	 *
	 * @param o the o
	 * @return the string
	 */
	public String updateQuery(Object o){
		StringBuilder sb= new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		for(Field f: o.getClass().getDeclaredFields()){
			f.setAccessible(true);
			try {
				sb.append(f.getName() + "=" + "'" + f.get(o) + "'");
				sb.append(",");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		sb.deleteCharAt(sb.length()-1);
		Field f2 = null;
		try {
			f2 = o.getClass().getDeclaredField("id");
			f2.setAccessible(true);
			sb.append(" WHERE id=" + f2.get(o));
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * Delete query string.
	 *
	 * @param id the id
	 * @return the string
	 */
	public String deleteQuery(int id){
		StringBuilder sb= new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE id=" + id);
		return sb.toString();
	}

	/**
	 * Find all list.
	 *
	 * @return the list
	 */
	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createFindAllQuerry();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Find by id t.
	 *
	 * @param id the id
	 * @return the t
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id",id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Insert.
	 *
	 * @param t the t
	 */
	public void insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = insertQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} finally {

			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	/**
	 * Update.
	 *
	 * @param t the t
	 */
	public void update(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = updateQuery(t);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {

			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = deleteQuery(id);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		} finally {

			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

	}
}
