# CustomerLoader
Application loading customers from file in csv or xml pretty format to mySql database

# Usage
Starting variables which need to be provided

-Dconfig.location= path to jdbc.properties file, avaiable in project.

-Dchunk.size= number of customer read and save into database at once.

-Dfile.path= path to file with customers

# Other databases
In order to use other database driver need to be provided and added to classpath.
