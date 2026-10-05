package org.firstinspires.ftc.teamcode.Autos;

import com.pedropathing.api.Paths;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import org.firstinspires.ftc.teamcode.OpModeStorage;
import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class janeAuto extends OpMode {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(70.9058, 22.4859, 90);
    private final Pose path1Start = poseFactory.of(70.9058, 22.4859, 0);
    private final Pose path1 = poseFactory.of(118.9822, 69.8004, 90);
    private final Pose path1Control1 = poseFactory.of(115.27, 25.9295, 0);
    private final Pose point2 = poseFactory.of(70.855, 117.6083, 175.3125);
    private final Pose point2Control1 = poseFactory.of(114.5226, 114.2493, 0);
    private final Pose point3 = poseFactory.of(23.1387, 69.3702, -95.8806);
    private final Pose point3Control1 = poseFactory.of(27.5638, 114.4295, 0);
    private final Pose point4 = poseFactory.of(70.855, 23.135, -4.5605);
    private final Pose point4Control1 = poseFactory.of(27.0512, 26.4147, 0);

    public Path path1() {
        return Paths.curve(path1Start, path1Control1, path1).linear(path1Start, path1);
    }

    public Path path2() {
        return Paths.curve(path1, point2Control1, point2).tangent();
    }

    public Path path3() {
        return Paths.curve(point2, point3Control1, point3).tangent();
    }

    public Path path4() {
        return Paths.curve(point3, point4Control1, point4).tangent();
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3()),
                follow(follower, path4())
                // Add mechanism commands here.
        );
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);//sets the starting point of your Robot
        follower.update();
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
