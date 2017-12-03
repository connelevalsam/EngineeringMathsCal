package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    AnchorPane anchorFile, anchorParams, anchorImage, anchorHelp;
    @FXML
    Pane paneNotation, paneAbout;
    @FXML
    ScrollPane scrollPane;
    @FXML
    TilePane tileView;
    @FXML ImageView imageView;
    Stage window1Stage;
    final String default_folder = "Pictures/cb";
    String filename = " ";
    final String path = System.getProperty("user.dir");
    final String currentDirectory = path + File.separator + default_folder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("System Initialized...");
        anchorFile.setVisible(false);
        anchorParams.setVisible(false);
        anchorImage.setVisible(false);
        anchorHelp.setVisible(false);
        paneAbout.setVisible(false);
        paneNotation.setVisible(false);

        //diagrams
        window1Stage = Main.homeStage;
        File folder = new File(currentDirectory);
        File[] listOfFiles = folder.listFiles();

        for (final File file : listOfFiles) {
            imageView = createImageView(file);
            tileView.getChildren().addAll(imageView);
        }

    }


    private ImageView createImageView(final File imageFile) {
        // DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
        // The last two arguments are: preserveRatio, and use smooth (slower)
        // resizing

        ImageView imageView = null;
        try {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,
                    true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {

                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){

                        if(mouseEvent.getClickCount() == 2){
                            try {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                imageView.setStyle("-fx-background-color: BLACK");
                                imageView.setFitHeight(window1Stage.getHeight() - 10);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                borderPane.setStyle("-fx-background-color: BLACK");
                                Stage newStage = new Stage();
                                newStage.setWidth(window1Stage.getWidth());
                                newStage.setHeight(window1Stage.getHeight());
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane, Color.BLACK);
                                newStage.setScene(scene);
                                newStage.show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            });
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return imageView;
    }

    /*===============================================================================================*/
    /*===========================           Help section            =================================*/
    /*===============================================================================================*/
    public void aboutMenu(ActionEvent actionEvent) {
        anchorHelp.setVisible(true);
        paneNotation.setVisible(false);
        paneAbout.setVisible(true);
    }

    public void tipsMenu(ActionEvent actionEvent) {
        anchorHelp.setVisible(true);
        paneAbout.setVisible(false);
        paneNotation.setVisible(true);
    }
}
