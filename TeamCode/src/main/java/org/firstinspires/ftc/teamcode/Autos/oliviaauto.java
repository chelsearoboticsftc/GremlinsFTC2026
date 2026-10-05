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
public class oliviaauto extends OpMode {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(56, 36, 180);
    private final Pose point2 = poseFactory.of(109.6405, 36.6338, -179.323);
    private final Pose point3 = poseFactory.of(110.0512, 77.882, -90.5705);
    private final Pose point4 = poseFactory.of(108.2733, 115.896, -87.3222);
    private final Pose point5 = poseFactory.of(69.028, 116.1553, -0.3786);
    private final Pose point6 = poseFactory.of(20.9068, 115.9783, 0.2108);
    private final Pose point7 = poseFactory.of(21.0839, 44.9099, 90.1427);
    private final Pose point8 = poseFactory.of(68.4363, 45.7096, -179.0325);

    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(path1, point2).reverseTangent();
    }

    public Path path3() {
        return Paths.line(point2, point3).reverseTangent();
    }

    public Path path4() {
        return Paths.line(point3, point4).reverseTangent();
    }

    public Path path5() {
        return Paths.line(point4, point5).reverseTangent();
    }

    public Path path6() {
        return Paths.line(point5, point6).reverseTangent();
    }

    public Path path7() {
        return Paths.line(point6, point7).reverseTangent();
    }

    public Path path8() {
        return Paths.line(point7, point8).reverseTangent();
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3()),
                follow(follower, path4()),
                follow(follower, path5()),
                follow(follower, path6()),
                follow(follower, path7())

                // Addmechanism commands here.

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
