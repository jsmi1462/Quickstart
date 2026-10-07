# Team starter code

Students work in `team/`. Mentors work in `pedro/Constants.java`.

| File | Who edits it | What it is |
|---|---|---|
| `pedro/Constants.java` | mentor, once per robot | Hardware names and tuned numbers. `TUNED` must be `true` before anything runs. |
| `team/Poses.java` | students | Named field spots. Numbers only. |
| `team/Routes.java` | students | One line per leg of the auto. |
| `team/TemplateAuto.java` | students | State machine. Copy a case, change the route. |
| `team/TeleOpMain.java` | students | Driving, pose readout, mechanism controls. |
| `team/LimelightHelper.java` | nobody | Wraps the Limelight. Call `relocalize(follower)`. |

## Coordinate frame

One frame everywhere: origin at field center, inches, heading in degrees (in `Poses`).
This is the Limelight's frame, so there is no conversion step except meters to inches inside
`LimelightHelper`.

## First-time setup (mentor)

1. Name the devices in the Robot Configuration: `pinpoint`, `limelight`, and the four drive motors.
2. Run the Tuning OpMode. Paste each tuner's output over the matching block in `Constants.java`.
   Set `TUNED = true`.
3. Limelight web UI: load the AprilTag pipeline for the current game and set the camera position.
4. Check the frame once: place the robot at a known pose, hold A in `TeleOpMain`, and confirm the
   telemetry pose matches where it is. If x and y look swapped or mirrored, the heading frame
   needs an offset in `LimelightHelper`.
5. Drive to each spot, read the pose off the telemetry, and type it into `Poses.java`.

## Before each competition

Tag the known-good version: `git tag comp-<event>`. Students experiment on branches.
