package com.hangman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class GUI extends Application{
    Group root = new Group();
    PhysicalEngine physicalEngine = new PhysicalEngine();
    RenderEngine renderEngine=new RenderEngine(physicalEngine,root);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FAME Rulez");
        Scene scene = new Scene(root, 512, 612,true);
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer renderAndPhysics = new AnimationTimer() {
                @Override
                public void handle(long time) {
                    physicalEngine.update(time);
                    renderEngine.render(time); }
            };

            renderAndPhysics.start();

        renderEngine.getSunPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("I've been cliked on " +mouseEvent.getSceneX()+","+mouseEvent.getSceneY());
                physicalEngine.impulseOnUFO();
                }
            });

        }

        public static void main(String[] args) {
            launch(args);
        }
}
