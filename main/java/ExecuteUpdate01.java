import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","gof7021470");
        Statement st = con.createStatement();
///1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan
//        number_of_employees değerlerini 16000 olarak UPDATE edin
String str="update companies set number_of_employees=16000 where number_of_employees<(select avg(number_of_employees) from companies)";
int a=st.executeUpdate(str);//kac tane satiri degistigini verir
        System.out.println(a);
        con.close();
        st.close();

    }
}
