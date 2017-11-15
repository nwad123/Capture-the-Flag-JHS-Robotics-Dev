
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
	DoubleSolenoid shooter = new DoubleSolenoid(1,2);
	Servo flagGrabber = new Servo(5);  //
	

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
			
			myRobot.tankDrive(controller1, controller2); //moved the robot with tank control, left side of robot is moved with controller while right is moved with the controller2
			
			Timer.delay(0.005); // wait for a motor update time
			
			compressor1.setClosedLoopControl(true); //turns on the compressor to compress shots, automatically stops
			
		
		
			
			if(controller1.getRawButton(2) == true)   //checks to see if button 2 is pressed, if it is the robograbber should go retract
			{
				flagGrabber.setAngle(0);   //fully extend the robo arm
			}
			if(controller1.getRawButton(3) == true)   //checks to see if the button 3 is pressed, if it is the robograbber should fully extend
			{ 
				flagGrabber.setAngle(170);   //fully extends the robo arm
			}
				
		}
		
		
		
		if(controller1.getRawButton(4) == true)
		{
			shooter.set(DoubleSolenoid.Value.kForward);
		}
		else
		{
			shooter.set(DoubleSolenoid.Value.kReverse);
		}
		
	
	}

	 // Runs during test mode
	 
	@Override
	public void test() {
	}
}
