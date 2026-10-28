package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class shooterSubsystemConstant {
    static DcMotor.ZeroPowerBehavior N_SHOOTER_ZERO_POWER_BEHAVIOR = DcMotor.ZeroPowerBehavior.BRAKE;
    static DcMotor.ZeroPowerBehavior P_SHOOTER_ZERO_POWER_BEHAVIOR = DcMotor.ZeroPowerBehavior.BRAKE;

    public static final DcMotorSimple.Direction N_SHOOTER_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction P_SHOOTER_DIRECTION = DcMotorSimple.Direction.FORWARD;
}



