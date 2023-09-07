# URL-Download-Tool
Download videos from URLs

This project is supposed to run on Linux x86 servers and is only for testing purposes.

For local testing purposes there is a Windows binary too.

# To Do List:
* Check if URL Form is empty before submitting on clientSide or Send error Response from Server if URL is invalid
* ~~Find a way to give the downloaded video an unique name for each Request (ServerSide).~~
* ~~Let the App handle concurrent Requests so Requests do not interrupt each other.~~
* ~~Add options to select MP3 or MP4 Download (Or more).~~
* --> There is Audio and Video Download available now (Audio:.m4a and Video:.mp4)
* Build a juicy Front-End for that sweet Eye-Candy.
* ~~Define the location of yt-dlp Binary INSIDE of the compiled .Jar File so only the .Jar File has to be deployed as a single File.~~
* ---> Downloads and Binary will be contained in Path where Jar is executed
~~* Build Scheduled Jobs to delete the videos from the Server after Client download~~
* --> Delete Schedule now compares current Date with videoDate and deletes it if its older than 10 Minutes
* --> The Videos Lastmodified Date had to be set manually on server so the difference between CurrentDate and VideoDate makes sense.