# DD2480_lab1_group18
Lab 1 of DD2480 (group 18): This project implement of the decide() function which is part of a hypothetical anti-ballistic missile system

Travis status:
[![Build Status](https://travis-ci.org/apeinot/DD2480_lab1_group18.svg?branch=master)](https://travis-ci.org/apeinot/DD2480_lab1_group18)

## Description of the project
The decide function will signal for an interceptor to be launched depending on radar data.

The data is passed through 15 conditions which, together, in a specified configuration will generate the signal.
The 15 conditions are called _launch interceptor conditions_ or *LICs* and vary greatly in complexity.
The *LICs* apply a range of conditions on the radar including but are not limited to: position, grouping and distance of radar blips.

The 15 conditions are individually evaluated, and form the fifteen elements of the _conditions met vector_ (*CMV*).
These conditions can then be evaluated in conjunction with each other. On two conditions the operator 'AND'
or 'OR' can be used. These values are stored in a 15x15 matrix called the _preliminary unlocking matrix_ (*PUM*).
Two conditions can also have neither of the operators. Then their spot in the matrix will be true regardless.
What operator to use on which two *LICs* is stored in a 15x15 matrix called the logical connector matrix.
For final launch signal, a _final unlocking vector_ (*FUV*) is calculated.
Position i in the vector is true if either the corresponding element in a _preliminary unlocking vector_ (*PUV*)
is false or if all elements the i:th row in the *PUM* is true.
If all elements of the *FUV* are true LAUNCH is set to true.



## Running the project

For running and testing see *Compilation, running and testing*.
There is currently no other way of running the project, apart from manually executing commands in build file.

### Platform and dependencies

*Platform*  
The project as been tested under Java 8 (other versions may also work).

*Dependencies*  
* ANT is needed to build the project ([APACHE ANT documentation](https://ant.apache.org/manual/))
* JUnit 4.12 and harmcrest_core_1.3 are already given in the lib directory

### Compilation, running and testing

In a terminal (in the root folder of the project), run `ant test` to compile and run the test of the project as described in the [build.xml](build.xml) file.

## Statement of contributions

(Each function addition is coupled with the corresponding testing)

* **Alexandre**
  * Creation of the repository
  * Set up of the continuous integration (Travis CI)
  * Set up of the basic project files (structure and build file) and libraries (JUnit 4.12)
  * Add LIC 0
  * Add LIC 5
  * Add LIC 7
  * Add LIC 11
  * Add LIC 12
  * Merge some pull requests into master
* **Emil**
  * Add LIC 3
  * Add LIC 8
  * Add LIC 10
* **Franz**
  * Add LIC 2
  * Add computation of PUM
  * Add computation of FUV
  * Add computation of LAUNCH
  * Add computation of CMV
  * Complete the body of the decide() function
  * Merge some pull requests into master
* **Jonathan**
  * Add LIC 1
  * Add LIC 6
  * Add LIC 13
* **Samuel**
  * Add LIC 4
  * Add LIC 9
  * Add LIC 14
