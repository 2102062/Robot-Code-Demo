package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PnuematicSubsystem extends SubsystemBase {
    private Solenoid solenoid1;
    private Solenoid solenoid2;

    public PnuematicSubsystem(Solenoid solenoid1, Solenoid solenoid2) {
        this.solenoid1 = solenoid1;
        this.solenoid2 = solenoid2;
    }

    public void setSolenoid1(boolean on) {
        solenoid2.set(!on);
        solenoid1.set(on);
    }
}
