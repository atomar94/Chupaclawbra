#include <Servo.h>

//define pins
int armServoPin = 8;
int clawServoPin = 7;
int clawPhiPin = 6;
Servo armServo;
Servo clawServo;
Servo clawPhiServo;

//experimentally found
const int MAX_ARM_FORWARD_ANGLE = 30;  //how close the claw can get to the ground
const int MAX_ARM_BACKWARD_ANGLE = 90; //how far away the claw can get from the ground
const int MAX_CLAW_FORWARD_ANGLE = 30;
const int MAX_CLAW_BACKWARD_ANGLE = 90;
const int MIN_CLAW_PHI = 30;
const int MAX_CLAW_PHI = 150;
const int STEPPER_SPEED = 360;        //rpm

int armAngle;
int clawAngle;
int clawPhi;
/*
 * specify a position to move the claw.
 * Waits for some time before returning to give servo time to move.
 * updates $clawAngle var.
 */
void moveClawToDegree(int degrees) {
  /*if(degrees < MAX_CLAW_FORWARD_ANGLE)
    degrees = MAX_CLAW_FORWARD_ANGLE;
  else if(degrees > MAX_CLAW_BACKWARD_ANGLE)
    degrees = MAX_CLAW_BACKWARD_ANGLE;
  */

}


/*
 * Move the arm in the phi direction by $degrees
 * Delays until movement is complete.
 */
void moveArmToDegree(int degrees) {
  /*
  //making sure we dont try to overrotate
  if(degrees < MAX_ARM_FORWARD_ANGLE)
    degrees = MAX_ARM_FORWARD_ANGLE;
  else if(degrees > MAX_CLAW_BACKWARD_ANGLE)
    degrees = MAX_CLAW_BACKWARD_ANGLE;
*/
  armServo.write(degrees);
  armAngle = degrees;
}

void moveClawPhi(int degrees) {
  clawPhiServo.write(degrees);
  clawPhi = degrees;
}

void operateClaw(int degrees) {
  clawServo.write(degrees);
  clawAngle = degrees;
}

void setup() {
    
  armServo.attach(armServoPin);
  clawServo.attach(clawServoPin);
  clawPhiServo.attach(clawPhiPin);
  //init arm pos.
  armAngle = 0;
  clawAngle = 0;
  clawAngle = MAX_CLAW_FORWARD_ANGLE;
  moveArmToDegree(40);
  //clawMoveToDegree(60);
  //baseMoveToDegree(60);

}

void loop() {
  delay(800);
  moveClawPhi(MIN_CLAW_PHI);
  delay(800);
  operateClaw(150);
  delay(1000);
  moveClawPhi(100);
  delay(1000);
  operateClaw(0);
}







