package com.gustavhaavik.game.components;

import com.gustavhaavik.game.KeyHandler;

public class PlayerController extends Component {
    @Override
    public void update() {
        System.out.println("PlayerController update");
    }

    @Override
    public void render() {
        System.out.println("PlayerController render");
    }

    @Override
    public void destroy() {
        System.out.println("PlayerController destroy");
    }
}
