package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme() {
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void showMessage(String str) {
        String message;
        String title;
        switch (str) {
            case "fill" -> {
                message = "Please fill in all the necessary blanks!";
                title = "Error!";
            }
            case "done" -> {
                message = "Operation successful!";
                title = "Result";
            }
            case "notFound" -> {
                message = "Record not found!";
                title = "Not Found!";
            }
            case "error" -> {
                message = "You have made a mistake!";
                title = "Error!";
            }
            default -> {
                message = str;
                title = "Message";
            }
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        String msg;
        if(str.equals("sure")) {
            msg = "Do you confirm?";
        } else {
            msg = str;
        }

        return JOptionPane.showConfirmDialog(null, msg, "Confirmation", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for(JTextField field : fieldList) {
            if(isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

}
