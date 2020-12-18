
### Table of Contents

1. [Installation](#installation)
2. [Project Motivation](#motivation)
3. [File Descriptions](#files)
4. [Instructions](#instructions)

## Installation <a name="installation"></a>

To run the project you need to install Kotlin.

## Project Motivation<a name="motivation"></a>

In this project, a file listener is programmed which:
* Loads files from a given directory
* Extracts the metadata in the file name (for the assignment to the patient).
* Saves the files in an archive, according to the current date archive/year/month/day 

## File Descriptions <a name="files"></a>

* FileListener.kt: Kotline file where the code is included
* pd_Tom_Miller_26.05.1990.pdf: sample pdf file from which FileListener will
  extract metadata from its title and then archive it. 

## Instructions<a name="instructions"></a>
* Executing FileListener will extract the First Name, Last Name and Birthday
information from the file pd_Tom_Miller_26.05.1990.pdf which is in directory
Filesystem and then archive it to archive + subdirectory with the given year/month/day.The pattern of file name entry is given as a string named (patternStringInput) and a dictionary (mapOf) that maps the entries to first name, last name, birthday (named dictionaryEntries).



