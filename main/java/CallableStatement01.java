import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
Java'da method'lar return type sahibi olsa da olmasa da method olarak adlandırılır.
SQL'de ise data return ediyorsa "function" denir. Return yapmiyorsa "procedure" olarak adlandırilir
 */
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed","postgres","gof7021470");
        Statement st = con.createStatement();

        //CallableStatement ile function çağırmayı parametrelendireceğiz.
        //1.Adım: Function kodunu yaz:
        String sql1 ="CREATE OR REPLACE FUNCTION  toplamaF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";
        //2. Adım: Function'ı çalıştır.
        st.execute(sql1);

        //3. Adım: Fonction'ı çağır.
        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}");

        //4. Adım: Return için registerOurParameter() methodunu, parametreler için ise set() ... methodlarını uygula.
        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 6);
        cst1.setInt(3, 4);

        //5. Adım: execute() methodu ile CallableStatement'ı çalıştır.
        cst1.execute();

        //6. Adım: Sonucu çağırmak için return data type tipine göre

        System.out.println(cst1.getBigDecimal(1));
        //2. Örnek: Koninin hacmini hesaplayan bir function yazın.
        String sql2 ="CREATE OR REPLACE FUNCTION  konialanF(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN r*r*h;\n" +
                "\n" +
                "END\n" +
                "$$";
        st.execute(sql2);
        CallableStatement cst2 = con.prepareCall("{? = call konialanF(?, ?)}");
        cst2.registerOutParameter(1, Types.NUMERIC);
        cst2.setInt(2,5);
        cst2.setInt(3, 4);
        cst2.execute();
        System.out.printf("%.2f",cst2.getBigDecimal(1));
    }
}
