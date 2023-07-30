package table;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class hawkerTableViewController {
    Connection con;
    PreparedStatement pst; 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<HawkerBean> tbl;

    @FXML
    void doFetchDetails(ActionEvent event) {
    	
    	TableColumn<HawkerBean, String>name=new TableColumn<HawkerBean, String>("Hawker Name");//any thing

    	name.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("hname"));

    	name.setMinWidth(100);
    	
    	TableColumn<HawkerBean, String>mobile=new TableColumn<HawkerBean, String>("Mobile Number");//any thing

    	mobile.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("mobile"));

    	mobile.setMinWidth(100);
    	
    	TableColumn<HawkerBean, String>areas=new TableColumn<HawkerBean, String>("Allocated Areas");//any thing

    	areas.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("alloareas"));

    	areas.setMinWidth(200);
    	
    	TableColumn<HawkerBean, String>doj=new TableColumn<HawkerBean, String>("Date Of Join");//any thing

    	doj.setCellValueFactory(new PropertyValueFactory<HawkerBean,String>("doj"));

    	doj.setMinWidth(100);
    	
    	
    	
    	
    	
    	tbl.getColumns().add(name);
    	tbl.setItems(fetchAllHawkers());
    	
    	tbl.getColumns().add(mobile);
    	tbl.setItems(fetchAllHawkers());
    	
    	tbl.getColumns().add(areas);
    	tbl.setItems(fetchAllHawkers());
    	
    	tbl.getColumns().add(doj);
    	tbl.setItems(fetchAllHawkers());
    	
    }
    
    ObservableList<HawkerBean> fetchAllHawkers()
    {
    	ObservableList<HawkerBean>ary = FXCollections.observableArrayList();
    	 try {
     		
  			pst = con.prepareStatement("select * from hawkers ");
  			
  			
  			
  			ResultSet table = pst.executeQuery();
  			
  			
  			while(table.next())
  			{
  				 
  				  String hname =	table.getString("hname");
  				  String mobile =	table.getString("mobile");
  				  String areas =	table.getString("alloareas");
  				  String doj =	String.valueOf(table.getDate("doj").toLocalDate());
  				 
      		      ary.add(new HawkerBean(hname, mobile, areas, doj));
     						
  			}
  			
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
    	 return ary;

    }

    @FXML
    void initialize() {
    	
    	 con = billcollect.MySqlConnector.doConnect();
    	
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'hawkerTableView.fxml'.";

    }

}

