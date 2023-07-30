package areamaster;

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

public class areaViewController {
	
	Connection con;
    PreparedStatement pst; 

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lblres;

    @FXML
    private ComboBox<String> comboAreas;

    @FXML
    void doAddArea(ActionEvent event) {
    	
    	
    	String area = comboAreas.getSelectionModel().getSelectedItem();
    	
    	try {
			pst = con.prepareStatement("insert into areas values(?)");
			pst.setString(1, area);
			
			pst.executeUpdate();
			lblres.setText("Record Saved........");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doDeleteArea(ActionEvent event) {
    	
       String area = comboAreas.getSelectionModel().getSelectedItem();
    	
    	try {
			pst = con.prepareStatement("delete from areas where area=?");
			pst.setString(1, area);
			
			pst.executeUpdate();
			lblres.setText("Record Deleted........");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {

   	 con = billcollect.MySqlConnector.doConnect();
   	
    	
        assert comboAreas != null : "fx:id=\"comboAreas\" was not injected: check your FXML file 'areaView.fxml'.";
        loadFromDatabase();

    }
    
    private void loadFromDatabase() {
        try {
            String query = "SELECT area FROM areas"; 
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            ObservableList<String> items = FXCollections.observableArrayList();
            while (rs.next()) {
                String areaName = rs.getString("area");
                items.add(areaName);
            }

            comboAreas.setItems(items);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
