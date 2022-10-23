package project;

public class Train {
    private int Year;
    private int Month;
    private int Day;
    private int Hour;
    private String Minute;
    private String Location;
    private String Context;
    public Train(int Year,int Month,int Day,int Hour,String Minute, String Location, String Context) {
        this.Year = Year;
        this.Month = Month;
        this.Day = Day;
        this.Hour = Hour;
        this.Minute = Minute;
        this.Location = Location;
        this.Context = Context;
    }
    public int getYear() {
        return Year;
    }

    public int getMonth() {
        return Month;
    }

    public int getDay() {
        return Day;
    }

    public int getHour() {
        return Hour;
    }

    public String getMinute() {
        return Minute;
    }

    public String getLocation() {
        return Location;
    }

    public String getContext() {
        return Context;
    }

    public Train() {
        this(0,0, 0, 0," "," "," ");
    }
}
