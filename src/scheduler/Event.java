package scheduler;

public class Event {

	String eventName;
	String eventTime;

	Event(String eventName, String eventTime) {
		this.eventName = eventName;
		this.eventTime = eventTime;
	}

	@Override
	public String toString() {
		String eventTotal = eventTime +" | "+eventName;
		return eventTotal;

	}

}
