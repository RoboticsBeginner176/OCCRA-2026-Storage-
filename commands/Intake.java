package frc.robot.commands;

import static frc.robot.Constants.IntakeConstants.*;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANIntake;

public class Intake extends Command{

    CANIntake m_Intake;

    public Intake(CANIntake intake, double speed) {
        m_Intake = intake;

        addRequirements(m_Intake);
    }


    public void initialize() {
    // Set the wheels to launching speed
        m_Intake.setIntakeWheel(kIntakeSpeed);
    }

    public boolean isFinished() {
    // Always return false so the command never ends on it's own. In this project we use the
    // scheduler to end the command when the button is released.
    return false;
    }

    public void end(boolean interrupted) {
    // Stop the wheels when the command ends.
    m_Intake.stop();
  }
}
