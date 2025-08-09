package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="teleop")
public class teleop extends OpMode {

    // Define all the motors for the robot
    DcMotor LeftFront;
    DcMotor RightFront;
    DcMotor LeftBack;
    DcMotor RightBack;

    // Define all the servos for the robot
    Servo arm;
    Servo claw;

    // This variable holds the arm's current position.
    double currentArmPosition;
    // This variable holds the arm's target position.
    double targetArmPosition;

    // This is the speed at which the arm moves per loop cycle.
    // You can tune this value to be faster or slower. A smaller value is slower.
    final double ARM_SPEED = 0.01;

    // Define servo positions - The values have been swapped here.
    final double ARM_UP_POSITION = 0.15;
    final double ARM_DOWN_POSITION = 0.96;
    final double CLAW_OPEN_POSITION = 1.0;
    final double CLAW_CLOSED_POSITION = 0.0;


    @Override
    public void init() {
        // Initialize the motors from the hardware map
        LeftFront = hardwareMap.get(DcMotor.class, "LF");
        RightFront = hardwareMap.get(DcMotor.class, "RF");
        LeftBack = hardwareMap.get(DcMotor.class, "LB");
        RightBack = hardwareMap.get(DcMotor.class, "RB");

        // Initialize the servos from the hardware map
        arm = hardwareMap.get(Servo.class, "arm");
        claw = hardwareMap.get(Servo.class, "claw");

        // Set the arm to the down position at the start of the program
        targetArmPosition = ARM_DOWN_POSITION;
        currentArmPosition = ARM_DOWN_POSITION;

        // Set the direction for the right motors to match the left motors
        RightFront.setDirection(DcMotor.Direction.REVERSE);
        RightBack.setDirection(DcMotor.Direction.REVERSE);

        // Set Zero Power Behavior to BRAKE
        // This causes the motors to actively stop instead of coasting when power is zero.
        LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Add a telemetry message to show that initialization is complete
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // --- DRIVETRAIN CONTROLS ---
        double drive = gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        // CORRECTED: The negative sign inverts the turning controls to match expectations.
        double turn = -gamepad1.right_stick_x;

        // Calculate the power for each motor
        double leftFrontPower = drive + strafe + turn;
        double rightFrontPower = drive - strafe - turn;
        double leftBackPower = drive - strafe + turn;
        double rightBackPower = drive + strafe - turn;

        // Normalize the motor powers to prevent drift
        // This ensures that the motor powers are scaled proportionally if any of them
        // exceed 1.0, maintaining the correct direction of movement.
        double denominator = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        denominator = Math.max(denominator, Math.abs(leftBackPower));
        denominator = Math.max(denominator, Math.abs(rightBackPower));

        if (denominator > 1.0) {
            leftFrontPower /= denominator;
            rightFrontPower /= denominator;
            leftBackPower /= denominator;
            rightBackPower /= denominator;
        }

        // Set the power for each motor
        LeftFront.setPower(leftFrontPower);
        RightFront.setPower(rightFrontPower);
        LeftBack.setPower(leftBackPower);
        RightBack.setPower(rightBackPower);

        // --- SERVO CONTROLS ---

        // Use Y and X buttons to set the *target* position for the arm.
        // You only need to tap the button once.
        if (gamepad1.y) {
            targetArmPosition = ARM_UP_POSITION;
            // Close the claw automatically when the arm goes up.
            claw.setPosition(CLAW_CLOSED_POSITION);
        } else if (gamepad1.x) {
            targetArmPosition = ARM_DOWN_POSITION;
            // Open the claw automatically when the arm goes down.
            claw.setPosition(CLAW_OPEN_POSITION);
        }

        // Move the arm towards the target position incrementally.
        if (Math.abs(currentArmPosition - targetArmPosition) > 0.01) {
            if (currentArmPosition < targetArmPosition) {
                currentArmPosition += ARM_SPEED;
            } else {
                currentArmPosition -= ARM_SPEED;
            }
        }

        // Clip the position to make sure it doesn't go past the limits
        currentArmPosition = Range.clip(currentArmPosition, ARM_UP_POSITION, ARM_DOWN_POSITION);

        // Set the servo's position in every loop
        arm.setPosition(currentArmPosition);


        // Use B and A buttons to open and close the claw (this logic is for manual override)
        if (gamepad1.b) {
            claw.setPosition(CLAW_OPEN_POSITION);
        } else if (gamepad1.a) {
            claw.setPosition(CLAW_CLOSED_POSITION);
        }

        // Add telemetry for servo positions
        telemetry.addData("Arm Target Position", targetArmPosition);
        telemetry.addData("Arm Current Position", currentArmPosition);
        telemetry.addData("Claw Position", claw.getPosition());
        telemetry.update();
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
