// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class moveArmPos extends CommandBase {

  


  private double kp=0.005;
  private double ki=0;
  private double kd=0;

  private double kp2 = 0.001;
  private double ki2 = 0;
  private double kd2 = 0;


  private Arm arm;

  private PIDController pidAngle = new PIDController(kp, ki, kd);
  private PIDController pidExtend = new PIDController(kp2, ki2, kd2);

  private double setPoint;
  private double exSetPoint;

  /** Creates a new moveArmPos. */
  public moveArmPos(Arm _arm, double _angleSetPoint, double _extentionSetpoint) {
    arm = _arm;
    setPoint = _angleSetPoint;
    exSetPoint = _extentionSetpoint;
    addRequirements(_arm);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    arm.moveAngleMotor(-MathUtil.clamp(pidAngle.calculate(arm.getAbsoluteAngle(), setPoint), -0.5, 0.5));

    //arm.moveTeleMotor(MathUtil.clamp(pidExtend.calculate(arm.getTeleDistance(), exSetPoint), -0.5, 0.5));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pidAngle.atSetpoint();
  }
}