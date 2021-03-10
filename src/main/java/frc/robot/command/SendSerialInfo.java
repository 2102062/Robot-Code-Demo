// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.arduino.ArduinoSerialConnection;
import edu.wpi.first.wpilibj.SerialPort;
/** Add your docs here. */
public class SendSerialInfo extends CommandBase 

{
    private SerialPort port;
    private String message;
    public SendSerialInfo(SerialPort port, String message) {
        this.port = port;
        this.message = message;
    }
    @Override
    public void initialize() {
        ArduinoSerialConnection.WriteToArduino(port, message);
    }

}
