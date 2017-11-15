
package org.usfirst.frc.team6411.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;

public class Robot extends SampleRobot {
	RobotDrive myRobot = new RobotDrive(0, 1);
	Joystick controller1 = new Joystick(0);
	Joystick controller2 = new Joystick(1);
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	SendableChooser<String> chooser = new SendableChooser<>();
	Compressor compressor1 = new Compressor(0);
	boolean compressorBoolean = false;
	DoubleSolenoid Shooter = new DoubleSolenoid(1,2);
	

	

	public Robot() {
		myRobot.setExpiration(0.1);
	}

	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto modes", chooser);
	
	}

	
	@Override
	public void autonomous() {
		String autoSelected = chooser.getSelected();
		// String autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);

		switch (autoSelected) {
		case customAuto:
			myRobot.setSafetyEnabled(false);
			myRobot.drive(-0.5, 1.0); // spin at half speed
			Timer.delay(2.0); // for 2 seconds
			myRobot.drive(0.0, 0.0); // stop robot
			break;
		case defaultAuto:
		default:
			myRobot.setSafetyEnabled(false);
			myRobot.drive(-0.5, 0.0); // drive forwards half speed
			Timer.delay(2.0); // for 2 seconds
			myRobot.drive(0.0, 0.0); // stop robot
			break;
		}
	}

	
	  //Runs the motors with arcade steering.
	 
	@Override
	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			myRobot.tankDrive(controller1, controller2); // drive with arcade style (use right
										// stick)
			
			Timer.delay(0.005); // wait for a motor update time
			
			compressor1.setClosedLoopControl(true); //always sets it to true
			
			if(controller1.getRawButton(2) == false)
			{
				Shooter.set(DoubleSolenoid.Value.kOff);
			}
			else
			{
				Shooter.set(DoubleSolenoid.Value.kForward);
			}
			
		}
	}

	
	
	
	 // Runs during test mode
	 
	@Override
	public void test() {
	}
}
