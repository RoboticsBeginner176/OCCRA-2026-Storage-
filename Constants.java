// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kOperatorControllerPort = 1;
  }

public static class DrivetrainConstants {
    // PWM ports/CAN IDs for motor controllers
    public static final int kLeftRearID = 2;
    public static final int kLeftFrontID = 1;
    public static final int kRightRearID = 3;
    public static final int kRightFrontID = 4;

    // Current limit for drivetrain motors
    public static final int kCurrentLimit = 60;

    public static final double kLeftRearSpeed = 0.125;
    public static final double kRightRearSpeed = 0.125;
    public static final double kLeftFrontSpeed = 0.125;
    public static final double kRightFrontSpeed = 0.125;
    public static final double kLeftRearBackSpeed = -0.1;
    public static final double kRightRearBackSpeed = -0.1;
    public static final double kLeftFrontBackSpeed = -0.1;
    public static final double kRightFrontBackSpeed = -0.1;
  }



public static class IntakeConstants {
    // PWM ports/CAN IDs for motor controllerskkk]k
    public static final int kIntakeID = 5;

    // Speeds for wheels when intaking. Intake speeds are negative to run the wheels
    // in reverse
    public static final double kIntakeSpeed = 1;


  }

public static class AdjusterConstants {
    // PWM ports/CAN IDs for motor controllerskkk]k
    public static final int kAdjusterID = 7;

    // Speeds for wheels when intaking. Intake speeds are negative to run the wheels
    // in reverse
    public static final double kAdjusterSpeed = 0.4*0.8;


  }

public static class LauncherConstants {
    // PWM ports/CAN IDs for motor controllerskkk]k
    public static final int kLauncherID = 6;

    // Speeds for wheels when intaking. Intake speeds are negative to run the wheels
    // in reverse
    public static final double kLauncherSpeed = 0.36*0.8;


  }


}