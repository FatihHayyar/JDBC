import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CountriesTest {
    /*
        Given
          User connects to the database
        When
          User sends the query to get the region ids from "countries" table
        Then
          Assert that the number of region ids greater than 1 is 17.
        And
          User closes the connection
       */
@Test
    public void countriesTest() throws SQLException {
    JdbcUtils.connectToDataBase("localhost","techproed","postgres","gof7021470");
    Statement st =JdbcUtils.getStatement();
    ResultSet region=st.executeQuery("select region_id from countries");
    List<Integer> liste=new ArrayList<Integer>();
    while(region.next()){
        liste.add(region.getInt("region_id"));
    }
    List<Integer> newliste=new ArrayList<Integer>();
    for (Integer w:liste){
        if(w>1){
            newliste.add(w);
        }
    }
Assert.assertEquals(17,newliste.size());
JdbcUtils.close();
}

}
