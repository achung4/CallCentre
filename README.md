# CallCentre
READ ME
--How to run:--
Open CallCentreThreadRunner.java and run it; the main method is in there.

--How to setup:--
Note: It is currently setup for testing purposes i.e. it's run ready
Open CallCentreThreadRunner.java
Step 1: line 20 - put number of freshers
	line 21 - put number of techinical lead(s)
	line 22 - put number of product manager(s)

	line 24 - put number of calls. if you change this, change the other test methods (see below)
	
	line 27 - put minimum delay for incoming calls
	line 28 - put maximum delay for incoming calls

	line 35 - select test cases and assign it to testQueue
	e.g. Call[] testQueue = test1(NUM_OF_CALLS);
	  or Call[] testQueue = test2(NUM_OF_CALLS);
	
Step 2: run it

Setup example:
number of freshers = 3
number of technical lead = 1
number of pproduct manager = 1

number of calls = 12

mininum delay = 0
maximum delay = 3

testQueue = test5(NUM_OF_CALLS);

Note:test5 creates random duration for calls so go to test5 method and you can change the min/max duration
minimum duration = 5
maximum duration = 10



