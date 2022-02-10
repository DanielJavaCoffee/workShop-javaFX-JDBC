package model.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Map<String, String> erros = new HashMap<>();
	
	public ValidationException(String exception) {
		super(exception);
	}
	
	public Map<String, String> getErros(){
		return erros;
	}
	
	public void addErro(String nomeDoCampo, String erro) {
		erros.put(nomeDoCampo, erro);
	}

}
