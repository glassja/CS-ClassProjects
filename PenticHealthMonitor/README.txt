Set the healthmonitor director to be the source root and the test directory as the test root.

Right click and run tests to run return results of tests.

To run the system with our simulated user inputs and sensor data, run the TestUser and when prompted, type 1 for walking, 2 for jogging, 3 for running and press enter.

Then type 4 and press enter, the GUI should open and begin running with HR and Step data received from timers based on the speed input.

To run the system with no hard coded data, run the mainStage. Beware this may break the calculators as of right now because of missing user values.

Uncomment the block in main of TestUser to generate five days of simulated data to display in the graphs.

TestUser's five days of simulated values can be changed in the random equations.



