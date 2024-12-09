package main;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.UserInfo;
import visitcounter.VisitCounter;

public class Main {

	public static void main(String[] args) {
		
		//test dada
		Map<String, UserInfo> map1 = new HashMap<>();
		map1.put("0050", new UserInfo(Optional.of(5L)));
		map1.put("123", new UserInfo(Optional.of(5L)));
		map1.put("456", new UserInfo(Optional.of(3L)));
		map1.put("Not an id", new UserInfo(Optional.of(20L)));
		map1.put(null, new UserInfo(Optional.of(20L)));
		map1.put("1", new UserInfo(Optional.of(20L)));
		map1.put("12", new UserInfo(Optional.of(20L)));


		Map<String, UserInfo> map2 = new HashMap<>();
		map2.put("0050", new UserInfo(Optional.of(3L)));
		map2.put("123", new UserInfo(Optional.of(3L)));
		map2.put("789", new UserInfo(Optional.of(4L)));
		map2.put("1", new UserInfo(Optional.of(20L)));
		map2.put("12", new UserInfo(Optional.of(20L)));
		map2.put("12.5", new UserInfo(Optional.of(20L)));
		map2.put(null, new UserInfo(Optional.of(20L)));
		map2.put("&*%", new UserInfo(Optional.of(10L)));
		
		Map<String, UserInfo> map3 = Map.of(); 
		
		VisitCounter vCounter = new VisitCounter();
		
		var result = vCounter.countWithStream(map1, map2, map3);
		
		var result2 = vCounter.countWithouStream(map1, map2, map3);
		
		System.out.println("Resul with stream: " + result);
		System.out.println("Result without stream: " + result2 );

	}

}
