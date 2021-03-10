// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.arduino;
import edu.wpi.first.wpilibj.SerialPort;
/** Add your docs here. */
public class ArduinoSerialConnection {


    public static void WriteToArduino(SerialPort port, String message)
    {
        port.writeString(message);
    }
}
