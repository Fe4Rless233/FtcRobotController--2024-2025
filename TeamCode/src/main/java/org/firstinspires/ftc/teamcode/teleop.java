package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="teleop")
public class teleop extends OpMode {

    // Define all the motors for the robot
    DcMotor LeftFront;
    DcMotor RightFront;
    DcMotor LeftBack;
    DcMotor RightBack;

    @Override
    public void init() {
        // Initialize the motors from the hardware map
        LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        RightFront = hardwareMap.get(DcMotor.class, "RightFront");
        LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        RightBack = hardwareMap.get(DcMotor.class, "RightBack");

        // Set the direction for the right motors to match the left motors
        // This is a common setup for Mecanum drives
        RightFront.setDirection(DcMotor.Direction.REVERSE);
        RightBack.setDirection(DcMotor.Direction.REVERSE);

        // Add a telemetry message to show that initialization is complete
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Get the joystick input values
        double drive = -gamepad1.left_stick_y;  // Forward/Backward motion
        double strafe = gamepad1.left_stick_x;  // Strafe left/right motion
        double turn = gamepad1.right_stick_x;   // Turning motion

        // Calculate the power for each motor
        // This formula combines the drive, strafe, and turn values to control the mecanum wheels
        double leftFrontPower = drive + strafe + turn;
        double rightFrontPower = drive - strafe - turn;
        double leftBackPower = drive - strafe + turn;
        double rightBackPower = drive + strafe - turn;

        // Set the power for each motor
        LeftFront.setPower(leftFrontPower);
        RightFront.setPower(rightFrontPower);
        LeftBack.setPower(leftBackPower);
        RightBack.setPower(rightBackPower);
    }

    @Override
    public void stop() {
        // Stop all motors when the program is stopped
        LeftFront.setPower(0);
        RightFront.setPower(0);
        LeftBack.setPower(0);
        RightBack.setPower(0);
    }
}