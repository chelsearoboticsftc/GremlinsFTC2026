package org.firstinspires.ftc.teamcode.TeleOps;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.ManualDrive;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.shooterSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.gatesSubsystem;

@TeleOp(name = "TeleOp1")
public class TeleOp1 extends OpMode {

    private IntakeSubsystem intake;
    private shooterSubsystem shooter;

    private gatesSubsystem gateServo;


    private Follower follower;
    @Override
    public void start(){
        follower.setPose(OpModeStorage.autonomousEndPose);
        follower.update();
    }
    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
        intake = new IntakeSubsystem(hardwareMap);
        shooter = new shooterSubsystem(hardwareMap);
        gateServo = new gatesSubsystem(hardwareMap);
    }

    @Override
    public void loop() {
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                follower.pose().heading()
        );

        if (gamepad2.aWasPressed()){
            intake.setPower(1);
        }

        if (gamepad2.aWasReleased()){
            intake.setPower(0);
        }

        if (gamepad2.xWasPressed()) {
            shooter.setVelocity(3000);
        }

        if (gamepad2.xWasReleased()){
            shooter.setVelocity(0);
        }


        if (gamepad2.rightBumperWasPressed()){
            shooter.setVelocity(3000);
            intake.setPower(1);
        }

        if (gamepad2.rightBumperWasReleased()){
            shooter.setVelocity(0);
            intake.setPower(0);
        }

        if (gamepad2.yWasPressed()){
            gateServo.setServoTargetPollen(true);
        }

        if (gamepad2.yWasReleased()){
            gateServo.setServoTargetPollen(false);
        }

        if (gamepad2.rightTriggerWasPressed()){
            shooter.setVelocity(3000);
            intake.setPower(1);
        }

        if (gamepad2.rightTriggerWasReleased()){
            shooter.setVelocity(0);
            intake.setPower(0);
        }

        if (gamepad2.bWasPressed()){
            gateServo.setServoTargetNectar(true);
        }

        if (gamepad2.bWasReleased()){
            gateServo.setServoTargetNectar(false);
        }



        follower.manual(powers);
        follower.update();
        Pose robotPose = follower.pose(); // returns a Pose object
        telemetry.addData("Robot X", robotPose.x());
        telemetry.addData("Robot Y", robotPose.y());
        telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
        telemetry.addData("NVelocity", shooter.getNVelocity());
        telemetry.addData("pShooter", shooter.getPVelocity());
    }
}