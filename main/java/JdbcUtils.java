import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
   private static Connection connection;
   private static Statement st;




    public static Connection connectToDataBase(String hostName, String dbName,String username, String password)  {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(connection!=null){
            System.out.println("Connection Success");
        }else {
            System.out.println("Connection Fail");
        }

        return connection;
    }

public static Statement getStatement(){
    try {
        st = connection.createStatement();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
return st;
}
public static boolean  execute(String sql){
        boolean isExecute;
       try {
          isExecute= st.execute(sql);
       }catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return isExecute;
}
public static void close(){
        try {
            connection.close();
            st.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
}
//table olusturun
public static void createTable(String tableName, String... columnName_dataType ){
    StringBuilder columnName_dataValue = new StringBuilder("");

    for(String w : columnName_dataType){

        columnName_dataValue.append(w).append(",");

    }

    columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);

    try {
        st.execute( "CREATE TABLE "+tableName+"("+columnName_dataValue+")");
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}



}
