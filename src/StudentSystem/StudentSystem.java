package StudentSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StudentSystem {
    private static final Connection connection = SQLConnection.Connect();
    private static final String table = "student";
    private static int id,age,score;
    private static String name, gender,birth;

    public static void Close() {
        SQLConnection.Close(connection);
    }

    public static void Insert() {
        if (get_information_insert()) {
            try {
                Statement statement = connection.createStatement();
                String sql = "insert into " + table + " (id,Name,Gender,Birth,Age,Score) values ('" + id + "','" + name + "','" + gender + "','" + birth + "','" + age + "','" + score + "')";
                int result = statement.executeUpdate(sql);
                if (result == 0) {
                    System.out.println("fail to add");
                } else {
                    System.out.println("successfully added");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Delete() {
        int id = get_studentId();
        if (Query_ById(id)) {
            boolean confirm_choice = Confirm();
            if (confirm_choice) {
                try {
                    Statement statement = connection.createStatement();
                    String sql = "update " + table + " set is_delete=1 where id='" + id + "'";
                    int result = statement.executeUpdate(sql);
                    if (result != 0) {
                        System.out.println("successfully delete");
                    } else {
                        System.out.println("fail to delete");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Delete cancelled.");
            }
        } else {
            System.out.println("The student's information could not be found. Please try again.");
        }
    }

    public static void QueryOne() {
        int id = get_studentId();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from "+table+" where is_delete=0 and id='"+id+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("The student's information could not be found. Please try again.");
            } else {
                System.out.printf("%6s%10s%10s%12s%9s%9s","ID","Name","Gender","Birth","Age","Score");
                System.out.println();
                System.out.printf("%7s%9s%10s%14s%7s%8s",String.valueOf(resultSet.getInt("id")),resultSet.getString("Name"),resultSet.getString("Gender"),resultSet.getString("Birth"),String.valueOf(resultSet.getInt("Age")),String.valueOf(resultSet.getInt("Score")));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void QueryAll() {
        String sql = "select * from "+table+" where is_delete=0";
        Query(sql);
    }

    public static void Update() {
        int id = get_studentId();
        if (Query_ById(id)) {
            int choice = get_UpdateChoice();
            switch (choice) {
                case 1: {
                    int change_id = Student.get_id();
                    boolean confirm = Confirm();
                    if (!Query_ById(change_id) && confirm) {
                        String sql = "update "+table+" set id='"+change_id+"' where id='"+id+"'";
                        SQL_Update(sql);
                    } else if(!confirm) {
                        System.out.println("Modify cancelled.");
                    } else {
                        System.out.println("The student id is repeated. Please try again.");
                    }
                    break;
                }
                case 2: {
                    String change_name = Student.get_name();
                    boolean confirm = Confirm();
                    if (confirm) {
                        String sql = "update " + table + " set Name='" + change_name + "' where id='" + id + "'";
                        SQL_Update(sql);
                    } else {
                        System.out.println("Modify cancelled.");
                    }
                    break;
                }
                case 3: {
                    String change_gender = Student.get_gender();
                    boolean confirm = Confirm();
                    if (confirm) {
                        String sql = "update " + table + " set Gender='" + change_gender + "' where id='" + id + "'";
                        SQL_Update(sql);
                    } else {
                        System.out.println("Modify cancelled.");
                    }
                    break;
                }
                case 4: {
                    String change_birth = Student.get_birth();
                    int change_age = Student.get_age();
                    boolean confirm = Confirm();
                    if (confirm) {
                        String sql = "update " + table + " set Birth='" + change_birth + "',Age='" + change_age + "' where id='" + id + "'";
                        SQL_Update(sql);
                    } else {
                        System.out.println("Modify cancelled.");
                    }
                    break;
                }
                case 5: {
                    int change_score = Student.get_score();
                    boolean confirm = Confirm();
                    if (confirm) {
                        String sql = "update " + table + " set Score='" + change_score + "' where id='" + id + "'";
                        SQL_Update(sql);
                    } else {
                        System.out.println("Modify cancelled.");
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        } else {
            System.out.println("The student's information could not be found. Please try again.");
        }
    }

    public static void Average() {
        int sum = get_sum_average()[0];
        int count = get_sum_average()[1];
        double average = (double) sum / count;
        System.out.println("Average："+average);
    }

    public static void Sum() {
        int sum = get_sum_average()[0];
        System.out.println("Sum："+sum);
    }

    public static void Max() {
        String sql = "select * from "+table+" where is_delete=0 and Score=(select MAX(Score) from "+table+")";
        Query(sql);
    }

    public static void Min() {
        String sql = "select * from "+table+" where is_delete=0 and Score=(select MIN(Score) from "+table+")";
        Query(sql);
    }

    public static void OrderById(int choice) {
        switch (choice) {
            case 1: {
                String sql = "select * from "+table+" where is_delete=0 order by id";
                Query(sql);
                break;
            }
            case 2: {
                String sql = "select * from "+table+" where is_delete=0 order by id desc";
                Query(sql);
                break;
            }
            default: {
                break;
            }
        }
    }

    public static void OrderByAge(int choice) {
        switch (choice) {
            case 1: {
                String sql = "select * from "+table+" where is_delete=0 order by Age";
                Query(sql);
                break;
            }
            case 2: {
                String sql = "select * from "+table+" where is_delete=0 order by Age desc";
                Query(sql);
                break;
            }
            default: {
                break;
            }
        }
    }

    public static void OrderByScore(int choice) {
        switch (choice) {
            case 1: {
                String sql = "select * from "+table+" where is_delete=0 order by Score";
                Query(sql);
                break;
            }
            case 2: {
                String sql = "select * from "+table+" where is_delete=0 order by Score desc";
                Query(sql);
                break;
            }
            default: {
                break;
            }
        }
    }

    private static int get_studentId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter the student id：");
        try {
            int id =sc.nextInt();
            if (id <= 0) {
                System.out.println("Please enter a positive integer!");
                return get_studentId();
            }
            return id;
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            return get_studentId();
        }
    }

    private static boolean get_information_insert() {
        Student s = new Student();
        s.get_information();
        id = s.return_id();
        if (Query_ById(id)) {
            System.out.println("The student id is repeated. Please try again.");
            return false;
        }
        age = s.return_age();
        score = s.return_score();
        name = s.return_name();
        gender = s.return_gender();
        birth = s.return_birth();
        return true;
    }

    private static boolean Query_ById(int id) { //查到则返回true
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from "+table+" where is_delete=0 and id='"+id+"'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void Query(String sql) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.printf("%6s%10s%10s%12s%9s%9s","ID","Name","Gender","Birth","Age","Score");
            System.out.println();
            while (resultSet.next()) {
                System.out.printf("%7s%9s%10s%14s%7s%8s",String.valueOf(resultSet.getInt("id")),resultSet.getString("Name"),resultSet.getString("Gender"),resultSet.getString("Birth"),String.valueOf(resultSet.getInt("Age")),String.valueOf(resultSet.getInt("Score")));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int get_UpdateChoice() {
        System.out.println("Select the item you want to modify, and enter the corresponding number.");
        System.out.println("1.id 2.Name 3.Gender 4.Birthday 5.Score 0.Cancel");
        int choice;
        try {
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            if (choice < 0 || choice > 5) {
                System.out.println("Please enter an integer as required.");
                return get_UpdateChoice();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter an integer as required.");
            return get_UpdateChoice();
        }
        return choice;
    }

    private static void SQL_Update(String sql) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            if (result != 0) {
                System.out.println("modify successfully");
            } else {
                System.out.println("fail to modify");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int[] get_sum_average() {
        int []arr = new int[2];
        int sum = 0,count = 0;
        try {
            Statement statement = connection.createStatement();
            String sql = "select Score from "+table+" where is_delete=0";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                sum += resultSet.getInt("Score");
                count++;
            }
            arr[0] = sum;
            arr[1] = count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    private static boolean Confirm() {
        System.out.println("Are you sure to proceed with the selected operation? y/n");
        boolean choice = Confirm_choice();
        return choice;
    }

    private static boolean Confirm_choice() {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        Pattern pattern = Pattern.compile(".*\\d+.*");
        if (pattern.matcher(choice).matches()) {
            System.out.println("You can't enter numbers");
            return Confirm_choice();
        } else if ("y".equals(choice)) {
            return true;
        } else if ("n".equals(choice)) {
            return false;
        } else {
            System.out.println("Please enter y/n");
            return Confirm_choice();
        }
    }
}
