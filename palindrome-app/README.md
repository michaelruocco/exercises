# Palindrome application

This is another interview question I have come across in the past, the problem was
stated as follows:

## Problem Statement

Your challenge is to write a web service application that given a string will return
true if that string is a palindrome, otherwise it will return false.

## Solution

This example solves the problem by building a reactive web service using spring web
flux. The application exposes the following get endpoint:

```
GET /palindromes/{input}
```

A get endpoint was used to support easier testing using curl or even through a web
browser. The response returns a json payload which plays back the input string,
the reversed input string, and a boolean value indicating if the input is a palindrome
or not.

Some curl commands with the responses returned from the calls are shown below:

```
curl http://localhost:8010/palindromes/racecar
{"input":"racecar","reversed":"racecar","palindrome":true}

curl http://localhost:8010/palindromes/1234
{"input":"1234","reversed":"4321","palindrome":false}
```

## Running the app

You can run the app by running the following gradle command:

```
./gradlew palindrome-app:bootRun
```