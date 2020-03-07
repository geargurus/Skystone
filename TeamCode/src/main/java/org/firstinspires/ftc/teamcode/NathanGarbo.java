package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
@Disabled
@Autonomous(name="GGTEST", group="XxX")
public class NathanGarbo extends LinearOpMode {
    /* Declare OpMode members. */

    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;

    public BNO055IMU imu;
    public double imuAngle;

    // For the encoders
    private ElapsedTime runtime = new ElapsedTime();

    static final double DRIVE_SPEED = 0.65;
    static final double TURN_SPEED = 0.5;

    private double TICKS_PER_IN = 1120/(4*Math.PI);

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */

        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d",

                frontLeft.getCurrentPosition(),
                frontRight.getCurrentPosition(),
                backLeft.getCurrentPosition(),
                backRight.getCurrentPosition()

        );

        telemetry.update();


        //The parameters for the IMU
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled = false;

        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);
        resetAngle();
        telemetry.addData("Heading", getHeading());
        telemetry.update();
        waitForStart();

        //forwardDrive
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        //verticalDrive(100, .5,.5);
        //returnToZero(.5);


		//Strafe Left
        telemetry.addData("Path", "Left Strafe");
        sleep(1000);
		frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
		backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
		frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
		backRight.setDirection(DcMotorSimple.Direction.REVERSE);

		horizontalDrive(25, .5, .5);
		returnToZero(1);

		//open grabby thingy

		//Forward Drive
        telemetry.addData("Path", "To Platform");
        sleep(1000);
		frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
		backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
		frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
		backRight.setDirection(DcMotorSimple.Direction.FORWARD);
		verticalDrive(30, .5,.5);
		returnToZero(1);

		//close grabby thingy

		//Backward Drive
        telemetry.addData("Path", "To Build Site");
        sleep(1000);
		frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
		backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
		frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
		backRight.setDirection(DcMotorSimple.Direction.REVERSE);
		verticalDrive(30, .5,.5);
		returnToZero(1);

		//Strafe Right
        telemetry.addData("Path", "To Line");
        sleep(1000);
		frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
		backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
		frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
		backRight.setDirection(DcMotorSimple.Direction.FORWARD);

		horizontalDrive(45, .5, .5);
		returnToZero(1);



        telemetry.addData("Path", "Complete");
        telemetry.update();

    }
    // getAngle() returns + when rotating counter clockwise (left) and - when rotating
    // clockwise (right).
	/*
	public void headingDriveVert(double leftPower, double rightPower/*
									//, int timeoutS
									)
	{
		//resetAngle();
		//runtime.reset();
		//while(opModeIsActive() && (runtime.seconds() < timeoutS))
		//{

		if(getHeading()>0)//if we veer leftward
		{
			if(rightPower<.5 && leftPower<.5)
			{
				leftPower+=.1;
			}
			else
			{
				rightPower-=.1;
			}
		}

		else if(getHeading()<0)//if we veer rightward
		{
			if(rightPower<.5 && leftPower<.5)
			{
				rightPower+=.1;
			}
			else
			{
				leftPower-=.1;
			}

		}

		telemetry.addData("Heading", getHeading());
		telemetry.addData("Left Power: ", leftPower);
		telemetry.addData("Right Power: ", rightPower);
		frontLeft.setPower(leftPower);
		frontRight.setPower(rightPower);
		backLeft.setPower(leftPower);
		backRight.setPower(rightPower);
		//}
	}
	*/
    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */

    /* (ticks (1120) * gear ratio (1/1))/(wheel diameter*pi)
     */
    //function that takes in a double for inches, left power, and right power
    public void verticalDrive(double inches, double leftPower, double rightPower)
    {
        telemetry.addData("VERTICAL", "D");
        resetAngle();
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //tells robot that the encoders are going to be used
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

		/*create two integers that use the current motor
		position, the inches given, and the calculation from motor
		ticks to inches in order to tell the motor how far to go on each side*/
        int leftTickGoal = (frontLeft.getCurrentPosition () + (int) (TICKS_PER_IN * inches));
        int rightTickGoal = frontRight.getCurrentPosition() + (int) (TICKS_PER_IN * inches);
        int leftTickGoalb = (backLeft.getCurrentPosition () + (int) (TICKS_PER_IN * inches));
        int rightTickGoalb = (backRight.getCurrentPosition() + (int) (TICKS_PER_IN * inches));

        //the current position is used that way the motors move every time

        /*sets the motors to the new goal created above*/
        frontLeft.setTargetPosition(leftTickGoal);
        frontRight.setTargetPosition(rightTickGoal);
        backLeft.setTargetPosition(leftTickGoalb);
        backRight.setTargetPosition(rightTickGoalb);

        /*tells the motors to run this loop until the new goal is achieved*/
        while( Math.abs(frontLeft.getCurrentPosition()) < leftTickGoal && Math.abs(frontRight.getCurrentPosition()) < rightTickGoal &&
                Math.abs(backLeft.getCurrentPosition()) < leftTickGoalb && Math.abs(backRight.getCurrentPosition()) < rightTickGoalb &&
                !isStopRequested())
        {
            telemetry.addData("VERTICAL", "D");

            //tells motors to go to the position set above
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            //sets left and right motors to power given
            //headingDriveVert(leftPower, rightPower);
            frontLeft.setPower(leftPower);
            frontRight.setPower(rightPower-.03);
            backLeft.setPower(leftPower-.02);
            backRight.setPower(rightPower);
            if(getHeading()<0)//if we veer leftward
            {
                if(rightPower<.5 && leftPower<.5)
                {
                    leftPower+=.01;
                    frontLeft.setPower(leftPower);
                    frontRight.setPower(rightPower-.03);
                    backLeft.setPower(leftPower-.02);
                    backRight.setPower(rightPower);
                }
                else
                {
                    rightPower-=.01;
                    frontLeft.setPower(leftPower);
                    frontRight.setPower(rightPower-.03);
                    backLeft.setPower(leftPower-.02);
                    backRight.setPower(rightPower);
                }
            }

            else if(getHeading()>0)//if we veer rightward
            {
                if(rightPower<.5 && leftPower<.5)
                {
                    rightPower+=.01;
                    frontLeft.setPower(leftPower);
                    frontRight.setPower(rightPower-.03);
                    backLeft.setPower(leftPower-.02);
                    backRight.setPower(rightPower);
                }
                else
                {
                    leftPower-=.01;
                    frontLeft.setPower(leftPower);
                    frontRight.setPower(rightPower-.03);
                    backLeft.setPower(leftPower-.02);
                    backRight.setPower(rightPower);
                }

            }
            //headingDriveVert(leftPower, rightPower);

            /*tells the phones to display the motor position while the function runs for user knowledge*/

            telemetry.addData("Heading", getHeading());
            telemetry.addData("Left Power: ", leftPower);
            telemetry.addData("Right Power: ", rightPower);
            telemetry.addData("LeftFront Enc ", frontLeft.getCurrentPosition());
            telemetry.addData("RightFront Enc ", frontRight.getCurrentPosition());
            telemetry.addData("LeftBack Enc ", backLeft.getCurrentPosition());
            telemetry.addData("RightBack Enc ", backRight.getCurrentPosition());
            updateTelemetry(telemetry);
            telemetry.update();
        }

        //tell all motors to stop once the required distance is reached
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(1000);

    }

    public void horizontalDrive(double inches, double frontPower, double backPower)
    {
        telemetry.addData("HORIZONTAL", "D");

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //tells robot that the encoders are going to be used
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

		/*create two integers that use the current motor
		position, the inches given, and the calculation from motor
		ticks to inches in order to tell the motor how far to go on each side*/
        int leftTickGoal = frontLeft.getCurrentPosition () + (int) (TICKS_PER_IN * inches);
        int rightTickGoal = frontRight.getCurrentPosition() + (int) (TICKS_PER_IN * inches);
        int leftTickGoalb = backLeft.getCurrentPosition () + (int) (TICKS_PER_IN * inches);
        int rightTickGoalb = backRight.getCurrentPosition() + (int) (TICKS_PER_IN * inches);

        //the current position is used that way the motors move every time

        /*sets the motors to the new goal created above*/
        frontLeft.setTargetPosition(leftTickGoal);
        frontRight.setTargetPosition(rightTickGoal);
        backLeft.setTargetPosition(leftTickGoalb);
        backRight.setTargetPosition(rightTickGoalb);

        /*tells the motors to run this loop until the new goal is achieved*/
        while( Math.abs(frontLeft.getCurrentPosition()) < leftTickGoal && Math.abs(frontRight.getCurrentPosition()) < rightTickGoal &&
                Math.abs(backLeft.getCurrentPosition()) < leftTickGoalb && Math.abs(backRight.getCurrentPosition()) < rightTickGoalb &&
                !isStopRequested())
        {
            //tells motors to go to the position set above
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            if(getHeading()<-.05)//if we veer leftward
            {
                if(frontPower<.5 && backPower<.5)
                {
                    backPower+=.01;
                    //sets left and right motors to power given
                    frontLeft.setPower(frontPower);
                    frontRight.setPower(frontPower-.03);
                    backLeft.setPower(backPower-.02);
                    backRight.setPower(backPower);
                }
                else
                {
                    frontPower-=.01;
                    //sets left and right motors to power given
                    frontLeft.setPower(frontPower);
                    frontRight.setPower(frontPower-.03);
                    backLeft.setPower(backPower-.02);
                    backRight.setPower(backPower);
                }
            }

            else if(getHeading()>.05)//if we veer rightward
            {
                if(frontPower<.5 && backPower<.5)
                {
                    frontPower+=.01;
                    //sets left and right motors to power given
                    frontLeft.setPower(frontPower);
                    frontRight.setPower(frontPower-.03);
                    backLeft.setPower(backPower-.02);
                    backRight.setPower(backPower);
                }
                else
                {
                    backPower-=.01;
                    //sets left and right motors to power given
                    frontLeft.setPower(frontPower);
                    frontRight.setPower(frontPower-.03);
                    backLeft.setPower(backPower-.02);
                    backRight.setPower(backPower);
                }

            }
            //sets left and right motors to power given
            frontLeft.setPower(frontPower);
            frontRight.setPower(frontPower-.03);
            backLeft.setPower(backPower-.02);
            backRight.setPower(backPower);

            /*tells the phones to display the motor position while the function runs for user knowledge*/

            telemetry.addData("Heading", getHeading());
            telemetry.addData("Left Power: ", frontPower);
            telemetry.addData("Right Power: ", backPower);
            telemetry.addData("LeftFront Enc ", frontLeft.getCurrentPosition());
            telemetry.addData("RightFront Enc ", frontRight.getCurrentPosition());
            telemetry.addData("LeftBack Enc ", backLeft.getCurrentPosition());
            telemetry.addData("RightBack Enc ", backRight.getCurrentPosition());
            updateTelemetry(telemetry);
            telemetry.update();
        }

        //tell all motors to stop once the required distance is reached
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(1000);

    }


    private void resetAngle()
    {
        imuAngle = 0;
    }

    public double getHeading() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX, AngleUnit.DEGREES);
        double heading = angles.firstAngle;
        return heading;
    }
    private void rotate(int degrees, double power)
    {
        double  leftPower, rightPower;

        // restart imu movement tracking.
        resetAngle();

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (degrees < 0)
        {   // turn right.
            leftPower = -power;
            rightPower = 0;
        }
        else if (degrees > 0)
        {   // turn left.
            leftPower = 0;
            rightPower = -power;
        }
        else return;

        // set power to rotate.
        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);

        // rotate until turn is completed.
        if (degrees < 0)
        {
            // On right turn we have to get off zero first.
            while (opModeIsActive() && getHeading() == 0) {}

            while (opModeIsActive() && getHeading() > degrees) {}
        }
        else    // left turn.
            while (opModeIsActive() && getHeading() < degrees) {}

        // turn the motors off.
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        // wait for rotation to stop.
        sleep(1000);

        // reset angle tracking on new heading.
        resetAngle();
    }
    private void returnToZero(double power)
    {

        // getAngle() returns + when rotating counter clockwise (left) and - when rotating
        // clockwise (right).

        if (getHeading() < -0.05)
        {   // turn right.
            frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);

            while (opModeIsActive() && getHeading() < -0.05) {
                // set power to rotate.
                frontLeft.setPower(power);
                backLeft.setPower(power);
                frontRight.setPower(power);
                backRight.setPower(power);
                telemetry.addData("Heading", getHeading());
                telemetry.addData("LeftFront Enc ", frontLeft.getCurrentPosition());
                telemetry.addData("RightFront Enc ", frontRight.getCurrentPosition());
                telemetry.addData("LeftBack Enc ", backLeft.getCurrentPosition());
                telemetry.addData("RightBack Enc ", backRight.getCurrentPosition());
                updateTelemetry(telemetry);
                telemetry.update();
                if (getHeading()>-.05 || !frontLeft.isBusy()){
                    break;
                }

            }
        }
        else if (getHeading() > 0.05)
        {   // turn left.
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
            while (opModeIsActive() && getHeading() > 0.05) {
                // set power to rotate.
                frontLeft.setPower(power);
                backLeft.setPower(power);
                frontRight.setPower(power);
                backRight.setPower(power);
                telemetry.addData("Heading", getHeading());
                telemetry.addData("LeftFront Enc ", frontLeft.getCurrentPosition());
                telemetry.addData("RightFront Enc ", frontRight.getCurrentPosition());
                telemetry.addData("LeftBack Enc ", backLeft.getCurrentPosition());
                telemetry.addData("RightBack Enc ", backRight.getCurrentPosition());
                updateTelemetry(telemetry);
                telemetry.update();
                if (getHeading()<.05 || !frontLeft.isBusy()){
                    break;
                }
            }
        }

        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        sleep(500);
        // turn the motors off.
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        telemetry.addData("WHILE:", "COMLETE");
        // wait for rotation to stop.
        sleep(1000);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // reset angle tracking on new heading.
        resetAngle();

    }
}

