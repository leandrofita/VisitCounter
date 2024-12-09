# VisitCounter
## Welcome to the VisitCounter repository! 🚀

This project demonstrates two distinct approaches to aggregate user visits from input maps:

📚 Features
1. Stream-based Implementation

* Leverages the powerful Java 8 Streams API for efficient and concise data processing.
* Handles filtering, flattening, and grouping in a functional programming style.

2. Non-Stream Implementation

Implements a traditional approach using loops and conditionals.
Offers clarity and simplicity while achieving the same result.

## 🛠️ What Does It Do?
Processes multiple input maps where:
* The key represents a user ID (as a String).
* The value contains visit counts wrapped in an Optional<Long>.
* Filters out invalid data (e.g., non-numeric keys, null values, or empty visit counts).
* Aggregates visits by converting user IDs to Long and summing their counts.
* Returns a final map of <UserID, TotalVisits>.

### 📜 Exercise Rules

- **The input will be an vararg of :** `Map<String, UserInfo>... visits)`.

- The return of the mhetod must be a `Map<Long, Long>` with each valid user ID and the total of visits of the user.

- The **key** in the map is the user ID, which must be converted to a `Long` if it's not `null`.

- **UserStats** has a `getVisitCount()` method that returns an `Optional<Long>`, which **never** will be `null`.

- The function must return a `Map<Long, Long>` where the `key` represents the user ID and the `value` represents the total number of visits.

- If the map is **null** or **empty**, return an empty map.

- The problem should be solved using the **Java 8 Streams API**.
