package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intakeflowerSubsystem {
    DcMotorEx leftServo;
    DcMotorEx rightServo;

    public intakeflowerSubsystem(HardwareMap hardwareMap) {

        this.leftServo = hardwareMap.get(DcMotorEx.class, "leftServo");
        this.rightServo = hardwareMap.get(DcMotorEx.class, "rightServo");

        leftServo.setZeroPowerBehavior(intakeflowerSubsystemConstants.LEFT_SERVO_ZERO_POWER_BEHAVIOR);
        rightServo.setZeroPowerBehavior(intakeflowerSubsystemConstants.RIGHT_SERVO_ZERO_POWER_BEHAVIOR);

        leftServo.setDirection(intakeflowerSubsystemConstants.LEFT_SERVO_DIRECTION);

        rightServo.setDirection(intakeflowerSubsystemConstants.RIGHT_SERVO_DIRECTION);
    }

    public void setPower(double power) {
        leftServo.setPower(power);
        rightServo.setPower(power);

    }
}
