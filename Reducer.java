import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducer extends Reducer<Text, Text, Text, IntWritable>{

  @Override
    public void reduce(Text ip, Iterable<Text> logList, Context context){
      int count = 0;
      for(Text hits : logList){
        count++;
      }
      context.write(ip, new IntWritable(count));
    }
}
