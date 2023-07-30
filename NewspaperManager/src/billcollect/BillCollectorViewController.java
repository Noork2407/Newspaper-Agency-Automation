package billcollect;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import hawkermanager.MySqlConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BillCollectorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblAmt;

    @FXML
    private Label lblFrom;

    @FXML
    private Label lblResp;

    @FXML
    private Label lblTo;

    @FXML
    private TextField txtMobile;
    
    Connection con;  
    PreparedStatement pst; 

    @FXML
    void doPayment(ActionEvent event) {
    	
    	String mob = txtMobile.getText(); 
    	
    	try {
			pst = con.prepareStatement("update bills set billstatus=1 where mobile=?");
			pst.setString(1, mob);
			
			pst.executeUpdate();
			lblResp.setText("Bill Paid........");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @FXML
    void getBillDetails(ActionEvent event) {
    	
    	String mob = txtMobile.getText();    	
    	try {
			pst = con.prepareStatement("Select * from bills where mobile=? and billstatus = 0 order by dateto DESC LIMIT 1");
			pst.setString(1, mob);
			
			ResultSet table= pst.executeQuery();
			
			System.out.println(table);
			
		
			while(table.next())
			{
				String amt = String.valueOf(table.getFloat("bill"));
				System.out.println(amt);
				java.sql.Date dfrom = table.getDate("datefrom");
				java.sql.Date dto = table.getDate("dateto");
				
				lblAmt.setText(amt);
				lblFrom.setText(dfrom.toString());
				lblTo.setText(dto.toString());
						
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
        
        assert lblAmt != null : "fx:id=\"lblAmt\" was not injected: check your FXML file 'BillCollectorView.fxml'.";
        assert lblFrom != null : "fx:id=\"lblFrom\" was not injected: check your FXML file 'BillCollectorView.fxml'.";
        assert lblResp != null : "fx:id=\"lblResp\" was not injected: check your FXML file 'BillCollectorView.fxml'.";
        assert lblTo != null : "fx:id=\"lblTo\" was not injected: check your FXML file 'BillCollectorView.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'BillCollectorView.fxml'.";

    }

}
