package Main;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javafx.scene.chart.PieChart.Data;


public class Utils {
    
    public static Date getDate() {
    LocalDate localDate = LocalDate.now();
	Date date = java.sql.Date.valueOf(localDate);
	return date;
    }
    
    public static Date endDate( int numDays ) 
    { 
    	 LocalDate localDate = LocalDate.now().plusDays(numDays);
    	 Date date = java.sql.Date.valueOf(localDate);
         return date;
    }
    
    public static String getDriverData() {
		return "org.apache.derby.jdbc.ClientDriver";
	}
    
    public static String getDBUrl() {
		return "jdbc:derby://localhost:3301/MyDB;create=true";
	}


    
    
  }
  


//   (year, month, date, hrs, min, sec)