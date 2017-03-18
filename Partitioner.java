import org.apache.hadoop.mapreduce.Partitioner;

public class Partitioner extends Partitioner<K, V> implements Configurable {

    private Configuration configuration;

    @Override
    public void setConf(Configuration configuration){
      this.configuration = configuration;
    }

    @Override
    public Configuration getConf(){
      return this.configuration;
    }

    //For future Thomas,
    //I think this will have to use compareTo because it's generic.
    //Asking James right now.
    public int getPartition(K key, V value, int numReduceTasks){
      if(value.equals("Jan")){
  			return 0;
  		}
  		else if(value.equals("Feb")){
  			return 1;
  		}
      else if(value.equals("Mar")){
  			return 2;
  		}
      else if(value.equals("Apr")){
  			return 3;
  		}
      else if(value.equals("May")){
  			return 4;
  		}
      else if(value.equals("Jun")){
  			return 5;
  		}
      else if(value.equals("Jul")){
  			return 6;
  		}
      else if(value.equals("Aug")){
  			return 7;
  		}
      else if(value.equals("Sep")){
  			return 8;
  		}
      else if(value.equals("Oct")){
  			return 9;
  		}
      else if(value.equals("Nov")){
  			return 10;
  		}
      else{
        return 11;
      }
    }
}
