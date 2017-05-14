package ukma.tprk.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return "[" + strDate + "] ";
    }

    public static String prefixFinder(String s1, String s2) {

        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException();
        }

        int minLength;

        if (s1.length() < s2.length()) {
            minLength = s1.length();
        } else {
            minLength = s2.length();
        }

        for (int i = 0; i < minLength; i++) {

            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, minLength);
    }

    public static double getFileSize(File file) {
        double bytes = file.length();
        double kilobytes = (bytes / 1024);

        return kilobytes;
    }

    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);

        fos.close();
    }

}
