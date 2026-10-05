package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem {

    DcMotorEx leftIntake;
    DcMotorEx rightIntake;

    public IntakeSubsystem(HardwareMap hardwareMap) {

        this.leftIntake = hardwareMap.get(DcMotorEx.class, "leftIntake");
        this.rightIntake = hardwareMap.get(DcMotorEx.class, "rightIntake");

        leftIntake.setZeroPowerBehavior(IntakeSubsystemConstants.LEFT_INTAKE_ZERO_POWER_BEHAVIOR);
        rightIntake.setZeroPowerBehavior(IntakeSubsystemConstants.RIGHT_INTAKE_ZERO_POWER_BEHAVIOR);

        leftIntake.setDirection(IntakeSubsystemConstants.LEFT_INTAKE_DIRECTION);
        rightIntake.setDirection(IntakeSubsystemConstants.RIGHT_INTAKE_DIRECTION);


    }

    public void setPower(double power) {
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }


}
