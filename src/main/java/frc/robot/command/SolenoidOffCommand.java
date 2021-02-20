package frc.robot.command;

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
        pnuematicSubsystem.setSolenoid(false);
        pnuematicSubsystem.clearSolenoidStickyFaults();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
