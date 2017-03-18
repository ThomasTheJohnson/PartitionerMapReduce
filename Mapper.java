import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mapper {


	public static void main(String[] args){

		String log = "127.0.0.1 - frank [10/Oct/2000:13:55:36 -0700] \"GET /apache_pb.gif HTTP/1.0\" 200 2326";
		String log2 = "10.211.47.159 - - [10/Aug/2009:20:31:43 -0700] \"GET /assets/img/release-schedule-logo.png HTTP/1.1\" 304 ";
		String log3 = "10.211.47.159 - - [10/Aug/2009:20:52:19 -0700] \"GET /assets/css/the-associates.css HTTP/1.1\" 304 –";
		String log4 = "10.216.113.172 - - [12/Aug/2009:06:01:21 -0700] \"GET /assets/img/dummy/films/district- 13/image-thumbs/2.jpg HTTP/1.1\" 200 9626";
		String log5 = "10.216.113.172 - - [13/Aug/2009:04:05:40 -0700] \"GET /assets/img/loading.gif HTTP/1.1\" 304 –";

		String ip = "\\d+\\.\\d+\\.\\d+\\.\\d+";
		String timeStamp = "\\d+\\/\\w+\\/\\d+\\:\\d+\\:\\d+\\:\\d+";
		String month = "\\/\\w+";

		Pattern ipPat = Pattern.compile(ip);
		Pattern timeStampPat = Pattern.compile(timeStamp);
		Pattern monthPat = Pattern.compile(month);

		Matcher ipMatch = ipPat.matcher(log);
		ipMatch.find();
		String logIp = log.substring(ipMatch.start(), ipMatch.end());

		Matcher timeMatch = timeStampPat.matcher(log);
		timeMatch.find();
		String time = log.substring(timeMatch.start(), timeMatch.end());


		Matcher monthMatch = monthPat.matcher(time);
		monthMatch.find();
		String theMonth = time.substring(monthMatch.start()+1, monthMatch.end());
		System.out.println(logIp + "\n" + time + "\n" + theMonth);
	}

}
