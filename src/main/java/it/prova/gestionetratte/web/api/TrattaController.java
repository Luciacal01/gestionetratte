package it.prova.gestionetratte.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.TratteDTO;
import it.prova.gestionetratte.service.TrattaService;

@RestController
@RequestMapping("api/tratta")
public class TrattaController {
	@Autowired
	private TrattaService trattaService;
	
	@GetMapping
	public List<TratteDTO> getAll() {
		return TratteDTO.createTrattaDTOListFromModelList(trattaService.listAllElements(true), true);
	}
	
	
}
