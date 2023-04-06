package Pet_registry;

import java.util.HashMap;
import java.util.Map;

public class UI {
    
    public static void showInfo(String msg) {
        System.out.println(msg);
    }

    public static void showInfoInt(String msg, Integer f) {
        System.out.printf(msg, f);
    }

    public static void showInfoStr(String msg, String f) {
        System.out.printf(msg, f);
    }

    public static String getInfo() {
        return System.console().readLine();           
    }

    public static void printMap(HashMap<String, String> map) {
        for(Map.Entry<String, String> item : map.entrySet()){
            System.out.println(item.getKey() + " - " + item.getValue());
        }
    }
}
