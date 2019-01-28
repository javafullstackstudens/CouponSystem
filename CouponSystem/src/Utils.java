import java.time.LocalDate;
import java.util.Date;


public class Utils {
    
    public static Date getDate() {
    LocalDate localDate = LocalDate.now();
	Date date = java.sql.Date.valueOf(localDate);
	return date;
    }
    
 
    
  }
  


//   (year, month, date, hrs, min, sec)