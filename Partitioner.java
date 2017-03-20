package stubs;

import java.util.HashMap;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Partitioner;

public class MonthPartitioner<K2, V2> extends Partitioner<Text, Text> implements
    Configurable {

  private Configuration configuration;
  HashMap<String, Integer> months = new HashMap<String, Integer>();

  @Override
  public void setConf(Configuration configuration) {
    //This sets the Partitioner configuration to the job configuration
	  this.configuration = configuration;
	  /*
	   * Add the months to a HashMap months.
	   */
	  months.put("Jan", 0);
	  months.put("Feb", 1);
	  months.put("Mar", 2);
	  months.put("Apr", 3);
	  months.put("May", 4);
	  months.put("Jun", 5);
	  months.put("Jul", 6);
	  months.put("Aug", 7);
	  months.put("Sep", 8);
	  months.put("Oct", 9);
	  months.put("Nov", 10);
	  months.put("Dec", 11);
  }

  /**
   * Implement the getConf method for the Configurable interface.
   */
  @Override
  public Configuration getConf() {
    //This line is called whenever the job is run to get the current configuration
	  return configuration;
  }

  public int getPartition(Text key, Text value, int numReduceTasks) {

	  /* This one line of code is actually quite simple. It just uses the global
	   * hashtable to look up the value 0-11 of a month as given in the value
	   * using this int it sends the key value pair to the correct reducer.
	   */
	  return (months.get(value.toString()));
  }
}
