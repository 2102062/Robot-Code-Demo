package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorSubsystem extends SubsystemBase {
    private Talon talonSR;
    private Spark spark;
    private WPI_VictorSPX victorSPX;
    private CANSparkMax sparkMax;
    private PWM pwmFan;
    private WPI_TalonFX falcon;
    private PWM pwmServo;

    public MotorSubsystem(Talon talonSR, Spark spark, WPI_VictorSPX victorSPX, CANSparkMax sparkMax, PWM pwmFan, WPI_TalonFX falcon, PWM pwmServo) {
        this.pwmFan = pwmFan;
        this.talonSR = talonSR; 
        this.spark = spark;
        this.victorSPX = victorSPX;
        this.sparkMax = sparkMax;
        this.falcon = falcon;
        this.pwmServo = pwmServo;
    }

    public void setTalonSR(double num) {
        talonSR.set(num);
    }

    public double getTalonSR() {
        return talonSR.get();
    }

    public void setSpark(double num) {
        spark.set(num);
    }

    public double getSpark() {
        return spark.get();
    }

    public void setVictorSPX(double num) {
        victorSPX.set(num);
    }

    public double getVictorSPX() {
        return victorSPX.get();
    }

    public void setSparkMax(double num) {
        sparkMax.set(num);
    }

    public double getSparkMax() {
        return sparkMax.get();
    }

    public void setFanSpeed(double num) {
        pwmFan.setSpeed(num);
    }

    public void setFalcon(double num) {
        falcon.set(num);
    }

    public double getFalcon() {
        return falcon.get();
    }

    public void setServo(double position) {
        pwmServo.setPosition(position);
    }

    public double getServo() {
        return pwmServo.getPosition();
    }
}