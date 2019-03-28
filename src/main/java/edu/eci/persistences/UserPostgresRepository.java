package edu.eci.persistences;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.User;
import edu.eci.persistences.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Qualifier("UserPostgresRepository")
public class UserPostgresRepository implements IUserRepository {

	@Autowired
	private DataSource dataSource;

	@Override
	public User getUserByUserName(String userName) {
		String query = "SELECT * FROM \"user\" WHERE \"user\".name=" + userName;
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement(); // instruccion con la conexion
			ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta en la base de datos pro la conexion
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setId(UUID.fromString(rs.getString("id")));
			}
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<User> findAll() {
		String query = "SELECT * FROM \"user\"";
		List<User> users = new ArrayList<>();
		System.out.println(dataSource);
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				User user = new User();
				user.setId(UUID.fromString(rs.getString("id")));
				user.setName(rs.getString("name"));
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public User find(UUID id) {
		String query = "SELECT * FROM \"user\" WHERE id='" + id.toString() + "'";
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setId(UUID.fromString(rs.getString("id")));				
			}
			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public UUID save(User entity) {
		String insert = "INSERT INTO \"user\" (id, name) VALUES ('" + entity.getId().toString() + "','" + entity.getName() + "')";
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(insert);
			return entity.getId();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(User entity) {
		String update = "UPDATE \"user\" SET name='" + entity.getName() + "' WHERE id='" + entity.getId().toString()+"'";
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(update);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(User o) {
		String delete = "DELETE FROM \"user\" WHERE id='" + o.getId().toString()+"'";
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(delete);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remove(String id) {
		String delete = "DELETE FROM \"user\" WHERE id='" + id+"'";
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(delete);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
