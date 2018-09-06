package org.usfirst.frc.team2212.robot.subsystems;

import org.usfirst.frc.team2212.robot.commands.DriveDistanceWithPID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	public static final double KP = 0.8;
	public static final double KI = 0.05;
	public static final double KD = 0.3;

	private SpeedController left;
	private SpeedController right;
    
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    
    public Drivetrain(SpeedController left, SpeedController right, Encoder leftEncoder, Encoder rightEncoder) {
    	this.left = left;
    	this.right = right;
    	this.leftEncoder = leftEncoder;
    	this.rightEncoder = rightEncoder;
    }
    
    public void drive(double leftSpeed, double rightSpeed) {
    	left.set(leftSpeed);
    	right.set(rightSpeed);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveDistanceWithPID(0, 0));
    }

	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	public Encoder getRightEncoder() {
		return rightEncoder;
	}
	
	public SpeedController getLeft() {
		return left;
	}

	public SpeedController getRight() {
		return right;
	}
}

