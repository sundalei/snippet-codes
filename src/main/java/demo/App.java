package demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {

    public static void main(String[] args) {
        try (InputStream in = App.class.getClassLoader().getResourceAsStream("mssql.properties")) {
            Properties props = new Properties();

            if (in == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            props.load(in);

            System.out.println(props.getProperty("url"));
            System.out.println(props.getProperty("username"));
            System.out.println(props.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
