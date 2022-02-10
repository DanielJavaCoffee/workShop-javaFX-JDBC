package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	void insert1(Department obj);
	void update1(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();
	void insert(Department obj);
	void update(Department obj);
}
