# Project #1 – The Library

## Table of Contents
1. [Introduction](#introduction)
2. [Algorithm Description](#algorithm-description)
3. [Data Structure](#data-structure)
4. [Future Improvements](#future-improvements)
5. [Vulnerabilities](#vulnerabilities)
6. [Conclusion](#conclusion)


## Introduction
The book catalog is not up to up date, and people keep taking books and never returning them, we have no idea how much we owed in book late fees.
The objetive is to organize and sort the collections of books.

## Algorithm Description
### Overview

All the search algorithms I used were linear search, which is not the most effective because in the average case you have to traverse the whole array. A future improvement would be to implement a binary search and it would have a better performance(O(log n)) compared to O(n).

## Data Structure
### Overview
The ADT I used was an arrayList since it is simpler to implement the add(), remove(), etc. methods. But after analyzing the project it is advisable to use a Double LinkedList since it has a better performance when adding more elements to the array and it is more convenient to adjust the ids of the books and not having to use the reallocate().



## Future Improvements
Some of the improvements that could be made is about saving the changes that were made in the program permanently in the csv files. Also improve the relationship between the Book csv and the User csv.


## Vulnerabilities
### Identified Vulnerabilities

A possible vulnerability is about how the ids of the books are generated when they are added to the catalog, because it can happen the case that the ids are repeated if a certain amount of books are removed from the catalog, and that would also affect the relationship between the check-outs books that the users have.

