import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GUI extends Application{
        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("FAME Rulez");

            Group root = new Group();
            Scene scene = new Scene(root, 512, 612,true);
            primaryStage.setScene(scene);
            primaryStage.show();

            Pane pane = new Pane();
            Image background = new Image("file:./img/space.png");
            ImageView backgroundView=new ImageView(background);
            backgroundView.setViewport(new Rectangle2D(0,0,512,512));
            root.getChildren().add(pane);
            pane.getChildren().add(backgroundView);
            Image sun = new Image("file:./img/sun.png");
            ImageView sunView=new ImageView(sun);
            pane.getChildren().add(sunView);
            sunView.setX((background.getWidth()-sun.getWidth())/2);
            sunView.setY((background.getHeight()-sun.getHeight())/2);
            sunView.setScaleX(1.2);
            sunView.setScaleY(1.2);

            Image earth = new Image("file:./img/earth.png");
            ImageView earthView=new ImageView(earth);
            pane.getChildren().add(earthView);
            earthView.setX(400);
            earthView.setY((background.getHeight()-earth.getHeight())/2);



        }

        public static void main(String[] args) {
            launch(args);
        }
}
