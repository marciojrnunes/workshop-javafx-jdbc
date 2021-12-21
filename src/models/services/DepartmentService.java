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

}
