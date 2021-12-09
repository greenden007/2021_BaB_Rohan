
package team.gif.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;

/**
 * Subsystems describe functionality of a particular feature of
 * the robot. It contains methods for commands (and other methods)
 *    to interact with the subsystem.
 * Example: An Elevator subsysytem might have commands which
 *    set a zero position setZeroPosition()
 *    get position getPosition()
 *    move hanger to a specific height setHeight()
 *
 * Describe the Subsystem functionality here
 */
public class NEO extends SubsystemBase {
  private static NEO instance = null;

  // creates a singleton class (only 1 instance can be instantiated)
  public static NEO getInstance() {
    if (instance == null) {
      instance = new NEO();
    }
    return instance;
  }
  private static final CANSparkMax NEOMotor = new CANSparkMax(RobotMap.MOTOR_SPARKMAX_ONE, CANSparkMaxLowLevel.MotorType.kBrushless);
  private static final CANEncoder NEOEncoder = NEOMotor.getEncoder();
  private static final CANPIDController NEOControl = NEOMotor.getPIDController();

  private final int maxAmps = 40;
  public NEO(){
    super();
    NEOMotor.restoreFactoryDefaults();
    NEOMotor.enableVoltageCompensation(12);
    NEOMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    NEOMotor.setSmartCurrentLimit(maxAmps, maxAmps);
    NEOControl.setOutputRange(0, 1);


    NEOControl.setP(0.0003);
    NEOControl.setFF(0.000175);

  }
  public void setRPM(double velocity) {
    NEOControl.setReference(velocity, ControlType.kVelocity);
  }
  public void setVoltage(double voltage) {
    NEOMotor.setVoltage(voltage);
  }
  public double getRPM() {
    return NEOEncoder.getVelocity();
  }
}
