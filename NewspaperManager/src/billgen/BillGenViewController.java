package billgen;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import java.util.ResourceBundle;

import hawkermanager.MySqlConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BillGenViewController {

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private ComboBox<String> comboMobile;

    @FXML
    private URL location;

    @FXML
    private DatePicker dtBillUpto;

    @FXML
    private DatePicker dtLastBill;

    @FXML
    private Label lblBill;
    
    @FXML
    private Label lblres;

    @FXML
    private TextField txtMissDay;



    @FXML
    private TextField txtSelPapers;

    @FXML
    private TextField txtSelPrices;

    @FXML
    private TextField txtTotalPrices;
    
    Connection con;  
    PreparedStatement pst; 

    @FXML
    void doBillSave(ActionEvent event) {
    	LocalDate lastBillDate = dtLastBill.getValue();
        LocalDate billUptoDate = dtBillUpto.getValue();
        
        long daysDifference = ChronoUnit.DAYS.between(lastBillDate, billUptoDate);
        
        float bill = Float.valueOf(daysDifference)*daysDifference;
        
        lblBill.setText(String.valueOf(bill));
        
        String mob = comboMobile.getSelectionModel().getSelectedItem();
        LocalDate dt = dtLastBill.getValue();
        java.sql.Date dl=java.sql.Date.valueOf(dt);
        LocalDate dt2 = dtBillUpto.getValue();
        java.sql.Date du=java.sql.Date.valueOf(dt2);

        
        
        
    	try {
			pst = con.prepareStatement("insert into bills values(?,?,?,?,0)");
			pst.setString(1,mob);
			pst.setDate(2,dl);
			pst.setDate(3, du);
			pst.setFloat(4, bill);
			
			pst.executeUpdate();
			lblres.setText("Record Saved........");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @FXML
    void doFetchDetails(ActionEvent event) {
    	
    	String mob = comboMobile.getSelectionModel().getSelectedItem();
    	
    	try {
			pst = con.prepareStatement("Select * from customers where mobile=?");
			pst.setString(1, mob);
			
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String selppr = table.getString("spapers");
				String selprc = table.getString("sprices");
				
				txtSelPapers.setText(selppr);
				txtSelPrices.setText(selprc);
				
				 String[] prices = selprc.split(",");
				 
				 float total = 0;
		            for (String price : prices) {
		                total += Float.parseFloat(price.trim());
		            }
		            
		         txtTotalPrices.setText(String.valueOf(total));
				
			}
			
			//-----for date----
			
			pst = con.prepareStatement("Select * from bills where mobile=?");
			pst.setString(1, mob);
			
			ResultSet table2=pst.executeQuery();
			System.out.println(table2);
			
			if(table2.next())
			{
				pst = con.prepareStatement("Select * from bills where mobile=? order by dateto DESC LIMIT 1");
				pst.setString(1, mob);
				
				ResultSet tabledate1 = pst.executeQuery();
				
				
				
				while(tabledate1.next())
				{
					java.sql.Date dt = tabledate1.getDate("dateto");
					System.out.println("tabledate1");
					dtLastBill.setValue(dt.toLocalDate());
							
				}
				
				
			}
			else
			{
				pst = con.prepareStatement("Select * from customers where mobile=?");
				pst.setString(1, mob);
				
				ResultSet tabledate2=pst.executeQuery();
				
				while(tabledate2.next())
				{
					System.out.println("tabledate2");
					java.sql.Date dt2 = tabledate2.getDate("dos");
					dtLastBill.setValue(dt2.toLocalDate());
					
				}
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @FXML
    void initialize() {
    	
    	con = MySqlConnector.doConnect();
        if(con==null)
        	System.out.println("Connection problem....");
        else
        	System.out.println("Connected Successfully....");
        
        //----------Filling up comboMobile--
        
        try {
			pst = con.prepareStatement("SELECT mobile FROM customers;");
			ResultSet table=pst.executeQuery();
			System.out.println(table);
			while(table.next())
			{
				String mobile = table.getString("mobile");
				ArrayList<String> items=new ArrayList<String>();
				items.add(mobile);
				comboMobile.getItems().addAll(items);
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    	
    	
        assert dtBillUpto != null : "fx:id=\"dtBillUpto\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert dtLastBill != null : "fx:id=\"dtLastBill\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert lblBill != null : "fx:id=\"lblBill\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtMissDay != null : "fx:id=\"txtMissDay\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert comboMobile != null : "fx:id=\"comboMobile\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtSelPapers != null : "fx:id=\"txtSelPapers\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtSelPrices != null : "fx:id=\"txtSelPrices\" was not injected: check your FXML file 'BillGenView.fxml'.";
        assert txtTotalPrices != null : "fx:id=\"txtTotalPrices\" was not injected: check your FXML file 'BillGenView.fxml'.";

    }

}

