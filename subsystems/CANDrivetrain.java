package frc.robot.subsystems;

import static frc.robot.Constants.DrivetrainConstants.*;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.*;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.*;
import com.revrobotics.spark.SparkLowLevel.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CANDrivetrain extends SubsystemBase{
  /*Class member variables. These variables represent things the class needs to keep track of and use between
  different method calls. */
  DifferentialDrive m_drivetrain;
  
  SparkMax leftFront, rightFront, leftRear, rightRear;
  
  /*Constructor. This method is called when an instance of the class is created. This should generally be used to set up
   * member variables and perform any configuration or set up necessary on hardware.
   */
  public CANDrivetrain() {
    leftFront = new SparkMax(kLeftFrontID, MotorType.kBrushed);
    rightFront = new SparkMax(kRightFrontID, MotorType.kBrushed);
    leftRear = new SparkMax(kLeftRearID, MotorType.kBrushed);
    rightRear = new SparkMax(kRightRearID, MotorType.kBrushed);
     

    SparkMaxConfig configRightFront = new SparkMaxConfig();    
    configRightFront
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .openLoopRampRate(0);
    rightFront.configure(configRightFront, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    SparkMaxConfig configRightRear = new SparkMaxConfig();    
    configRightRear
      .inverted(false)
      .follow(kRightFrontID)
      .idleMode(IdleMode.kBrake)
      .openLoopRampRate(0);
    rightRear.configure(configRightRear, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
 
 
    SparkMaxConfig configLeftFront = new SparkMaxConfig();    
    configLeftFront
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .openLoopRampRate(0);
    leftFront.configure(configLeftFront, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    SparkMaxConfig configLeftRear = new SparkMaxConfig();    
    configLeftRear
      .inverted(false)
      .follow(kLeftFrontID)
      .idleMode(IdleMode.kBrake)
      .openLoopRampRate(0);
    leftRear.configure(configLeftRear, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

      // Put the front motors into the differential drive object. This will control all 4 motors with
      // the rears set to follow the fronts
      m_drivetrain = new DifferentialDrive(leftFront, rightFront);
    

  
  }
  /*Method to control the drivetrain using arcade drive. Arcade drive takes a speed in the X (forward/back) direction
   * and a rotation about the Z (turning the robot about it's center) and uses these to control the drivetrain motors */
  public void arcadeDrive(double speed, double rotation) {
    m_drivetrain.arcadeDrive(-speed, rotation);
    
  }
  

  @Override
  public void periodic() {
  }

  public void stop() {
    leftFront.set(0);
    rightFront.set(0);
    leftRear.set(0);
    rightRear.set(0);
  }
}
