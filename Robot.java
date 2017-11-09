package org.usfirst.frc.team6411.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	
	/*****Pneumatics*************************************************************/
	Compressor airBooster = new Compressor(0);
	DoubleSolenoid magicAir = new DoubleSolenoid(2,3);
	
	public Robot() {
		DriveTrain.setExpiration(0.1);
	}

	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto modes", chooser);
		
		airBooster.setClosedLoopControl(true);
	}

	public void disabledInit(){

    }

    public void disabledPeriodic() {
      
    }

    public void autonomousInit() {
       
        
    }

    public void autonomousPeriodic() {
        
    }

    public void teleopInit() {
       
    }

    double vmotion = 0;
    double tmotion = 0;
    
    public void teleopPeriodic() {
       
    	vmotion = controller.getY();
    	tmotion = controller.getX();
    	
    	DriveTrain.arcadeDrive(controller);
    	
    	airBooster.setClosedLoopControl(true);
    	
    	if(controller.getRawButton(1) == true) {
    		magicAir.set(DoubleSolenoid.Value.kForward);
    	}
    	else {
    		magicAir.set(DoubleSolenoid.Value.kReverse);
    	}	
    	
    }

    public void testPeriodic() {
        
    }
}