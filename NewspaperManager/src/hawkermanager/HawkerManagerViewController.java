
package hawkermanager;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class HawkerManagerViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboAreas;

    @FXML
    private ComboBox<String> comboHawker;

    @FXML
    private Label picpath;
    
    @FXML
    private Label lblres;


    @FXML
    private ImageView ppic;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAdhaar;

    @FXML
    private TextField txtAllocatedAreas;

    @FXML
    private TextField txtMobile;
    Connection con;  
    PreparedStatement pst; 


    @FXML
    void doAppendAreas(ActionEvent event) {
    	 String selectedArea = comboAreas.getSelectionModel().getSelectedItem();

    	   
    	    String currentText = txtAllocatedAreas.getText();

    	
    	    String updatedText = currentText.isEmpty() ? selectedArea : currentText + ", " + selectedArea;

    	   
    	    txtAllocatedAreas.setText(updatedText);

    }

    @FXML
    void doDismiss(ActionEvent event) {
    	String Hawker = comboHawker.getSelectionModel().getSelectedItem();
    	
    	try {
			pst = con.prepareStatement("delete from hawkers where hname=?");
			pst.setString(1, Hawker);
			
			
			
			
			pst.executeUpdate();
			lblres.setText("Record Deleted........");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    @FXML
    void doEnroll(ActionEvent event) {
    	String Hawker = comboHawker.getSelectionModel().getSelectedItem();
    	String allocated = txtAllocatedAreas.getText();
    	String mobileno = txtMobile.getText();
    	String add = txtAddress.getText();
    	String adhaar = txtAdhaar.getText();
    	String picp = picpath.getText();
    	
    	try {
			pst = con.prepareStatement("insert into hawkers values(?,?,?,?,?,current_date(),?)");
			pst.setString(1, Hawker);
			pst.setString(2,mobileno);
			pst.setString(3,add);
			pst.setString(4,allocated);
			pst.setString(5,picp);
		
			pst.setString(6,adhaar);
			
			
			
			pst.executeUpdate();
			lblres.setText("Record Saved........");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    @FXML
    void doSearch(ActionEvent event) throws FileNotFoundException {
    	
    	 try {
    		 String name= comboHawker.getSelectionModel().getSelectedItem();
 			pst = con.prepareStatement("select * from hawkers where hname=?");
 			pst.setString(1, name);
 			
 			
 			ResultSet table = pst.executeQuery();
 			while(table.next())
 			{
 				 
 				  String mobile =	table.getString(2);
 				  String addres =	table.getString(3);
 				  String areas =	table.getString(4);
 				  String path =	table.getString(5);
 				  String adhaar = table.getString(7);
     			
     			txtMobile.setText(mobile);
    			txtAddress.setText(addres);
    			txtAllocatedAreas.setText(areas);
    			txtAdhaar.setText(adhaar);
    			picpath.setText(path);
    			
    			if(picpath.getText()!="Pic Path")
    			{
    				ppic.setImage(new Image(new FileInputStream(path)));
    			}
    	
    			
    						
 			}
 			
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}



    }

    @FXML
    void doUpdate(ActionEvent event) {
    	String Hawker = comboHawker.getSelectionModel().getSelectedItem();
    	String allocated = txtAllocatedAreas.getText();
    	String mobileno = txtMobile.getText();
    	String add = txtAddress.getText();
    	String adhaar = txtAdhaar.getText();
    	String picp = picpath.getText();
    	
    	try {
			pst = con.prepareStatement("update hawkers set mobile=?,address=?,alloareas=?,picpath=?,adhaar=? where hname=? ");
			
			pst.setString(1,mobileno);
			pst.setString(2,add);
			pst.setString(3,allocated);
			pst.setString(4,picp);
		     
			pst.setString(5,adhaar);
			pst.setString(6, Hawker);
			
			
			
			pst.executeUpdate();
			lblres.setText("Record Updated.......");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

    @FXML
    void doUpload(ActionEvent event) {
    	
    	FileChooser filechooser = new FileChooser();
    	filechooser.setTitle("Open Resource File");
    	filechooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif") );
    	File selectedfile = filechooser.showOpenDialog(null);
    	
    	if(selectedfile!=null)
    	{
    		picpath.setText(selectedfile.getPath());
    		Image img = new Image(selectedfile.toURI().toString());
    		
    		ppic.setImage(img);
    	}

    }

    @FXML
    void doView(ActionEvent event) {
    	
    	try {
			pst = con.prepareStatement("select * from hawkers ");
			
			
			ResultSet table = pst.executeQuery();
			while(table.next())
			{
			  String hawker =	table.getString(1);
			  String mobile =	table.getString(2);
			  String addres =	table.getString(3);
			  String areas =	table.getString(4);
			  String path =	table.getString(5);
			  java.sql.Date date =	table.getDate(6);
			  String adhaar = table.getString(7);
			  
			  System.out.println(hawker+"\t"+mobile+"\t"+addres+"\t"+areas+"\t"+path+"\t"+date+"\t"+adhaar);
			  
			  
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
      
    
//----------Filling up combohawker--
        
        try {
			pst = con.prepareStatement("SELECT hname FROM hawkers;");
			ResultSet table=pst.executeQuery();
			System.out.println(table);
			while(table.next())
			{
				String name = table.getString("hname");
				ArrayList<String> items1=new ArrayList<String>();
				items1.add(name);
				comboHawker.getItems().addAll(items1);
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
				comboAreas.getItems().addAll(items);
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        
         
    	
        assert comboAreas != null : "fx:id=\"comboAreas\" was not injected: check your FXML file 'HawkerManagerView.fxml'.";
        assert comboHawker != null : "fx:id=\"comboHawker\" was not injected: check your FXML file 'HawkerManagerView.fxml'.";
        assert picpath != null : "fx:id=\"picpath\" was not injected: check your FXML file 'HawkerManagerView.fxml'.";
        assert ppic != null : "fx:id=\"ppic\" was not injected: check your FXML file 'HawkerManagerView.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'HawkerManagerView.fxml'.";
        assert txtAdhaar != null : "fx:id=\"txtAdhaar\" was not injected: check your FXML file 'HawkerManagerView.fxml'.";
        assert txtAllocatedAreas != null : "fx:id=\"txtAllocatedAreas\" was not injected: check your FXML file 'HawkerManagerView.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'HawkerManagerView.fxml'.";

    }

}
/*
    	ArrayList<String> area=new ArrayList<String>(Arrays.asList("Select","New Cantt Road","Dogar Basti","Green Avenue","Balbir Basti","Giani Zail Singh Avenue","Main Bazar","Mohalla Mohikhana","Puri Colony"));
    	comboAreas.getItems().addAll(area);
    	ArrayList<String>hawker=new ArrayList<String>(Arrays.asList("Select","Ramu","Himmat Singh","Golu Pandit","Babu Rao","Giani","Sham","Motilal","Puneet"));
    	comboHawker.getItems().addAll(hawker);*/
