package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

//import android.health.connect.datatypes.units.Velocity;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class shooterSubsystem {

    DcMotorEx nShooter;
    DcMotorEx pShooter;

    public shooterSubsystem(HardwareMap hardwareMap) {
        this.nShooter = hardwareMap.get(DcMotorEx.class, "nShooter");
        this.pShooter = hardwareMap.get(DcMotorEx.class, "pShooter");

        nShooter.setZeroPowerBehavior(shooterSubsystemConstant.N_SHOOTER_ZERO_POWER_BEHAVIOR);
        pShooter.setZeroPowerBehavior(shooterSubsystemConstant.P_SHOOTER_ZERO_POWER_BEHAVIOR);

        nShooter.setDirection(shooterSubsystemConstant.N_SHOOTER_DIRECTION);
        pShooter.setDirection(shooterSubsystemConstant.P_SHOOTER_DIRECTION);

        pShooter.setVelocityPIDFCoefficients(
                shooterSubsystemConstant.P_SHOOTER_VELOCITY_P, //Proportional Gain
                shooterSubsystemConstant.P_SHOOTER_VELOCITY_I, //Integral Gain
                shooterSubsystemConstant.P_SHOOTER_VELOCITY_D, //Derivative Gain
                shooterSubsystemConstant.P_SHOOTER_VELOCITY_F);//Feed Forward Gain

         nShooter.setVelocityPIDFCoefficients(
                shooterSubsystemConstant.N_SHOOTER_VELOCITY_P, //Proportional Gain
                shooterSubsystemConstant.N_SHOOTER_VELOCITY_I, //Integral Gain
                shooterSubsystemConstant.N_SHOOTER_VELOCITY_D, //Derivative Gain
                shooterSubsystemConstant.N_SHOOTER_VELOCITY_F);//Feed Forward Gain

    }

    public void setNVelocity(double Velocity) {
        nShooter.setVelocity(Velocity);

    }
    public void setPVelocity(double Velocity) {
        pShooter.setVelocity(Velocity);

    }

    public double getNVelocity(){
        return nShooter.getVelocity();
    }

    public double getPVelocity(){
        return pShooter.getVelocity();
    }

}




