package org.firstinspires.ftc.teamcode.opmodes.calibration;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwareRobot;
import org.firstinspires.ftc.teamcode.subsystems.CVSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;

@TeleOp(name="AprilTag Logger", group="Calibration")
public class AprilTagLogger extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        this.telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        HardwareRobot robot = new HardwareRobot(hardwareMap);
        DriveSubsystem drive = new DriveSubsystem(
                robot.leftFront,
                robot.rightFront,
                robot.leftBack,
                robot.rightBack
        );
        CVSubsystem cv = new CVSubsystem(robot.cameraName, telemetry);

        waitForStart();

        double speed = 0.3;
        while (opModeIsActive()) {
            /// TELEOP ///
            double forward = -speed * gamepad1.left_stick_y;
            double strafe = speed * gamepad1.left_stick_x;
            double turn = speed * gamepad1.right_stick_x;
            drive.driveRobotCentric(strafe, forward, turn);

            /// CV ///
            cv.telemeterRelativeAprilTags();
            cv.telemeterAbsoluteAprilTags();

        }

    }
}
