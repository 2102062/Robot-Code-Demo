// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.simulation.PCMDataJNI;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.command.Falcon500ManualControlCommand;
import frc.robot.command.FalconRotationCommand;
import frc.robot.command.FanManualControlCommand;
import frc.robot.command.SendI2CInfo;
import frc.robot.command.SendSerialInfo;
import frc.robot.command.ServoManualPositionCommand;
import frc.robot.command.SolenoidOffCommand;
import frc.robot.command.SolenoidOnCommand;
import frc.robot.command.AllMotorsManualControl;
import frc.robot.command.CylinderInCommand;
import frc.robot.command.CylinderOutCommand;
import frc.robot.command.SparkManualControlCommand;
import frc.robot.command.SparkMaxManualControlCommand;
import frc.robot.command.TalonSRManualControlCommand;
import frc.robot.command.VictorSPXManualControlCommand;
import frc.robot.subsystem.MotorSubsystem;
import frc.robot.subsystem.PnuematicSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  private final double LOWER_PRESSURE_LIMIT = 20.0;
  private final double UPPER_PRESSURE_LIMIT = 60.0;


  private Talon talonSR;
  private Spark spark;
  private WPI_VictorSPX victorSPX;
  private CANSparkMax sparkMax;
  private WPI_TalonFX falcon;
  private PWM pwmFan;
  private PWM pwmServo;

  private Solenoid cylinderSolenoid1;
  private Solenoid cylinderSolenoid2;
  private Solenoid solenoid;

  private DigitalInput limitSwitch;

  public PowerDistributionPanel pdp;
  public Compressor airCompressor; 

  public RobotStick joystick;

  public MotorSubsystem motorSubsystem;
  public PnuematicSubsystem pnuematicSubsystem;

  public I2C Wire;

  public SerialPort arduino;
  /*
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    System.out.println("robotInit() starting...");
    talonSR = new Talon(7);
    spark = new Spark(8);
    victorSPX = new WPI_VictorSPX(1);
    sparkMax = new CANSparkMax(5, MotorType.kBrushed);
    pwmFan = new PWM(6);
    pwmServo = new PWM(9);
    falcon = new WPI_TalonFX(0);

    pdp = new PowerDistributionPanel();
    pdp.clearStickyFaults();

    airCompressor = new Compressor();
    airCompressor.setClosedLoopControl(true);

    cylinderSolenoid1 = new Solenoid(2);
    cylinderSolenoid2 = new Solenoid(1);
    solenoid = new Solenoid(0);

    limitSwitch = new DigitalInput(9);

    motorSubsystem = new MotorSubsystem(talonSR, spark, victorSPX, sparkMax, pwmFan, falcon, pwmServo);
    pnuematicSubsystem = new PnuematicSubsystem(cylinderSolenoid1, cylinderSolenoid2, solenoid);

    joystick = new RobotStick(0);

    DashHelper.startDash();

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    airCompressor.start();
    System.out.println("robotInit() complete!");

    Wire = new I2C(I2C.Port.kOnboard, 8);

    SerialPort arduino = new SerialPort(9600, SerialPort.Port.kUSB);
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().cancelAll();
    m_autoSelected = m_chooser.getSelected();
    airCompressor.start();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    System.out.println("Initializing teleop");

    CommandScheduler.getInstance().cancelAll();
    CommandScheduler.getInstance().schedule(new ServoManualPositionCommand(motorSubsystem, joystick, limitSwitch));

    joystick.getButton(2).whenPressed(new SendSerialInfo(arduino, "Go"));
    joystick.getButton(3).whenPressed(new FalconRotationCommand(motorSubsystem, joystick));
    joystick.getButton(4).whenPressed(new AllMotorsManualControl(motorSubsystem, joystick));
    joystick.getButton(5).whenPressed(new ServoManualPositionCommand(motorSubsystem, joystick, limitSwitch));
    joystick.getButton(6).whenPressed(new SparkManualControlCommand(motorSubsystem, joystick));
    joystick.getButton(7).whenPressed(new TalonSRManualControlCommand(motorSubsystem, joystick));
    joystick.getButton(8).whenPressed(new VictorSPXManualControlCommand(motorSubsystem, joystick));
    joystick.getButton(9).whenPressed(new SparkMaxManualControlCommand(motorSubsystem, joystick));
    joystick.getButton(10).whenPressed(new Falcon500ManualControlCommand(motorSubsystem, joystick));
    joystick.getButton(11).whenPressed(new SolenoidOnCommand(pnuematicSubsystem));
    joystick.getButton(11).whenReleased(new SolenoidOffCommand(pnuematicSubsystem));
    joystick.getButton(12).whenPressed(new CylinderOutCommand(pnuematicSubsystem));
    joystick.getButton(12).whenReleased(new CylinderInCommand(pnuematicSubsystem));
    
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
