package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "Foundation Blue Side")
public class Autonomous extends LinearOpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor liftOne;
    public DcMotor liftTwo;

    public Servo intake;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        liftOne = hardwareMap.dcMotor.get("liftOne");
        liftTwo = hardwareMap.dcMotor.get("liftTwo");

        intake = hardwareMap.servo.get("intake");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        //Strafe left
        frontLeft.setPower(-.6);
        frontRight.setPower(.6);
        backLeft.setPower(.5);
        backRight.setPower(-.5);
        sleep(1550);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);

        //Drawer slides up
        liftOne.setPower(1);
        sleep(400);
        liftOne.setPower(0);

        //Forwards
        frontLeft.setPower(.5);
        frontRight.setPower(.6);
        backLeft.setPower(.5);
        backRight.setPower(.6);
        sleep(1800);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);

        //Drawer slides down
        liftOne.setPower(-1);
        sleep(400);
        liftOne.setPower(0);

        //Back
        frontLeft.setPower(-.4);
        frontRight.setPower(-.7);
        backLeft.setPower(-.4);
        backRight.setPower(-.7);
        sleep(5000);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);

        sleep(300);

        //Drawer slides up
        liftOne.setPower(1);
        sleep(600);
        liftOne.setPower(0);

        //Strafe right
        frontLeft.setPower(.6);
        frontRight.setPower(-.6);
        backLeft.setPower(-.6);
        backRight.setPower(.6);
        sleep(1200);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);

        //Drawer slides down
        liftOne.setPower(-1);
        sleep(400);
        liftOne.setPower(0);

        //Intake down
        liftTwo.setPower(-1);
        sleep(400);
        liftTwo.setPower(0);

        //Strafe right
        frontLeft.setPower(.6);
        frontRight.setPower(-.6);
        backLeft.setPower(-.5);
        backRight.setPower(.5);
        sleep(1000);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);

    }
}
