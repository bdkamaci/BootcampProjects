import core.Helper;
import view.LoginView;

public class App {
    public static void main(String[] args) {

            /* Katmanli Mimari
            DAO - DB Connection (Data Access Object)
            Core - Sistemle alakali dosyala
            Entity - Dao'dan cekilen veriler gelecek
            Business - View'in iletisim kurdugu yer
            View - Arayuz yonetimi
            */
        Helper.setTheme();
        LoginView loginView = new LoginView();

        }
    }