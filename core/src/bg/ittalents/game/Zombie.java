package bg.ittalents.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

/**
 * Created by Ob1 on 6/28/2015.
 */
public class Zombie extends Actor {
    private Animation animation;
    private TextureRegion currentRegion;
    private int tapCounter;
    private float timeLiving;
    private TextureAtlas zombieAtlas;
    private Zombie currentZombie;
    private boolean isDead;
    private float stateTime;
    private int zombieLevel;
    private final int zombieShootCounter;
    private float paragonLevel;
    private Texture[] enemiesArray;
    private int initialZombieLevel;

    public Zombie(int zombieLevel, float paragonLevel) {
        enemiesArray = new Texture[3];
        enemiesArray[0] = Assets.enemySingleImage;
        enemiesArray[1] = Assets.enemySingleImageLevel2;
        enemiesArray[2] = Assets.enemySingleImageLevel3;

        if (zombieLevel == 3){
            this.currentRegion = new TextureRegion(enemiesArray[2]); //red enemy
        }
        else if (zombieLevel == 2) {
            this.currentRegion = new TextureRegion(enemiesArray[1]); // green enemy
        }
        else{
            this.currentRegion = new TextureRegion(enemiesArray[0]); //white enemy
        }
        this.zombieShootCounter = zombieLevel;
        this.tapCounter = 0;
        this.initialZombieLevel = zombieLevel;
        this.zombieLevel = zombieLevel;
        this.paragonLevel = paragonLevel;
        this.timeLiving = paragonLevel;
        this.zombieAtlas = new TextureAtlas(Gdx.files.internal("DyingZombie/dyingZombie.atlas"));
        this.animation = new Animation(1 / 16f, zombieAtlas.getRegions());
        this.currentZombie = this;

        this.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("PUCAM ZOMBITO");
                dyingZombieSound();
                tapCounter += User.getSingletonUser().getWeapon();
//                currentRegion = new TextureRegion(enemiesArray[zombieLevel - 1]);
                if (tapCounter >= currentZombie.zombieShootCounter) {
                    currentZombie.isDead = true;
                    return true;
                }
                else {
                    currentRegion = new TextureRegion(enemiesArray[((currentZombie.zombieLevel - 1) - 1)]);
                    currentZombie.zombieLevel -= 1;
                    return true;
                }
            }
        });
    }

    @Override
    public void act(float delta) {
        if (isDead) {
            this.zombieLevel = initialZombieLevel;
            stateTime += delta;
            this.currentRegion = this.animation.getKeyFrame(stateTime);
            if (stateTime >= 1 / 2f) {
                this.currentRegion = new TextureRegion(enemiesArray[zombieLevel - 1]);
                this.stateTime = 0;
                this.tapCounter = 0;
                this.timeLiving = paragonLevel;
                currentZombie.setVisible(false);
                currentZombie.isDead = false;
                GameScreen.points += 10 * zombieLevel;
            }
        }
    }

    public void timeLiving(float deltaTime) {
        if (this.isVisible()) {
            timeLiving -= deltaTime;
        }
    }

    public void checkTimeLiving() {
        if ((timeLiving <= 0) && (this.isVisible())) {
            currentZombie.setVisible(false);
            Assets.zombieBite.play();
            this.zombieLevel = initialZombieLevel;
            this.currentRegion = new TextureRegion(enemiesArray[zombieLevel - 1]);
            GameScreen.lives -= 1;
            this.timeLiving = paragonLevel;
            tapCounter = 0;
            dyingZombieBackground();
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(currentRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),
                getScaleX(), getScaleY(), getRotation());
    }

    private void dyingZombieSound() {
        Random rand = new Random();
        int dyingSound = rand.nextInt(2);
        if (dyingSound == 0) {
            Assets.dyingZombie2.play();
        } else {
            Assets.dyingZombie1.play();
        }
    }

    private void dyingZombieBackground() {
        GameScreen.mainStage.addAction(Actions.sequence(Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        GameScreen.backGroundSprite.setColor(Color.RED);
                    }
                }),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        GameScreen.backGroundSprite.setTexture(Assets.scaryZombieImage);
                    }
                }),
                Actions.delay(0.2f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        GameScreen.backGroundSprite.setTexture(Assets.policeBuildingBackground);
                    }
                }),
                Actions.delay(2f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        GameScreen.backGroundSprite.setColor(Color.WHITE);
                    }
                })));
    }
//     This hit() instead of checking against a bounding box, checks a bounding circle.
//    @Override
//    public Actor hit(float x, float y, boolean touchable) {
//        // If this Actor is hidden or untouchable, it cant be hit
//        if (!this.isVisible() || this.getTouchable() == Touchable.disabled)
//            return null;
//
//        // Get centerpoint of bounding circle, also known as the center of the rect
//        float centerX = getWidth() / 2;
//        float centerY = getHeight() / 2;
//
//        // Square roots are bad m'kay. In "real" code, simply square both sides for much speedy fastness
//        // This however is the proper, unoptimized and easiest to grok equation for a hit within a circle
//        // You could of course use LibGDX's Circle class instead.
//
//        // Calculate radius of circle
//        float radius = (float) Math.sqrt(centerX * centerX +
//                centerY * centerY);
//
//        // And distance of point from the center of the circle
//        float distance = (float) Math.sqrt(((centerX - x) * (centerX - x))
//                + ((centerY - y) * (centerY - y)));
//
//        // If the distance is less than the circle radius, it's a hit
//        if (distance <= radius) return this;
//
//        // Otherwise, it isnt
//        return null;
//    }
}