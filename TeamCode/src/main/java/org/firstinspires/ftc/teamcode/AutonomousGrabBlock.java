package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "Grab Block then Strafe Left")
public class AutonomousGrabBlock extends LinearOpMode {

    //Wheels
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    //Lift motors
    public DcMotor liftOne;
    public DcMotor liftTwo;

    public Servo intake;

    //For encoders to track movement
    private double ANDYMARK_TICKS_PER_IN = 1120/(4*Math.PI);
    private double TORQUENADO_TICKS_PER_IN = 1440/(2/Math.PI);

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

        intake.setPosition(0);

        //Drive straight
        frontLeft.setPower(.6);
        frontRight.setPower(.6);
        backLeft.setPower(.6);
        backRight.setPower(.6);
        sleep(950);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);

        //Lower intake
        liftTwo.setPower(-1);
        sleep(330);
        liftTwo.setPower(0);

        //Grab block
        sleep(800);
        intake.setPosition(0.3);
        sleep(800);
        liftTwo.setPower(1);
        sleep(150);
        liftTwo.setPower(0);

        //Back up
        frontLeft.setPower(-.6);
        frontRight.setPower(-.6);
        backLeft.setPower(-.6);
        backRight.setPower(-.6);
        sleep(1150);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);

        //Left
        frontLeft.setPower(-.6);
        frontRight.setPower(.6);
        backLeft.setPower(.6);
        backRight.setPower(-.6);
        sleep(2300);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);

        sleep(300);

        intake.setPosition(0);

        sleep(300);

        //Right
        frontLeft.setPower(.6);
        frontRight.setPower(-.6);
        backLeft.setPower(-.6);
        backRight.setPower(.6);
        sleep(900);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
    }
}
