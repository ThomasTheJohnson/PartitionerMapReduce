package stubs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountReducer extends Reducer<Text, Text, Text, IntWritable> {

  @Override
  public void reduce(Text ip, Iterable<Text> logList, Context context)
      throws IOException, InterruptedException {
    //Sets a counting variable hits
	  int hits = 0;

	  for(Text logs : logList){
        //For every log in the logList it increments the counting variable by 1
        hits++;
      }
    //writes the number of hits in a month to the IP associated with it.
	  context.write(ip, new IntWritable(hits));

  }
}
