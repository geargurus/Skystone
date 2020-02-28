package org.firstinspires.ftc.teamcode.Regionals;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "Sampleee")
public class Sampleee extends LinearOpMode { // extends LinearOpMode
    // over it implement methods

    DcMotor frontLeft , frontRight , backLeft , backRight, liftOne , liftTwo; // claim your motors outside under public class
    Servo intake, arm;
    double TICKS_PER_IN = 1120/(4*Math.PI);
    int tickGoal;

    @Override
    public void runOpMode() throws InterruptedException {

        frontRight = hardwareMap.dcMotor.get("frontRight"); // initialize
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        liftOne = hardwareMap.dcMotor.get("liftOne");
        liftTwo = hardwareMap.dcMotor.get("liftTwo");
        intake = hardwareMap.servo.get("intake");
        arm = hardwareMap.servo.get("arm");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        //write code;

        forward(12, 0.75);
        strafeLeft(4, 0.75);
        turnRight(3, 0.5);

        liftUp(400);
        liftDown(400);

        intakeGrab();
        armGrab();



    }

    public void forward(double inches, double power) {
        drive(inches, power, power);
    }

    public void backward(double inches, double power) {
        drive(-inches, power, power);
    }

    public void turnLeft(double inches, double power) {
        driveLeft(inches, power);
    }

    public void turnRight(double inches, double power) {
        driveRight(inches, power);
    }

    public void stopBase() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void strafeRight(double inches, double power) {
        driveStrafe(inches, power);
    }

    public void strafeLeft(double inches, double power) {
        driveStrafe(-inches, power);
    }

    public void liftUp(long time) {
        liftOne.setPower(1);
        sleep(time);
        liftOne.setPower(0);
    }

    public void liftDown(long time) {
        liftOne.setPower(-1);
        sleep(time);
        liftOne.setPower(0);
    }

    public void intakeUp(long time) {
        liftTwo.setPower(1);
        sleep(time);
        liftTwo.setPower(0);
    }

    public void intakeDown(long time) {
        liftTwo.setPower(-1);
        sleep(time);
        liftTwo.setPower(0);
    }

    public void intakeGrab() {
        intake.setPosition(0.3);
    }

    public void intakeRelease() {
        intake.setPosition(0);
    }

    public void armGrab() {
        arm.setPosition(0.3);
    }

    public void armRelease() {
        arm.setPosition(0);
    }

    public void drive(double inches, double leftPower, double rightPower) {
        tickGoal = (int) (TICKS_PER_IN * inches);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setTargetPosition(tickGoal);
        frontLeft.setTargetPosition(tickGoal);
        backRight.setTargetPosition(tickGoal);
        frontLeft.setTargetPosition(tickGoal);

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backLeft.setPower(leftPower);
        backRight.setPower(rightPower);

        while (frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy()) {
            telemetry.addData("TickGoal", tickGoal);
            telemetry.addData("fL", frontLeft.getCurrentPosition());
            telemetry.addData("fR", frontRight.getCurrentPosition());
            telemetry.addData("bL", backLeft.getCurrentPosition());
            telemetry.addData("bR", backRight.getCurrentPosition());
            telemetry.update();
        }

        stopBase();

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveStrafe(double inches, double power) {
        tickGoal = (int) (TICKS_PER_IN * inches);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setTargetPosition(tickGoal);
        frontLeft.setTargetPosition(tickGoal);
        backRight.setTargetPosition(tickGoal);
        frontLeft.setTargetPosition(tickGoal);

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        while (frontLeft.isBusy() || frontRight.isBusy() || backLeft.isBusy() || backRight.isBusy()) {
            telemetry.addData("TickGoal", tickGoal);
            telemetry.addData("fL", frontLeft.getCurrentPosition());
            telemetry.addData("fR", frontRight.getCurrentPosition());
            telemetry.addData("bL", backLeft.getCurrentPosition());
            telemetry.addData("bR", backRight.getCurrentPosition());
            telemetry.update();
        }

        stopBase();

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveRight(double inches, double power) {
        tickGoal = (int) (TICKS_PER_IN * inches);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setTargetPosition(-tickGoal);
        frontLeft.setTargetPosition(tickGoal);
        backRight.setTargetPosition(-tickGoal);
        frontLeft.setTargetPosition(tickGoal);

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        while (frontLeft.isBusy()  /*frontRight.isBusy()*/ || backLeft.isBusy() /*backRight.isBusy()*/) {
            telemetry.addData("TickGoal", tickGoal);
            telemetry.addData("fL", frontLeft.getCurrentPosition());
            telemetry.addData("fR", frontRight.getCurrentPosition());
            telemetry.addData("bL", backLeft.getCurrentPosition());
            telemetry.addData("bR", backRight.getCurrentPosition());
            telemetry.update();
        }

        stopBase();

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveLeft(double inches, double power) {
        tickGoal = (int) (TICKS_PER_IN * inches);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setTargetPosition(tickGoal);
        frontLeft.setTargetPosition(-tickGoal);
        backRight.setTargetPosition(tickGoal);
        backLeft.setTargetPosition(-tickGoal);

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(power);

        while (/*frontLeft.isBusy()*/  frontRight.isBusy()  /*backLeft.isBusy()*/ || backRight.isBusy()) {
            telemetry.addData("TickGoal", tickGoal);
            telemetry.addData("fL", frontLeft.getCurrentPosition());
            telemetry.addData("fR", frontRight.getCurrentPosition());
            telemetry.addData("bL", backLeft.getCurrentPosition());
            telemetry.addData("bR", backRight.getCurrentPosition());
            telemetry.update();
        }

        stopBase();

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
