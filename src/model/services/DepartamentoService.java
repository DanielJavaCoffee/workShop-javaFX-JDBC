package model.services;

import java.util.ArrayList;
import java.util.List;

import model.intidade.Departamento;

public class DepartamentoService {
	
	public List<Departamento> findAll(){
		List<Departamento> list = new ArrayList<>();
		list.add(new Departamento(1, "Livros"));
		list.add(new Departamento(2, "Carro"));
		list.add(new Departamento(3, "Computadores"));
		return list;
	}

}
