package edu.eci.persistences.repositories;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public interface DAO<T extends Serializable, PK> {

    public List<T> findAll();

    public T find(PK id);

    public PK save(T entity);

    public void update(T entity);

    public void delete(T o);

    public void remove(String id);
}
