package frc.robot.command;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.PnuematicSubsystem;

public class SolenoidOffCommand extends CommandBase {
    private PnuematicSubsystem pnuematicSubsystem;

    public SolenoidOffCommand(PnuematicSubsystem pnuematicSubsystem) {
        this.pnuematicSubsystem = pnuematicSubsystem;
        addRequirements(pnuematicSubsystem);
    }

    @Override
    public void initialize() {
        pnuematicSubsystem.setSolenoid1(false);
        System.out.println("Solenoid off");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}