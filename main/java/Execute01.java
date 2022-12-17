import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //driver a kaydol
        Class.forName("org.postgresql.Driver");
        //Database e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","gof7021470");
        //Statement olustur
        Statement st = con.createStatement();
        System.out.println("Connection Success");
        //calistir
        //1.Örnek: "workers" adında bir table oluşturup
        // "worker_id,worker_name, worker_salary" sütunlarını ekleyin.
        boolean a1=st.execute("create table workers(\n" +
                "\t  worker_id char(5),\n" +
                "\t\t  worker_name varchar(20),\n" +
                "\t\t  worker_salary int\n" +
                "\t  )");
        //execute DDL ve DQL komutlarinda kullanilabilir

        System.out.println(a1);
        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.

st.execute("alter table workers add worker_adress varchar(80)");
//3.Örnek: Drop workers table
        st.execute("drop table workers");

        //baglanti ve statementi kapat
con.close();
st.close();
    }
}
