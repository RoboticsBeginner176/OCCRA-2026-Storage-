package frc.robot.subsystems;

import static frc.robot.Constants.LauncherConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkLowLevel.*;
import com.revrobotics.spark.config.*;
import com.revrobotics.spark.config.SparkBaseConfig.*;

public class CANLauncher extends SubsystemBase{
SparkMax Spark_Launcher;

    public CANLauncher() {
        Spark_Launcher = new SparkMax(kLauncherID, MotorType.kBrushless);

        SparkMaxConfig configLauncher = new SparkMaxConfig();    
        configLauncher
         .idleMode(IdleMode.kBrake)
         .smartCurrentLimit(60);
         Spark_Launcher.configure(configLauncher, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

public void setLauncherWheel(double speed) {
    Spark_Launcher.set(speed);
  }
  
public void stop() {
    Spark_Launcher.set(0);
  }


public Command getLauncherCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the Launcher speed values
        () -> {
          setLauncherWheel(kLauncherSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }

  public Command getReverseLauncherCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the Launcher speed values
        () -> {
          setLauncherWheel(-kLauncherSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }
}