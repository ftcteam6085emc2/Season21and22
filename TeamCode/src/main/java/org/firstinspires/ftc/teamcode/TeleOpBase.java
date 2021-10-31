package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.*;

@TeleOp(name = "TeleOpBase", group = "Test")
public class TeleOpBase extends OpMode {

    HWMap robot = new HWMap();
    boolean startCheck = true;
    int start = 0;

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.RearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.RearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.spin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.Dump.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        robot.FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.spin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Dump.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.spin.setTargetPosition(0);
        robot.Dump.setTargetPosition(0);
        robot.spin.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.Dump.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void loop() {
        robot.FrontRight.setVelocity(2700 * (-gamepad1.right_stick_y - gamepad1.right_stick_x));
        robot.FrontLeft.setVelocity(2700 * (gamepad1.left_stick_y - gamepad1.left_stick_x));
        robot.RearRight.setVelocity(2700 * (-gamepad1.right_stick_y + gamepad1.right_stick_x));
        robot.RearLeft.setVelocity(2700 * (gamepad1.left_stick_y + gamepad1.left_stick_x));

        /*if(gamepad1.a) {
            robot.spin.setVelocity(2700);
        }else if(gamepad1.b){
            robot.Dump.setVelocity(2700);
        }else if(gamepad1.y) {
            robot.spin.setVelocity(-2700);
        }else if(gamepad1.x) {
            robot.Dump.setVelocity(-2700);
        }else{
            robot.spin.setVelocity(0);
            robot.Dump.setVelocity(0);
        }*/

        if(gamepad1.a){
            spin(0);
        }
        if(gamepad1.b){
            spin(-500);
        }
        if(gamepad1.x) {
            spin(-1000);
        }

        if(gamepad1.dpad_left){
            dump(0);
        }
        if(gamepad1.dpad_up){
            dump(-300);
        }
        if(gamepad1.dpad_right) {
            dump(500);
        }

        if (gamepad1.start && startCheck && start == 0) {
            robot.FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.RearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.RearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            start++;
            startCheck = false;
        } else if (gamepad1.start && startCheck && start == 1) {
            robot.FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            robot.FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            robot.RearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            robot.RearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            start--;
            startCheck = false;
        } else if (!gamepad1.start) {
            startCheck = true;
        }
    }

    private void spin(int position) {
        robot.spin.setTargetPosition(position);

        robot.spin.setPower(1);
        while (robot.spin.isBusy()){}
        robot.spin.setPower(0);
    }

    private void dump(int position) {
        robot.Dump.setTargetPosition(position);

        robot.Dump.setPower(1);
        while (robot.Dump.isBusy()){}
        robot.Dump.setPower(0);
    }
}