import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        Connection con=JdbcUtils.connectToDataBase("localhost","techproed","postgres","gof7021470");
        Statement st =JdbcUtils.getStatement();
//JdbcUtils.execute("create table schulers(id char(5), name varchar(20), no int)");

        JdbcUtils.createTable("School","classes VARCHAR(20)","teacher_name VARCHAR(20)","id INT");
        JdbcUtils.close();
    }
}
