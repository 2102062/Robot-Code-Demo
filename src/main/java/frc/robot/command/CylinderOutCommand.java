package frc.robot.command;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.PnuematicSubsystem;

public class CylinderOutCommand extends CommandBase {
    private PnuematicSubsystem pnuematicSubsystem;

    public CylinderOutCommand(PnuematicSubsystem pnuematicSubsystem) {
        this.pnuematicSubsystem = pnuematicSubsystem;
        addRequirements(pnuematicSubsystem);
    }

    @Override
    public void initialize() {
        pnuematicSubsystem.setCylinderSolenoids(true);
        System.out.println("Solenoid on");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}