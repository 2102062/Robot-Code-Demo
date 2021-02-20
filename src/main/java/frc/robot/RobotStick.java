package frc.robot;

import org.w3c.dom.ranges.RangeException;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotStick extends Joystick {
    private final int MAX_BUTTONS = 12;
    private JoystickButton[] buttons;

    public RobotStick(int port) {
        super(port);
        buttons = new JoystickButton[MAX_BUTTONS];
        for(int i = 0; i < MAX_BUTTONS; i++) {
            buttons[i] = new JoystickButton(this, i);
        }
    }
    
    public JoystickButton getButton(int button) {
        if(button >= MAX_BUTTONS| button < 0) {
            throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Button not in range on joystick");
        }
        return buttons[button];
    }
}
