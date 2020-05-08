package project;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import project.gui.GrahamVisualizer;
import project.mylist.MyList;
import project.point.Point;
import project.point.PointUtilities;
import project.tools.FileGrabber;
import project.tools.GrahamScan;
import javafx.application.Application;
import javafx.scene.*;

/**
 * Main class showing the usage of my graham scan implementation
 * 
 * @author Jan de Boer
 *
 */
public class MainClass extends Application
{
	/**
	 * Main function
	 * @param args
	 */
    static MyList<Point> myPoints;

    public static void main(final String[] args) {
        final String fileDataString = FileGrabber.loadFileWithReader();
        myPoints = new MyList<Point>();
        if (fileDataString != null) {
            myPoints.addObjectsToTail(PointUtilities.getPointArrayFromString(fileDataString));
        }
        //System.out.println(myPoints);
        //System.out.println("------");
        //System.out.println("Convex Case:");
        final MyList<Point> convexCase = GrahamScan.getConvexCase(myPoints);
        System.out.println(convexCase);
        launch();
    }

    /**
     * Application Window showing the points and the case.
     * Not working.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World!");
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/

        StackPane root = new StackPane();
        Scene grahamScene = new Scene(root, 800, 600);
        root.getChildren().addAll(GrahamVisualizer.visualizeGrahamScan(myPoints, 800, 600));
        stage.setScene(grahamScene);
        stage.show();
    }
}