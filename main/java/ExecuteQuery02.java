import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","gof7021470");
        Statement st = con.createStatement();
//1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan
// company ve number_of_employees değerlerini çağırın.
ResultSet st1 =st.executeQuery("select company,number_of_employees from companies order by number_of_employees desc offset 1 row limit 1");
while (st1.next()){
    System.out.println(st1.getString("company") + "--" + st1.getInt("number_of_employees"));

}
con.close();
st1.close();
st1.close();

    }
}
