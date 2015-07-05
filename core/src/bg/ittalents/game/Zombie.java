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

import bg.ittalents.game.Resource.Assets;

/**
 * Created by Ob1 on 6/28/2015.
 */
public class Zombie extends Actor {
    public static final int POINTS_GIVING = 10;
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
    private Texture[] enemiesArrayWindow;
    private int initialZombieLevel;
    private boolean position;

    public Zombie(int zombieLevel, float paragonLevel, final boolean position) {
        this.position = position;
        enemiesArray = new Texture[3];
        enemiesArray[0] = Assets.enemySingleImage;
        enemiesArray[1] = Assets.enemySingleImageLevel2;
        enemiesArray[2] = Assets.enemySingleImageLevel3;

        enemiesArrayWindow = new Texture[3];
        enemiesArrayWindow[0] = Assets.enemySingleImageWindow;
        enemiesArrayWindow[1] = Assets.enemySingleImageLevel2Window;
        enemiesArrayWindow[2] = Assets.enemySingleImageLevel3Window;

        if (position) {
            if (zombieLevel == 3) {
                this.currentRegion = new TextureRegion(enemiesArray[2]); //red enemy
            } else if (zombieLevel == 2) {
                this.currentRegion = new TextureRegion(enemiesArray[1]); // green enemy
            } else {
                this.currentRegion = new TextureRegion(enemiesArray[0]); //white enemy
            }
        } else {
            if (zombieLevel == 3) {
                this.currentRegion = new TextureRegion(enemiesArrayWindow[2]); //red enemy
            } else if (zombieLevel == 2) {
                this.currentRegion = new TextureRegion(enemiesArrayWindow[1]); // green enemy
            } else {
                this.currentRegion = new TextureRegion(enemiesArrayWindow[0]); //white enemy
            }
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
                if (GameScreen.outOfAmmo == true) {
                    return true;
                } else {
                    dyingZombieSound();
                    tapCounter += User.getSingletonUser().getWeapon();
                    if (tapCounter >= currentZombie.zombieShootCounter) {
                        currentZombie.isDead = true;
                        return true;
                    } else {
                        if (position) {
                            currentRegion = new TextureRegion(enemiesArray[((currentZombie.zombieLevel - 1) - 1)]);
                            currentZombie.zombieLevel -= 1;
                            return true;
                        } else {
                            currentRegion = new TextureRegion(enemiesArrayWindow[((currentZombie.zombieLevel - 1) - 1)]);
                            currentZombie.zombieLevel -= 1;
                            return true;
                        }
                    }
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
                if (position) {
                    this.currentRegion = new TextureRegion(enemiesArray[zombieLevel - 1]);
                } else {
                    this.currentRegion = new TextureRegion(enemiesArrayWindow[zombieLevel - 1]);
                }
                this.stateTime = 0;
                this.tapCounter = 0;
                this.timeLiving = paragonLevel;
                currentZombie.setVisible(false);
                currentZombie.isDead = false;
                GameScreen.points += POINTS_GIVING * zombieLevel;
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
            if (position) {
                this.currentRegion = new TextureRegion(enemiesArray[zombieLevel - 1]);
            } else {
                this.currentRegion = new TextureRegion(enemiesArrayWindow[zombieLevel - 1]);
            }
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
                        GameScreen.scaryZombieBackgroundImage.setVisible(true);
                    }
                }),
                Actions.delay(0.17f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        GameScreen.scaryZombieBackgroundImage.setVisible(false);
                    }
                }),
                Actions.delay(1.8f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        GameScreen.backGroundSprite.setColor(Color.WHITE);
                    }
                })));
    }

}