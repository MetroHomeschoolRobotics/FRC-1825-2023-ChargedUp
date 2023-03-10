// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutonomousExperiment extends CommandBase {

  private Drivetrain _drivetrain;
  private PIDController _PIDController = new PIDController(0.015, 0, 0.0025);

  Double distance;
  Double turnAngle;


  /** Creates a new AutonomousExperiment. */
  public AutonomousExperiment(Drivetrain drivetrain, double _distance, double _turnAngle) {
    // Use addRequirements() here to declare subsystem dependencies.
    distance = _distance;
    turnAngle = _turnAngle;
    _drivetrain = drivetrain;
    addRequirements(drivetrain);
  }




  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _drivetrain.resetHeading();
    _drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Double turning = _PIDController.calculate(_drivetrain.getHeading(),turnAngle)/0.5;
    Double foward = _PIDController.calculate(_drivetrain.getDistance(),distance)/0.5;
    if(turnAngle != 0){
        _drivetrain.autoDrive(0, turning);
    }else if(distance != 0){
        _drivetrain.autoDrive(foward,0);
    }
    _drivetrain.getSignal();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _drivetrain.autoDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
