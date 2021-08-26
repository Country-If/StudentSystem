package StudentSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {
    private static final Connection connection = SQLConnection.Connect();
    private static final String table = "user";
    private static String username;
    private static String password;

    public static void Register() {
        if (judge_register()) {
            try {
                Statement statement = connection.createStatement();

                String sql_insert = "insert into " + table + " (username,password) values ('" + username + "','" + password + "')";
                int result = statement.executeUpdate(sql_insert);
                if (result == 0) {
                    System.out.println("fail to register");
                } else {
                    System.out.println("registered successfully");
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Login() {
        get_information();

        try {
            Statement statement = connection.createStatement();
            String sql = "select * from "+table+" where username='"+ username +"' AND password='"+ password +"'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()) {
                System.out.println("Incorrect name or password. Please try again.");
                System.out.println();
                Menu.start_user_system();
            } else {
                System.out.println("login successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        } finally {
            Close();
        }
    }

    public static void Close() {
        SQLConnection.Close(connection);
    }

    private static boolean judge_register() {
        get_information();

        try {
            Statement statement = connection.createStatement();
            String sql_select_username = "select * from "+table+" where username='"+username+"'";
            ResultSet resultSet_username = statement.executeQuery(sql_select_username);
            if (resultSet_username.next()) {
                System.out.println("The account already exists, please try again");
                System.out.println();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void get_information() {
        System.out.print("Please input your name：");
        username = PreventSQLInjection();
        System.out.print("Please enter your password：");
        password = PreventSQLInjection();
    }

    private static String PreventSQLInjection() {
        String str;
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        if (str.contains("'")) {
            System.out.println("An illegal character \"'\" was entered. Please re-enter.");
            return PreventSQLInjection();
        }
        return str;
    }
}
