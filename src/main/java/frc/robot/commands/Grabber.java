// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;

public class Grabber extends CommandBase {

  private final Pneumatics grabberPneumatic;

  /** Creates a new Grabber. */
  public Grabber(Pneumatics pneumatic) {
    // Use addRequirements() here to declare subsystem dependencies.
    grabberPneumatic = pneumatic;
    addRequirements(pneumatic);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    grabberPneumatic.setGrabberDeploy();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    grabberPneumatic.changeGrabberState();
    if(grabberPneumatic.pressureIsLow()){
      grabberPneumatic.setCompressor(true);
    }else{
      grabberPneumatic.setCompressor(false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    grabberPneumatic.setGrabberOff();
    grabberPneumatic.setCompressor(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}