package by.tc.auction.controller.command.realization.auction_operation;

import java.util.HashMap;
import java.util.Map;

public class TimeGetter {

	private static final Long TEN_MINUTES_MILLISECONDS = 600000L;
	private static final Long HOUR_MILLISECONDS = 3600000L;
	private static final Long DAY_MILLISECONDS = 86400000L;
	private static final Long WEEK_MILLISECONDS = 14515200000L;
	
	private Map<Time, Long> time;
	
	private static TimeGetter instance = new TimeGetter();
	
	
	private TimeGetter() {
		time = new HashMap<>();
		time.put(Time.TEN_MINUTES, TEN_MINUTES_MILLISECONDS);
		time.put(Time.DAY, DAY_MILLISECONDS);
		time.put(Time.HOUR, HOUR_MILLISECONDS);
		time.put(Time.WEEK, WEEK_MILLISECONDS);
	}
	
	public static TimeGetter getInstance() {
		return instance;
	}
	
	public Long getMilliseconds(Time type) {
		return time.get(type);
	}
}
