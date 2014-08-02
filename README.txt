Google Moog Player version 1.0 by Oliver Ceccopieri

This is just a simple java program that mimics keystrokes to make music on the Google Moog synth:
http://www.google.com/doodles/robert-moogs-78th-birthday


-- How to run the program:

In your favorite internet browser, navigate to the Google Moog page:
http://www.google.com/doodles/robert-moogs-78th-birthday

While the page is up, run "run.bat", which will start the player as a Java class

When prompted, type in the file name of a song (include the extension)
For example, type "kirby.txt" to play the music stored in the kirby file

The program will load and display all the notes and measures
Then it will give you 5 seconds to switch to the window where you have Google Moog open
Don't close the Command Prompt window running the Moog Player, but simply switch to the Google Moog tab in your browser within 5 seconds
If nothing plays, try clicking on one of the keys on the Moog, as this will activate the object in your browser

The song will play, and when completed, the program will close


-- How to make music:

Create a new text file and name it anything, or modify an existing song text file

Each line in your file is one measure
Simply fill a line with key presses just like you would normally play on the Moog
For example, a C-major scale would read "rtyuiop["

To put a rest in your measure, type "/"
For example, a C-major scale broken into groups of 2 notes would read "rt/yu/io/p[/"

To map the Tab key (the lowest note on the Moog) type "~"
For example, an F-major scale would read "~qw3rtyu"

Feel free to use extra lines and spaces to organize your song
All empty lines and extra spaces are ignored by the parser

All lines leading with a "#" symbol are treated as comments and ignored by the parser
For example, the line "#This is my song" will not affect how the program runs

All lines leading with a "$" symbol are variable definitions, and not played as notes
A variable can be defined at any point in your song, as long as you follow the proper format, which is:
	$variable = value
For example, to set the speed of a song to 100, somewhere in the file should be the line "$speed = 100"

Currently, the only variable that has any effect on your song is the $speed variable, which defaults to a value of 100 if not defined in the song file
The $speed variable determines how many milliseconds a note is held, and how many milliseconds come between each note, so the higher the number, the slower the song is played

More useful variables and features will be added in later versions