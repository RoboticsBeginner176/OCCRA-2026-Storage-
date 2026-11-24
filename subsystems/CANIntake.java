package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.*;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkLowLevel.*;
import com.revrobotics.spark.config.*;
import com.revrobotics.spark.config.SparkBaseConfig.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANIntake extends SubsystemBase{
    SparkMax Spark_Intake;

    public CANIntake() {
        Spark_Intake = new SparkMax(kIntakeID, MotorType.kBrushless);

        SparkMaxConfig configIntake = new SparkMaxConfig();    
        configIntake
         .idleMode(IdleMode.kBrake)
         .smartCurrentLimit(60);
         Spark_Intake.configure(configIntake, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

public void setIntakeWheel(double speed) {
    Spark_Intake.set(speed);
  }
  
public void stop() {
    Spark_Intake.set(0);
  }


    public Command getIntakeCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setIntakeWheel(kIntakeSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }

  public Command getReverseIntakeCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setIntakeWheel(-kIntakeSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }

}
