import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class GUI extends Application{
        Rotate rx=new Rotate(1,256,256);
        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("FAME Rulez");

            Group root = new Group();
            Group gameRoot = new Group();
            Scene scene = new Scene(root, 512, 612,true);
            Scene nextScene = new Scene(gameRoot, 512,612,true);
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
            Pane sunPane = new Pane();
            pane.getChildren().add(sunPane);
            sunPane.getChildren().add(sunView);
            sunPane.setTranslateX((background.getWidth()-sun.getWidth())/2);
            sunPane.setTranslateY((background.getHeight()-sun.getHeight())/2);
            sunView.setScaleX(1.2);
            sunView.setScaleY(1.2);

            Image earth = new Image("file:./img/earth.png");
            ImageView earthView=new ImageView(earth);
            pane.getChildren().add(earthView);
            earthView.setX(400);
            earthView.setY((background.getHeight()-earth.getHeight())/2);

            Image[] ufo = new Image[6];

            for (int i=0;i<6;i++){
                ufo[i]=new Image("file:./img/ufo_"+i+".png");
            }

            ImageView ufoView = new ImageView(ufo[0]);
            pane.getChildren().add(ufoView);
            ufoView.setX(100);
            ufoView.setY(100);

            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long time) {
                    final double[] scaleValue={1,1.05,1.1,1.25,1.3,1.35,1.4,1.45,1.5,1.45,1.4,1.35,1.3,1.25,1.1,1.05};
                    int animationSunIndex=
                            (int)(((time/1000000)/75)% scaleValue.length);
                    sunView.setScaleX(scaleValue[animationSunIndex]);
                    sunView.setScaleY(scaleValue[animationSunIndex]);
                //    System.out.println(scaleValue[animationSunIndex]);
                    earthView.getTransforms().add(rx);
                    int animationUfoIndex = (int)(((time/1000000)/75)%ufo.length);
                    ufoView.setImage(ufo[animationUfoIndex]);
                    if(
                            ufoView.getLayoutBounds().intersects(
                                    earthView.getLayoutBounds())){
                        System.out.println("Boum !");
                    }
                }
            };

            timer.start();

            sunPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("I've been cliked on "
                            +mouseEvent.getSceneX()+","+mouseEvent.getSceneY());
                primaryStage.setScene(nextScene);
                }
            });

        }

        public static void main(String[] args) {
            launch(args);
        }
}
