package org.firstinspires.ftc.teamcode.Autos;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.shooterSubsystem;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class blueAutoShoot extends OpMode {

    private Follower follower;

    private shooterSubsystem shooter;
    private IntakeSubsystem intake;

    private final PoseFactory poseFactory = PoseFactory.degrees();
    private final Pose start = poseFactory.of(106.5449, 135.2433, 270);
    private final Pose path1Start = poseFactory.of(106.5449, 135.2433, 270);
    private final Pose path1 = poseFactory.of(140.6061, 13.9562, 180);
    private final Pose path1Control1 = poseFactory.of(140.253, 126.911, 0);
    private final Pose path1Control2 = poseFactory.of(86.1654, 36.724, 0);


    public Path path1() {
        return Paths.curve(path1Start, path1Control1, path1Control2, path1).linear(path1Start, path1);

    }
    private Command autoRoutine() {
        return sequential(

                instant(() -> shooter.setVelocity(2000)),
                waitMs(3000),
                instant(() -> shooter.setVelocity(0)),


                follow(follower, path1()));
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);//sets the starting point of your Robot
        follower.update();
        intake = new IntakeSubsystem(hardwareMap);
        shooter = new shooterSubsystem(hardwareMap);
    }
    @Override
    public void start() {
        schedule(autoRoutine());
    }
    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
    }

    @Override
    public void stop() {
        OpModeStorage.autonomousEndPose = follower.pose(); //saves your position in that file
    }



}
