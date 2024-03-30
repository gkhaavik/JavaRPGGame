package com.gustavhaavik.game.components.animator;

import com.gustavhaavik.game.components.Component;

public class Animator extends Component {
    private final Animation[] animations;
    private int currentAnimation;

    public Animator(Animation... animations) {
        this.animations = animations;
        currentAnimation = 0;
    }

    public void update() {
        animations[currentAnimation].update();
    }

    @Override
    public void render() {

    }

    @Override
    public void destroy() {
    }

    public void setAnimation(int index) {
        currentAnimation = index;
    }

    public Animation getCurrentAnimation() {
        return animations[currentAnimation];
    }

    public Animation getAnimation(int index) {
        return animations[index];
    }

    public int getAnimationCount() {
        return animations.length;
    }
}
