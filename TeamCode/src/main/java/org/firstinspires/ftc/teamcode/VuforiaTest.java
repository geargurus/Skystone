package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous (name = "Vuforia Test")
@Disabled
public class VuforiaTest extends LinearOpMode {

    VuforiaLocalizer vuforiaLocalizer;
    VuforiaLocalizer.Parameters parameters;
    VuforiaTrackables visionTrackers;
    VuforiaTrackable target;
    VuforiaTrackableDefaultListener listener;

    OpenGLMatrix lastKnownLocation;
    OpenGLMatrix phoneLocation;

    public static final String VUFORIA_KEY = "AYxtCgr/////AAABmVK4HL6L4UdSph/Ev2oWJxgejw2a+mJ5yNoPDvkwRxocRNHr4" +
            "27/+eQFRyYXHCQ2bso+oh9WThcu+2owlgi6XAQ/MQEwFLegINqYIMipY3PG7ezFiGiq7RuCXYPLayQtMuGx3gdHDOncAnGbssT" +
            "jlf/6zV47JQ923BpFGq84mJxgTdMp1bjtcsCGEV8VjLTOFOOE36ZG9WGGoKO6bfuXImeL10Gb9C5M3oVKKIaYjG5cqd+XOjhgK" +
            "PbNAHTBrvOu7rUKLy/L2icGV2mWW59/2yy6Lb4o+vubqIvKddXxrG788hnCs7GNhOiAGjzB6xTQF2Nb+r4dlKLsZfKobF93Kjo" +
            "1EN+y7UHShJXagylRWUBP";

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        while (opModeIsActive()) {
            telemetry.update();
            idle();
        }

    }

    public void setUpVuforia() {
        parameters = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);
    }

}
