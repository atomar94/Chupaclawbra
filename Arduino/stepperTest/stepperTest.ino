#include <Stepper.h>

Stepper myStepper(64, 10, 11, 12, 13);
void setup() {
  // put your setup code here, to run once:
  myStepper.setSpeed(360);
  
}

void loop() {
  // put your main code here, to run repeatedly:
  myStepper.step(32); //this is blocking so we can just set it low and it will loop
}
