package org.opensourcearcade.jinvaders;

import java.awt.event.KeyEvent;

public class Keyboard {
    private boolean leftKey;
    private boolean rightKey;
    private boolean spaceKey;
    private boolean escKey;
    private boolean enterKey;
    private boolean backKey;
    private boolean spaceKeyReleased;

    public boolean isHelpKey() {
        return helpKey;
    }

    public void setHelpKey(boolean helpKey) {
        this.helpKey = helpKey;
    }

    private boolean helpKey;
    private int lastKey;

    public Keyboard() {
        this.spaceKeyReleased = true;
    }

    public int getLastKey() {
        return lastKey;
    }

    public void setLastKey(int lastKey) {
        this.lastKey = lastKey;
    }

    public boolean isLeftKey() {
        return leftKey;
    }

    public void setLeftKey(boolean leftKey) {
        this.leftKey = leftKey;
    }

    public boolean isRightKey() {
        return rightKey;
    }

    public void setRightKey(boolean rightKey) {
        this.rightKey = rightKey;
    }

    public boolean isSpaceKey() {
        return spaceKey;
    }

    public void setSpaceKey(boolean spaceKey) {
        this.spaceKey = spaceKey;
    }

    public boolean isEscKey() {
        return escKey;
    }

    public void setEscKey(boolean escKey) {
        this.escKey = escKey;
    }

    public boolean isEnterKey() {
        return enterKey;
    }

    public void setEnterKey(boolean enterKey) {
        this.enterKey = enterKey;
    }

    public boolean isBackKey() {
        return backKey;
    }

    public void setBackKey(boolean backKey) {
        this.backKey = backKey;
    }


    public void keyEvent(KeyEvent event, boolean pressed) {
        int keyCode = event.getKeyCode();
        joyEvent(keyCode, pressed);
    }

    public void joyEvent(int keyCode, boolean pressed) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                this.leftKey = pressed;
                break;
            case KeyEvent.VK_RIGHT:
                this.rightKey = pressed;
                break;
            case KeyEvent.VK_SPACE:
                this.spaceKey = pressed;
                if (!pressed)
                    this.spaceKeyReleased = true;
                break;
            case KeyEvent.VK_ESCAPE:
                this.escKey = pressed;
                break;
            case KeyEvent.VK_ENTER:
                this.enterKey = pressed;
                break;
            case KeyEvent.VK_H:
                this.helpKey = pressed;
                break;
            case KeyEvent.VK_BACK_SPACE:
                this.backKey = pressed;
                break;
        }
    }

    public boolean isSpaceKeyReleased() {
        return spaceKeyReleased;
    }

    public void setSpaceKeyReleased(boolean spaceKeyReleased) {
        this.spaceKeyReleased = spaceKeyReleased;
    }
}
