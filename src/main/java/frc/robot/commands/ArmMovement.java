// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;

public class ArmMovement extends CommandBase {

  private Arm arm;
  private CommandXboxController controller;
  private double value;
  public PIDController armPIDController;

  /** Creates a new ArmMovement. */
  public ArmMovement(CommandXboxController _controller, Arm _arm, double encoderValue) {
    arm = _arm;
    value = encoderValue;
    controller = _controller;
    addRequirements(_arm);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    arm.resetAngleEncoders();
    arm.setAngleEncoders(value);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println(controller.getRightY());
    arm.moveAngleMotor(controller.getRightY());
    // telescoping the arm //Great job on the comments :) A+ 
    if(controller.getRightTriggerAxis()>0.01){
      arm.resetShaftEncoders();
      arm.moveTeleMotor(controller.getRightTriggerAxis());
    }else if(controller.getLeftTriggerAxis()>0.01){
      arm.resetShaftEncoders();
      arm.moveTeleMotor(-(controller.getLeftTriggerAxis()));
    }else{
      arm.moveTeleMotor(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
