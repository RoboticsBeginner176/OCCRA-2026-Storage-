package frc.robot.subsystems;

import static frc.robot.Constants.AdjusterConstants.*;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkLowLevel.*;
import com.revrobotics.spark.config.*;
import com.revrobotics.spark.config.SparkBaseConfig.*;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANAdjuster extends SubsystemBase{
    SparkMax Spark_Adjuster;

    public CANAdjuster() {
        Spark_Adjuster = new SparkMax(kAdjusterID, MotorType.kBrushless);

        SparkMaxConfig configAdjuster = new SparkMaxConfig();    
        configAdjuster
         .idleMode(IdleMode.kBrake)
         .smartCurrentLimit(60);
         Spark_Adjuster.configure(configAdjuster, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

public void setAdjusterWheel(double speed) {
    Spark_Adjuster.set(speed);
  }
  
public void stop() {
    Spark_Adjuster.set(0);
  }


    public Command getAdjusterCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the Adjuster speed values
        () -> {
          setAdjusterWheel(kAdjusterSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }

  public Command getReverseAdjusterCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the Adjuster speed values
        () -> {
          setAdjusterWheel(-kAdjusterSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }
}
