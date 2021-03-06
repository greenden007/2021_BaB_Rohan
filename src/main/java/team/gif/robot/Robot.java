
package team.gif.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import team.gif.robot.commands.*;
import team.gif.robot.subsystems.CIM;
import team.gif.robot.subsystems.LimitSwitch;
import team.gif.robot.subsystems.NEO;
import team.gif.robot.subsystems.drivers.Pigeon;

import java.sql.SQLOutput;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static OI oi;
  public static Command CIMCommand = null;
  public static WPI_TalonSRX talon = null;
  public static Command NEOCommand = null;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    System.out.println("robot init");
    talon = new WPI_TalonSRX(RobotMap.MOTOR_TALON_ONE);
    // autonomous chooser on the dashboard.
    Pigeon pigeon = new Pigeon(talon);
    pigeon.resetPigeonPosition();
    Pigeon.getInstance().addToShuffleboard("Pigeon");
    CIMCommand = new CIMJoystick();
    NEOCommand = new NEORPM();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    CommandScheduler.getInstance().run();

    SmartDashboard.putBoolean("Limit Switch", LimitSwitch.getInstance().getSwitchStatus());
    LimitSwitch.getInstance().updateSwitchStatus();
    SmartDashboard.putNumber("NEO RPM", NEO.getInstance().getRPM());
    SmartDashboard.putNumber("CIM RPM%", CIM.getInstance().getSpeedPercent());
    System.out.println("Pigeon Heading: " + Pigeon.getInstance().get360Heading());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    System.out.println("teleop init");

    oi = new OI();
    CIMCommand.schedule();
    NEOCommand.schedule();
  }

  @Override
  public void teleopPeriodic() {

  }
  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic(){
  }
}