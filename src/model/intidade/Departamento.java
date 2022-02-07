package model.intidade;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Departamento implements Initializable {

	private Integer id;
	private String nome;
	
	public Departamento() {
		
	}
	
	public Departamento(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public String ToString() {
		return "ID: " + this.id + " Nome: " + this.nome;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
