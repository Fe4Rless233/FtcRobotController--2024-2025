package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="auto")
public class auto extends LinearOpMode {
    private ElapsedTime runTimer;
    DcMotor LeftFront, RightFront, LeftBack, RightBack;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize the hardware variables
        LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        RightFront = hardwareMap.get(DcMotor.class, "RightFront");
        LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        RightBack = hardwareMap.get(DcMotor.class, "RightBack");

        // Initialize the timer
        runTimer = new ElapsedTime();

        // Wait for the start button to be pressed
        waitForStart();
        runTimer.reset();

        // Move the robot forward
        MoveForward(1, 5000);
        StopMove();


        telemetry.update();
    }

    public void MoveForward(double power, long time) {
        LeftFront.setPower(-power);
//        LeftBack.setPower(power);
        RightFront.setPower(power);
//        RightBack.setPower(power);

        sleep(time);
    }

    public void MoveBackward(double power, long time) {
        LeftFront.setPower(-power);
        LeftBack.setPower(-power);
        RightFront.setPower(-power);
        RightBack.setPower(-power);

        sleep(time);
    }

    public void StopMove() {
        LeftFront.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(0);
    }

    public void TurnRight(double power, long time) {
        LeftFront.setPower(power);
        LeftBack.setPower(power);
        RightFront.setPower(-power);
        RightBack.setPower(-power);
        sleep(time);
    }

    public void TurnLeft(double power, long time) {
        LeftFront.setPower(-power);
        LeftBack.setPower(-power);
        RightFront.setPower(power);
        RightBack.setPower(power);
        sleep(time);
    }

    public void StrafeRight(double power, long time) {
        LeftFront.setPower(power);
        LeftBack.setPower(-power);
        RightFront.setPower(-power);
        RightBack.setPower(power);

        sleep(time);
    }

    public void StrafeLeft(double power, long time) {
        LeftFront.setPower(-power);
        LeftBack.setPower(power);
        RightFront.setPower(power);
        RightBack.setPower(-power);

        sleep(time);
    }
}
