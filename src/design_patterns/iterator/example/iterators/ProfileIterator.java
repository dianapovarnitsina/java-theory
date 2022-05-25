package design_patterns.iterator.example.iterators;

import design_patterns.iterator.example.profile.Profile;

public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}