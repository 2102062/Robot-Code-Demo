package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PnuematicSubsystem extends SubsystemBase {
    private Solenoid cylinderSolenoid1;
    private Solenoid cylinderSolenoid2;
    private Solenoid solenoid;

    public PnuematicSubsystem(Solenoid cylinderSolenoid1, Solenoid cylinderSolenoid2, Solenoid solenoid) {
        this.cylinderSolenoid1 = cylinderSolenoid1;
        this.cylinderSolenoid2 = cylinderSolenoid2;
        this.solenoid = solenoid;
    }

    public void setSolenoid(boolean on) {
        solenoid.set(on);
    }

    public void setCylinderSolenoids(boolean on) {
        cylinderSolenoid2.set(!on);
        cylinderSolenoid1.set(on);
    }
}
