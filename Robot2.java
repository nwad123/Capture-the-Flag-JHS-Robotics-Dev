
package org.usfirst.frc.team6411.robot;

import edu.wpi.first.wpilibj.RobotDrive;					//these are all of the imports
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;								//this imports all of the referenced libraries

public class Robot extends SampleRobot {
	RobotDrive myRobot = new RobotDrive(0, 1);
	Joystick controller1 = new Joystick(0);			//sets up a controller to the 0 port called controller1 (this refers 
	Joystick controller2 = new Joystick(1);			//sets up a controller to the 1 port called controller2
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	SendableChooser<String> chooser = new SendableChooser<>();
	Compressor compressor1 = new Compressor(0);				//sets up the compressor as compressor1 on the 0 port 
//	DoubleSolenoid shooter = new DoubleSolenoid(1,2);      //sets up the double solenoid in port 1 and 2 with the name shooter
	Servo flagGrabber = new Servo(4);					  //sets up the servo to port 4 with the name flag grabber
			

	public Robot() {
		myRobot.setExpiration(0.1);
	}

	@Override
	public void robotInit() {								//code automatically sets itself up here and its objects
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto modes", chooser);
	
	}

	
	@Override
	public void autonomous() {								//Code that is used when you press the auto mode, can choose the default or custom ones.
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

	
	 
	 
	@Override
	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {			//checks that you enabled operator control on the other robo program, and that you enabled its controller
			
			myRobot.tankDrive(controller1, controller2); //moved the robot with tank control, left side of robot is moved with controller while right is moved with the controller2
			
			Timer.delay(0.005); // wait for a motor update time
			
			compressor1.setClosedLoopControl(true); //turns on the compressor to compress shots, automatically stops
			
		
		
			
			if(controller1.getRawButton(2) == true)   //checks to see if button 2 is pressed, if it is the robograbber should go retract
			{
				flagGrabber.setAngle(0);   //fully retract the robo arm
			}
			if(controller1.getRawButton(3) == true)   //checks to see if the button 3 is pressed, if it is the robograbber should fully extend
			{ 
				flagGrabber.setAngle(170);   //fully extends the robo arm
			}
			
			
			
			
			
			
			
			
			
			
			
			
//			if(controller1.getRawButton(4) == true)			
//			{
//				shooter.set(DoubleSolenoid.Value.kForward);   //MAKES THE DOUBLE SOLENOID MOVE FORWARD
//			}
//			else
//			{
//				shooter.set(DoubleSolenoid.Value.kReverse);		//MAKES THE SOLENOID MOVE REVERSE
//			}
			
				
		}
		
	}

	 // Runs during test mode
	 
	@Override
	public void test() {
	}
}
