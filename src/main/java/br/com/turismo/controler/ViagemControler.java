package br.com.turismo.controler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.turismo.model.Local;
import br.com.turismo.model.Viagem;
import br.com.turismo.model.ViagemInfo;
import br.com.turismo.repository.ViagemRepository;

//http://localhost:8080/api/saveViagem	
/*
  INSERIR
   {
    "id"       : 1,
    "localDe"  : "Araraquara",
    "localPara": "Curitiba",
    "dtSaida"  : "09/07/2019",
    "dtVolta"  : "15/07/2019" 
   }
 */

@RestController
public class ViagemControler {

	@Autowired
	ViagemRepository repository;

	@PostMapping("api/saveViagem")
	public Viagem saveViagem(@RequestBody ViagemInfo viagem) {
		Viagem v = new Viagem();
		v.localDe = new Local();
		v.localPara = new Local();
		v.localDe.setId(viagem.localDe);
		v.localPara.setId(viagem.localPara);
		v.setDtSaida(viagem.getDtSaida());
		v.setDtVolta(viagem.getDtVolta());
		v.setId(viagem.id);

		return repository.save(v);
	}

	// http://localhost:8080/api/allViagem
	@GetMapping("api/allViagem")
	public List<Viagem> allViagem() throws IOException {

		List<Viagem> lstViagem = (List<Viagem>) repository.findAll();

		List<List<String>> rows = Arrays.asList(Arrays.asList("Jean", "author", "Java"),
				Arrays.asList("David", "editor", "Python"), Arrays.asList("Scott", "editor", "Node.js"));

		FileWriter csvWriter = new FileWriter("new.csv");
		File f = new File("new.csv");

		csvWriter.append("LocalDe");
		csvWriter.append("|");
		csvWriter.append("LocalPara");
		csvWriter.append("|");
		csvWriter.append("DataSaida");
		csvWriter.append("|");
		csvWriter.append("DataVolta");
		csvWriter.append("|");
		csvWriter.append("Valor");
		csvWriter.append("\n");

		for (Viagem viagem : lstViagem) {
			csvWriter.append(String.join("-", viagem.localDe.getDescricao()) + ":");
			csvWriter.append(String.join("-", viagem.localPara.getDescricao()) + ":");

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Long diff = (long) 0;
			try {

				java.util.Date dateInicio = formatter.parse(viagem.dtSaida);
				java.util.Date dateFim =  formatter.parse(viagem.dtVolta);

				diff = getDateDiff(dateInicio, dateFim, TimeUnit.DAYS);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			csvWriter.append(String.join(" - ", viagem.dtSaida) + ": ");
			csvWriter.append(String.join(" - ", viagem.dtVolta) + ": ");
			csvWriter.append(String.join(" - ",
					"" + diff * viagem.localPara.getPrecoPorDia()));
			csvWriter.append("\n");
		}

		csvWriter.flush();
		csvWriter.close();

		System.out.println(f.getAbsolutePath());

		return lstViagem;
	}

	public static long getDateDiff(java.util.Date dateInicio, java.util.Date dateFim, TimeUnit timeUnit) {
		long diffInMillies = dateFim.getTime() - dateInicio.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
