package com.hangman;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

public class DemoBox2D {
    public World world;
    public Body body;

    public DemoBox2D(){
        world = new World(new Vec2(5,-10));

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0,-10);
        Body groundBody= world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(50,10);
        groundBody.createFixture(groundBox,0);


        BodyDef bodyDef=new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(0, 4);
        bodyDef.angle=0;
        body = world.createBody(bodyDef);

        PolygonShape dynamicBox=new PolygonShape();
        dynamicBox.setAsBox(1, 1);

        FixtureDef fixtureDef= new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density = 1;
        fixtureDef.friction = 0.3f;

        body.createFixture(fixtureDef);
    }

    public static void main(String[] args){
        DemoBox2D demo=new DemoBox2D();

        float timeStep = 1.0f / 60.0f;
        int velocityIterations = 6;
        int positionIterations = 2;

        for (int i = 0; i < 60; ++i)
        {
            demo.world.step(timeStep, velocityIterations, positionIterations);
            Vec2 position = demo.body.getPosition();
            float angle = demo.body.getAngle();
            System.out.println("Postion : x="+position.x+", y="+position.y+", angle = "+angle);
        }
    }
}
