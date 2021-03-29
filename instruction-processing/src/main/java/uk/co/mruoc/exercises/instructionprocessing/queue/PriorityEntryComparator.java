package uk.co.mruoc.exercises.instructionprocessing.queue;

import java.util.Comparator;

public class PriorityEntryComparator implements Comparator<PriorityEntry> {

    @Override
    public int compare(PriorityEntry entry1, PriorityEntry entry2) {
        int result = compareByPriority(entry1, entry2);
        if (result == 0) {
            return compareByAscendingInsertionIndex(entry1, entry2);
        }
        return result;
    }

    private int compareByPriority(PriorityEntry entry1, PriorityEntry entry2) {
        return entry1.getPriority().compareTo(entry2.getPriority());
    }

    private int compareByAscendingInsertionIndex(PriorityEntry entry1, PriorityEntry entry2) {
        return Integer.compare(entry1.getInsertionIndex(), entry2.getInsertionIndex());
    }

}
