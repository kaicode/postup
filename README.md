# PostUp

Post text for others to see from the linux command line.
Simply post your text to the address you would like to use for viewing. Useful for sharing logs, lightweight monitoring, general scripting tool. No security yet.

N.B Your text must be url-encoded.

## Build
`mvn package`

## Example
Once built and deployed:
`curl http://localhost:8080/postup/simple-example?data=Hello%20World`
Your text is now available at http://localhost:8080/postup/simple-example

### Other Examples
`curl --data-urlencode "data=Hello brave new world" http://localhost:8080/postup/brave-new-world`
``curl --data-urlencode "data=`date; cat myfile`" http://localhost:8080/postup/myfile``
``curl --data-urlencode "data=`date; mysql -uroot -pyourpassword -e \"show processlist;\"`" http://localhost:8080/postup/localhost-mysql-process-list``
