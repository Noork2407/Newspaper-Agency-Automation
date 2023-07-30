
package customers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import hawkermanager.MySqlConnector;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CustomerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lblres;

    @FXML
    private ListView<String> ListAvailppr;

    @FXML
    private ListView<String> ListAvailprice;

    @FXML
    private ListView<String> ListSelppr;

    @FXML
    private ListView<String> ListSelprice;

    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private DatePicker dos;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAreaHawker;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;
    
    Connection con;  
    PreparedStatement pst; 
    
    @FXML
    void doDoubleclickDelete(MouseEvent event) {
    	
    	if(event.getClickCount()==2)
    	{
    		int a = ListSelppr.getSelectionModel().getSelectedIndex();
   	     
    		String ppr = ListSelppr.getSelectionModel().getSelectedItem();
    		String price = ListSelprice.getItems().get(a);
    		
    		ListSelppr.getItems().remove(ppr);
    		ListSelprice.getItems().remove(price);
    	}
    	

    }

    @FXML
    void doDoubleclickInsert(MouseEvent event) {
    	
    	if(event.getClickCount()==2)
    	{
    		int a = ListAvailppr.getSelectionModel().getSelectedIndex();
    	     
    		String ppr = ListAvailppr.getSelectionModel().getSelectedItem();
    		String price = ListAvailprice.getItems().get(a);
    		
    		ListSelppr.getItems().add(ppr);
    		ListSelprice.getItems().add(price);
    		
    	}
    	

    }

    @FXML
    void doSubscribe(ActionEvent event) {
    	
    	 String mob = txtMobile.getText();
    	 String name = txtName.getText();
    	 String add = txtAddress.getText();
    	 String email = txtEmail.getText();
    	 LocalDate ld = dos.getValue();
    	 java.sql.Date date = java.sql.Date.valueOf(ld);
    	 String area = comboArea.getSelectionModel().getSelectedItem();
    	 String hawker = txtAreaHawker.getText();
    	 
    	 ObservableList<String> selectedppr = ListSelppr.getItems();
    	 String Selppr = String.join(",", selectedppr);
    	 
    	 ObservableList<String> selectedprice = ListSelprice.getItems();
    	 String Selprice = String.join(",", selectedprice);
    	 
    
    	 try {
			pst=con.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, mob);
			pst.setString(2, name);
			pst.setString(3, Selppr);
			pst.setString(4, Selprice);
			pst.setString(5, area);
			pst.setString(6, hawker);
			pst.setString(7, email);
			pst.setString(8, add);
			pst.setDate(9, date);
			
			pst.executeUpdate();
			lblres.setText("Record Saved........");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 
    	 
    	 
    	 
    	 
    	
    }
    
    @FXML
    void doSearch(ActionEvent event) {
    	
    	String mob = txtMobile.getText();
    	
    	try {
			pst=con.prepareStatement("select * from customers where mobile=?");
			pst.setString(1, mob);
			ResultSet table=pst.executeQuery();
			
			while(table.next())
			{
				String mobile = table.getString("mobile");
				String name = table.getString("name");
				String ppr = table.getString("spapers");
				String prc = table.getString("sprices");
				String area = table.getString("area");
				String hwk = table.getString("hawker");
				String email = table.getString("email");
				String add = table.getString("address");
				java.sql.Date dt = table.getDate("dos");
				
				txtMobile.setText(mobile);
				txtName.setText(name);
				ListSelppr.getItems().setAll(Arrays.asList(ppr.split(",")));
				ListSelprice.getItems().setAll(Arrays.asList(prc.split(",")));
				comboArea.setValue(area);
				txtAreaHawker.setText(hwk);
				txtEmail.setText(email);
				txtAddress.setText(add);
				dos.setValue(dt.toLocalDate());
				
				
				
				
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }


    @FXML
    void doUnsubscribe(ActionEvent event) {
    	
    	 String mob = txtMobile.getText();
    	 try {
			pst=con.prepareStatement("delete from customers where mobile=?");
			pst.setString(1, mob);
			pst.executeUpdate();
			lblres.setText("Record Deleted........");

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doUpdate(ActionEvent event) {
    	
    	 String mob = txtMobile.getText();
    	 String name = txtName.getText();
    	 String add = txtAddress.getText();
    	 String email = txtEmail.getText();
    	 LocalDate ld = dos.getValue();
    	 java.sql.Date date = java.sql.Date.valueOf(ld);
    	 String area = comboArea.getSelectionModel().getSelectedItem();
    	 String hawker = txtAreaHawker.getText();
    	 
    	 ObservableList<String> selectedppr = ListSelppr.getItems();
    	 String Selppr = String.join(",", selectedppr);
    	 
    	 ObservableList<String> selectedprice = ListSelprice.getItems();
    	 String Selprice = String.join(",", selectedprice);
    	 
    
    	 try {
			pst=con.prepareStatement("update customers set name=?, spapers=?, sprices=?, area=?, hawker=?, email=? ,address=?,dos=? where mobile=?");
			pst.setString(9, mob);
			pst.setString(1, name);
			pst.setString(2, Selppr);
			pst.setString(3, Selprice);
			pst.setString(4, area);
			pst.setString(5, hawker);
			pst.setString(6, email);
			pst.setString(7, add);
			pst.setDate(8, date);
			
			pst.executeUpdate();
			lblres.setText("Record Updated........");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @FXML
    void doGetHawkers(ActionEvent event) {
    	 
    	System.out.println("in method");
    	String area = comboArea.getSelectionModel().getSelectedItem();
    	
    	try {
			pst = con.prepareStatement("SELECT hname FROM hawkers WHERE alloareas LIKE ?;");
			pst.setString(1,"%"+area+"%");
			
            ResultSet table = pst.executeQuery();
           
			
			while(table.next())
 			{
				 System.out.println(table.getString("hname"));
				String name =table.getString("hname");
				

	    	    String currentText = txtAreaHawker.getText();
	    	    String updatedText = currentText.isEmpty() ? name : currentText + ", " + name;
	    	    
				txtAreaHawker.setText(updatedText);
				
			
 				
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
    
       // ArrayList<String>pprs=new ArrayList<String>(Arrays.asList("Select","Dainik Jagran","The Indian Express","Times Of India ","The Tribue","Ajit","The Hindustan Times","Dainik Bhaskar","Rozana Spokesman"));
    	//ListAvailppr.getItems().addAll(pprs);
    	
    	// ArrayList<String>prices=new ArrayList<String>(Arrays.asList("Select","5","7","9","9","5","7","8","5"));
     	//ListAvailprice.getItems().addAll(prices);
     	
     	//<String>area=new ArrayList<String>(Arrays.asList("Select","New Cantt Road","Dogar Basti","Green Avenue","Balbir Basti","Giani Zail Singh Avenue","Main Bazar","Mohalla Mohikhana","Puri Colony","Bazigar Basti"));
     	//comboArea.getItems().addAll(area);
     	
     	
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
				ListAvailppr.getItems().addAll(items);
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
//----------Filling up comboareas-
        
        try {
			pst = con.prepareStatement("SELECT area FROM areas;");
			ResultSet table=pst.executeQuery();
			System.out.println(table);
			while(table.next())
			{
				String ar = table.getString("area");
				ArrayList<String> items=new ArrayList<String>();
				items.add(ar);
				comboArea.getItems().addAll(items);
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//----------Filling up combonewspaper--
        
        try {
			pst = con.prepareStatement("SELECT price FROM papers;");
			ResultSet table=pst.executeQuery();
			System.out.println(table);
			while(table.next())
			{
				String prc = table.getString("price");
				ArrayList<String> items=new ArrayList<String>();
				items.add(prc);
				ListAvailprice.getItems().addAll(items);
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	
    	
    	
        assert ListAvailppr != null : "fx:id=\"ListAvailppr\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert ListAvailprice != null : "fx:id=\"ListAvailprice\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert ListSelppr != null : "fx:id=\"ListSelppr\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert ListSelprice != null : "fx:id=\"ListSelprice\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert comboArea != null : "fx:id=\"comboArea\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert dos != null : "fx:id=\"dos\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert txtAreaHawker != null : "fx:id=\"txtAreaHawker\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'CustomerView.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'CustomerView.fxml'.";

    }

}
