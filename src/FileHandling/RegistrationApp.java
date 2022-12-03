package FileHandling;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.Files.createFile;

public class RegistrationApp{
        static Scanner sc = new Scanner(System.in);
        public static void main(String[] args) throws IOException {
            String fileName = "key.txt";
            String directory = System.getProperty("user.dir");
            String finalPath = directory + File.separator + fileName;
            File file = new File(finalPath);

            ArrayList<String> logIn = new ArrayList<>();
            ArrayList<String> pin = new ArrayList<>();

            createFile(file.toPath());
            System.out.println(finalPath);
            while (true) {
                System.out.println("Welcome to Registration Page !! ");
                System.out.println("You have access to: ");
                System.out.println("1: Sign in");
                System.out.println("2: Log in");
                System.out.println("3: Exit");
                System.out.println("Please choose one of them !!");
                int option = sc.nextInt();
                switch (option) {
                    case 1 -> signIn(finalPath);
                    case 2 -> logIn(finalPath);
                    case 3 -> {
                        System.out.println("Thanks for using our Registration service :) ");
                        System.exit(0);
                    }
                    default -> System.out.println("UPS something went wrong !!");
                }
            }
        }

        private static void signIn(String finalPath) {
            try{
                FileWriter wr = new FileWriter(finalPath,true);
                BufferedWriter bw = new BufferedWriter(wr);

                System.out.println("Please set your name !!");
                String name = sc.next();
                System.out.println("Please set your password !!");
                String password = sc.next();
                System.out.println("Please enter password one more time !!");
                String confirm = sc.next();
                if(password != null && password.length() >= 4 && password.length() <= 6  && password.equals(confirm)){
                    System.out.println("I saved all dates, thank you :)  ");
                    System.out.println("----------------------------------");
                    Thread.sleep(1000);
                    bw.write(name + ":" + confirm + "\n");
                    bw.flush();
                    bw.close();
                }else{
                    System.out.println("Please check length of password !!( like **** or ******)");
                    System.out.println("----------------------------------");
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found !!");
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("UPS something went wrong !!");
                System.out.println("----------------------------------");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        private static void logIn(String finalPath) {
            try{
                FileReader rd = new FileReader(finalPath);
                BufferedReader br = new BufferedReader(rd);
                String check;
                System.out.println("Please enter your name !!");
                String name = sc.next();
                System.out.println("Please enter your password !!");
                String password = sc.next();
                boolean checking = false;
                while((check = br.readLine()) != null){
                    String[] part = check.split(":");
                    if (part[0].equals(name) && part[1].equals(password)){
                        System.out.println("Everything is ok, Welcome " + name + " :)");
                        System.out.println("----------------------------------");
                        Thread.sleep(1000);
                        checking = true;
                    }
                }
                if (!checking){
                    System.out.println("UPS something went wrong !!");
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found !!");
            } catch (IOException e){
                System.out.println("UPS something went wrong !!");
                System.out.println("----------------------------------");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
