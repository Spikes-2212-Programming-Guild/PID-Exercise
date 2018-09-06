package org.usfirst.frc.team2212.robot.commands;

import org.usfirst.frc.team2212.robot.Robot;
import org.usfirst.frc.team2212.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceWithPID extends Command {
	private double distance;
	private double tolerance;
	private PIDController pidControllerLeft;
	private PIDController pidControllerRight;
	private double timeWhenFirstOnTarget;
	private boolean firstTimeOnTarget = true;
	
    public DriveDistanceWithPID(double distance, double tolerance) {
        requires(Robot.drivetrain);
        this.tolerance = tolerance;
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pidControllerLeft = new PIDController(Drivetrain.KP, Drivetrain.KI, Drivetrain.KD, Robot.drivetrain.getLeftEncoder(), Robot.drivetrain.getLeft());
    	pidControllerLeft.setSetpoint(distance);
    	pidControllerLeft.setAbsoluteTolerance(tolerance);
    	pidControllerLeft.setOutputRange(-1, 1);
    	
    	pidControllerRight = new PIDController(Drivetrain.KP, Drivetrain.KI, Drivetrain.KD, Robot.drivetrain.getRightEncoder(), Robot.drivetrain.getRight());
    	pidControllerRight.setSetpoint(distance);
    	pidControllerRight.setAbsoluteTolerance(tolerance);
    	pidControllerRight.setOutputRange(-1, 1);
    	
    	pidControllerLeft.enable();
    	pidControllerRight.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(pidControllerLeft.onTarget() && pidControllerRight.onTarget())
        	if(firstTimeOnTarget) {
        		timeWhenFirstOnTarget = Timer.getFPGATimestamp();
        		firstTimeOnTarget = false;
        	} else if(Timer.getFPGATimestamp()-timeWhenFirstOnTarget == 1000) {
        		return true;
        	}
        else
        	firstTimeOnTarget = true;
        	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
