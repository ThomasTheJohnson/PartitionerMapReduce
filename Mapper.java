//Traditional Java Imports
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Hadoop Java Imports
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Mapper extends Mapper<LongWritable, Text, Text, Text> {

  @Override
  public void map(LongWritable key, Text value, Context context)
    throws IOException, InterruptedException {

    //Converts line from input file into a string
    String line = value.toString();

    //These lines of code search through the log using regular expressions
	  //To match the IP address and the time that the log occurred. These lines
    //are the same as the previous project
	  String ip = "\\d+\\.\\d+\\.\\d+\\.\\d+";
	  Pattern ipPat = Pattern.compile(ip);
	  Matcher ipMatch = ipPat.matcher(line);
	  ipMatch.find();

    String timeStamp = "\\d+\\/\\w+\\/\\d+\\:\\d+\\:\\d+\\:\\d+";
	  Pattern timeStampPat = Pattern.compile(timeStamp);
	  Matcher timeStampMatch = timeStampPat.matcher(line);
	  timeStampMatch.find();
    //this new line creates a string that will be used to find the month
    String foundTime = line.subString(timeStampMatch.start(), timeStampMatch.end());

    //This is where things get a little bit different. To only emit
    //the month, we need to search the timestamp for the month only
    String month = "\\/\\w+";
    Pattern monthPat = Pattern.compile(month);
    Matcher monthMatch = monthPat.matcher(foundTime);
    monthMatch.find()

    /* This line here is quite long, but it is fairly simple. Using the matchers and string methods
    provided in the Java library we extract the IP from the input line of text via ipMatch
    and we wrap that in a Text object. After that, we extract only the month from the string "foundTime"
    which is the timeStamp that we pulled out of the input line then wrap that in a Text object and write them as
    a key value pair to the context of the job.
    */
    context.write(new Text(line.subString(ipMatch.start(), ipMatch.end()), new Text(foundTime.subString(monthMatch.start()+ 1, monthMatch.end()))));

    }
/*
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

		Matcher ipMatch = ipPat.matcher(log2);
		ipMatch.find();
		String logIp = log2.substring(ipMatch.start(), ipMatch.end());

		Matcher timeMatch = timeStampPat.matcher(log2);
		timeMatch.find();
		String time = log2.substring(timeMatch.start(), timeMatch.end());


		Matcher monthMatch = monthPat.matcher(time);
		monthMatch.find();
		String theMonth = time.substring(monthMatch.start()+1, monthMatch.end());
		System.out.println(logIp + "\n" + time + "\n" + theMonth);
	}

}
*/
