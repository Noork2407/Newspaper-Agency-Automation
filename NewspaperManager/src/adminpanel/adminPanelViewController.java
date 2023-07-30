package adminpanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class adminPanelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void doOpenAreaMaster(ActionEvent event) {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/areamaster/areaView.fxml")); 
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

    @FXML
    void doOpenBillCollector(ActionEvent event) {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billcollect/BillCollectorView.fxml")); 
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

    @FXML
    void doOpenBillGenerators(ActionEvent event) {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billgen/BillGenView.fxml")); 
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

    @FXML
    void doOpenBillTables(ActionEvent event) {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/billTable/billTableView.fxml")); 
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

    @FXML
    void doOpenCustomerManager(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customers/CustomerView.fxml")); 
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

    @FXML
    void doOpenCustomerTable(ActionEvent event) {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/customerdash/CustomerDashView.fxml")); 
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

    @FXML
    void doOpenDevloperInfo(ActionEvent event) {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/aboutus/aboutUsView.fxml")); 
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

    @FXML
    void doOpenHawkerManager(ActionEvent event) {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/hawkermanager/HawkerManagerView.fxml")); 
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

    @FXML
    void doOpenHawkerTable(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/table/hawkerTableView.fxml")); 
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

    @FXML
    void doOpenPaperMaster(ActionEvent event) {
    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/papermaster/PaperMasterView.fxml")); 
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

    @FXML
    void initialize() {

    }

}
