%≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠
% Joshua Glass
% CSCI 320
% Assignment 5
%≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠≠

mylength([], 0).
mylength([_H|T], Len):- mylength(T, N), Len is N + 1.


mylast([H], H).
mylast([_|T], H):- mylast(T, H).


mymax([H|T], MaxVal):- mymax(T, H, MaxVal).
mymax([], X, MaxVal):- MaxVal = X.
mymax([H|T], X, MaxVal):- H =< X, mymax(T, X, MaxVal).
mymax([H|T], X, MaxVal):- H > X, mymax(T, H, MaxVal).


intersect([], [], []).
intersect([], _, []).
intersect(_, [], []).
intersect([H|T], List2, [H|IntList]):- member(H, List2), 
                                       intersect(T, List2, IntList).
intersect([_|T], List2, IntList):- intersect(T, List2, IntList).


union([H1|T1], [], [H1|T1]).
union([], [H2|T2], [H2|T2]).
union([H1|T1], List2, UnionList):- member(H1, List2), union(T1, List2, UnionList).
union([H1|T1], List2, [H1|UnionList]):- not(member(H1, List2)),
                                        union(T1, List2, UnionList).


mergesort([],[]).
mergesort([X],[X]).
mergesort(List,Sorted):- List=[_,_|_], 
                          divide(List,L1,L2), 
                          mergesort(L1,Sorted1),
                          mergesort(L2,Sorted2),
                          merge(Sorted1,Sorted2,Sorted).
merge([],L,L).
merge(L,[],L):-L\=[].
merge([X|T1],[Y|T2],[X|T]):-X=<Y,merge(T1,[Y|T2],T).
merge([X|T1],[Y|T2],[Y|T]):-X>Y,merge([X|T1],T2,T).

divide(List, L1, L2):- halve(List, L1, L2).
halve(L,A,B):-hv(L,[],A,B).
hv(L,L,[],L).
hv(L,[_|L],[],L).
hv([H|T],Acc,[H|L],B):-hv(T,[_|Acc],L,B).