module NewspaperManager {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	
	
	opens application to javafx.graphics, javafx.fxml;
	opens papermaster to javafx.graphics, javafx.fxml;
	opens hawkermanager to javafx.graphics, javafx.fxml;
	opens customers to javafx.graphics, javafx.fxml;
	opens billgen to javafx.graphics, javafx.fxml;
	opens billcollect to javafx.graphics, javafx.fxml;
	opens areamaster to javafx.graphics, javafx.fxml;
	opens table to javafx.graphics, javafx.fxml, javafx.base;
	opens customerdash to javafx.graphics, javafx.fxml, javafx.base;
	opens billTable to javafx.graphics, javafx.fxml, javafx.base;
	opens aboutus to javafx.graphics, javafx.fxml;
	opens admin to javafx.graphics, javafx.fxml;
	opens adminpanel to javafx.graphics, javafx.fxml;
	
}
