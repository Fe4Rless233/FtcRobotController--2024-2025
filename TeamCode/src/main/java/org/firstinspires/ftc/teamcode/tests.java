package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp(name = "test")
public class tests extends OpMode {
    DcMotor motor;
    @Override
    public void init()
    {
        telemetry.addData("Initialization","is a sucess");
        telemetry.update();
    }
    @Override
    public void loop()
    {

    }
}
