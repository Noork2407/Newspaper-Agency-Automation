package billTable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class billTableViewController {
	
	Connection con;
    PreparedStatement pst; 
    ObservableList<BillBean> list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label lblres;


    @FXML
    private RadioButton chkPaid;

    @FXML
    private RadioButton chkPending;

    @FXML
    private ToggleGroup chkStatus;

    @FXML
    private TableView<BillBean> tbl;

    @FXML
    private TextField txtBill;

    @FXML
    private TextField txtMobile;
   
    @FXML
    void doExport(ActionEvent event) throws IOException {
    	
    	

        Writer writer = null;
        try {
        	File file = new File("C:\\Users\\DELL\\excel_ppr\\bills.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="mobile,datefrom,dateto,bill \n";
            writer.write(text);
            System.out.println("List size: " + list.size());

            for (BillBean p : list)
            {
 				text = p.getMobile()+ "," +p.getDatefrom()+ "," +p.getDateto()+ "," + p.getBill()+ "\n";
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

    @SuppressWarnings("unchecked")
	@FXML
    void doGetHistory(ActionEvent event) {
    	tbl.getColumns().clear();
    	
    	TableColumn<BillBean, String>mobile=new TableColumn<BillBean, String>("Customer Mobile");//any thing

    	mobile.setCellValueFactory(new PropertyValueFactory<BillBean,String>("mobile"));

    	mobile.setMinWidth(100);
    	
    	TableColumn<BillBean, String>datefrom=new TableColumn<BillBean, String>("Date From");//any thing

    	datefrom.setCellValueFactory(new PropertyValueFactory<BillBean,String>("datefrom"));

    	datefrom.setMinWidth(100);
    	
    	TableColumn<BillBean, String>dateto=new TableColumn<BillBean, String>("Date To");//any thing

    	dateto.setCellValueFactory(new PropertyValueFactory<BillBean,String>("dateto"));

    	dateto.setMinWidth(100);
    	
    	TableColumn<BillBean, String>bill=new TableColumn<BillBean, String>("Customer Bill");//any thing

    	bill.setCellValueFactory(new PropertyValueFactory<BillBean,String>("bill"));

    	bill.setMinWidth(100);
    	
    	
    	
    	/*tbl.getColumns().add(mobile);
    	tbl.setItems(fetchCustomerBills());
    	
    	tbl.getColumns().add(datefrom);
    	tbl.setItems(fetchCustomerBills());
    	
    	tbl.getColumns().add(dateto);
    	tbl.setItems(fetchCustomerBills());
    	
    	tbl.getColumns().add(bill);
    	tbl.setItems(fetchCustomerBills());
    	
    	*/
    	
    	    
    	    tbl.getColumns().addAll(mobile, datefrom, dateto, bill);
    	    list = fetchCustomerBills(); // Populate the list here
    	    System.out.println("List size: " + list.size());

    	    tbl.setItems(list);

    }
    
    ObservableList<BillBean> fetchCustomerBills()
    {
    	String mob = txtMobile.getText();
    	ObservableList<BillBean>ary = FXCollections.observableArrayList();
    	
    	float sum =0;
    	
   	   try {
  		
			   pst = con.prepareStatement("select * from bills where mobile=?");
			  pst.setString(1, mob);
	
			   ResultSet table = pst.executeQuery();
			
			
			  while(table.next())
			    {
				 
				  sum+= Float.valueOf(table.getString("bill"));
				     String mobile =	table.getString("mobile");
				     Date datef =	table.getDate("datefrom");
				     String datefrom = datef.toString();
				     Date datet =	table.getDate("dateto");
				     String dateto = datet.toString();
				     String bill = table.getString("bill");
   		      
				     ary.add(new BillBean(mobile, datefrom, dateto, bill));
				
  						
			      }
			
			
		     } catch (SQLException e) {
			// TODO Auto-generated catch block
			      e.printStackTrace();
		      }
   	   
   	            txtBill.setText(String.valueOf(sum));
 	            return ary;
    }
    

    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
	@FXML
    void doSearchStatus(ActionEvent event) {
    	
    	tbl.getColumns().clear();
    	TableColumn<BillBean, String>mobile=new TableColumn<BillBean, String>("Customer Mobile");//any thing

    	mobile.setCellValueFactory(new PropertyValueFactory<BillBean,String>("mobile"));

    	mobile.setMinWidth(100);
    	
    	TableColumn<BillBean, String>datefrom=new TableColumn<BillBean, String>("Date From");//any thing

    	datefrom.setCellValueFactory(new PropertyValueFactory<BillBean,String>("datefrom"));

    	datefrom.setMinWidth(100);
    	
    	TableColumn<BillBean, String>dateto=new TableColumn<BillBean, String>("Date To");//any thing

    	dateto.setCellValueFactory(new PropertyValueFactory<BillBean,String>("dateto"));

    	dateto.setMinWidth(100);
    	
    	TableColumn<BillBean, String>bill=new TableColumn<BillBean, String>("Customer Bill");//any thing

    	bill.setCellValueFactory(new PropertyValueFactory<BillBean,String>("bill"));

    	bill.setMinWidth(100);
    	
    	
    	
    	/*tbl.getColumns().add(mobile);
    	tbl.setItems(fetchAllBills());
    	
    	tbl.getColumns().add(datefrom);
    	tbl.setItems(fetchAllBills());
    	
    	tbl.getColumns().add(dateto);
    	tbl.setItems(fetchAllBills());
    	
    	tbl.getColumns().add(bill);
    	tbl.setItems(fetchAllBills());
    */	
    	tbl.getColumns().addAll(mobile, datefrom, dateto, bill);
	    list = fetchAllBills(); // Populate the list here
	    System.out.println("List size: " + list.size());

	    tbl.setItems(list);
    	

    }
    
    
    ObservableList<BillBean> fetchAllBills()
    {
    	ObservableList<BillBean>ary = FXCollections.observableArrayList();
    	
    	
    	if(chkPaid.isSelected())
    	{
    		float sum =0;
    		 try {
    	     		
    	  			pst = con.prepareStatement("select * from bills where billstatus=1");
    	  			
    	  	
    	  			ResultSet table = pst.executeQuery();
    	  			
    	  			
    	  			while(table.next())
    	  			{
    	  				 
    	  				 sum+= Float.valueOf(table.getString("bill"));
    	  				  String mobile =	table.getString("mobile");
    	  				  Date datef =	table.getDate("datefrom");
    	  				  String datefrom = datef.toString();
    	  				  Date datet =	table.getDate("dateto");
  	  				      String dateto = datet.toString();
    	  				  String bill = table.getString("bill");
    	      		      
    	  				  ary.add(new BillBean(mobile, datefrom, dateto, bill));
    	  				
    	     						
    	  			}
    	  			
    	  			
    	  		} catch (SQLException e) {
    	  			// TODO Auto-generated catch block
    	  			e.printStackTrace();
    	  		}
    		 
    		 
    		     txtBill.setText(String.valueOf(sum));
    	    	 return ary;
    	}
    	else if(chkPending.isSelected())
    	{
    		float sum =0;
    		try {
	     		
	  			pst = con.prepareStatement("select * from bills where billstatus=0");
	  			
	  	
	  			ResultSet table = pst.executeQuery();
	  			
	  			
	  			while(table.next())
	  			{
	  				 
	  				sum+= Float.valueOf(table.getString("bill"));
	  				  String mobile =	table.getString("mobile");
	  				  Date datef =	table.getDate("datefrom");
	  				  String datefrom = datef.toString();
	  				  Date datet =	table.getDate("dateto");
	  				      String dateto = datet.toString();
	  				  String bill = table.getString("bill");
	      		      
	  				 ary.add(new BillBean(mobile, datefrom, dateto, bill));
	  				
	     						
	  			}
	  			
	  			
	  		} catch (SQLException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
    		txtBill.setText(String.valueOf(sum));
	    	 return ary;
    	}
    	else
    		return ary;
    	
    	

    }

    @FXML
    void initialize() {
    	
    	con = billcollect.MySqlConnector.doConnect();
        assert chkPaid != null : "fx:id=\"chkPaid\" was not injected: check your FXML file 'billTableView.fxml'.";
        assert chkPending != null : "fx:id=\"chkPending\" was not injected: check your FXML file 'billTableView.fxml'.";
        assert chkStatus != null : "fx:id=\"chkStatus\" was not injected: check your FXML file 'billTableView.fxml'.";
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'billTableView.fxml'.";
        assert txtBill != null : "fx:id=\"txtBill\" was not injected: check your FXML file 'billTableView.fxml'.";
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'billTableView.fxml'.";

    }

}
