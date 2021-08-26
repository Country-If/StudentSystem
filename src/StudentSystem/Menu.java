package StudentSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static int choice;
    public static void start_user_system() {
        System.out.println("1.Register  2.Login  0.Exit");
        User_system();
    }

    public static void start_student_system() {
        System.out.println();
        System.out.println("1.Add 2.Delete 3.Single query 4.Query all 5.Modify 6.Average 7.Sum 8.Highest 9.Lowest 10.Sort 0.Exit");
        Student_system();
    }

    private static void User_system() {
        try {
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 0: {
                    User.Close();
                    System.exit(0);
                }
                case 1: {
                    User.Register();
                    System.out.println("1.Register  2.Login  0.Exit");
                    User_system();
                    break;
                }
                case 2: {
                    User.Login();
                    break;
                }
                default: {
                    System.out.println("please enter 0-2!");
                    User_system();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            User_system();
        }
    }

    private static void Student_system() {
        try {
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 0: {
                    StudentSystem.Close();
                    System.exit(0);
                }
                case 1: {
                    StudentSystem.Insert();
                    start_student_system();
                    break;
                }
                case 2: {
                    StudentSystem.Delete();
                    start_student_system();
                    break;
                }
                case 3: {
                    StudentSystem.QueryOne();
                    start_student_system();
                    break;
                }
                case 4: {
                    StudentSystem.QueryAll();
                    start_student_system();
                    break;
                }
                case 5: {
                    StudentSystem.Update();
                    start_student_system();
                    break;
                }
                case 6: {
                    StudentSystem.Average();
                    start_student_system();
                    break;
                }
                case 7: {
                    StudentSystem.Sum();
                    start_student_system();
                    break;
                }
                case 8: {
                    StudentSystem.Max();
                    start_student_system();
                    break;
                }
                case 9: {
                    StudentSystem.Min();
                    start_student_system();
                    break;
                }
                case 10: {
                    System.out.println("1.Sort by id  2.Sort by age  3.Sort by grade  0.Cancel");
                    Order();
                    start_student_system();
                    break;
                }
                default: {
                    System.out.println("please enter 0-10!");
                    Student_system();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            Student_system();
        }
    }

    private static void Order() {
        try {
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 0: break;
                case 1: {
                    int choice = get_order_choice_0_2();
                    if (choice != 0) {
                        StudentSystem.OrderById(choice);
                    }
                    break;
                }
                case 2: {
                    int choice = get_order_choice_0_2();
                    if (choice != 0) {
                        StudentSystem.OrderByAge(choice);
                    }
                    break;
                }
                case 3: {
                    int choice = get_order_choice_0_2();
                    if (choice != 0) {
                        StudentSystem.OrderByScore(choice);
                    }
                    break;
                }
                default: {
                    System.out.println("please enter 0-3!");
                    Order();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            Order();
        }
    }

    private static int get_order_choice_0_2() {
        System.out.println("1.Ascending sort  2.Descending sort  0.Cancel");
        int order_choice = 0;
        try {
            Scanner sc = new Scanner(System.in);
            order_choice = sc.nextInt();
            if (order_choice < 0 || order_choice > 2) {
                System.out.println("please enter 0-2！");
                get_order_choice_0_2();
            }
            return order_choice;
        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers!");
            get_order_choice_0_2();
        }
        return order_choice;
    }
}
