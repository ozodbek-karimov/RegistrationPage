package FileHandling;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class RegistrationApp {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String fileName = "account.txt";
        String directory = System.getProperty("user.dir");
        String finalPath = directory + File.separator + fileName;
        File file = new File(finalPath);

        System.out.println("🔊🔊 Welcome to Registration Page !! ");
        while (true) {
            System.out.println("""
                    1 -> Sign in
                    2 -> Log in
                    3 -> Delete account
                    0 -> Exit""");
            switch (sc.nextInt()) {
                case 1 -> signIn(finalPath);
                case 2 -> logIn(finalPath);
                case 3 -> deleteAccount(finalPath);
                case 0 -> {
                    System.out.println("Thanks for using our Registration service :) ");
                    System.exit(0);
                }
                default -> System.out.println("UPS something went wrong !!");
            }
        }
    }



    // LOG IN OYNASI
    private static void logIn(String finalPath) {
        try {
            FileReader rd = new FileReader(finalPath);
            BufferedReader br = new BufferedReader(rd);
            String check;
            System.out.println("Please enter your name !!");
            String name = sc.next();
            System.out.println("Please enter your password !!");
            String password = sc.next();
            boolean checking = false;
            while ((check = br.readLine()) != null) {
                String[] part = check.split(":");
                if (part[0].equals(name) && part[1].equals(password)) {
                    System.out.println("Everything is ok, Welcome " + name + " :)");
                    System.out.println("----------------------------------");
                    Thread.sleep(1000);
                    checking = true;
                }
            }
            if (!checking) {
                System.out.println("UPS something went wrong !!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !!");
        } catch (IOException e) {
            System.out.println("UPS something went wrong !!");
            System.out.println("----------------------------------");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    // YOZILISH OYNASI
    private static void signIn(String finalPath) {
        try {
            FileWriter wr = new FileWriter(finalPath, true);
            BufferedWriter bw = new BufferedWriter(wr);

            System.out.println("Please enter your name !!");
            String name = sc.next();
            System.out.println("Please set your password !!");
            String password = sc.next();
            System.out.println("Please enter password one more time !!");
            String confirmPassword = sc.next();
            if (password != null && password.length() >= 4 && password.length() <= 6 && password.equals(confirmPassword)) {
                System.out.println("All of informations are saved, thank you :)  ");
                System.out.println("----------------------------------");
                Thread.sleep(1000);
                bw.write(name + ":" + confirmPassword + "\n");
                bw.flush();
                bw.close();
            } else {
                System.out.println("Please check length of password !!( like **** or ******)");
                System.out.println("----------------------------------");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found !!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("UPS something went wrong !!");
            System.out.println("----------------------------------");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    //O'CHIRISH OYNASI
    private static void deleteAccount(String finalPath) {
        try {
            FileReader reader = new FileReader(finalPath);
            BufferedReader br = new BufferedReader(reader);

            System.out.print("Enter your name: ");
            String name = sc.next();
            System.out.print("Enter your password: ");
            String password = sc.next();

            ArrayList<String> list = new ArrayList<>();
            String str;
            boolean finding = false;

            while ((str = br.readLine()) != null) {
                String[] part = str.split(":");
                if (part[0].equals(name) && part[1].equals(password)) {
                    System.out.println(name + " do you want to delete your acoount ?");
                    System.out.println("Please write [ yes ] to delete...");

                    if (sc.next().toLowerCase().equals("yes")) {
                        finding = true;
                        continue;
                    }
                    System.out.println(name + " your account deleted !!");
                }
                list.add(str);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    //FAYL YARTISH OYNASI

    private static void createFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("File created\n");
            } else {
                System.out.println("File has already exist!\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with creating !\n");
            System.exit(0);
        }
    }
}
