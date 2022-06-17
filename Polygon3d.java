package application;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
//create an object of cube:
class Object3d{
		Box box = new Box(150, 150, 150);	 
//create an class of point
}

class Point3d extends Polygon3d{
	int tx = WIDTH/2;
	int ty = HEIGHT/2;
	 Rotate xRotate;
	 Rotate yRotate;
}
//main class extends application
public class Polygon3d extends Application {
//variables
  public static final int WIDTH = 800;
  public static final int HEIGHT = 600;

  public double anchorX, anchorY;
  public double anchorAngleX = 0;
  public double anchorAngleY = 0;
  public final DoubleProperty angleX = new SimpleDoubleProperty(0);
  public final DoubleProperty angleY = new SimpleDoubleProperty(0);
//method override
  @Override
  public void start(Stage primaryStage) {
	Object3d object =  new Object3d();  //creating an object of cube
    Group group = new Group();//scene->group
    group.getChildren().add(object.box);//adding that object in group
    Scene scene = new Scene(group, WIDTH, HEIGHT);//creating a scene
    scene.setFill(Color.ORANGE);//setting color
    Point3d point = new Point3d();//creating an object 
    group.translateXProperty().set(point.tx);//translating that group at center
    group.translateYProperty().set(point.ty);//translating that group at center
    
   //rotation properties    
    group.getTransforms().addAll(
        point.xRotate = new Rotate(0, Rotate.X_AXIS),
        point.yRotate = new Rotate(0, Rotate.Y_AXIS)
    );
    point.xRotate.angleProperty().bind(angleX);
    point.yRotate.angleProperty().bind(angleY);
    
    scene.setOnMousePressed(event -> {
	      anchorX = event.getSceneX();
	      anchorY = event.getSceneY();
	      anchorAngleX = angleX.get();
	      anchorAngleY = angleY.get();
	    });

	    scene.setOnMouseDragged(event -> {
	      angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
	      angleY.set(anchorAngleY + anchorX - event.getSceneX());
	    });
          
    //stage setting title and adding scene and displaying program
    primaryStage.setTitle("Cube Rotation");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}