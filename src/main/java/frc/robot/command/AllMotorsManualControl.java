package frc.robot.command;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.MotorSubsystem;

public class AllMotorsManualControl extends CommandBase {
    private MotorSubsystem motorSubsystem;
    private Joystick joystick;

    public AllMotorsManualControl(MotorSubsystem motorSubsystem, Joystick joystick) {
        this.motorSubsystem = motorSubsystem;
        this.joystick = joystick;
        addRequirements(motorSubsystem); 
    }

    @Override
    public void execute() {
        double stickPos = joystick.getRawAxis(1);
        motorSubsystem.setSpark(stickPos);
        motorSubsystem.setTalonSR(stickPos);
        motorSubsystem.setVictorSPX(stickPos);
        motorSubsystem.setSparkMax(stickPos);
        motorSubsystem.setFalcon(stickPos);
    }

    @Override
    public void end(boolean interrupted) {
        motorSubsystem.setSpark(0);
        motorSubsystem.setTalonSR(0);
        motorSubsystem.setVictorSPX(0);
        motorSubsystem.setSparkMax(0);
        motorSubsystem.setFalcon(0);   
    }
}
