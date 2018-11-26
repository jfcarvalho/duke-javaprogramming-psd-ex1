import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData)
        {
            if(qe.getMagnitude() > magMin)
            {
              answer.add(qe);  
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

            
            for(int k=1; k < quakeData.size(); k++)
            {
                QuakeEntry quake = quakeData.get(k);
                Location loc = quake.getLocation();    
                if(loc.distanceTo(from) < distMax*1000)
                {
                    System.out.println(quake.getInfo());
                    answer.add(quake);
                }
                
            }   
        
        
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> largest  = filterByMagnitude(list, 5.0);
        System.out.println("read data for "+largest.size()+" quakes");
        for(QuakeEntry q:largest)
        {
            System.out.println(q);
        }
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location durham = new Location(35.988, -78.907);
        //ArrayList<QuakeEntry> list_filtered = filterByDistanceFrom(list, 1000, durham);
                
        // This location is Bridgeport, CA
         Location bridgetport =  new Location(38.17, -118.82);
                 ArrayList<QuakeEntry> list_filtered2 = filterByDistanceFrom(list, 1000, bridgetport);
      //  System.out.println(list_filtered.size());
        System.out.println(list_filtered2.size());
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
