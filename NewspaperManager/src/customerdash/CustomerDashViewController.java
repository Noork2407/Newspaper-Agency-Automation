package customerdash;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerDashViewController {
	
	Connection con;
    PreparedStatement pst; 

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lblres;
    
	ObservableList<CustomerBean>list = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> comboArea;

    @FXML
    private ComboBox<String> comboPaper;
    
    @FXML
    private TableView<CustomerBean> tbl;

    @FXML
    void doExport(ActionEvent event) throws IOException {
    	

        Writer writer = null;
        try {
        	File file = new File("C:\\Users\\DELL\\excel_ppr\\customers.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text = "name,mobile,hawker,address,email\n";// column bnjay
    		writer.write(text);
            System.out.println("List size: " + list.size());
            for(CustomerBean c : list)
    		{
            	text = c.getName()+","+c.getMobile()+","+c.getHawker()+","+c.getAddress()+","+c.getEmail()+"\n";
            	System.out.println(text);
                writer.write(text);
    		}
            lblres.setText("Exported Successfully");
           System.out.println("Exported Successfully");
            
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
        finally {
     	   
           writer.flush();
           writer.close();
           
        }


    }
    
   
    
    
    
    //=================================================================

    @SuppressWarnings("unchecked")
	@FXML
    void doGetAreaCus(ActionEvent event) {
    	
    	
    	tbl.getColumns().clear();
    	
    	TableColumn<CustomerBean, String>name=new TableColumn<CustomerBean, String>("Customer Name");//any thing

    	name.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("name"));

    	name.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String>mobile=new TableColumn<CustomerBean, String>("Mobile");//any thing

    	mobile.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("mobile"));

    	mobile.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String>hawker=new TableColumn<CustomerBean, String>("Hawker Name");//any thing

    	hawker.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("hawker"));

    	hawker.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String>address=new TableColumn<CustomerBean, String>("Address");//any thing

    	address.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("address"));

    	address.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String>email=new TableColumn<CustomerBean, String>("Email");//any thing

    	email.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("email"));

    	email.setMinWidth(100);
    	
    	
    	
    	
    	tbl.getColumns().addAll(name, mobile,hawker,address,email);
	    list = fetchAllAreaCustomers(); // Populate the list here
	    System.out.println("List size: " + list.size());

	    tbl.setItems(list);
    	

    }
    
    ObservableList<CustomerBean> fetchAllAreaCustomers()
    {
    	ObservableList<CustomerBean>ary = FXCollections.observableArrayList();

    	
    	String area = comboArea.getSelectionModel().getSelectedItem();
    	 try {
     		
  			pst = con.prepareStatement("select * from customers where area=?");
  			pst.setString(1, area);
  	
  			ResultSet table = pst.executeQuery();
  			
  			
  			while(table.next())
  			{
  				 
  				  String name =	table.getString("name");
  				  String mobile =	table.getString("mobile");
  				  String hawker =	table.getString("hawker");
  				  String address =	table.getString("address");
  				String email =	table.getString("email");
      		      
  				ary.add(new CustomerBean(name, mobile, hawker, address, email));
     						
  			}
  			
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
    	 return ary;

    }
    
    
    
    
    
    //==========================================================================
    
    
    

    @SuppressWarnings("unchecked")
	@FXML
    void doGetPprCus(ActionEvent event) {
    	
    	tbl.getColumns().clear();
    	
    	TableColumn<CustomerBean, String>name=new TableColumn<CustomerBean, String>("Customer Name");//any thing

    	name.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("name"));

    	name.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String>mobile=new TableColumn<CustomerBean, String>("Mobile");//any thing

    	mobile.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("mobile"));

    	mobile.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String>hawker=new TableColumn<CustomerBean, String>("Hawker Name");//any thing

    	hawker.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("hawker"));

    	hawker.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String>address=new TableColumn<CustomerBean, String>("Address");//any thing

    	address.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("address"));

    	address.setMinWidth(100);
    	
    	TableColumn<CustomerBean, String>email=new TableColumn<CustomerBean, String>("Email");//any thing

    	email.setCellValueFactory(new PropertyValueFactory<CustomerBean,String>("email"));

    	email.setMinWidth(100);
    	
    
    	
    	tbl.getColumns().addAll(name, mobile,hawker,address,email);
	    list = fetchAllPaperCustomers(); // Populate the list here
	    System.out.println("List size: " + list.size());

	    tbl.setItems(list);
    	

    }
    
    ObservableList<CustomerBean> fetchAllPaperCustomers()
    {
    	ObservableList<CustomerBean>ary = FXCollections.observableArrayList();
    	
    	String ppr = comboPaper.getSelectionModel().getSelectedItem();
    	 try {
     		
  			pst = con.prepareStatement("select * from customers WHERE spapers LIKE ?;");
  			pst.setString(1, "%"+ppr+"%");
  	
  			ResultSet table = pst.executeQuery();
  			
  			
  			while(table.next())
  			{
  				 
  				  String name =	table.getString("name");
  				  String mobile =	table.getString("mobile");
  				  String hawker =	table.getString("hawker");
  				  String address =	table.getString("address");
  				String email =	table.getString("email");
      		      
  				ary.add(new CustomerBean(name, mobile, hawker, address, email));
     						
  			}
  			
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
    	 return ary;

    }

    
    
    //===========================================================================================
    
    
    
    @FXML
    void initialize() {
    	
    	 con = billcollect.MySqlConnector.doConnect();
    	loadAreaFromDatabase();
    	loadPaperFromDatabase();
    	
        assert comboArea != null : "fx:id=\"comboArea\" was not injected: check your FXML file 'CustomerDashView.fxml'.";
        assert comboPaper != null : "fx:id=\"comboPaper\" was not injected: check your FXML file 'CustomerDashView.fxml'.";

    }
    
    private void loadAreaFromDatabase() {
        try {
            String query = "SELECT area FROM areas"; 
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            ObservableList<String> items = FXCollections.observableArrayList();
            while (rs.next()) {
                String areaName = rs.getString("area");
                items.add(areaName);
            }

            comboArea.setItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private void loadPaperFromDatabase() {
        try {
            String query = "SELECT paper FROM papers"; 
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            ObservableList<String> items = FXCollections.observableArrayList();
            while (rs.next()) {
                String areaName = rs.getString("paper");
                items.add(areaName);
            }

            comboPaper.setItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
