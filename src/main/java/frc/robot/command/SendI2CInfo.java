// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.arduino.ArduinoI2CConnection;
import edu.wpi.first.wpilibj.I2C;

/** Add your docs here. */
public class SendI2CInfo extends CommandBase 
{
    private I2C device;
    private String text;
    public SendI2CInfo(I2C device, String text) {
        this.device = device;
        this.text = text;
    }
    @Override
    public void initialize() {
        ArduinoI2CConnection.sendInfo(device, text);
    }
}
