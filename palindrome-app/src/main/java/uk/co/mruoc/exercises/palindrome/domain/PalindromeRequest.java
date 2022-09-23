package uk.co.mruoc.exercises.palindrome.domain;

import uk.co.mruoc.exercises.palindrome.domain.filter.Filter;

public interface PalindromeRequest {

    int getMinLength();

    String getInput();

    Filter getFilter();
}
