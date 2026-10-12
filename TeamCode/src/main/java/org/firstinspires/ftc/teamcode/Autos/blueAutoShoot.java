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

    private final Pose start = poseFactory.of(85.6447, 133.4902, 90);
    private final Pose path1Start = poseFactory.of(85.6447, 133.4902, 270);
    private final Pose path1 = poseFactory.of(86.2549, 111.4046, 270);
    private final Pose point2 = poseFactory.of(113.1678, 110.5313, -1.8587);
    private final Pose point3 = poseFactory.of(130.3569, 30.5707, 102.1323);

    public Path path1() {
        return Paths.line(path1Start, path1).linear(path1Start, path1);
    }

    public Path path2() {
        return Paths.line(path1, point2).tangent();
    }

    public Path path3() {
        return Paths.line(point2, point3).reverseTangent();
    }
    private Command autoRoutine() {
        return sequential(

                instant(() -> shooter.setVelocity(2000)),
                waitMs(3000),
                instant(() -> shooter.setVelocity(0)),


                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3()));
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
