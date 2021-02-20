package frc.robot.command;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.MotorSubsystem;

public class ServoManualPositionCommand extends CommandBase {
    private MotorSubsystem motorSubsystem;
    private Joystick joystick;
    private DigitalInput limitSwitch;

    public ServoManualPositionCommand(MotorSubsystem motorSubsystem, Joystick joystick, DigitalInput limitSwitch) {
        this.motorSubsystem = motorSubsystem;
        this.joystick = joystick;
        this.limitSwitch = limitSwitch;
        addRequirements(motorSubsystem);
    }

    @Override
    public void execute() {
        double stickPos = joystick.getRawAxis(1);
        double normalized = (stickPos + 1)/2;
        motorSubsystem.setServo(limitSwitch.get() ? 1 - normalized : normalized);
    }
    
    @Override
    public void end(boolean interrupted) {
        motorSubsystem.setServo(0);
    }
}