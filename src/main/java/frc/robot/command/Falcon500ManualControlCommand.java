package frc.robot.command;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.MotorSubsystem;

public class Falcon500ManualControlCommand extends CommandBase {
    private MotorSubsystem motorSubsystem;
    private Joystick joystick;

    public Falcon500ManualControlCommand(MotorSubsystem motorSubsystem, Joystick joystick) {
        this.motorSubsystem = motorSubsystem;
        this.joystick = joystick;
        addRequirements(motorSubsystem); 
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double axis = joystick.getRawAxis(1);
        motorSubsystem.setFalcon(axis*.5);
    }
} 