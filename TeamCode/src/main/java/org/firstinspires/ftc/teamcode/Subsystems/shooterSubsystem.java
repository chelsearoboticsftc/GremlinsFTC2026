package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.health.connect.datatypes.units.Velocity;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class shooterSubsystem {

    DcMotorEx nShooter;
    DcMotorEx pShooter;

    public shooterSubsystem(HardwareMap hardwaremap) {
        this.nShooter = hardwareMap.get(DcMotorEx.class, "nShooter");
        this.pShooter = hardwareMap.get(DcMotorEx.class, "pShooter");

        nShooter.setZeroPowerBehavior(shooterSubsystemConstant.N_SHOOTER_ZERO_POWER_BEHAVIOR);
        pShooter.setZeroPowerBehavior(shooterSubsystemConstant.P_SHOOTER_ZERO_POWER_BEHAVIOR);

        nShooter.setDirection(shooterSubsystemConstant.N_SHOOTER_DIRECTION);
        pShooter.setDirection(shooterSubsystemConstant.P_SHOOTER_DIRECTION);
    }

    public void setVelocity(double Velocity) {
        nShooter.setVelocity(Velocity);
        pShooter.setVelocity(Velocity);
    }

}




