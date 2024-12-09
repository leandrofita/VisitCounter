package visitcounter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.UserInfo;


public class VisitCounter {
	
public Map<Long, Long> countWithStream(Map<String, UserInfo> ...visits) {
		
		//retornando um map vazio caso o array de maps venha vazio
		if(null == visits || visits.length == 0) {
			return Map.of();
		}
		
		
		// Cria um stream a partir dos mapas recebidos.
		return Arrays.stream(visits)
				.filter(Objects::nonNull) //filtra as possíveis entradas nulas nos mapas recebidos										
				.flatMap(visit -> visit.entrySet().stream()) // "Achata" o stream de mapas para um stream de entradas (key-value).
				.filter(entry -> 
						isLong(entry.getKey()) &&
						entry.getValue() != null &&
						entry.getValue().getVisitCount().isPresent())
				.collect(Collectors.groupingBy(entry -> Long.parseLong(entry.getKey()), // Agrupa por ID do usuário, convertendo a chave (String) para Long.
						Collectors.summingLong(entry -> entry.getValue().getVisitCount().get()) // Soma os valores de visitas para cada ID.
						));
		
		
	}
	
public Map<Long, Long> countWithouStream(Map<String, UserInfo> ...visits){

		// retornando um map vazio caso o array de maps venha vazio
		if (null == visits || visits.length == 0) {
			return Map.of();
		}

		Map<Long, Long> resultMap = new HashMap<>();

		for (Map<String, UserInfo> visitMap : visits) {
			if(null != visitMap) {
				for (Entry<String, UserInfo> entry : visitMap.entrySet()) {
					if (isLong(entry.getKey()) 
							&& entry.getValue() != null 
							&& entry.getValue().getVisitCount().isPresent()) {
						
						Long key = Long.parseLong(entry.getKey());
						Long value = entry.getValue().getVisitCount().get();
						
						resultMap.put(key, resultMap.getOrDefault(key, 0L) + value);
						
					}
				}				
			}
		}
		
		return resultMap;

	}
	
	private static boolean isLong(String x) {
		try {
			if(null != x) {
				Long.parseLong(x);
				return true;
			} return false;
		} catch (Exception e) {
			return false;
		}
	}

}
