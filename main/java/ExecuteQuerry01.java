import java.sql.*;

public class ExecuteQuerry01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","gof7021470");
        Statement st = con.createStatement();
        System.out.println("Connection Success");
        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın
String country="select country_name from countries where region_id=1";
boolean rslt1=st.execute(country);
        System.out.println(rslt1);
//recordlari gormek icin ExecuteQuery metodunu kullanmaliyiz
        ResultSet ccc=st.executeQuery(country);
        String str="";
        while(ccc.next()){
           str+=ccc.getString("country_name")+" ";
        }
        System.out.println(str);
        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
String country2="select country_id,country_name from countries where region_id>2";
ResultSet aaa=st.executeQuery(country2);

        while (aaa.next()){
            System.out.println(aaa.getString("country_name")+" "+ aaa.getString("country_id"));
        }
        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
String country3=" select * from companies where number_of_employees =(select min(number_of_employees) from companies)";
ResultSet bbb=st.executeQuery(country3);
while (bbb.next()){
    System.out.println(bbb.getInt(1) + " " + bbb.getString(2) + " " + bbb.getInt(3));

}

con.close();
st.close();
    }
}
