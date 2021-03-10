package frc.robot.command;

import com.fasterxml.jackson.databind.ser.std.NumberSerializers.IntLikeSerializer;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.MotorSubsystem;

public class FalconRotationCommand extends CommandBase{
    private Joystick joystick;

    public FalconRotationCommand(MotorSubsystem motorSubsystem, Joystick joystick) {
        this.joystick = joystick;
    }

    @Override
    public void initialize() {
        
    }
}
