package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystem.PnuematicSubsystem;

public class SolenoidOnCommand extends CommandBase {
    private PnuematicSubsystem pnuematicSubsystem;
    public SolenoidOnCommand(PnuematicSubsystem pnuematicSubsystem) {
        this.pnuematicSubsystem = pnuematicSubsystem;
        addRequirements(pnuematicSubsystem);
    }

    @Override
    public void initialize() {
        pnuematicSubsystem.setSolenoid(true);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
