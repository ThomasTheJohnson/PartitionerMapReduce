package stubs;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogMonthMapper extends Mapper<LongWritable, Text, Text, Text> {

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
	  String foundTime = line.substring(timeStampMatch.start(), timeStampMatch.end());

	  //This is where things get a little bit different. To only emit
	  //the month, we need to search the timestamp for the month only
	  String month = "\\/\\w+";
	  Pattern monthPat = Pattern.compile(month);
	  Matcher monthMatch = monthPat.matcher(foundTime);
	  monthMatch.find();

	  /* This line here is quite long, but it is fairly simple. Using the matchers and string methods
	   *     provided in the Java library we extract the IP from the input line of text via ipMatch
	   *     and we wrap that in a Text object. After that, we extract only the month from the string "foundTime"
	   *     which is the timeStamp that we pulled out of the input line then wrap that in a Text object and write them as
	   *     a key value pair to the context of the job.
	   */

	  context.write(new Text(line.substring(ipMatch.start(), ipMatch.end())), new Text(foundTime.substring(monthMatch.start()+ 1, monthMatch.end())));

  }
}
