/*
 * Group Members:
 * 1) Rakan mansour alhaidari   ID: 446102988
 */

import java.util.Scanner;

public class LibrarySimulator {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // إنشاء عملاء المكتبة
        Member user1 = new Member(156, "Ali", 0);
        Member user2 = new Member(200, "Rakan", 0);
        Member user3 = new Member(127, "Saud", 0);

        int mainChoice;
        String line;

        // رسالة الترحيب
        System.out.println("====================================");
        System.out.println("     Welcome to the Library System   ");
        System.out.println("====================================");

        // القائمة الرئيسية
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1) " + user1.getId() + " - " + user1.getName());
            System.out.println("2) " + user2.getId() + " - " + user2.getName());
            System.out.println("3) " + user3.getId() + " - " + user3.getName());
            System.out.println("4) Login as Administrator");
            System.out.println("5) Exit");
            System.out.print("Enter choice (1-5): ");

            line = input.nextLine();
            mainChoice = 0;

            // التأكد من أن المدخل رقم من 1 إلى 5
            if(line.length() == 1 && line.charAt(0) >= '1' && line.charAt(0) <= '5') {
                mainChoice = line.charAt(0) - '0';

                // الدخول لحساب العميل
                if(mainChoice == 1) user1.userMenu(input);
                else if(mainChoice == 2) user2.userMenu(input);
                else if(mainChoice == 3) user3.userMenu(input);

                // الدخول كأدمن
                else if(mainChoice == 4) Member.adminMenu(input);

                // الخروج
                else if(mainChoice == 5) System.out.println("Exiting... Bye!");
            } 
            else {
                System.out.println("Invalid choice. Please enter 1-5");
            }

        } while(mainChoice != 5); // نهاية البرنامج

        input.close();
    }
}
