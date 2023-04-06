import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

public class PhysicalEngine {
    public World world;
    public Body body;

    public PhysicalEngine(){
        world = new World(new Vec2(0,10));

        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0,502);
        Body groundBody= world.createBody(groundBodyDef);
        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(512,10);
        groundBody.createFixture(groundBox,0);

        BodyDef bodyDef=new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(100, 100);
        bodyDef.angle=0;
        body = world.createBody(bodyDef);

        PolygonShape dynamicBox=new PolygonShape();
        dynamicBox.setAsBox(40, 40);

        FixtureDef fixtureDef= new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density = 1;
        fixtureDef.friction = 0.3f;

        body.createFixture(fixtureDef);
    }

    public double getUFOXPosition(){
        return body.getPosition().x;
    }
    public double getUFOYPosition(){
        return body.getPosition().y;
    }

    public void update(long time){
        float timeStep = 1.0f / 60.0f;
        int velocityIterations = 6;
        int positionIterations = 2;
        world.step(timeStep, velocityIterations, positionIterations);
        }

    public void impulseOnUFO(){
        Vec2 impulse = new Vec2(0,-10000);
        Vec2 center = new Vec2(20,20);
        body.applyLinearImpulse(impulse,center);
    }

}
