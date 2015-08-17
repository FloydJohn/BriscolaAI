package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IO {
    public static int askNumber(String prompt) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.print(prompt);
            String text;
            try {
                text = console.readLine();
            } catch (IOException e) {
                System.out.println("Console not available.");
                return 0;
            }
            try {
                return Integer.parseInt(text);
            } catch (Exception e) {
                System.out.println("Error: you can only insert numbers. Please retry.");
            }
        }
    }
}