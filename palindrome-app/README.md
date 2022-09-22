# Palindrome application

This is another interview question I have come across in the past, the problem was
stated as follows:

## Problem Statement

Your challenge is to write a web service application that given a string will return
true if that string is a palindrome, otherwise it will return false.

## Solution

This example solves the problem by building a reactive web service using spring web
flux. The application exposes the following get endpoint:

```http
GET /palindromes/{input}
```

A get endpoint was used to support easier testing using curl or even through a web
browser. The response returns a json payload which plays back the input string,
along with an array of all the palindromes found within the string, any that are
duplicates or shorter than 3 characters will not be included:

As an example, if `racecar` is input, then technically there are multiple 
palindromes `racecar`, `aceca`, `cec` and even `e` but only the longest i.e. `racecar`
will be returned.

If the input string does not contain any palindromes then an empty array will be
returned.

Some curl commands with the responses returned from the calls are shown below:

```curl
curl http://localhost:8010/palindromes/racecar
{"input":"racecar","palindromes":["racecar"]}

curl http://localhost:8010/palindromes/1234
{"input":"1234","palindromes":[]}
```

## Running the app

You can run the app by running the following gradle command:

```gradle
./gradlew palindrome-app:bootRun
```