# Student guide

You already have the project cloned and Git installed. This guide covers everything after that.

Use the **Terminal** tab at the bottom of Android Studio for the Git commands. It opens in the project folder.

## 1. One-time setup on each computer

Tell Git who you are, so your work has your name on it:

```
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

The first time you push, GitHub will ask you to sign in. Use the account your mentor added to the repo.

Then in Android Studio, wait for the bar at the bottom to say Gradle sync is finished. The first sync takes a few minutes.

## 2. Make your own branch

Never work directly on the shared branch. You will be on `rookie-starter`. Make a branch for your work:

```
git switch -c yourname-auto
```

You can check which branch you are on with `git branch`. The one with the star is yours.

## 3. The daily loop

Start of every session, get everyone else's changes:

```
git pull
```

Work, then save a snapshot of your work. Do this whenever something works, not just at the end:

```
git add -A
git commit -m "Drive to the scoring spot and relocalize"
```

Write the message so a teammate could tell what changed. "stuff" and "fix" are not messages.

End of every session, back up your work to GitHub:

```
git push
```

The first push on a new branch will print a command starting with `git push --set-upstream`. Copy it, run it, and you are done.

## 4. Running on the robot

1. Connect your computer to the Control Hub's Wi-Fi.
2. In Terminal: `adb connect 192.168.43.1:5555`
3. Choose the Control Hub as the device at the top of Android Studio, and press the green Run button.
4. On the Driver Station phone, pick your OpMode from the list and press INIT, then play.

If the Hub is missing from the device list, plug it in with USB-C once, then try again.

## 5. Where your work goes

Everything you write is in `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/team/`.
Do not edit anything in the `pedro` folder. That holds the robot's tuned numbers, and it only works
if a mentor does it.

See `team/README.md` for what each file is.

## 6. When something goes wrong

- **Red error text in the build.** Read the first error, not the last. It names a file and line number.
- **`git pull` says there is a conflict.** Stop. Do not try to fix it. Get a mentor.
- **You changed something and it got worse.** `git status` shows what changed. `git restore <file>`
  throws away your edits to that file and goes back to your last commit. This cannot be undone.
- **The robot does something unexpected.** Hit STOP on the Driver Station first. Then look at the code.

## 7. Rules for the team

- Your branch is yours. Push to it as often as you like.
- Nobody pushes to `rookie-starter` or `master` directly. A mentor merges finished, tested work.
- Before competition, a mentor tags the known-good version, and we do not touch it after that.
