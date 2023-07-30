package papermaster;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaperMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lblres;

    @FXML
    private ComboBox<String> combonewsppr;

    @FXML
    private TextField txtprice;
    Connection con;  //irv  which is actually a ref var of connection class
    PreparedStatement pst; 


    @FXML
    void doAvail(ActionEvent event) {
    	
    	float price = Float.valueOf(txtprice.getText());
    	String ppr = combonewsppr.getSelectionModel().getSelectedItem();
    	
    	try {
			pst = con.prepareStatement("insert into papers values(?,?)");
			pst.setString(1, ppr);
			pst.setFloat(2, price);
			pst.executeUpdate();
			lblres.setText("Record Saved........");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doEdit(ActionEvent event) {
    	
    	float price = Float.valueOf(txtprice.getText());
    	String ppr = combonewsppr.getSelectionModel().getSelectedItem();
    	
    	try {
			pst = con.prepareStatement("update papers set price=? where paper=? ");
			pst.setString(2, ppr);
			pst.setFloat(1, price);
			pst.executeUpdate();
			lblres.setText("Record Updated........");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    @FXML
    void doWithdraw(ActionEvent event) {
    	
    	String ppr = combonewsppr.getSelectionModel().getSelectedItem();
    	int a = combonewsppr.getSelectionModel().getSelectedIndex();
    	try {
			pst = con.prepareStatement("Delete from papers where paper=?");
			pst.setString(1, ppr);
			pst.executeUpdate();
			combonewsppr.getItems().remove(a);
			lblres.setText("Record Deleted........");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    @FXML
    void dosearch(ActionEvent event) {
    	
    	
    	String ppr = combonewsppr.getSelectionModel().getSelectedItem();
    	try {
			pst = con.prepareStatement("select * from papers where paper=?");
			pst.setString(1, ppr);
			ResultSet table = pst.executeQuery();
			
			while(table.next())
 			{
				float prc =table.getFloat("price");
				
				txtprice.setText(String.valueOf(prc));
 				
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
        
 //----------Filling up combonewspaper--
        
        try {
			pst = con.prepareStatement("SELECT paper FROM papers;");
			ResultSet table=pst.executeQuery();
			System.out.println(table);
			while(table.next())
			{
				String paper = table.getString("paper");
				ArrayList<String> items=new ArrayList<String>();
				items.add(paper);
				combonewsppr.getItems().addAll(items);
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        assert combonewsppr != null : "fx:id=\"combonewsppr\" was not injected: check your FXML file 'PaperMasterView.fxml'.";
        assert txtprice != null : "fx:id=\"txtprice\" was not injected: check your FXML file 'PaperMasterView.fxml'.";

    }

}
