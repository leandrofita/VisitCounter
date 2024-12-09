package visitcounter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.UserInfo;


public class VisitCounter {

	/**
	 * Counts visits by user ID using Java Streams.
	 *
	 * @param visits Variable-length argument of maps where the key is a String
	 *               (user ID) and the value is a UserInfo object containing visit
	 *               information.
	 * @return A map where the key is the user ID (as Long) and the value is the
	 *         total number of visits. If the input is null or empty, an empty map
	 *         is returned.
	 */

	public Map<Long, Long> countWithStream(Map<String, UserInfo> ...visits) {
		
		// Returning an empty map if the input array is null or empty
		if(null == visits || visits.length == 0) {
			return Map.of();
		}
		
		
		// Creating a stream from the array of maps
		return Arrays.stream(visits)
				.filter(Objects::nonNull) // Filtering out null maps										
				.flatMap(visit -> visit.entrySet().stream()) // Flattening the stream of maps into a stream of entries (key-value pairs)
				.filter(entry -> 
						isLong(entry.getKey()) && // Filtering out invalid keys that are not valid Long values
						entry.getValue() != null &&
						entry.getValue().getVisitCount().isPresent()) // Ensuring the value is non-null and contains a valid visit count
				.collect(Collectors.groupingBy(entry -> Long.parseLong(entry.getKey()), // Grouping by user ID (key converted to Long)
						Collectors.summingLong(entry -> entry.getValue().getVisitCount().get())  // Summing visit counts for each user ID
						));
		
		
	}
	
	/**
	 * Counts visits by user ID without using Java Streams.
	 *
	 * @param visits Variable-length argument of maps where the key is a String
	 *               (user ID) and the value is a UserInfo object containing visit
	 *               information.
	 * @return A map where the key is the user ID (as Long) and the value is the
	 *         total number of visits. If the input is null or empty, an empty map
	 *         is returned.
	 */
	public Map<Long, Long> countWithouStream(Map<String, UserInfo> ...visits){

		// returning an empty map case the given array is null or empty
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
						
						// Adding or updating the visit count for the user ID
						resultMap.put(key, resultMap.getOrDefault(key, 0L) + value);
						
					}
				}				
			}
		}
		
		return resultMap;

	}
	
	/**
	 * Checks if a given String can be parsed into a valid Long value.
	 *
	 * @param x The input string to check.
	 * @return True if the string is a valid Long, false otherwise.
	 */
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
