package calendarproject;

import static calendarproject.MonthlyCalendar.listModel;
import java.util.Arrays;
import java.util.*;
import java.io.*;

public class DataHandler {
    //DataHandler use = new DataHandler();

    public static ArrayList<String> eventList = new ArrayList<String>();

    public static void addToList(int type, int userType, int userID, int year, int month, int day, String eventName, int startTime, int endTime) throws IOException{
        //the first constructor: type is to differenciate if an event happens once or on a certain interval
        //0 for one time
        //1 for weekly
        //2 for monthly
        //3 for yearly
        //and most likely 4 will be a custom option
        
        //for usertype, 0 is regular user, 1 is group leader
        
        eventList.add(type + "," + userType + "," + userID + "," + year + "," + month + "," + day + "," + eventName + "," + startTime + "," + endTime);
        //Reminder: The calendar counts January as month 0, and December as month 11
        //write();
        //When you put an event in, it writes it to a text file. 
        //read();
        //Reads from text file.  
        System.out.println(Arrays.toString(eventList.toArray()));
        //for testing purposes
    }
    
    public static void arrayToString(){
        //create everything used in the loops
        String typeAsString;
        String userTypeAsString;
        String userIDAsString;
        String yearAsString;
        String monthAsString;
        String dayAsString;
        String eventNameTemp;
        String startTimeAsString;
        String endTimeAsString;
        
        //Initalize, these should never be used
        int type = 0;
        int userType = 0;
        int userID = 0;
        int year = 0;
        int month = 0;
        int day = 0;
        String eventName = "null";
        int startTime = 0;
        int endTime = 0;
        
        String[] eventArray = eventList.toArray(new String[0]);
        for(int i = 0; i < eventArray.length; i++){
            String evt = eventArray[i];
            String[] arrOfEvt = evt.split(",");
            for(int a = 0; a < arrOfEvt.length; a++){
                typeAsString = arrOfEvt[0];
                userTypeAsString = (arrOfEvt[1]);
                userIDAsString = (arrOfEvt[2]);
                yearAsString = (arrOfEvt[3]);
                monthAsString = (arrOfEvt[4]);
                dayAsString = (arrOfEvt[5]);
                eventNameTemp = arrOfEvt[6];
                startTimeAsString = (arrOfEvt[7]);
                endTimeAsString = (arrOfEvt[8]);
              
                type = Integer.parseInt(typeAsString);
                userType = Integer.parseInt(userTypeAsString);
                userID = Integer.parseInt(userIDAsString);
                year = Integer.parseInt(yearAsString);
                month = Integer.parseInt(monthAsString);
                day = Integer.parseInt(dayAsString);
                eventName = eventNameTemp.trim();
                startTime = Integer.parseInt(startTimeAsString);
                endTime = Integer.parseInt(endTimeAsString);
            }
        newEntry(type, userType, userID, year, month, day, eventName, startTime, endTime);
        }  
    }
    
    public static void newEntry(int type, int userType, int userID, int year, int month, int day, String eventName, int startTime, int endTime){
        //type, userType, and userID will go unused for now
        
        String output = "(username) has a " + eventName + " on ";
        String outputMonth;
        
        switch (month){
            case 0: outputMonth = "Jan";
            break;
            case 1: outputMonth = "Feb";
            break;
            case 2: outputMonth = "Mar";
            break;
            case 3: outputMonth = "Apr";
            break;
            case 4: outputMonth = "May";
            break;
            case 5: outputMonth = "Jun";
            break;
            case 6: outputMonth = "Jul";
            break;
            case 7: outputMonth = "Aug";
            break;
            case 8: outputMonth = "Sep";
            break;
            case 9: outputMonth = "Oct";
            break;
            case 10: outputMonth = "Nov";
            break;
            case 11: outputMonth = "Dec";
            break;
            default: outputMonth = "AAAAAAAAAAAAAAAAAAAAAAA";
            break;
        }
        
        output = output + outputMonth + " " + day + ", " + year + " at " + startTime + " to " + endTime + ".";
        
        listModel.addElement(output);
    }
    
    
    //puts events into txt file
    public static void write() throws IOException{
            try
            {
                FileWriter text = new FileWriter("calendar.txt", false);
                BufferedWriter writer = new BufferedWriter(text);
                for (String str : eventList)
                {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
            }
            catch (IOException e)
            {
                System.out.println("");
            }
                
        }
    //Reads from file and puts it into the ArrayList
    public static void read() throws IOException{
            Scanner reader = null;
            try
            {
                reader = new Scanner(new File("calendar.txt")).useDelimiter("\\n");
            }
            catch ( FileNotFoundException ex)
            {
                System.out.println(ex+"  file not found ");
            }
            while (reader.hasNext())
            {
                String str = reader.nextLine();
                eventList.add(str);
            }
            reader.close();
            
    }
     public static void merge(String inputFile1, String inputFile2, String outputFile) throws IOException{
        String output="";
        try(Scanner sc1=new Scanner((new File(inputFile1)));
        Scanner sc2=new Scanner((new File(inputFile2)))){

        while(sc1.hasNext() || sc2.hasNext()){
            output+=sc1.next() + "\n" + sc2.next();
            output+="\n";
        }
        sc1.close();
		sc2.close();

        }

        try(PrintWriter pw=new PrintWriter(new File(outputFile))){
        pw.write(output);
        } 
    }
}