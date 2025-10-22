package interviews;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CircuitBreaker {

	public static void main(String[] args) throws Exception {
		
		Request r1 = new Request("B", 1);
		Request r2 = new Request("B", 2);
		Request r3 = new Request("B", 3);
		Request r4 = new Request("B", 4);
		Request r5 = new Request("B", 10);
		Request r6 = new Request("B", 13);
		Request r7 = new Request("B", 17);
		
		WebClient webclient = new WebClient();
		
		System.out.println(webclient.execute(r1));
		System.out.println(webclient.execute(r2));
		System.out.println(webclient.execute(r3));
		System.out.println(webclient.execute(r4));
		System.out.println(webclient.execute(r5));
		System.out.println(webclient.execute(r6));
		System.out.println(webclient.execute(r7));
		
	}

}

class Request {
	
	String host;
	int minute;
	
	Request(String host, int minute) {
		this.host = host;
		this.minute = minute;
	}

	// This makes request to external server
	Response call() {
		return new Response(500, "Failed");
	}
}

// You don't need to make any changes for this class
class Response {
	int status;
	String body;
	
	Response(int status, String body) {
		this.status = status;
		this.body = body;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", body=" + body + "]";
	}
	
	
}

class WebClient {

	Map<String, Deque<Integer>> failuresSlidingWindowMap;

	WebClient() {
		this.failuresSlidingWindowMap = new HashMap<>();
	}

	public Response execute(Request request) throws Exception {
		
		if(!failuresSlidingWindowMap.containsKey(request.host)) {
			failuresSlidingWindowMap.put(request.host, new LinkedList<>());
		}

		cleanup(request.host, request.minute);
		
		Deque<Integer> failureTimes = failuresSlidingWindowMap.get(request.host);

		if (failureTimes.size() >= 3 && request.minute-failureTimes.getLast() < 5) {
			// block the call
			Response response = new Response(429, "Blocked");
			return response;

		} else {
			Response response = request.call();
			int statusCode = response.status;
			if (statusCode == 500)
				failureTimes.add(request.minute);
			return response;
		}

	}

	void cleanup(String host, int minute) {
		Deque<Integer> failureTimes = failuresSlidingWindowMap.get(host);
		while (!failureTimes.isEmpty() && minute - failureTimes.getFirst() >= 10) {
			failureTimes.removeFirst();
		}
	}
}
