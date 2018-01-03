package SYS;

import java.time.ZoneId;

public class UserValues
{
    private static int age = 30;
    private static int height = 68;
    private static int weight = 59;
    private static char gender = 'm';
    private static ZoneId timezone;
    private static int timeformat;

    public UserValues (int years, int inches, char inGender, int kilograms, ZoneId zone, int format)
    {
        age = years;
        height = inches;
        weight = kilograms;
        gender = inGender;
        timezone = zone;
        timeformat = format;
    }

    public static int getAge()
    {
        return age;
    }

    public static int getHeight()
    {
        return height;
    }

    public static int getWeight() { return weight; }

    public static ZoneId getZone()
    {
        return timezone;
    }

    public int getFormat()
    {
        return timeformat;
    }

    public static char getGender() { return gender; }
}
