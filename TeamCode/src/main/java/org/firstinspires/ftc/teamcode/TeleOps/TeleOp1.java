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
import org.firstinspires.ftc.teamcode.Subsystems.shieldSubsystem;

@TeleOp(name = "TeleOp1")
public class TeleOp1 extends OpMode {

    private IntakeSubsystem intake;
    private shooterSubsystem shooter;

    private gatesSubsystem gateServo;
    private shieldSubsystem shield;


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
        shield = new shieldSubsystem(hardwareMap);
    }

    @Override
    public void loop() {
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -gamepad1.right_stick_x,
                follower.pose().heading()
        );

        if (gamepad1.yWasPressed()){
            //lines up the driving for robot on red side
//            Math.toRadians(180);
            follower.setPose(new Pose(8,8,0));
        }

        if (gamepad1.xWasPressed()){
            //lines up the driving for robot on the red side
            follower.setPose(new Pose(137.5,137.5, 180));
        }

        if (gamepad2.aWasPressed()){
            intake.setPower(0.7);
        }

        if (gamepad2.aWasReleased()){
            intake.setPower(0);
        }

        if (gamepad2.yWasPressed()){
            gateServo.setServoTargetPollen(true);
        }

        if (gamepad2.yWasReleased()){
            gateServo.setServoTargetPollen(false);
        }

        if (gamepad1.aWasPressed()){
            shield.setServoTargetShield(true);
        }

        if (gamepad1.bWasPressed()){
            shield.setServoTargetShield(false);
        }

        if (gamepad2.rightBumperWasPressed()){
            shooter.setNVelocity(1650);
        }

        if (gamepad2.rightBumperWasReleased()){
            shooter.setNVelocity(0);
        }
        if (gamepad2.leftBumperWasPressed()){
            shooter.setPVelocity(1950);
        }

        if (gamepad2.leftBumperWasReleased()){
            shooter.setPVelocity(0);
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