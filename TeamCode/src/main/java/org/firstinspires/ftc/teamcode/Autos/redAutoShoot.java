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
import org.firstinspires.ftc.teamcode.Subsystems.shooterSubsystem;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class redAutoShoot extends OpMode {

    private Follower follower;

    private shooterSubsystem shooter;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(46.62, 8.7559, 90);
    private final Pose path1Start = poseFactory.of(46.62, 8.7559, 90);
    private final Pose path1 = poseFactory.of(5.8659, 103.9387, 0);
    private final Pose path1Control1 = poseFactory.of(4.3129, 28.2583, 0);
    private final Pose path1Control2 = poseFactory.of(52.1457, 89.3146, 0);

    public Path path1() {
        return Paths.line(start,
                path1).linear(start, path1);
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
