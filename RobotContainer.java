// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.commands.Adjuster;
import frc.robot.subsystems.CANAdjuster;
import frc.robot.subsystems.CANDrivetrain;
import frc.robot.commands.Intake;
import frc.robot.subsystems.CANIntake;
import frc.robot.commands.Launcher;
import frc.robot.subsystems.CANLauncher;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final CANDrivetrain m_Drivetrain = new CANDrivetrain();
  private final CANAdjuster m_Adjuster = new CANAdjuster();
  private final CANIntake m_Intake = new CANIntake();
  private final CANLauncher m_Launcher = new CANLauncher();



  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ArcadeDrive` when Condition changes
    m_Drivetrain.setDefaultCommand(
        new RunCommand(
            () ->
                m_Drivetrain.arcadeDrive(
                  -(m_driverController.getLeftTriggerAxis() - m_driverController.getRightTriggerAxis()), m_driverController.getLeftX()),
            m_Drivetrain));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
     m_operatorController.leftTrigger().whileTrue(m_Launcher.getLauncherCommand());
     m_operatorController.rightTrigger().whileTrue(m_Launcher.getReverseLauncherCommand());
     m_operatorController.a().whileTrue(m_Intake.getIntakeCommand());
     m_operatorController.b().whileTrue(m_Intake.getReverseIntakeCommand());
     m_operatorController.rightTrigger().whileTrue(m_Adjuster.getAdjusterCommand());
     m_operatorController.leftTrigger().whileTrue(m_Adjuster.getReverseAdjusterCommand());
     m_operatorController.x().whileTrue(
      Commands.parallel(
        m_Launcher.getReverseLauncherCommand(),
        m_Adjuster.getAdjusterCommand(),
        Commands.waitSeconds(2).andThen(m_Intake.getReverseIntakeCommand())));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
private Command driveFor(double speed, double seconds) {
  return new RunCommand(() -> m_Drivetrain.arcadeDrive(speed, 0), m_Drivetrain)
      .withTimeout(seconds)
      .andThen(new InstantCommand(m_Drivetrain::stop, m_Drivetrain));
}

private Command turnFor(double rotation, double seconds) {
  return new RunCommand(() -> m_Drivetrain.arcadeDrive(0, rotation), m_Drivetrain)
      .withTimeout(seconds)
      .andThen(new InstantCommand(m_Drivetrain::stop, m_Drivetrain));
}

public Command getAutonomousCommand() {
  return Commands.sequence(
    driveFor(0.5, 0.5),  
    Commands.parallel(
          m_Launcher.getReverseLauncherCommand(),
          m_Adjuster.getAdjusterCommand(),
          Commands.waitSeconds(2).andThen(m_Intake.getReverseIntakeCommand()))
          .withTimeout(5),
      driveFor(-0.5, 2.5),
      turnFor(0.5, 2.0));
}



}
