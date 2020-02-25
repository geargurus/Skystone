package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Grab Block 1 Blue")
public class Trial_Run extends LinearOpMode {


    private ElapsedTime runTime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1120; //encoders
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 3.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    DcMotor frontRight; // declare your motors
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;

    DcMotor liftOne;
    DcMotor liftTwo;

    Servo intake;




    public void runOpMode() throws InterruptedException {

        frontRight = hardwareMap.dcMotor.get("frontRight"); // initialize
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        liftOne = hardwareMap.dcMotor.get("liftOne");
        liftTwo = hardwareMap.dcMotor.get("liftTwo");
        intake = hardwareMap.servo.get("intake");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status" , "Resetting Encoders"); //add caption by using parenthesis
        telemetry.update(); // mode

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // so you can stop and decide to not use encoders

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftOne.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("frontLeft", frontLeft.getCurrentPosition()); //positioning
        telemetry.addData("frontRight", frontRight.getCurrentPosition());
        telemetry.addData("backLeft", backLeft.getCurrentPosition());
        telemetry.addData("backRight", backRight.getCurrentPosition());
        telemetry.addData("liftOne", liftOne.getCurrentPosition());
        telemetry.addData("liftTwo", liftTwo.getCurrentPosition());
        telemetry.addData("servo", intake.getPosition());
        telemetry.update();


        public void drive(double speed,
                          double backLeftInches, double backRightInches,
                          double frontLeftInches, double frontRightInches,
                          double liftOneInches, double liftTwoInches,
                          double timeouts)
        {
            int newBackLeftTarget;
            int newBackRightTarget;
            int newFrontLeftTarget;
            int newFrontRightTarget;
            int newLiftOneTarget;
            int newLiftTwoTarget;


                if (opModeIsActive()){
                    //Determine's new target's value
                    newBackLeftTarget = backLeft.getCurrentPosition() + (int)(backLeftInches * COUNTS_PER_INCH);
                    newBackRightTarget = backRight.getCurrentPosition() + (int)(backRightInches * COUNTS_PER_INCH);
                    newFrontLeftTarget = frontLeft.getCurrentPosition() + (int)(frontLeftInches * COUNTS_PER_INCH);
                    newFrontRightTarget = frontRight.getCurrentPosition() + (int)(frontRightInches * COUNTS_PER_INCH);
                    newLiftOneTarget = liftOne.getCurrentPosition() + (int)(liftOneInches * COUNTS_PER_INCH);
                    newLiftTwoTarget = liftTwo.getCurrentPosition() + (int)(liftTwoInches * COUNTS_PER_INCH);
                    backLeft.setTargetPosition(newBackLeftTarget);     //gives power to int
                    backRight.setTargetPosition(newBackRightTarget);
                    frontLeft.setTargetPosition(newFrontLeftTarget);
                    frontRight.setTargetPosition(newFrontRightTarget);
                    liftOne.setTargetPosition(newLiftOneTarget);
                    liftTwo.setTargetPosition(newLiftTwoTarget);

                    //Turn on run to position to move the desired distance
                    backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    liftOne.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    liftTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                    // reset the timeout time and start motion, stops the robot for a certain amount of time
                    runTime.reset();
                    backLeft.setPower(Math.abs(speed));
                    backRight.setPower(Math.abs(speed));
                    frontLeft.setPower(Math.abs(speed));
                    frontRight.setPower(Math.abs(speed));
                    liftOne.setPower(Math.abs(speed));
                    liftTwo.setPower(Math.abs(speed));

                    while (opModeIsActive() &&  (runTime.seconds() < timeouts) && (backLeft.isBusy()  && backRight.isBusy() && frontLeft.isBusy() && frontRight.isBusy()))
                    {

                }

        }

    }
}
