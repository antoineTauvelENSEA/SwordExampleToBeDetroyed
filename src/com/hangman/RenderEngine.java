package com.hangman;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;

public class RenderEngine {
    Rotate rx=new Rotate(1,256,256);
    boolean destroyed=false;
    boolean collision=false;
    boolean old_collision=false;

    ImageView sunView;
    ImageView earthView;
    ImageView ufoView;
    Image[] ufo = new Image[6];
    Image[] explosion = new Image[8];

    PhysicalEngine physicalEngine;
    Group root;
    Pane sunPane = new Pane();

    public RenderEngine(PhysicalEngine physicalEngine, Group root){
        this.physicalEngine = physicalEngine;
        this.root=root;
        Pane spacePane = new Pane();
        Image background = new Image("file:./img/space.png");
        ImageView backgroundView=new ImageView(background);
        backgroundView.setViewport(new Rectangle2D(0,0,512,512));
        root.getChildren().add(spacePane);
        spacePane.getChildren().add(backgroundView);
        Image sun = new Image("file:./img/sun.png");
        sunView=new ImageView(sun);
        spacePane.getChildren().add(sunPane);
        sunPane.getChildren().add(sunView);
        sunPane.setTranslateX((background.getWidth()-sun.getWidth())/2);
        sunPane.setTranslateY((background.getHeight()-sun.getHeight())/2);

        Image earth = new Image("file:./img/earth.png");
        earthView=new ImageView(earth);
        spacePane.getChildren().add(earthView);
        earthView.setX(400);
        earthView.setY((background.getHeight()-earth.getHeight())/2);

        for (int i=0;i<6;i++){
            ufo[i]=new Image("file:./img/ufo_"+i+".png");
        }
        for (int i=0;i<8;i++){
            explosion[i]=new Image("file:./img/explosion_"+i+".png");
        }
        ufoView = new ImageView(ufo[0]);
        spacePane.getChildren().add(ufoView);

        Pane riddlePane = new Pane();
        root.getChildren().add(riddlePane);

        Label riddleLabel=new Label("Essai");
        riddlePane.getChildren().add(riddleLabel);
        riddlePane.setTranslateY(550);
        riddlePane.setTranslateX(100);
    }

    void render(long time){
        ufoView.setX(physicalEngine.getUFOXPosition());
        ufoView.setY(physicalEngine.getUFOYPosition());

        final double[] scaleValue={1,1.05,1.1,1.25,1.3,1.35,1.4,1.45,1.5,1.45,1.4,1.35,1.3,1.25,1.1,1.05};
        int animationSunIndex=
                (int)(((time/1000000)/75)% scaleValue.length);
        sunView.setScaleX(scaleValue[animationSunIndex]);
        sunView.setScaleY(scaleValue[animationSunIndex]);
        //    System.out.println(scaleValue[animationSunIndex]);
        earthView.getTransforms().add(rx);
        int animationUfoIndex = (int)(((time/1000000)/75)%ufo.length);
        int animationExplosionIndex = (int)(((time/1000000)/75)%explosion.length);
        ufoView.setImage(!destroyed? ufo[animationUfoIndex]:explosion[animationUfoIndex]);
        if(
                ufoView.getBoundsInParent().intersects(
                        earthView.getBoundsInParent())){
            collision=true;
            destroyed=destroyed^(collision&!(old_collision));

            System.out.println("Boum !");
        }
        else {
            collision=false;
        }
        old_collision=collision;
    }

    public Pane getSunPane() {
        return sunPane;
    }
}
