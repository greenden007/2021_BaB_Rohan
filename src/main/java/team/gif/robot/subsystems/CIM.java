
package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;
import team.gif.robot.subsystems.drivers.Pigeon;

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
public class CIM extends SubsystemBase {
  private static CIM instance = null;

  // creates a singleton class (only 1 instance can be instantiated)
  public static CIM getInstance() {
    if (instance == null) {
      instance = new CIM();
    }
    return instance;
  }
  private static final TalonSRX talon = new TalonSRX(RobotMap.MOTOR_TALON_ONE);
  public CIM(){
    talon.setNeutralMode(NeutralMode.Brake);
  }
  public void setSpeed(double speed) {
    talon.set(ControlMode.PercentOutput, speed);
  }
  public double getSpeedPercent() {
    return talon.getMotorOutputPercent();
  }
}
