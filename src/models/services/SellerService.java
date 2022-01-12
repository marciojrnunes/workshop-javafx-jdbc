package models.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import models.entities.Seller;

public class SellerService {
	
	//Recupera os dados de departamento do banco de dados
	private SellerDao dao = DaoFactory.createSellerDao();
	
	public List<Seller> findAll(){
		return dao.findAll();
	}

	//Verifica se o departmento será inserido ou atualizado
	public void saveOrUpdate(Seller obj) {
		if(obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	
	//Remove departamento do banco de dados
	public void remove(Seller obj) {
		dao.deleteById(obj.getId());
	}
	
}
