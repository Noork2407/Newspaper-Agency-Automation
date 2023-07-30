package admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;

public class adminViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField txtAdminPass;

    @FXML
    void doAdminLogin(ActionEvent event) {
    	
    	
    	String Pass = "123";
    	System.out.println(txtAdminPass.getText());
    	System.out.println(Pass);
    	if (txtAdminPass.getText().equals(Pass))
    	{
    		try{
        		Parent root=FXMLLoader.load(getClass().getResource("/adminpanel/adminPanelView.fxml")); 
    			Scene scene = new Scene(root);
    			Stage stage=new Stage();
    			stage.setScene(scene);
    			stage.show();
        		
        		
    			//to hide the opened window
    			 
    			/*Scene scene1=(Scene)lblResp.getScene();
    			   scene1.getWindow().hide();
    			 */

    		}
    		catch(Exception e)
    		{
    			e.printStackTrace();
    		}
    	}
    	else
    	{
    		
    				Alert alert = new Alert(AlertType.WARNING);
    				
        			alert.setTitle("Information Dialog");
        			alert.setContentText("Invalid Password!!!!");

        			alert.showAndWait();
    	}

    }

    @FXML
    void initialize() {
        assert txtAdminPass != null : "fx:id=\"txtAdminPass\" was not injected: check your FXML file 'adminView.fxml'.";

    }

}
