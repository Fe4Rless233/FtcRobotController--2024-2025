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

        LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        RightFront = hardwareMap.get(DcMotor.class, "RightFront");
        LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        RightBack = hardwareMap.get(DcMotor.class, "RightBack");

        // Set the direction for the right-side motors so they spin in the correct orientation.
        RightFront.setDirection(DcMotor.Direction.REVERSE);
        RightBack.setDirection(DcMotor.Direction.REVERSE);

        runTimer = new ElapsedTime();

        waitForStart();
        runTimer.reset();

        MoveForwardLeft(1, 2000);
        StopMove();

        MoveForward(1, 1000);
        StopMove();

        MoveBackward(1, 1000);
        StopMove();

        TurnRight(1, 1000);
        StopMove();

        TurnLeft(1, 1000);
        StopMove();

        StrafeRight(1, 1500);
        StopMove();

        StrafeLeft(1, 1500);
        StopMove();

        MoveForwardRight(1, 2000);
        StopMove();

        MoveBackwardLeft(1, 2000);
        StopMove();

        MoveBackwardRight(1, 2000);
        StopMove();

        telemetry.update();
    }

    public void MoveForward(double power, long time) {
        // All motors spin forward to move the robot forward.
        LeftFront.setPower(power);
        LeftBack.setPower(power);
        RightFront.setPower(power);
        RightBack.setPower(power);
        sleep(time);
    }

    public void MoveBackward(double power, long time) {
        // All motors spin backward to move the robot backward.
        LeftFront.setPower(-power);
        LeftBack.setPower(-power);
        RightFront.setPower(-power);
        RightBack.setPower(-power);
        sleep(time);
    }

    public void TurnRight(double power, long time) {
        // Right motors spin backward, left motors spin forward.
        LeftFront.setPower(power);
        LeftBack.setPower(power);
        RightFront.setPower(-power);
        RightBack.setPower(-power);
        sleep(time);
    }

    public void TurnLeft(double power, long time) {
        // Left motors spin backward, right motors spin forward.
        LeftFront.setPower(-power);
        LeftBack.setPower(-power);
        RightFront.setPower(power);
        RightBack.setPower(power);
        sleep(time);
    }

    public void StrafeRight(double power, long time) {
        // The diagonal motors spin in opposite directions to create sideways thrust.
        LeftFront.setPower(power);
        LeftBack.setPower(-power);
        RightFront.setPower(-power);
        RightBack.setPower(power);
        sleep(time);
    }

    public void StrafeLeft(double power, long time) {
        // The diagonal motors spin in opposite directions to create sideways thrust.
        LeftFront.setPower(-power);
        LeftBack.setPower(power);
        RightFront.setPower(power);
        RightBack.setPower(-power);
        sleep(time);
    }

    public void MoveForwardLeft(double power, long time) {
        // The front-right and back-left motors spin forward.
        LeftFront.setPower(0);
        LeftBack.setPower(power);
        RightFront.setPower(power);
        RightBack.setPower(0);
        sleep(time);
    }

    public void MoveForwardRight(double power, long time) {
        // The front-left and back-right motors spin forward.
        LeftFront.setPower(power);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(power);
        sleep(time);
    }

    public void MoveBackwardLeft(double power, long time) {
        // The front-left and back-right motors spin backward.
        LeftFront.setPower(-power);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(-power);
        sleep(time);
    }

    public void MoveBackwardRight(double power, long time) {
        // The front-right and back-left motors spin backward.
        LeftFront.setPower(0);
        LeftBack.setPower(-power);
        RightFront.setPower(-power);
        RightBack.setPower(0);
        sleep(time);
    }

    public void StopMove() {
        LeftFront.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(0);
    }
}