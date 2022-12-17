import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
Parametrelendirilmis(Parameterised) SQL query(sorgu)'leri ile calisir. Bir sorguyu 0 veya daha fazla parametre ile kullanabiliriz
 */
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","gof7021470");
        Statement st = con.createStatement();
//1. Örnek: Prepared statement kullanarak company adı
// IBM olan number_of_employees değerini 9999 olarak güncelleyin.

                String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ? ";

                //2. Adım: PreparedStatement objesini oluştur.
                PreparedStatement pst1 = con.prepareStatement(sql1);

                //3. Adım: setInt(), setString(), ... methodlarını kullanarak soru işaretleri yerlerine değer gir.
                pst1.setInt(1, 9999);
                pst1.setString(2, "IBM");
        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);


        //4. Adım: Query'yi çalıştır.
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 = "SELECT * FROM companies";

        ResultSet rs1 =  st.executeQuery(sql2);

        while(rs1.next()){
            System.out.println(rs1.getInt(1)+"--"+rs1.getString(2)+"--"+rs1.getInt(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int guncellenenSatirSayi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayi = " + guncellenenSatirSayisi2);

        ResultSet rs2 =  st.executeQuery(sql2);

        while(rs2.next()){
            System.out.println(rs2.getInt(1)+"--"+rs2.getString(2)+"--"+rs2.getInt(3));
        }

        con.close();
        st.close();
        rs1.close();
        rs2.close();
    }
}




