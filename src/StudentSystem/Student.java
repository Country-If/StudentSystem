package StudentSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Student {
    private static int id,age,score;
    private static String name="", gender ="",birthYear="",birthMonth="",birthDay="";
    private static final int ThisYear = Calendar.getInstance().get(Calendar.YEAR);

    public int return_id() {
        return id;
    }
    public int return_age() {
        return age;
    }
    public int return_score() {
        return score;
    }
    public String return_name() {
        return name;
    }
    public String return_gender() {
        return gender;
    }
    public String return_birth() {
        return birthYear+"-"+birthMonth+"-"+birthDay;
    }

    public static int get_id() {
        Scanner sc = new Scanner(System.in);
        System.out.print("student id：");
        try {
            int id =sc.nextInt();
            if (id <= 0) {
                System.out.println("Please enter a positive integer!");
                return get_id();
            }
            return id;
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            return get_id();
        }
    }
    public static String get_name() {
        Scanner sc = new Scanner(System.in);
        System.out.print("name：");
        String name = sc.nextLine();
        Pattern pattern = Pattern.compile(".*\\d+.*");
        if (pattern.matcher(name).matches()) {
            System.out.println("You can't enter numbers");
            return get_name();
        }
        return name;
    }
    public static String get_gender() {
        Scanner sc = new Scanner(System.in);
        System.out.print("gender：");
        String gender = sc.nextLine();
        Pattern pattern = Pattern.compile(".*\\d+.*");
        if (pattern.matcher(gender).matches()) {
            System.out.println("You can't enter numbers");
            return get_gender();
        } else if (!("male".equals(gender)) && !("female".equals(gender))) {
            System.out.println("Please enter male/female");
            return get_gender();
        }

        return gender;
    }
    private static String get_birthYear() {
        Scanner sc = new Scanner(System.in);
        System.out.print("year of birth(Year)：");
        try {
            int birthYear = sc.nextInt();
            if (birthYear < ThisYear-30 || birthYear > ThisYear-5) {
                System.out.println("Incorrect input, please re-enter!");
                return get_birthYear();
            }
            return String.valueOf(birthYear);
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            return get_birthYear();
        }
    }
    private static String get_birthMonth() {
        Scanner sc = new Scanner(System.in);
        System.out.print("month of birth(Month)：");
        try {
            int birthMonth = sc.nextInt();
            if (birthMonth < 1 || birthMonth > 12) {
                System.out.println("Incorrect input, please re-enter!");
                return get_birthMonth();
            }
            return String.valueOf(birthMonth);
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            return get_birthMonth();
        }
    }
    private static String get_birthDay() {
        Scanner sc = new Scanner(System.in);
        System.out.print("day of birth(Day)：");
        try {
            int birthDay = sc.nextInt();
            if (!checkDate(birthYear+"-"+birthMonth+"-"+birthDay)) {
                System.out.println("Incorrect input, please re-enter!");
                return get_birthDay();
            }
            return String.valueOf(birthDay);
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            return get_birthDay();
        }
    }
    public static String get_birth() {
        birthYear = get_birthYear();
        birthMonth = get_birthMonth();
        birthDay = get_birthDay();
        return birthYear+"-"+birthMonth+"-"+birthDay;
    }
    public static int get_age() {
        return ThisYear - Integer.parseInt(birthYear);
    }
    public static int get_score() {
        Scanner sc = new Scanner(System.in);
        System.out.print("scores：");
        try {
            int score = sc.nextInt();
            if (score < 0) {
                System.out.println("Please enter a positive integer!");
                return get_score();
            }
            return score;
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            return get_score();
        }
    }

    private static boolean checkDate(String str) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sd.setLenient(false);
            sd.parse(str);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void get_information() {
        id = get_id();
        name = get_name();
        gender = get_gender();
        birthYear = get_birthYear();
        birthMonth = get_birthMonth();
        birthDay = get_birthDay();
        age= get_age();
        score = get_score();
    }
}
