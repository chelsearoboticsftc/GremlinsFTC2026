package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class intakeflowerSubsystem {
    Servo leftServo;
    Servo rightServo;

    public intakeflowerSubsystem(HardwareMap hardwareMap) {

        this.leftServo = hardwareMap.get(Servo.class, "leftServo");
        this.rightServo = hardwareMap.get(Servo.class, "rightServo");

     
    }

    public void setPostition(double postition) {
        leftServo.setPosition(postition);
        rightServo.setPosition(postition);

    }
}
