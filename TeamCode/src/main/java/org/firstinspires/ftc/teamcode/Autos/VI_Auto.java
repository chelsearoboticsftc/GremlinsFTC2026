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
public class VI_Auto extends OpMode {

    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose heartstart = poseFactory.of(72.98, 5.9786, 90);
    private final Pose path1 = poseFactory.of(15.1671, 70.3643, 180);
    private final Pose path1Control1 = poseFactory.of(19.8043, 35.23, 0);
    private final Pose point2 = poseFactory.of(38.8257, 109.4714, -169.7394);
    private final Pose point2Control1 = poseFactory.of(16.57, 105.6186, 0);
    private final Pose point3 = poseFactory.of(73.58, 88.0501, 114.8332);
    private final Pose point3Control1 = poseFactory.of(63.4486, 110.2143, 0);
    private final Pose point4 = poseFactory.of(106.2386, 109.2586, 179.7166);
    private final Pose point4Control1 = poseFactory.of(89.0671, 109.4514, 0);
    private final Pose point51 = poseFactory.of(130.6629, 72.8314, 93.3412);
    private final Pose point51Control1 = poseFactory.of(128.8129, 106.4486, 0);
    private final Pose point6 = poseFactory.of(75.6829, 6.9086, 38.6834);
    private final Pose point6Control1 = poseFactory.of(119.75, 42.0814, 0);

    public Path path1() {
        return Paths.curve(heartstart, path1Control1, path1).linear(heartstart, path1);
    }

    public Path path2() {
        return Paths.curve(path1, point2Control1, point2).reverseTangent();
    }

    public Path path3() {
        return Paths.curve(point2, point3Control1, point3).reverseTangent();
    }

    public Path path4() {
        return Paths.curve(point3, point4Control1, point4).reverseTangent();
    }

    public Path path51() {
        return Paths.curve(point4, point51Control1, point51).reverseTangent();
    }

    public Path path6() {
        return Paths.curve(point51, point6Control1, point6).reverseTangent();
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                // Add mechanism commands here.
                follow(follower, path2()),
                follow(follower, path3()),
                follow(follower, path4()),
                follow(follower, path51()),
                follow(follower, path6())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(path1);//sets the starting point of your Robot
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
