package br.com.turismo.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.turismo.model.Local;
import br.com.turismo.repository.LocalRepository;

//http://localhost:8080/api/saveLocal	
/*
INSERIR
 {
  "id"          : 1,
  "descricao"   : "Belo Local, ótimo para passear com a família!",
  "precoPorDia" : 100.00 
 }
*/

@RestController
public class LocalControler {

	@Autowired
	LocalRepository repository;
	
	@PostMapping("api/saveLocal")
	public Local saveLocal(@RequestBody Local local){
		return repository.save(local);
	}
	
	//http://localhost:8080/api/allLocal
	@GetMapping("api/allLocal")
	public List<Local> allLocal(){
		return (List<Local>) repository.findAll();
	}
	
}
