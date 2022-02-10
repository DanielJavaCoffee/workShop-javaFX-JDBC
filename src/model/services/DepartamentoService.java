package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class DepartamentoService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll(){
		return dao.findAll();
	}
	
	public void save(Department departamento) {
		if(departamento.getId() == null) {
			dao.insert(departamento);
		} else {
			dao.update(departamento);
		}
	}
	public void remove(Department department) {
		dao.deleteById(department.getId());
	}
}
