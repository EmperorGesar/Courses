/* Simple differentiation predicate */

diff(X, X, 1) :- !.
diff(_, C, 0) :- atomic(C), !.

diff(X, U+V, DU+DV) :- diff(X, U, DU), diff(X, V, DV), !.
diff(X, U-V, DU-DV) :- diff(X, U, DU), diff(X, V, DV), !.

diff(X, C*U, C*DU+0*U) :- atomic(C), C \== X, diff(X, U, DU), !.

diff(X, U*V, V*DU+U*DV) :- diff(X, U, DU), diff(X, V, DV), !.
diff(X, U/V, (V*DU-U*DV)/(V*V)) :- diff(X, U, DU), diff(X, V, DV), !.
