package models.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import models.entities.Department;

public class DepartmentService {
	
	//Recupera os dados de departamento do banco de dados
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findAll(){
		return dao.findAll();
	}

	//Verifica se o departmento será inserido ou atualizado
	public void saveOrUpdate(Department obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	//Remove departamento do banco de dados
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}
	
}
