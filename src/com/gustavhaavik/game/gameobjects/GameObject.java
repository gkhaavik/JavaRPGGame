package com.gustavhaavik.game.gameobjects;

import com.gustavhaavik.game.components.Component;

import java.util.ArrayList;

public class GameObject {
    private final ArrayList<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void update() {
        for (Component component : components) {
            component.update();
        }
    }

    public void render() {
        for (Component component : components) {
            component.render();
        }
    }

    public void destroy() {
        for (Component component : components) {
            component.destroy();
        }
    }

    public <T extends Component> T getComponent(Class<T> type) {
        for (Component component : components) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }
        return null;
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public void removeComponent(Class<? extends Component> type) {
        components.removeIf(component -> type.isInstance(component));
    }

    public void removeAllComponents() {
        components.clear();
    }


    public void updateComponents(Class<? extends Component> type) {
        for (Component component : components) {
            if (type.isInstance(component)) {
                component.update();
            }
        }
    }

    public void renderComponents(Class<? extends Component> type) {
        for (Component component : components) {
            if (type.isInstance(component)) {
                component.render();
            }
        }
    }

    public void destroyComponents(Class<? extends Component> type) {
        for (Component component : components) {
            if (type.isInstance(component)) {
                component.destroy();
            }
        }
    }
}
