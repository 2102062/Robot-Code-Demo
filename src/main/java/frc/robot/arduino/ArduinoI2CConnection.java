// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.arduino;
import edu.wpi.first.wpilibj.I2C;


/** Add your docs here. */
public class ArduinoI2CConnection  {
    
    // Copyright Jonah Snider 2018
    

    /* This is outside a method */
    // Open a new I2C connection on port 4
   
    /* This is inside a method */
    
    // If the back button gets pressed, run this
    //if (Global.driver.Buttons.Back.changedDown) {
     // sendInfo(Wire, "On");
    //}
    
    public static void sendInfo(I2C Device, String text){
      char[] CharArray = text.toCharArray(); // Turn the string into a character array
    
      byte[] WriteData = new byte[CharArray.length]; //make a new array to fill with bytes
      for(int i = 0; i < CharArray.length; i++){
        WriteData[i] = (byte)CharArray[i]; //turn each char into bytes and add it to the byte array
      }
    
      Device.transaction(WriteData, WriteData.length, null, 0); //send byte array to device
    }
    
    }
