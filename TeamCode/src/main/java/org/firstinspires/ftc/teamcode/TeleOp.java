package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ServiceConfigurationError;
@Disabled
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")

public class TeleOp extends OpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor liftLeft;
    public DcMotor liftRight;

    public Servo grabLeft;
    public Servo grabRight;

    public DcMotor intake;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        liftLeft = hardwareMap.dcMotor.get("liftLeft");
        liftRight = hardwareMap.dcMotor.get("liftRight");

        grabLeft = hardwareMap.servo.get("grabLeft");
        grabRight = hardwareMap.servo.get("grabRight");
        intake = hardwareMap.dcMotor.get("intake");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        liftLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_left) {
            frontRight.setPower(-.5);
            backRight.setPower(.5);
            frontLeft.setPower(.5);
            backLeft.setPower(-.5);
        } else if (gamepad1.dpad_right) {
            frontLeft.setPower(-.5);
            backLeft.setPower(.5);
            frontRight.setPower(.5);
            backRight.setPower(-.5);
        } else {
            frontLeft.setPower(gamepad1.left_stick_y*.8);
            frontRight.setPower(gamepad1.right_stick_y*.8);
            backLeft.setPower(gamepad1.left_stick_y*.8);
            backRight.setPower(gamepad1.right_stick_y*.8);
        }

        //servo close
        if(gamepad2.right_bumper){ //push _ you get results

            grabLeft.setPosition(.5);
            grabRight.setPosition(.5);


        }
        else {
            grabLeft.setPosition(0);
            grabRight.setPosition(0);
        }

        if(gamepad2.a) {
            intake.setPower(.7);
        }
        else if (gamepad2.b){
            intake.setPower(-.7);
        }
        else {
            intake.setPower(0);
        }

        //Bring Lift up
        if(Math.abs(gamepad2.right_trigger)>.1){ //push _ you get results
            liftLeft.setPower(gamepad2.right_trigger);
            liftRight.setPower(gamepad2.right_trigger);


        }
        else {
            liftLeft.setPower(0);
            liftRight.setPower(0);

        }


        //Bring lift back down
        if(Math.abs(gamepad2.left_trigger)>.1){ //push _ you get results
            liftLeft.setPower(-gamepad2.left_trigger);
            liftRight.setPower(-gamepad2.left_trigger);

        }
        else {
            liftLeft.setPower(0);
            liftRight.setPower(0);

        }
    }

    /*
    @Override
   public void init() {
       //"nicknames" for
       topLeft = hardwareMap.dcMotor.get("topLeft");
       topRight = hardwareMap.dcMotor.get("topRight");
       bottomLeft = hardwareMap.dcMotor.get("bottomLeft");
       bottomRight = hardwareMap.dcMotor.get("bottomRight");
       liftLeft = hardwareMap.dcMotor.get("liftLeft");
       liftRight = hardwareMap.dcMotor.get("liftRight");
       grabLeft = hardwareMap.servo.get("grabLeft");
       grabRight = hardwareMap.servo.get("grabRight");

       //Reverse wheels
       topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
       bottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);


   }

   @Override
   public void loop() {
       //servo close
       if(gamepad2.right_bumper){ //push _ you get results

           grabLeft.setPosition(1);
           grabRight.setPosition(1);


       }
       else {
           grabLeft.setPosition(0);
           grabLeft.setPosition(0);

       }

       //servo open
       if(gamepad2.left_bumper){ //push _ you get results
           grabLeft.setPosition(-1);
           grabRight.setPosition(-1);


       }
       else {
           grabLeft.setPosition(0);
           grabLeft.setPosition(0);

       }








       //Bring Lift up
       if(Math.abs(gamepad2.right_trigger)>.1){ //push _ you get results
           liftLeft.setPower(-gamepad2.right_trigger);
           liftRight.setPower(gamepad2.right_trigger);


       }
       else {
           liftLeft.setPower(0);
           liftRight.setPower(0);

       }


       //Bring lift back down
       if(Math.abs(gamepad2.right_trigger)>.1){ //push _ you get results
           liftLeft.setPower(gamepad2.right_trigger);
           liftRight.setPower(-gamepad2.right_trigger);

       }
       else {
           liftLeft.setPower(0);
           liftRight.setPower(0);

       }


       //Wheels on gamepad
       topLeft.setPower(gamepad1.left_stick_y);
       topRight.setPower(gamepad1.right_stick_y);
       bottomLeft.setPower(gamepad1.left_stick_y);
       bottomRight.setPower(gamepad1.right_stick_y);

   }
}

     */
}