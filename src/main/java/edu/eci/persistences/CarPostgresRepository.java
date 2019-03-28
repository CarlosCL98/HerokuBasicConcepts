/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.persistences;

import edu.eci.models.Car;
import edu.eci.models.User;
import edu.eci.persistences.repositories.ICarRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *
 * @author carloscl
 */
@Component
@Qualifier("CarPostgresRepository")
public class CarPostgresRepository implements ICarRepository {
	
	@Autowired
	private DataSource dataSource;	
	
    @Override
    public List<Car> findAll() {
    	String 	query = "SELECT * FROM car";
    	List<Car> cars = new ArrayList();
        try(Connection connection = dataSource.getConnection()){
        	Statement stmt = connection.createStatement();
        	ResultSet rta = stmt.executeQuery(query);
        	while(rta.next()) {
        		Car car = new Car();
        		car.setLicencePlate(rta.getString("licencePlate"));
        		car.setBrand(rta.getString("brand"));
        		cars.add(car);
        	}
        	return cars;
        } catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
    }

    @Override
    public Car find(String id) {
        String query = "SELECT * FROM car WHERE licencePlate='"+id+"'";
        try (Connection connection = dataSource.getConnection()) {
        	Car car = null;
			Statement stmt = connection.createStatement();
			ResultSet rta = stmt.executeQuery(query);
			while (rta.next()) {
				car = new Car();
				car.setLicencePlate(rta.getString("licencePlate"));
				car.setBrand(rta.getString("brand"));
			}
			return car;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
    }

    @Override
    public String save(Car entity) {
    	String insert = "INSERT INTO car (licencePlate, brand) VALUES ('"+entity.getLicencePlate()+"','"+entity.getBrand()+"')";
        try (Connection connection = dataSource.getConnection()) {        	
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(insert);			
			return entity.getLicencePlate();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
    }

    @Override
    public void update(Car entity) {
    	String insert = "UPDATE car SET brand='"+entity.getBrand()+"' WHERE licencePlate='"+entity.getLicencePlate()+"'";
        try (Connection connection = dataSource.getConnection()) {        	
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(insert);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
    }

    @Override
    public void delete(Car o) {
    	String delete = "DELETE FROM car WHERE licencePlate='" + o.getLicencePlate()+"'";
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
    	String delete = "DELETE FROM car WHERE licencePlate='" + id + "'";
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(delete);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
    }
}
