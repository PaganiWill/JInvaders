package org.opensourcearcade.jinvaders.joystick;

import org.bbi.linuxjoy.LinuxJoystick;
import org.bbi.linuxjoy.LinuxJoystickEvent;
import org.bbi.linuxjoy.LinuxJoystickEventCallback;
import org.bbi.linuxjoy.XboxController;
import org.opensourcearcade.jinvaders.Keyboard;

import java.awt.event.KeyEvent;

public class EventCallbackHandler implements LinuxJoystickEventCallback {

	//https://github.com/wheerdam/linuxjoy/tree/master
	Keyboard keyboard;

	public EventCallbackHandler(Keyboard keyboard){
		this.keyboard = keyboard;
	}

	public void callback(LinuxJoystick j, LinuxJoystickEvent ev) {

        if (ev.isAxisChanged() == XboxController.D_X) {
            if (ev.getValue() > 0) {
                keyboard.joyEvent(KeyEvent.VK_RIGHT, true);
            } else if (ev.getValue() < 0) {
                keyboard.joyEvent(KeyEvent.VK_LEFT, true);
            } else {
                keyboard.joyEvent(KeyEvent.VK_LEFT, false);
                keyboard.joyEvent(KeyEvent.VK_RIGHT, false);
            }
        }

        if (ev.isButtonDown() == XboxController.A) {
            keyboard.joyEvent(KeyEvent.VK_SPACE, true);
        }if (ev.isButtonUp() == XboxController.A) {
			keyboard.joyEvent(KeyEvent.VK_SPACE, false);
		}

		if (ev.isButtonDown() == XboxController.START) {
			keyboard.joyEvent(KeyEvent.VK_ENTER, true);
		}if (ev.isButtonUp() == XboxController.START) {
			keyboard.joyEvent(KeyEvent.VK_ESCAPE, false);
		}
	}
}