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
POST /palindromes
{
  "input": "racecar",
  "minLength": 3
}
```

A get endpoint was used to support easier testing using curl or even through a web
browser. The response returns a json payload which plays back the input string,
along with an array of all the palindromes found within the string, any that are
duplicates or shorter than 3 characters will not be included by default.

As an example, if `racecar` is input, then technically there are multiple 
palindromes `racecar`, `aceca` and `cec` but only the longest i.e. `racecar`
will be returned as the others are effectively "duplicates" in that they are
just substrings of the longer palindrome.

If the input string does not contain any palindromes then an empty array will be
returned.

If you want to modify the minimum length, or the logic for filtering the palindromes
returned this can be done by specifying the `minLength` or `filter` parameters
on the request. The filter options are either `NONE` which will return all palindromes,
or `REMOVE_DUPLICATES` which will filter duplicate palindromes as described above. The
`minLength` value is just an integer for the shortest length of palindrome you want to
be returned.

Some curl commands with the responses returned from the calls are shown below:

```curl
curl -X POST \
  -d '{"input":"racecar"}' \
  -H "Content-Type:application/json" \
  http://localhost:8010/palindromes
{"input":"racecar","minLength":3,"filter":"REMOVE_DUPLICATES","palindromes":["racecar"]}

curl -X POST \
  -d '{"input":"racecar","minLength":2,"filter":"NONE"}' \
  -H "Content-Type:application/json" \
  http://localhost:8010/palindromes
{"input":"racecar","minLength":2,"filter":"NONE","palindromes":["cec","racecar","aceca"]}%   

curl -X POST \
  -d '{"input":"1234","minLength":3}' \
  -H "Content-Type:application/json" \
  http://localhost:8010/palindromes
{"input":"1234","minLength":3,"palindromes":[]}
```

## Running the app

You can run the app by running the following gradle command:

```gradle
./gradlew palindrome-app:bootRun
```