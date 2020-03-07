package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ServiceConfigurationError;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")

public class TeleOpV2 extends OpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor liftOne;
    public DcMotor liftTwo;

    public Servo intake;
    public Servo arm;

    public double driveSpeed = 1.0;
    public double strafeSpeed = 1.0;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        liftOne = hardwareMap.dcMotor.get("liftOne");
        liftTwo = hardwareMap.dcMotor.get("liftTwo");

        intake = hardwareMap.servo.get("intake");
        arm = hardwareMap.servo.get("arm");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        if(gamepad1.right_bumper && gamepad1.left_bumper) {
            strafeSpeed = 0.25;
            driveSpeed = 0.4;
        } else {
            strafeSpeed = 0.5;
            driveSpeed = 0.8;
        }

        if (gamepad1.dpad_left) {
            frontRight.setPower(-strafeSpeed);
            backRight.setPower(strafeSpeed);
            frontLeft.setPower(strafeSpeed);
            backLeft.setPower(-strafeSpeed);
        } else if (gamepad1.dpad_right) {
            frontLeft.setPower(-strafeSpeed);
            backLeft.setPower(strafeSpeed);
            frontRight.setPower(strafeSpeed);
            backRight.setPower(-strafeSpeed);
        } else {
            frontLeft.setPower(driveSpeed * gamepad1.left_stick_y);
            frontRight.setPower(driveSpeed * gamepad1.right_stick_y);
            backLeft.setPower(driveSpeed * gamepad1.left_stick_y);
            backRight.setPower(driveSpeed * gamepad1.right_stick_y);
        }

        //Bring Lift up
        if(gamepad2.dpad_up){
            liftOne.setPower(1);
        } else
        if(gamepad2.dpad_down) {
            liftOne.setPower(-1);
        } else {
            liftOne.setPower(0);
        }

        //Bring Intake Up
        if(gamepad2.a){
            liftTwo.setPower(-1);
        } else
        if(gamepad2.b) {
            liftTwo.setPower(1);
        } else {
            liftTwo.setPower(0);
        }

        //Intake
        if(Math.abs(gamepad2.right_trigger) > 0.1) {
            intake.setPosition(0.4);
        } else {
            intake.setPosition(0);
        }

        //Arm
        if(Math.abs(gamepad2.left_trigger) > 0.1) {
            arm.setPosition(0.6);
        } else {
            arm.setPosition(0);
        }

        telemetry.addData("Drawer Slides Pos", liftOne.getCurrentPosition());
        telemetry.addData("Intake Pos", liftTwo.getCurrentPosition());
        telemetry.addData("Front Left", frontLeft.getCurrentPosition());
        telemetry.addData("Front Right", frontRight.getCurrentPosition());
        telemetry.addData("Back Left", backLeft.getCurrentPosition());
        telemetry.addData("Back Right", backRight.getCurrentPosition());
        telemetry.addData("FJKALF", gamepad1.left_stick_y);
        telemetry.addData("arm", gamepad2.left_stick_x);
        telemetry.update();
    }
}