package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class shooterSubsystemConstant {
    static DcMotor.ZeroPowerBehavior N_SHOOTER_ZERO_POWER_BEHAVIOR = DcMotor.ZeroPowerBehavior.BRAKE;
    static DcMotor.ZeroPowerBehavior P_SHOOTER_ZERO_POWER_BEHAVIOR = DcMotor.ZeroPowerBehavior.BRAKE;

    public static final DcMotorSimple.Direction N_SHOOTER_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction P_SHOOTER_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static final double P_SHOOTER_VELOCITY_P = 1.0;
    public static final double P_SHOOTER_VELOCITY_I = 0.0;
    public static final double P_SHOOTER_VELOCITY_D = 0.0;
    public static final double P_SHOOTER_VELOCITY_F = 14.5;

    public static final double N_SHOOTER_VELOCITY_P = 1.0;
    public static final double N_SHOOTER_VELOCITY_I = 0.0;
    public static final double N_SHOOTER_VELOCITY_D = 0.0;
    public static final double N_SHOOTER_VELOCITY_F = 14.5;
}




