/* Membership predicate without duplicates when backtracking */

member2(X, L) :- member3(X, L, []).

member3(X, [X|_], R) :- not(member1(X, R)).
member3(X, [H|T], R) :- member3(X, T, [H|R]).

member1(X, [X|_]) :- !.
member1(X, [_|T]) :- member1(X, T).

not(X) :- X, !, fail.
not(_).
