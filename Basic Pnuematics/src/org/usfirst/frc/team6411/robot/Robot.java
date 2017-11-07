package org.usfirst.frc.team6411.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends SampleRobot {
	RobotDrive DriveTrain = new RobotDrive(0, 1, 2, 3);
	Joystick controller = new Joystick(0);
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	SendableChooser<String> chooser = new SendableChooser<>();

	public Robot() {
		DriveTrain.setExpiration(0.1);
	}

	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto modes", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * if-else structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void disabledInit(){

    }

    public void disabledPeriodic() {
      
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
    }

    public void teleopInit() {
       
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       
    	DriveTrain.arcadeDrive(controller);
    	
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        
    }
}